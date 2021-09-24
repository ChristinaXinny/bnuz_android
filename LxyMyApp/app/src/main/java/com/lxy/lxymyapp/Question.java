package com.lxy.lxymyapp;

public class Question {
    //Question类的文本资源Id
    private int mTextResId;         //mTextResId用来保存地理知识  问题字符串的资源ID
    //Question类的一种状态答案是否正确
    private boolean mAnswerTrue;
    //是否已经回答
    private boolean mAnswered;



    public Question(int mTextResId, boolean mAnswerTrue) {
        this.mTextResId = mTextResId;
        this.mAnswerTrue = mAnswerTrue;
    }

    public int getTextResId() {
        return mTextResId;
    }

    public void setTextResId(int mTextResId) {
        this.mTextResId = mTextResId;
    }

    public boolean isAnswerTrue() {
        return mAnswerTrue;
    }

    public void setAnswerTrue(boolean mAnswerTrue) {
        this.mAnswerTrue = mAnswerTrue;

    }

    public void setAnswered(boolean mAnswered) {
        this.mAnswered = mAnswered;
    }

    public boolean isAnswered() {
        return mAnswered;
    }

}
