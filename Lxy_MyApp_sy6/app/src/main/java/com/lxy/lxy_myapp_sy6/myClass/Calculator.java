package com.lxy.lxy_myapp_sy6.myClass;

public class Calculator {

    public float calcu(float n, char op) throws Exception {
        return fact(n);// 这里只有阶乘一个一元运算符
    }

    /**
     * 二元运算符
     *
     * @param a
     *            操作数1
     * @param op
     *            操作符
     * @param b
     *            操作数2
     * @return 结果浮点型
     * @throws Exception
     */
    public float calcu(float a, char op, float b) throws Exception {
        switch (op) {
            case '+':
                return a + b;
            case '-':
                return a - b;
            case '*':
                return a * b;
            case '/':
                return div(a, b);
            case '%':
                return mod(a, b);
            case '^':
                return (float) Math.pow(a, b);
            default:
                throw new Exception("ERROR");
        }
    }

    private float div(float a, float b) throws Exception {
        if (b == 0)
            throw new Exception("除数不能为0!");
        return a / b;
    }

    // 取余
    private float mod(float a, float b) throws Exception {
        if (b == 0)
            throw new Exception("除数不能为0!");
        return a % b;
    }

    // 阶乘 n！(n<=20)
    private float fact(float n) throws Exception {
        if (n < 0)
            throw new Exception("阶乘运算数不能为负数！");
        if (n > 34)
            throw new Exception("阶乘数不能超过34，否则越界！");// 可以考虑改进以使计算更大数值的阶乘

        if (n == 0)
            return 1;// 0!=1
        int num = (int) n;
        if (num == n) {// n为整数
            float result = 1;
            while (num > 0) {
                result *= num--;
            }
            return result;
        } else
            throw new Exception("阶乘运算数必须为整数！");
    }
}
