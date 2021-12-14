package com.lxy.lxyapachelist;

import android.media.Image;

import com.loopj.android.image.SmartImageView;

public class NewsBean {
    private String icon;      // 图片
    private String title;    // 题目
    private String content;  // 内容
    private int type;     // 类型
    private int comment;     // 评论数

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }


    public int getComment() {
        return comment;
    }

    public void setComment(int comment) {
        this.comment = comment;
    }
}
