package com.lxy.lxy_myapp_sy5;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Paint;
import android.os.Bundle;
import android.text.Editable;
import android.text.Html;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private TextView title;
    private Button bntLogin, bntJoin;
    private EditText textName, textPass;
    private String myName;
    private String myPassword;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        ActionBar actionBar = getSupportActionBar();
        actionBar.setLogo(R.mipmap.ic_launcher);
        actionBar.setDisplayUseLogoEnabled(true);
        actionBar.setDisplayShowHomeEnabled(true);

        title = findViewById(R.id.titlee);
        title.setText(Html.fromHtml("欢迎参加<font color='#FFA8CC'>手机软件</font>射击赛"));

        bntJoin = findViewById(R.id.bnt_join);
        bntLogin = findViewById(R.id.bnt_login);

        textName = findViewById(R.id.name);
        textPass = findViewById(R.id.password);


        bntJoin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                myName = textName.getText().toString();
                myPassword = textPass.getText().toString();
                Toast.makeText(MainActivity.this, "注册成功", Toast.LENGTH_SHORT).show();
            }
        });

        bntLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (myName.equals(textName.getText().toString()) && myPassword.equals(textPass.getText().toString())) {
                    Toast.makeText(MainActivity.this, "欢迎学生:"+myName+"!",Toast.LENGTH_SHORT).show();
                }
                else{
                    Toast.makeText(MainActivity.this, "登录失败",Toast.LENGTH_SHORT).show();
                }
            }
        });


    }
}








