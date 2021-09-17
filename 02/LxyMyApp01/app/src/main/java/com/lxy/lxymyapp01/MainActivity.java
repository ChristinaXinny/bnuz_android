package com.lxy.lxymyapp01;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

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


    @SuppressLint("WrongViewCast")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);



//       对 mQuestionTextView 进行设置
//       这里设置问题对显示 根据mQuestionsBank的数组动态变换里面的文字
        mQuestionTextView = findViewById(R.id.question_text_view);
        mQuestionTextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mCurrentIndex = (mCurrentIndex + 1) %mQuestionsBank.length;
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

//     更新问题的文字
    private void updateQuestion(){
        int que = mQuestionsBank[mCurrentIndex].getTextResId();
        mQuestionTextView.setText(que);
    }

//    将按钮的弹框提出 进行和答案的对错和用户选择的对错进行判断
    private void checkAnswer(boolean userPressedTrue){
        boolean answerIsTrue = mQuestionsBank[mCurrentIndex].isAnswerTrue();

        int massId = 0;
        if (userPressedTrue == answerIsTrue){
            massId = R.string.correct_toast;

        }
        else{
            massId = R.string.incorrect_toast;
        }

        Toast.makeText(this, massId, Toast.LENGTH_SHORT).show();



    }



}












