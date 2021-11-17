package com.lxy.lxysy9app;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.text.DateFormat;
import java.util.Calendar;
import java.util.Locale;

import static android.app.AlertDialog.*;
import static android.view.View.*;
import static android.view.View.OnClickListener;
import static android.widget.RadioGroup.*;
import static android.widget.TextView.*;

public class MainActivity extends AppCompatActivity implements DatePickerDialog.OnDateSetListener {


    private EditText nameEd, passEd, defineEd;
    private ImageView nameImg, passImg, defineImg;

    private String nameStr="", passStr="", defineStr="", genderStr="", schoolStr="", placeStr="";

    private RadioGroup genderRG;
    private RadioButton genderRB;   // 男？女？

    private Spinner schoolSpinner;

    private Button placeBtn, dataBtn, loginBtn;
    private EditText placeEd, dataEd;



    //获取日期格式器对象
    Calendar calendar = Calendar.getInstance(Locale.CHINA);
    //获取日期格式器对象
    DateFormat format =  DateFormat.getDateTimeInstance();




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        // 用户名
        nameEd = findViewById(R.id.name_edit);
        nameImg = findViewById(R.id.name_img);
        nameEd.setOnKeyListener(new View.OnKeyListener() {
            @Override
            public boolean onKey(View view, int i, KeyEvent keyEvent) {
                String str = nameEd.getText().toString();
                if (str.equals("")){
                    Builder builder  = new Builder(MainActivity.this);
                    builder.setTitle("用户名出错" ) ;
                    builder.setMessage("用户名不能为空" ) ;
                    builder.setPositiveButton("确定" ,  null );
                    builder.show();
                    nameStr = "";
                }
                else{
                    nameStr = str;
                    nameImg.setVisibility(VISIBLE);
                }
                return false;
            }
        });

        // 密码
        passEd = findViewById(R.id.pass_edit);
        passImg = findViewById(R.id.pass_img);
        passEd.setOnEditorActionListener(new OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView textView, int i, KeyEvent keyEvent) {
                String pass = passEd.getText().toString();
                if (pass.equals("") || pass.length() > 10 || pass.length() < 4) {
                    Builder builder  = new Builder(MainActivity.this);
                    builder.setTitle("密码有误" ) ;
                    builder.setMessage("请输入密码4～10位" ) ;
                    builder.setPositiveButton("确定" ,  null );
                    builder.show();
                    passStr = "";
                    Toast.makeText(MainActivity.this, "为空｜超出10｜少于2", Toast.LENGTH_LONG);
                    passImg.setImageResource(R.drawable.error);
                } else {
                    passStr = pass;
                    passImg.setImageResource(R.drawable.fine);
                }
                passImg.setVisibility(VISIBLE);
                return false;
            }
        });

        // 确认密码
        defineEd = findViewById(R.id.define_edit);
        defineImg = findViewById(R.id.define_img);
        defineEd.setOnEditorActionListener(new OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView textView, int i, KeyEvent keyEvent) {
                String de = defineEd.getText().toString();
                if (  de.equals("") || (!de.equals(passEd.getText().toString()))) {
                    Builder builder  = new Builder(MainActivity.this);
                    builder.setTitle("密码错误" ) ;
                    builder.setMessage("查看密码两次输入是否一致" ) ;
                    builder.setPositiveButton("确定" ,  null );
                    builder.show();
                    defineStr = "";
                    Toast.makeText(MainActivity.this, "两次密码不相同", Toast.LENGTH_SHORT);
                    defineImg.setImageResource(R.drawable.error);
                }
                else {
                    defineStr = de;
                    defineImg.setImageResource(R.drawable.fine);
                }
                defineImg.setVisibility(VISIBLE);
                return false;
            }
        });


        // 性别
        genderRG = findViewById(R.id.gender_rg);
        genderRG.setOnCheckedChangeListener(new OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {
                genderRB = radioGroup.findViewById(i);
                genderStr = genderRB.getText().toString();
                System.out.println(genderStr);
            }
        });


        // 所在学校
        schoolSpinner = findViewById(R.id.spinner);
        schoolSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                schoolStr = (String) schoolSpinner.getSelectedItem();
                System.out.println(schoolStr);
            }
            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });


        //所在地区
        placeEd = findViewById(R.id.place_text);
        placeEd.setEnabled(false);
        placeBtn = findViewById(R.id.place_btn);
        placeBtn.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent it = new Intent(MainActivity.this,PlaceActivity.class);
                it.putExtra("place","");
                startActivityForResult(it, 3);
            }
        });
        Intent it = getIntent();
        String place = it.getStringExtra("place");
        System.out.println("place:" + place);
        if (place != null) {
            placeEd.setText(place);
            placeStr = place;
        } else {
            placeStr = "";
            Toast.makeText(MainActivity.this, "请选择地区", Toast.LENGTH_LONG).show();
        }


        // 日期选择器
        dataBtn = findViewById(R.id.data_btn);
        dataEd = findViewById(R.id.data_text);
        dataEd.setEnabled(false);
        dataBtn.setOnClickListener(new OnClickListener() {
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




        // 注册
        loginBtn = findViewById(R.id.login_btn);
        loginBtn.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                if ( nameStr.equals("") || passStr.equals("") || defineStr.equals("")){
                    Toast.makeText(MainActivity.this, "请将信息输入完整" ,Toast.LENGTH_LONG).show();
                }
                else{
                    Intent it = new Intent(MainActivity.this, SuccessActivity.class);
                    String ss = nameStr + "," + passStr + "," + genderStr + "," + schoolStr + "," + placeStr ;
                    it.putExtra("info",ss);
                    startActivity(it);
                }

            }
        });


    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 3 && resultCode == RESULT_OK) {
            placeStr = data.getStringExtra("place");
            placeEd.setText(placeStr);
        }

    }


    @Override
    public void onDateSet(DatePicker dp,int year,int month,int day) {
        @SuppressLint("DefaultLocale") String desc = String.format("嗨皮波斯得：%d年%d月%d日", year, month+1, day);
        dataEd.setText(desc);
    }
}