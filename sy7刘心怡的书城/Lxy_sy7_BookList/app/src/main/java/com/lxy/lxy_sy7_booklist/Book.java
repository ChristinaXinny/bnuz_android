package com.lxy.lxy_sy7_booklist;

public class Book {
    private String name; // 书名
    private String author; // 作者名
    private String press; // 出版社
    private int img;

    public Book(String name, String author, String press, int img) {
        this.name = name;
        this.author = author;
        this.press = press;
        this.img = img;
    }

    public String getName() {
        return name;
    }

    public String getAuthor() {
        return author;
    }

    public String getPress() {
        return press;
    }

    public int getImg() {
        return img;
    }
}
