package com.lxy.lxy_sy7_booklist;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private List<Book> mBookList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initBook();
        BookAdapter bookAdapter = new BookAdapter(MainActivity.this, R.layout.book_item, mBookList);
        ListView listView = findViewById(R.id.list_view );
        listView.setAdapter(bookAdapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Book book = mBookList.get(i);
                Toast.makeText(MainActivity.this, book.getName()+"\n"+book.getAuthor()+"\n"+book.getPress(),Toast.LENGTH_LONG).show();
            }
        });

    }


    public void initBook() {
        for (int j = 0; j < 2; j++) {
            Book book1 = new Book("书名：计算机组成原理", "作者：Bryant", "出版社：机械工业出版社", R.drawable.book1);
            mBookList.add(book1);
            Book book2 = new Book("书名：Python数据挖掘", "作者：张良", "出版社：机械工业出版社", R.drawable.book2);
            mBookList.add(book2);
            Book book3 = new Book("书名：疯狂java", "作者：李刚", "出版社：电子工业出版社", R.drawable.book3);
            mBookList.add(book3);
            Book book4 = new Book("书名：现在操作系统", "作者：Andrew", "出版社：机械工业出版社", R.drawable.book4);
            mBookList.add(book4);
            Book book5 = new Book("书名：软件工程", "作者：程努华", "出版社：中国工信出版社", R.drawable.book5);
            mBookList.add(book5);
            Book book6 = new Book("书名：Androids权威编程", "作者：Bill", "出版社：中国工信出版社", R.drawable.book6);
            mBookList.add(book6);
            Book book7 = new Book("书名：Python机器学习", "作者：Andreas", "出版社：中国工信出版社", R.drawable.book7);
            mBookList.add(book7);
            Book book8 = new Book("书名：数据库系统", "作者：王珊", "出版社：高等教育出版社", R.drawable.book8);
            mBookList.add(book8);
            Book book9 = new Book("书名：计算机算法", "作者：苏运河", "出版社：国防工业出版社", R.drawable.book9);
            mBookList.add(book9);
        }
    }
}