package com.lxy.lxybaiwangluapp;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class TestloginActivity extends AppCompatActivity implements View.OnClickListener {
    private Button login, cancel;
    private CheckBox checkBox, checkBox2;
    private EditText editName, editPassword;
    //声明一个SharedPreferences对象和一个Editor对象
    private SharedPreferences preferences;
    private SharedPreferences.Editor editor;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_testlogin);
        login = (Button) findViewById(R.id.login);
        cancel = (Button) findViewById(R.id.cancel);
        editName = (EditText) findViewById(R.id.editName);
        editPassword = (EditText) findViewById(R.id.editPassword);
        checkBox = (CheckBox) findViewById(R.id.checkBox);
        checkBox2 = (CheckBox) findViewById(R.id.checkBox2);
        //获取preferences和editor对象
        preferences = getSharedPreferences("UserInfo", MODE_PRIVATE);
        editor = preferences.edit();
        String name = preferences.getString("userName",null);
        if (name == null) {
            checkBox.setChecked(false);
        } else {
            editName.setText(name);
            checkBox.setChecked(true);
        }
        String password = preferences.getString("userPassword", null);
        if (password == null) {
            checkBox2.setChecked(false);
        } else {
            editPassword.setText(password);
            checkBox2.setChecked(true);
        }

        //为login和cancel设置监听事件
        login.setOnClickListener(this);
        cancel.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        //判断用户是进行的是登陆操作还是取消操作
        switch (v.getId()) {
            case R.id.login:
                String name = editName.getText().toString().trim();
                String password = editPassword.getText().toString().trim();
                //验证用户名和密码，若为 liuxinyi - 1901030043 即可登录
                if (name.equals("liuxinyi") && password.equals("1901030043")) {
                    // 判断记住密码 & 用户名
                    if (checkBox.isChecked()) {
                        //选择记住
                        //将用户输入的用户名存入储存中，键为userName
                        editor.putString("userName", name);
                        //将用户输入的密码存入储存中，userPassword
                        editor.putString("userPassword", password);
                    } else {
                        //未选择 ---清除 用户名密码
                        editor.remove("userName");
                        editor.remove("userPassword");
                    }
                    editor.commit();
                    // 判断自动登录
                    if (checkBox2.isChecked()) {
                        editor.putString("userPassword", password);
                        editor.putBoolean("AUTO_ISCHECK", true).commit();
                    } else {

                    }
                    //提示登陆成功
                    Toast.makeText(this, "login success", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent();
                    intent.setClass(TestloginActivity.this, MainActivity.class);
                    startActivity(intent);
                } else {
                    //若登陆不成功，则将错误的用户名和密码清除，并提示登陆失败
                    editor.remove("userName");
                    editor.remove("userPassword");
                    editor.commit();
                    Toast.makeText(this, "login failed", Toast.LENGTH_SHORT).show();
                }
                break;
            //若用户选择了取消，则直接退出登录
            case R.id.cancel:
                finish();
        }

    }
}
