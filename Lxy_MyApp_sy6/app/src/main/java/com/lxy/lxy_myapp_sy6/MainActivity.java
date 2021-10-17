package com.lxy.lxy_myapp_sy6;

import androidx.appcompat.app.AppCompatActivity;

import android.view.View.OnClickListener;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.lxy.lxy_myapp_sy6.myClass.Calculator;
import com.lxy.lxy_myapp_sy6.myClass.MyKuoHao;

import java.net.IDN;
import java.util.ArrayList;
import java.util.List;
import java.util.Stack;


/**
 *  推荐测试数据
 */

public class MainActivity extends AppCompatActivity {

    // 文本框
    private EditText textQuestion;  // 显示题目
    private EditText textResult;    //显示答案

    //数字1-9
    private Button num1;
    private Button num2;
    private Button num3;
    private Button num4;
    private Button num5;
    private Button num6;
    private Button num7;
    private Button num8;
    private Button num9;
    private Button num0;

    //运算符
    private Button optPlus;         // +
    private Button optSubtract;     // -
    private Button optMultiply;     // *
    private Button optDivide;       // /
    private Button optRemainder;    // %
    private Button optFraction;     // 1/x
    private Button optEqual;        // =

    //括号
    private Button bntA;    // (
    private Button bntB;    // )

    //小数点
    private Button symbolPoint;  // .

    //大操作
    private Button mCE;         // 清除    清除本次操作 不包括历史记录
    private Button mDE;         // 删除    删除一个符号 【数字+符号】
    private Button mC;          // 清除    全部

    //小操作存储符号
    private Button mClear;      // 清除  存到strList中mIndex数字
    private Button mRead;       // 读取  存到strList中mIndex数字
    private Button mSave;       // 存储  存到strList中
    private Button mPlus;       // m+   strList中下一个数
    private Button mSubtract;   // m-   strList中上一个数


    // 当前存储、操作、定义数据
    private StringBuffer strBufAll = new StringBuffer();    // 记录这一次的全部操作
    private Stack<Integer> stSta = new Stack<>();
    private String nowAns = "0.0";
    private String nowDo = "";
    private int leftKuoNum = 0;  // 记录左括号
    private int rightKuoNum = 0; // 记录右括号

    // 小操作
    private List<String> strListRes = new ArrayList<>();        // 存储全部结果值
    private List<String> strListAll = new ArrayList<>();       // 存储全部操作
    private List<Stack<Integer>> stStaAll = new ArrayList<>(); // 记录每一次的状态
    private int mIndex = 0;        // 记录strList中读取、存储、删除的下标


    //关于栈记录的状态说明
    // 初始 : 0
    // 数字 : 1
    // +—  : 2
    // ( : 3
    // ) :4
    // . : 5

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //绑定文本
        textQuestion = findViewById(R.id.text_question);
        textResult = findViewById(R.id.text_result);

        //设定textQuestion和textResult框不可编辑
        textQuestion.setFocusable(false);
        textQuestion.setFocusableInTouchMode(false);
        textResult.setFocusable(false);
        textResult.setFocusableInTouchMode(false);


        //绑定数字按钮
        num0 = (Button) findViewById(R.id.num_0);
        num1 = (Button) findViewById(R.id.num_1);
        num2 = (Button) findViewById(R.id.num_2);
        num3 = (Button) findViewById(R.id.num_3);
        num4 = (Button) findViewById(R.id.num_4);
        num5 = (Button) findViewById(R.id.num_5);
        num6 = (Button) findViewById(R.id.num_6);
        num7 = (Button) findViewById(R.id.num_7);
        num8 = (Button) findViewById(R.id.num_8);
        num9 = (Button) findViewById(R.id.num_9);

        //绑定小数点
        symbolPoint = (Button) findViewById(R.id.symbol_point);

        //绑定运算符
        optPlus = findViewById(R.id.opr_plus);            // +
        optSubtract = findViewById(R.id.opt_subtract);    // -
        optMultiply = findViewById(R.id.opt_multiply);    // *
        optDivide = findViewById(R.id.opt_divide);        // /
        optRemainder = findViewById(R.id.opt_remainder);  // %
        optFraction = findViewById(R.id.opt_fraction);    // 1/x
        optEqual = findViewById(R.id.opt_equal);          // =

        // 绑定 左右括号
        bntA = findViewById(R.id.bnt_a);
        bntB = findViewById(R.id.bnt_b);

        // 绑定大操作
        mDE = findViewById(R.id.m_de);        // de 删除一个符号【数字+符号】
        mCE = findViewById(R.id.m_ce);        // ce 清除本次操作 不包括历史记录
        mC = findViewById(R.id.m_c);          // c  清除全部

        //绑定小操作存储
        mClear = findViewById(R.id.m_clear);        // mc
        mRead = findViewById(R.id.m_read);          // mr
        mSave = findViewById(R.id.m_save);          // ms
        mPlus = findViewById(R.id.m_plus);          // m+
        mSubtract = findViewById(R.id.m_subtract);  // m-

        //初始化 显示栏
        textQuestion.setText("");
        textResult.setText("0.0");

        stSta.push(0);


        //数字按钮按下后，strBuf写入数字
        num0.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                if (stSta.peek() == 0) {
                    textResult.setText("0.0");
                    textQuestion.setText("");
                    return;
                }
                if (stSta.peek() == 4) {
                    myShowToast(stSta.peek());
                    return;
                }
                if (strBufAll.charAt(strBufAll.length()-1) == '/' || strBufAll.charAt(strBufAll.length()-1) == '%' ){
                    Toast.makeText(MainActivity.this, "除数不能为0", Toast.LENGTH_LONG).show();
                }
                stSta.push(1);
                strBufAll.append("0");
                textQuestion.setText(strBufAll.toString());
            }
        });
        num1.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                if (stSta.peek() == 0) {
                    textResult.setText("0.0");
                }
                if (stSta.peek() == 4) {
                    myShowToast(stSta.peek());
                    return;
                }
                stSta.push(1);
                strBufAll.append("1");
                textQuestion.setText(strBufAll.toString());

            }
        });
        num2.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                if (stSta.peek() == 0) {
                    textResult.setText("0.0");
                }
                if (stSta.peek() == 4) {
                    myShowToast(stSta.peek());
                    return;
                }
                stSta.push(1);
                strBufAll.append("2");
                textQuestion.setText(strBufAll.toString());
            }
        });
        num3.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                if (stSta.peek() == 0) {
                    textResult.setText("0.0");
                }
                if (stSta.peek() == 4) {
                    myShowToast(stSta.peek());
                    return;
                }
                stSta.push(1);
                strBufAll.append("3");
                textQuestion.setText(strBufAll.toString());
            }
        });
        num4.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                if (stSta.peek() == 0) {
                    textResult.setText("0.0");
                }
                if (stSta.peek() == 4) {
                    myShowToast(stSta.peek());
                    return;
                }
                stSta.push(1);
                strBufAll.append("4");
                textQuestion.setText(strBufAll.toString());
            }
        });
        num5.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                if (stSta.peek() == 0) {
                    textResult.setText("0.0");
                }
                if (stSta.peek() == 4) {
                    myShowToast(stSta.peek());
                    return;
                }
                stSta.push(1);
                strBufAll.append("5");
                textQuestion.setText(strBufAll.toString());
            }
        });
        num6.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                if (stSta.peek() == 0) {
                    textResult.setText("0.0");
                }
                if (stSta.peek() == 4) {
                    myShowToast(stSta.peek());
                    return;
                }
                stSta.push(1);
                strBufAll.append("6");
                textQuestion.setText(strBufAll.toString());
            }
        });
        num7.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                if (stSta.peek() == 0) {
                    textResult.setText("0.0");
                }
                if (stSta.peek() == 4) {
                    Toast.makeText(new MainActivity(), "请输入符号", Toast.LENGTH_LONG).show();
                    return;
                }
                stSta.push(1);
                strBufAll.append("7");
                textQuestion.setText(strBufAll.toString());
            }
        });
        num8.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                if (stSta.peek() == 0) {
                    textResult.setText("0.0");
                }
                if (stSta.peek() == 4) {
                    myShowToast(stSta.peek());
                    return;
                }
                stSta.push(1);
                strBufAll.append("8");
                textQuestion.setText(strBufAll.toString());
            }
        });
        num9.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                if (stSta.peek() == 0) {
                    textResult.setText("0.0");
                }
                if (stSta.peek() == 4) {
                    myShowToast(stSta.peek());
                    return;
                }
                stSta.push(1);
                strBufAll.append("9");
                textQuestion.setText(strBufAll.toString());
            }
        });


        // 小数点
        symbolPoint.setOnClickListener(new OnClickListener() {
            public void onClick(View v) {
                if (stSta.peek() != 1) {
                    myShowToast(stSta.peek());
                    // . 前面不能不是数字
                    return;
                }
                boolean isPoint = false;
                for (int i=strBufAll.length()-1; i>0; i--){
                    if (strBufAll.charAt(i) == '.'){
                        isPoint = true;
                        break;
                    }
                    if (strBufAll.charAt(i)>'9' || strBufAll.charAt(i) < '0'){
                        break;
                    }
                }
                if (!isPoint){
                    strBufAll.append(".");
                    textQuestion.setText(strBufAll.toString());
                    stSta.push(5);
                }
                else{
                    Toast.makeText(MainActivity.this, "数字不能有两个小数点",Toast.LENGTH_LONG).show();
                }
            }
        });


        // 大操作
        // 删除一个字符
        mDE.setOnClickListener(new OnClickListener() {
            public void onClick(View v) {
                if (stSta.peek() == 0) {
                    myShowToast(stSta.peek());
                    return;
                }
                // 不为空
                strBufAll.deleteCharAt(strBufAll.length() - 1);
                textQuestion.setText(strBufAll.toString());
                stSta.pop();
            }
        });
        // 删除一组字符 123、32、2、+、%
        mCE.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                // 删除当前的这个 不清空存储的
                textResult.setText("0.0");
                textQuestion.setText("");
                strBufAll = new StringBuffer("");
                stSta = new Stack<>();
                stSta.push(0);
                nowAns = "0.0";
                nowDo = "";
                leftKuoNum = 0;
                rightKuoNum = 0;


                //if (stSta.peek() == 0 ){
                //    // 空 不能进行删除操作
                //    return;
                //}
                //if (stSta.peek() != 1) {
                //    //不是数字 不是空
                //    strBufAll.delete(strBufAll.length() - 1, strBufAll.length());
                //    textQuestion.setText(strBufAll.toString());
                //    stSta.pop();
                //}
                //else {
                //    while(stSta.peek() !=0 &&  stSta.peek() == 1) {
                //        // 只要前一个不为空 以及 前一个是数字
                //        strBufAll.deleteCharAt(strBufAll.length() - 1);
                //        textQuestion.setText(strBufAll.toString());
                //        stSta.pop();
                //    }
                //}
            }
        });

        // 全部清空  包括记录 相当于初始化
        mC.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                strBufAll = new StringBuffer("");
                stSta = new Stack<>();
                textQuestion.setText("");
                textResult.setText("0.0");
                strListRes = new ArrayList<>();
                strListAll = new ArrayList<>();
                stStaAll = new ArrayList<>();
                mIndex = 0;
                leftKuoNum = 0;
                rightKuoNum = 0;
                nowAns = "0.0";
                nowDo = "";
                stSta.push(0);
            }
        });


        //小操作
        // 删除存储中一次操作全过程 以及 结果 以及 栈
        mClear.setOnClickListener(new OnClickListener() {
            public void onClick(View v) {
                if (strListAll.size() == 0) {
                    return;
                }
                strBufAll = new StringBuffer("");
                stSta = new Stack<>();
                textResult.setText("0.0");
                textQuestion.setText("");
                strListRes.remove(mIndex);
                strListAll.remove(mIndex);
                stStaAll.remove(mIndex);
                mIndex = 0;
                stSta.push(0);
            }
        });

        //equation
        // 效果和m+相同 但是m+会改变mIndex
        mRead.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                if (strListAll.size() == 0) {
                    return;
                }
                String qu = strListAll.get(mIndex);
                String res = strListRes.get(mIndex);
                stSta = stStaAll.get(mIndex);
                strBufAll = new StringBuffer(qu);
                textQuestion.setText(qu);
                textResult.setText(res);
            }
        });

        // 将当前算式+结果存入 无结果的 存""
        mSave.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                if (strBufAll.toString().equals("")) {
                    strListAll.add(nowDo);
                } else {
                    strListAll.add(strBufAll.toString());
                }
                mIndex = 0;
                strListRes.add(textResult.getText().toString());
                stStaAll.add(stSta);
            }
        });

        mPlus.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                if (strListAll.size() == 0) {
                    return;
                }
                mIndex++;
                if (mIndex >= strListAll.size()) {
                    mIndex = 0;
                }
                String qu = strListAll.get(mIndex);
                String res = strListRes.get(mIndex);
                stSta = stStaAll.get(mIndex);
                strBufAll = new StringBuffer(qu);
                textQuestion.setText(qu);
                textResult.setText(res);
            }
        });
        mSubtract.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                if (strListAll.size() == 0) {
                    return;
                }
                mIndex--;
                if (mIndex < 0) {
                    mIndex = strListAll.size() - 1;
                }
                String qu = strListAll.get(mIndex);
                String res = strListRes.get(mIndex);
                stSta = new Stack<>();
                stSta = stStaAll.get(mIndex);
                strBufAll = new StringBuffer(qu);
                textQuestion.setText(qu);
                textResult.setText(res);
            }
        });


// 不连续操作
/* 不连续操作 start -------------
        // 加
        optPlus.setOnClickListener(new OnClickListener() {
            public void onClick(View v) {
                if(strBuf.toString().equals("")){
                    return;
                }
                strOpt = "+";
                strBufAll.append(strOpt);
                textQuestion.setText(strBufAll.toString());
                if (!strBuf.toString().equals("")) {
                    numAa += Double.parseDouble(strBuf.toString());
                    strBuf = new StringBuffer("");
                }
                // 在按了“=”而没有输入数字时，strResult不为空；在按了“=”并且输入数字后，strResult结果为空。（后面同理）
                if (strResult != null) {
                    numAa = Double.parseDouble(strResult);
                    strResult = null;
                }
                textResult.setText(String.valueOf(numAa));
                flag = true;
            }
        });

        //减
        optSubtract.setOnClickListener(new OnClickListener() {
            public void onClick(View v) {
                if(strBuf.toString().equals("")){
                    return;
                }
                strOpt = "-";
                strBufAll.append(strOpt);
                textQuestion.setText(strBufAll.toString());
                System.out.println(strBuf.toString());
                if(strBuf.toString().equals("")){
                    return;
                }
                if (b_sub == false && strBuf.toString() != "") {
                    numAa = Double.parseDouble(strBuf.toString());
                    textResult.setText(String.valueOf(numAa));
                    strBuf = new StringBuffer("");
                    b_sub = true;
                } else {
                    if (strBuf.toString() != "") {
                        numAa -= Double.parseDouble(strBuf.toString());
                        strBuf = new StringBuffer("");
                    }
                    if (strResult != null) {
                        numAa = Double.parseDouble(strResult);
                        strResult = null;
                        strBuf = new StringBuffer("");

                    }
                    textResult.setText(String.valueOf(numAa));
                    strBuf = new StringBuffer("");

                }
                strBuf = new StringBuffer("");

                flag = true;
            }
        });

        //乘
        optMultiply.setOnClickListener(new OnClickListener() {
            public void onClick(View v) {
                if(strBuf.toString().equals("")){
                    return;
                }
                strOpt = "*";
                strBufAll.append(strOpt);
                textQuestion.setText(strBufAll.toString());

                if (b_mul == false && strBuf.toString() != "") {
                    numAa = Double.parseDouble(strBuf.toString());
                    textResult.setText(String.valueOf(numAa));
                    strBuf = new StringBuffer("");
                    b_mul = true;
                } else {
                    if (strBuf.toString() != "") {
                        numAa *= Double.parseDouble(strBuf.toString());
                        strBuf = new StringBuffer("");
                    }
                    if (strResult != null) {
                        numAa = Double.parseDouble(strResult);
                        strResult = null;
                    }
                    textResult.setText(String.valueOf(numAa));
                }
                flag = true;
            }
        });

        //除
        optDivide.setOnClickListener(new OnClickListener() {
            public void onClick(View v) {
                if(strBuf.toString().equals("")){
                    return;
                }
                strOpt = "/";
                strBufAll.append(strOpt);
                textQuestion.setText(strBufAll.toString());
                if (b_div == false && strBuf.toString() != null) {
                    numAa = Double.parseDouble(strBuf.toString());
                    textResult.setText(String.valueOf(numAa));
                    strBuf = new StringBuffer("");
                    b_div = true;
                } else {
                    if (strBuf.toString() != "") {
                        if (Double.parseDouble(strBuf.toString()) == 0) {
                            Toast.makeText(MainActivity.this, "除数不能为零！", Toast.LENGTH_LONG)
                                    .show();
                        } else {
                            numAa /= Double.parseDouble(strBuf.toString());
                            strBuf = new StringBuffer("");
                        }
                    }
                    if (strResult != null) {
                        numAa = Double.parseDouble(strResult);
                        strResult = null;
                    }
                    textResult.setText(String.valueOf(numAa));
                }
                flag = true;
            }
        });

        //等于
        optEqual.setOnClickListener(new OnClickListener() {

            public void onClick(View v) {
                if(strBuf.toString().equals("")){
                    return;
                }
                strBufAll.append("=");
                textQuestion.setText(strBufAll.toString());
                if (strOpt.equals("+")) {
                    numBb = Double.parseDouble(strBuf.toString());
                    strResult = String.valueOf(numAa + numBb);
                    textResult.setText(strResult);
                    strBuf = new StringBuffer("");
                    strBufAll = new StringBuffer("");
                }
                if (strOpt.equals("-")) {
                    numBb = Double.parseDouble(strBuf.toString());
                    strResult = String.valueOf(numAa - numBb);
                    textResult.setText(strResult);
                    strBuf = new StringBuffer("");
                    strBufAll = new StringBuffer("");

                }
                if (strOpt.equals("*")) {
                    numBb = Double.parseDouble(strBuf.toString());
                    strResult = String.valueOf(numAa * numBb);
                    textResult.setText(strResult);
                    strBuf = new StringBuffer("");
                    strBufAll = new StringBuffer("");
                }
                if (strOpt.equals("/")) {
                    numBb = Double.parseDouble(strBuf.toString());
                    if (numBb != 0) {
                        strResult = String.valueOf(numAa / numBb);
                        textResult.setText(strResult);
                    } else {
                        Toast.makeText(MainActivity.this, "除数不能为零！", Toast.LENGTH_LONG).show();
                    }
                    strBuf = new StringBuffer("");
                    strBufAll = new StringBuffer("");
                }
                myClear();
            }
        });

    //不连续操作 end ---------
*/


        //栈 start 【不支持（）】 ------------
        /*
        // 加
        optPlus.setOnClickListener(new OnClickListener() {
            public void onClick(View v) {
                if(strBuf.toString().equals("")){
                    return;
                }
                strOpt = "+";
                strBufAll.append(strOpt);
                textQuestion.setText(strBufAll.toString());
                if (!strBuf.toString().equals("")) {
                    numAa += Double.parseDouble(strBuf.toString());
                    strBuf = new StringBuffer("");
                }
                // 在按了“=”而没有输入数字时，strResult不为空；在按了“=”并且输入数字后，strResult结果为空。（后面同理）
                if (strResult != null) {
                    numAa = Double.parseDouble(strResult);
                    strResult = null;
                }
                textResult.setText(String.valueOf(numAa));
                flag = true;
            }
        });

        //减
        optSubtract.setOnClickListener(new OnClickListener() {
            public void onClick(View v) {
                if(strBuf.toString().equals("")){
                    return;
                }
                strOpt = "-";
                strBufAll.append(strOpt);
                textQuestion.setText(strBufAll.toString());
                System.out.println(strBuf.toString());
                if(strBuf.toString().equals("")){
                    return;
                }
                if (b_sub == false && strBuf.toString() != "") {
                    numAa = Double.parseDouble(strBuf.toString());
                    textResult.setText(String.valueOf(numAa));
                    strBuf = new StringBuffer("");
                    b_sub = true;
                } else {
                    if (strBuf.toString() != "") {
                        numAa -= Double.parseDouble(strBuf.toString());
                        strBuf = new StringBuffer("");
                    }
                    if (strResult != null) {
                        numAa = Double.parseDouble(strResult);
                        strResult = null;
                        strBuf = new StringBuffer("");

                    }
                    textResult.setText(String.valueOf(numAa));
                    strBuf = new StringBuffer("");

                }
                strBuf = new StringBuffer("");

                flag = true;
            }
        });

        //乘
        optMultiply.setOnClickListener(new OnClickListener() {
            public void onClick(View v) {
                if(strBuf.toString().equals("")){
                    return;
                }
                strOpt = "*";
                strBufAll.append(strOpt);
                textQuestion.setText(strBufAll.toString());

                if (b_mul == false && strBuf.toString() != "") {
                    numAa = Double.parseDouble(strBuf.toString());
                    textResult.setText(String.valueOf(numAa));
                    strBuf = new StringBuffer("");
                    b_mul = true;
                } else {
                    if (strBuf.toString() != "") {
                        numAa *= Double.parseDouble(strBuf.toString());
                        strBuf = new StringBuffer("");
                    }
                    if (strResult != null) {
                        numAa = Double.parseDouble(strResult);
                        strResult = null;
                    }
                    textResult.setText(String.valueOf(numAa));
                }
                flag = true;
            }
        });

        //除
        optDivide.setOnClickListener(new OnClickListener() {
            public void onClick(View v) {
                if(strBuf.toString().equals("")){
                    return;
                }
                strOpt = "/";
                strBufAll.append(strOpt);
                textQuestion.setText(strBufAll.toString());
                if (b_div == false && strBuf.toString() != null) {
                    numAa = Double.parseDouble(strBuf.toString());
                    textResult.setText(String.valueOf(numAa));
                    strBuf = new StringBuffer("");
                    b_div = true;
                } else {
                    if (strBuf.toString() != "") {
                        if (Double.parseDouble(strBuf.toString()) == 0) {
                            Toast.makeText(MainActivity.this, "除数不能为零！", Toast.LENGTH_LONG)
                                    .show();
                        } else {
                            numAa /= Double.parseDouble(strBuf.toString());
                            strBuf = new StringBuffer("");
                        }
                    }
                    if (strResult != null) {
                        numAa = Double.parseDouble(strResult);
                        strResult = null;
                    }
                    textResult.setText(String.valueOf(numAa));
                }
                flag = true;
            }
        });

        //等于
        optEqual.setOnClickListener(new OnClickListener() {

            public void onClick(View v) {
                //if(strBuf.toString().equals("")){
                //    return;
                //}
                ////strBufAll.append("=");
                ////textQuestion.setText(strBufAll.toString());
                //if (strOpt.equals("+")) {
                //    numBb = Double.parseDouble(strBuf.toString());
                //    strResult = String.valueOf(numAa + numBb);
                //    textResult.setText(strResult);
                //    strBuf = new StringBuffer("");
                //    strBufAll = new StringBuffer("");
                //}
                //if (strOpt.equals("-")) {
                //    numBb = Double.parseDouble(strBuf.toString());
                //    strResult = String.valueOf(numAa - numBb);
                //    textResult.setText(strResult);
                //    strBuf = new StringBuffer("");
                //    strBufAll = new StringBuffer("");
                //
                //}
                //if (strOpt.equals("*")) {
                //    numBb = Double.parseDouble(strBuf.toString());
                //    strResult = String.valueOf(numAa * numBb);
                //    textResult.setText(strResult);
                //    strBuf = new StringBuffer("");
                //    strBufAll = new StringBuffer("");
                //}
                //if (strOpt.equals("/")) {
                //    numBb = Double.parseDouble(strBuf.toString());
                //    if (numBb != 0) {
                //        strResult = String.valueOf(numAa / numBb);
                //        textResult.setText(strResult);
                //    } else {
                //        Toast.makeText(MainActivity.this, "除数不能为零！", Toast.LENGTH_LONG).show();
                //    }
                //    strBuf = new StringBuffer("");
                //    strBufAll = new StringBuffer("");
                //}
                //System.out.println(strBufAll.toString());

                myZhan();
                myClear();

            }
        });


        //栈 end ---------
        */

        // 括号
        bntA.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                System.out.println(stSta);
                if (stSta.peek() == 1 || stSta.peek() == 4) {
                    myShowToast(stSta.peek());
                    // ( 前面不能为数字 和 ） 必须是*
                    return;
                }
                if (stSta.peek() == 0) {
                    textResult.setText("0.0");
                }
                stSta.push(3);
                leftKuoNum++;
                strBufAll.append("(");
                textQuestion.setText(strBufAll.toString());

            }
        });
        bntB.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                // ) 前面不能是* 也不能是空 也必须有可以匹配的（
                if (stSta.peek() == 0 || stSta.peek() == 2 || leftKuoNum <= rightKuoNum) {
                    myShowToast(stSta.peek());
                    return;
                }
                stSta.push(4);
                rightKuoNum++;
                strBufAll.append(")");
                textQuestion.setText(strBufAll.toString());
            }
        });

        //新c类 start 【支持（）】 ------------
        ///*
        // 加 符号前面不能是 空 ｜（
        optPlus.setOnClickListener(new OnClickListener() {
            public void onClick(View v) {
                if (stSta.peek() == 0 || stSta.peek() == 3 || stSta.peek() == 2 || stSta.peek() == 5) {
                    myShowToast(stSta.peek());
                    return;
                }
                stSta.push(2);
                strBufAll.append("+");
                textQuestion.setText(strBufAll.toString());
            }
        });

        //减
        optSubtract.setOnClickListener(new OnClickListener() {
            public void onClick(View v) {
                if (stSta.peek() == 0 || stSta.peek() == 3 || stSta.peek() == 2 || stSta.peek() == 5) {
                    myShowToast(stSta.peek());
                    return;
                }
                stSta.push(2);
                strBufAll.append("-");
                textQuestion.setText(strBufAll.toString());
            }
        });

        //乘
        optMultiply.setOnClickListener(new OnClickListener() {
            public void onClick(View v) {
                if (stSta.peek() == 0 || stSta.peek() == 3 || stSta.peek() == 2 || stSta.peek() == 5) {
                    myShowToast(stSta.peek());
                    return;
                }
                stSta.push(2);
                strBufAll.append("*");
                textQuestion.setText(strBufAll.toString());
            }
        });

        //除
        optDivide.setOnClickListener(new OnClickListener() {
            public void onClick(View v) {
                if (stSta.peek() == 0 || stSta.peek() == 3 || stSta.peek() == 2 || stSta.peek() == 5) {
                    myShowToast(stSta.peek());
                    return;
                }

                stSta.push(2);
                strBufAll.append("/");
                textQuestion.setText(strBufAll.toString());

            }
        });

        // 余
        optRemainder.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                if (stSta.peek() == 0 || stSta.peek() == 3 || stSta.peek() == 2 || stSta.peek() == 5) {
                    myShowToast(stSta.peek());
                    return;
                }
                //boolean isZo = false;
                //int inn = strBufAll.length()-1;
                //if (strBufAll.charAt(inn)=='0' ){
                //    if (strBufAll.length()==1){
                //
                //    }
                //}
                //for (int i=0; i<2; i++){
                //    if (strBufAll.charAt(inn) == '.'){
                //        isZo = true;
                //        break;
                //    }
                //    if (strBufAll.charAt(i)>'9' || strBufAll.charAt(i) < '0'){
                //        break;
                //    }
                //}
                //if (!isPoint){
                //    strBufAll.append(".");
                //    textQuestion.setText(strBufAll.toString());
                //    stSta.push(5);
                //}
                //else{
                //    Toast.makeText(MainActivity.this, "数字不能有两个小数点",Toast.LENGTH_LONG).show();
                //}
                stSta.push(2);
                strBufAll.append("%");
                textQuestion.setText(strBufAll.toString());
            }
        });


        //等于
        optEqual.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                System.out.println("====start====");
                System.out.println(nowDo);
                System.out.println(stSta);
                int iss = stSta.peek();
                if (stSta.peek() == 0 || stSta.peek() == 2 || stSta.peek() == 3 || stSta.peek() == 5  ) {
                    myShowToast(iss);
                    return;
                }
                if (leftKuoNum != rightKuoNum){
                    Toast.makeText(MainActivity.this, "括号没完", Toast.LENGTH_LONG).show();
                    return;
                }
                try {
                    nowAns = myKuoHaoTest();
                } catch (Exception e) {
                    Toast.makeText(MainActivity.this,"!警告 有问题!",Toast.LENGTH_LONG).show();
                    Toast.makeText(MainActivity.this,""+e,Toast.LENGTH_LONG).show();
                    System.out.println(e);
                    //e.printStackTrace();
                }
                nowDo = strBufAll.toString();
                textResult.setText(nowAns);
                leftKuoNum = 0;
                rightKuoNum = 0;
                stSta = new Stack<>();
                stSta.push(0);
                strBufAll = new StringBuffer("");
                System.out.println("=====end====");
            }
        });


        //新c类 end ---------
        //*/


    }


    //    c类
    public String myKuoHaoTest() throws Exception {
        MyKuoHao myKuoHao = new MyKuoHao();
        String ans = myKuoHao.getAnsKuoHao(strBufAll.toString());
        return ans;
    }


    public void myShowToast(int preSta) {
        switch (preSta) {
            case 0:
                Toast.makeText(MainActivity.this, "请先输入数字", Toast.LENGTH_LONG).show();
                break;
            case 1:
                Toast.makeText(MainActivity.this, "不可以在数字后直接输入括号", Toast.LENGTH_LONG).show();
                break;
            case 2:
                Toast.makeText(MainActivity.this, "注意格式", Toast.LENGTH_LONG).show();
                break;
            case 3:
                Toast.makeText(MainActivity.this, "前面是左括号", Toast.LENGTH_LONG).show();
                break;
            case 4:
                Toast.makeText(MainActivity.this, "前面是右括号", Toast.LENGTH_LONG).show();
                break;
            case 5:
                Toast.makeText(MainActivity.this, "小数点后只能是数字", Toast.LENGTH_LONG).show();
                break;
            default:
        }
    }


}




/*
999/9*3(9-3)
18

 */












