package com.lxy.lxy_myapp_sy6.myClass;

import java.util.Stack;

public class MyKuoHao {


    public MyKuoHao() {

    }


    public String getAnsKuoHao(String str) throws Exception {
        //String s = "(1 + 2^3!-4)*(5!-(6-( 7-(89-0!))))#";// 2013
        //strBufAll.append("#");
        String ans;

        String s = str + "#";
        StringBuffer rpn = new StringBuffer();
        //try {
            float ansNum = calcExpression(s, rpn);
            System.out.println("结果：" + calcExpression(s, rpn));
            System.out.println("逆波兰表达式：" + rpn);

            System.out.println("\n计算逆波兰表达式：");
            calcExpressionRpn(rpn.toString());
            ans = String.valueOf(ansNum);
            return ans;
            //textResult.setText(String.valueOf(ans));
        //} catch (Exception e) {
        //    e.printStackTrace();

        //}
        //return "";
    }

    private Stack<Character> optr = new Stack<Character>();// 操作符栈
    private Stack<Float> opnd = new Stack<Float>();// 操作数栈
    private final Character END_Character = '#';// 输入表达式的结束字符

    public final int ADD = 0, SUB = 1, MUL = 2, DIV = 3, MOD = 4, POW = 5, FAC = 6, L_P = 7, R_P = 8, EOF = 9;
    public final char[][] PRI = {//运算符优先等级  [栈顶]'levle'[当前]
            //         |----------------当前运算符----------------|
            //           +   -   *   /   %   ^   !   (   )   #(结束字符)
            /*    + */ {'>','>','<','<','<','<','<','<','>','>'},
            /*    - */ {'>','>','<','<','<','<','<','<','>','>'},
            /* 栈     * */ {'>','>','>','>','>','<','<','<','>','>'},
            /* 顶     / */ {'>','>','>','>','>','<','<','<','>','>'},
            /* 运     % */ {'>','>','>','>','>','<','<','<','>','>'},
            /* 算     ^ */ {'>','>','>','>','>','>','<','<','>','>'},
            /* 符     ! */ {'>','>','>','>','>','>','>',' ','>','>'},
            /*    ( */ {'<','<','<','<','<','<','<','<','=',' '},
            /*    ) */ {' ',' ',' ',' ',' ',' ',' ',' ',' ',' '},
            /*    # */ {'<','<','<','<','<','<','<','<',' ','='}
    };

    /**
     * 中缀表达式计算,程序核心部分
     * @param expression
     *            输入的有效中缀表达式
     * @param RPN
     *            输出的逆波兰表达式（Reverse Polish Notation)
     * @return
     */
    public float calcExpression(String expression, StringBuffer RPN) throws Exception {
        // 初始时操作符栈压入开始标识符，与结束字符对应
        optr.push(END_Character);

        // 剔除表达式中的空格
        expression = removeSpace(expression);

        int i = 0;// 表示读取表示式的位置
        while (!optr.empty()) {
            char currenSymbol = expression.charAt(i);// 当前扫描到的表达式符号
            if (Character.isDigit(currenSymbol)) {
                i = readNumber(expression, i, opnd);// 读入（可能多位的）操作数
                RPN.append(opnd.peek() + " ");// 计入逆波兰表达式
            } else {
                switch (orderBetween(optr.peek(), currenSymbol)) {
                    case '>':// 栈顶运算符的优先级大于当前运算符，则1.取出操作符栈顶运算符，2.取出数据栈中的一个或多个数据（取决于运算符的类型），3.运算并把结果压入数据栈中
                        char op = optr.pop();// 取出栈顶操作符
                        RPN.append(op + " ");// 当操作符可以计算时计入逆波兰表达式，与逆波兰表达式的计算过程恰好吻合

                        Calculator ca = new Calculator();// 基本计算操作对象

                        if ('!' == op) {// 一元运算符的计算
                            float number = opnd.pop();// 取出操作数栈顶数值
                            System.out.println("计算过程：" + "[" + number + "]" + "" + op + "=" + "[" +ca.calcu(number, op) +"]" );
                            opnd.push(ca.calcu(number, op));// 计算并将结果入栈
                        } else {// 二元运算符的计算
                            float number2 = opnd.pop();// 取出操作数栈顶数值
                            float number1 = opnd.pop();// 取出操作数栈顶数值
                            System.out.println("计算过程：" + "[" + number1 + "]" + "" + op + "" + "[" + number2 + "]" + "=" + "[" +ca.calcu(number1, op, number2) + "]");
                            opnd.push(ca.calcu(number1, op, number2));// 计算并将结果入栈
                        }
                        break;
                    case '<':// 栈顶运算符的优先级小于当前运算符，计算推迟，当前运算符入栈
                        optr.push(currenSymbol);
                        i++;
                        break;
                    case '=':// 栈顶运算符的优先级等于当前运算符，脱括号并接收下一个字符
                        optr.pop();
                        i++;
                        break;
                    case ' ':
                        throw new Exception("ERROR");
                }
            }
        }

        return opnd.pop();// 操作数栈的最后一个元素即为需要的结果
    }

    /**
     * 只关注于求中缀表达式的接口
     * @param expression
     * @return
     * @throws Exception
     */
    public float calcExpression(String expression) throws Exception {
        return calcExpression(expression, new StringBuffer());
    }

    /**
     * 对逆波兰表达式进行计算
     * @param rpn
     * @return
     * @throws Exception
     */
    public float calcExpressionRpn(String rpn) throws Exception{
        String[] chs = rpn.split(" ");
        int i = 0;
        int chLength = chs.length;
        while(i < chLength) {
            Calculator ca = new Calculator();// 基本计算操作对象
            if( convertStrToDigit(chs[i]) != null ) {// 操作数直接入栈
                opnd.push(convertStrToDigit(chs[i]));
            } else {
                char op = chs[i].charAt(0);
                if("!".equals( chs[i] )) {
                    float number = opnd.pop();
                    opnd.push(ca.calcu(number, op));
                    System.out.println("计算过程：" + "[" + number + "]" + "" + op + "=" + "[" +ca.calcu(number, op) +"]" );

                } else {
                    float number2 = opnd.pop();// 取出操作数栈顶数值
                    float number1 = opnd.pop();// 取出操作数栈顶数值
                    System.out.println("计算过程：" + "[" + number1 + "]" + "" + op + "" + "[" + number2 + "]" + "=" + "[" +ca.calcu(number1, op, number2) + "]");
                    opnd.push(ca.calcu(number1, op, number2));// 计算并将结果入栈
                }
            }
            i++;
        }
        return opnd.pop();
    }

    /**
     * 将字符串转化为浮点数
     * @param str
     * @return
     */
    private Float convertStrToDigit(String str) {
        try{
            float num = Float.valueOf(str);
            return num;
        } catch(Exception e){
            return null;
        }
    }

    /**
     *  将char型的数字字符转为浮点型数据
     * @param ch
     * @return
     */
    private float CharToFloat(char ch) {
        return Float.valueOf("" + ch);
    }

    /**
     * 将起始为i的子串解析为数值，并存入操作数栈中
     * @param expression 表达式
     * @param i 开始解析的位置
     * @param stk 将解析完毕的数值存入此栈中
     * @return 该数值解析完毕后，返回表达式需要解析的下一个位置
     * @throws Exception 解析错误
     */
    private int readNumber(String expression, int i, Stack<Float> stk) throws Exception {
        stk.push(CharToFloat(expression.charAt(i++)));// 当前数位对应的数值进栈
        char op = expression.charAt(i); // 读取下一个字符
        while (Character.isDigit(op)) {// 只要后续还有紧邻的数字
            stk.push(stk.pop() * 10 + CharToFloat(op));// 弹出原操作数并追加新数位后，新数值重新入栈
            op = expression.charAt(++i);// 下一个字符
        }
        if ('.' != op)
            return i;// 如果最后一个数字后面不是小数点，说明解析完成，则返回当前位置
        op = expression.charAt(++i);
        float fraction = 1;
        while (Character.isDigit(op)) {
            stk.push(stk.pop() + CharToFloat(op) * (fraction /= 10));
            op = expression.charAt(++i);
        }
        if ('.' == op)
            throw new Exception("ERROR");// 如果还有小数点则错误
        return i;// 返回当前解析字符的位置
    }

    /**
     * 根据运算符获取在优先级表中的秩（RANK）
     * @param op
     * @return
     * @throws Exception
     */
    private int getOperRank(char op) throws Exception {
        switch (op) {
            case '+':
                return ADD;
            case '-':
                return SUB;
            case '*':
                return MUL;
            case '/':
                return DIV;
            case '%':
                return MOD;
            case '^':
                return POW;
            case '!':
                return FAC;
            case '(':
                return L_P;
            case ')':
                return R_P;
            case '#':
                return EOF;
            default:
                throw new Exception("ERROR");
        }
    }

    /**
     * 比较栈顶和当前字符的优先级
     * @param peekOptr 栈顶运算符
     * @param currenOptr 当前扫描到的运算符
     * @return 优先级表中的数据项
     * @throws Exception
     */
    private Character orderBetween(Character peekOptr, char currenOptr) throws Exception {
        return PRI[getOperRank(peekOptr)][getOperRank(currenOptr)];
    }

    /**
     * 剔除字符串之间的空格
     * @param str
     * @return
     */
    public String removeSpace(String str) {
        char[] chs = str.toCharArray();
        StringBuffer newStr = new StringBuffer();
        int i = 0;
        while (i < str.length()) {
            if (' ' != chs[i]) {
                newStr.append(chs[i]);
            }
            i++;
        }
        return newStr.toString();
    }

}
