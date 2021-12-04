package com.lxy.lxymemoclient;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.DatePickerDialog;
import android.content.ContentResolver;
import android.content.ContentValues;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

import androidx.annotation.Nullable;

import java.util.Calendar;

public class NewActivity extends Activity implements DatePickerDialog.OnDateSetListener {
    private ImageButton btnDate, btnBack, btnSave;
    private EditText etDate, etTitle, etContext;
    ContentResolver contentResolver;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new);






        // 返回上一界面按钮
        btnBack = findViewById(R.id.btn_back);
        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                NewActivity.this.finish();
            }
        });

        // 绑定主题、内容、日期
        etTitle= findViewById(R.id.et_title);
        etContext = findViewById(R.id.et_context);
        btnDate = (ImageButton) findViewById(R.id.btn_date);
        etDate = (EditText) findViewById(R.id.et_date);
        etDate.setEnabled(false);
        btnDate.setOnClickListener(new View.OnClickListener() {
            //设置按钮的点击事件监听器
            @Override
            public void onClick(View v) {
                //获取实例，包含当前年月日
                Calendar calendar = Calendar.getInstance();
                DatePickerDialog dialog = new DatePickerDialog(NewActivity.this, NewActivity.this,
                        calendar.get(Calendar.YEAR),
                        calendar.get(Calendar.MARCH),
                        calendar.get(Calendar.DAY_OF_MONTH));
                dialog.show();
            }
        });


        // 保存按钮
        btnSave = findViewById(R.id.btn_save);
        contentResolver = getContentResolver();
        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String title = etTitle.getText().toString();
                String date = etDate.getText().toString();
                String context = etContext.getText().toString();
                ContentValues values = new ContentValues();
                values.put(Memos.Memo.TITLE, title);
                values.put(Memos.Memo.DATE, date);
                values.put(Memos.Memo.CONTEXT, context);
                contentResolver.insert(Memos.Memo.DICT_CONTENT_URI, values);
                Toast.makeText(NewActivity.this, "保存成功！"
                        , Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public void onDateSet(DatePicker dp, int year, int month, int day) {
        @SuppressLint("DefaultLocale") String desc = String.format("%d-%d-%d", year, month + 1, day);
        etDate.setText(desc);
    }
}
