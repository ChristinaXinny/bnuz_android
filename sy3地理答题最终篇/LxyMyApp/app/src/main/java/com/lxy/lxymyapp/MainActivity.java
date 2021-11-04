package com.lxy.lxymyapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.nfc.Tag;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    //TAG常量 关于打印日志
    private static final String TAG = "QuizActivity";
    //新增键-值对的键
    private static final String KEY_INDEX = "index";

    private static final String KEY_ANSWERED = "KEY_ANSWERED";

    private static  final String KEY_COUNT = "count";
    private static  final String KEY_NUM = "num";

    //分数
    private int count = 0;
    //已经答题的数目
    private int num = 0;


    private Button mTrueButton;
    private Button mFalseButton;
    private Button mNexButton;
    private Button mPreButton;
    private TextView mQuestionTextView;

    private ImageButton mNexImgButton;
    private ImageButton mPreImgButton;


    //     文字数组  将string.xml中的文字内容存为java文件中的数组
    private Question[] mQuestionsBank = new Question[]{
            new Question(R.string.question_africa, false),
            new Question(R.string.question_america, true),
            new Question(R.string.question_asia, false),
            new Question(R.string.question_australia, true),
            new Question(R.string.question_mideast, true),
            new Question(R.string.question_oceans, true),
    };

    //    当前文字数组的 显示当前下表
    private int mCurrentIndex = 0;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //为onCreate(Bundle)方法添加日志输出代码
        Log.d(TAG, "onCreate(Bundle) called");

        if (savedInstanceState != null) {
            boolean answerIsAnswered[] = savedInstanceState.getBooleanArray(KEY_ANSWERED);

            //当界面被create的时候 将存储起来的KEY_COUNT获取到 并赋值给count
            for (int i = 0; i < mQuestionsBank.length; i++) {
                mQuestionsBank[i].setAnswered(answerIsAnswered[i]);
            }
        }

        //在onCreate(Bundle)方法中检查存储的bundle信息
        if (savedInstanceState != null){
            count = savedInstanceState.getInt(KEY_COUNT,0);
            num = savedInstanceState.getInt(KEY_NUM,0);
            mCurrentIndex = savedInstanceState.getInt(KEY_INDEX, 0);
        }



//       对 mQuestionTextView 进行设置
//       这里设置问题对显示 根据mQuestionsBank的数组动态变换里面的文字
        mQuestionTextView = findViewById(R.id.question_text_view);
        mQuestionTextView.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                mCurrentIndex = (mCurrentIndex + 1) % mQuestionsBank.length;
                updateQuestion();
            }
        });



//        true 按钮设置
        mTrueButton = (Button) findViewById(R.id.true_button);
        mTrueButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                checkAnswer(true);

            }
        });

//        false按钮
        mFalseButton = (Button) findViewById(R.id.false_button);
        mFalseButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                checkAnswer(false);
            }
        });


//       mNextButton 按钮
        mNexButton = (Button)findViewById(R.id.next_button);
        mNexButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mCurrentIndex = (mCurrentIndex + 1) % mQuestionsBank.length;
                checkIfAnswered();
                updateQuestion();

            }
            //
        });


//        向前的按钮
        mPreButton = findViewById(R.id.prev_button);
        mPreButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mCurrentIndex == 0 ){
                    mCurrentIndex = mQuestionsBank.length;
                }
                mCurrentIndex = (mCurrentIndex - 1) % mQuestionsBank.length;
                checkIfAnswered();
                updateQuestion();
            }
        });





        mNexImgButton = findViewById(R.id.next_img_button);
        mNexImgButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mCurrentIndex = (mCurrentIndex + 1) %mQuestionsBank.length;
                updateQuestion();
            }
        });

        mPreImgButton = findViewById(R.id.prev_img_button);
        mPreImgButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mCurrentIndex = (mCurrentIndex - 1) %mQuestionsBank.length;
                updateQuestion();
            }
        });

        updateQuestion();
    }


    //覆盖更多生命周期方法
    @Override
    protected void onStart() {
        super.onStart();
        Log.d(TAG,"onStart() called");
    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.d(TAG,"onStop() called");

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.d(TAG,"onDestroy() called");

    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.d(TAG,"onPause() called");

    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.d(TAG,"onResume() called");
    }


    //覆盖onSaveInstanceState(Bundle)方法
    @Override
    protected void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
        //存储是否作答状态
        boolean answerIsAnswered[] = new boolean[mQuestionsBank.length];
        for (int i = 0; i < mQuestionsBank.length; i++) {
            answerIsAnswered[i] = mQuestionsBank[i].isAnswered();
        }
        //存储KEY_ANSWERED
        outState.putBooleanArray(KEY_ANSWERED, answerIsAnswered);

        // 将count 存储到KEY_COUNT中
        outState.putInt(KEY_COUNT, count);
        outState.putInt(KEY_NUM, num);

        Log.i(TAG, "onSaveInstanceState");
        outState.putInt(KEY_INDEX, mCurrentIndex);
    }




    // 判断是否作答。
    private void checkIfAnswered() {
        boolean answerIsAnswered = mQuestionsBank[mCurrentIndex].isAnswered();
        if (answerIsAnswered == true) {
            mTrueButton.setEnabled(false);
            mFalseButton.setEnabled(false);
        } else {
            mTrueButton.setEnabled(true);
            mFalseButton.setEnabled(true);
        }
    }




    //     更新问题的文字
    private void updateQuestion(){
        int que = mQuestionsBank[mCurrentIndex].getTextResId();
        mQuestionTextView.setText(que);
    }

    //    将按钮的弹框提出 进行和答案的对错和用户选择的对错进行判断
    private void checkAnswer(boolean userPressedTrue){
        boolean answerIsTrue = mQuestionsBank[mCurrentIndex].isAnswerTrue();
        mQuestionsBank[mCurrentIndex].setAnswered(true);
        checkIfAnswered();

        int massId = 0;
        if (userPressedTrue == answerIsTrue){
            massId = R.string.correct_toast;
            count ++;
        }
        else{
            massId = R.string.incorrect_toast;
        }
        num++;
        System.out.println(num);
        Toast.makeText(this, massId, Toast.LENGTH_SHORT).show();
        if (num == 6){
            Toast.makeText(this, "you count is "+(count*16.6), Toast.LENGTH_SHORT).show();

        }
    }

}





















