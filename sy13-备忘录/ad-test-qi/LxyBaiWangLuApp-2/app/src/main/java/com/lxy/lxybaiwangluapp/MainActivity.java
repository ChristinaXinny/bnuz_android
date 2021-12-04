package com.lxy.lxybaiwangluapp;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.app.DatePickerDialog;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;

import java.util.Calendar;

public class MainActivity extends AppCompatActivity implements DatePickerDialog.OnDateSetListener {

    private Button btnTime, btnNew, btnFind;
    private EditText etTitle, etContext, etTime;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //去除标题
        //requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_main);
        this.getSupportActionBar().hide();

        // 日期选择器
        btnTime = findViewById(R.id.btn_time);
        etTime = findViewById(R.id.et_time);
        etTime.setEnabled(false);
        btnTime.setOnClickListener(new View.OnClickListener() {
            //设置按钮的点击事件监听器
            @Override
            public void onClick(View v) {
                //获取实例，包含当前年月日
                Calendar calendar = Calendar.getInstance();
                DatePickerDialog dialog = new DatePickerDialog(MainActivity.this,MainActivity.this,
                        calendar.get(Calendar.YEAR),
                        calendar.get(Calendar.MARCH),
                        calendar.get(Calendar.DAY_OF_MONTH));
                dialog.show();
            }
        });
    }

    @Override
    public void onDateSet(DatePicker dp,int year,int month,int day) {
        @SuppressLint("DefaultLocale") String desc = String.format("%d-%d-%d", year, month+1, day);
        etTime.setText(desc);
    }
}