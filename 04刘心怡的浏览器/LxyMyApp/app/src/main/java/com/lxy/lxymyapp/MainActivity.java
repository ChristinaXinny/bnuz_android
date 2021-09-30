package com.lxy.lxymyapp;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import static android.content.Intent.ACTION_VIEW;

public class MainActivity extends AppCompatActivity {

    // 打开网址、发送短信、打开照片框
    private Button webBnt, messageBnt, imageBnt;
    // 网址、手机号、短信内容
    private EditText webEt, phoneEt, messageEt;
    TextView numName;

    private Toast toast;

    public static final String MY_ACTION = "lxy_action";
    public static final String MY_CATEGORY = "lxy_category";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //获取到
        webEt = (EditText) findViewById(R.id.web_side);
        webBnt = (Button) findViewById(R.id.web_bnt);
        webBnt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //http://www.sina.com
                String webUrl = webEt.getText().toString();
                System.out.println("webbb"+webUrl);
                System.out.println(webUrl!=null&&webUrl.length()!=0);
                //Uri uri= Uri.parse("http://www.baidu.com");
                if (webUrl!=null&&webUrl.length()!=0){
                    System.out.println("dddd");
                    Intent i = new Intent();
                    i.setAction(ACTION_VIEW);
                    System.out.println(webUrl);
                    Uri uri = Uri.parse(webUrl);
                    i.setData(uri);
                    startActivity(i);
                }
                else {
                    Toast t = Toast.makeText(MainActivity.this,"请输入网址",Toast.LENGTH_LONG);
                    t.show();
                    System.out.println("aaaa");


                }
            }
        });

        phoneEt = (EditText) findViewById(R.id.phone_number);
        messageEt = (EditText) findViewById(R.id.message_content);
        messageBnt = (Button) findViewById(R.id.put_message_btn);
        messageBnt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String phNum = phoneEt.getText().toString();
                String me = messageEt.getText().toString();
                Uri uri = Uri.parse("smsto:" + phNum);
                Intent it = new Intent(Intent.ACTION_SENDTO, uri);
                it.putExtra("address", "13319010910"); //发送默认电话
                it.putExtra("sms_body", me);
                startActivity(it);
            }
        });


        numName = (TextView) findViewById(R.id.id_name);
        imageBnt = (Button) findViewById(R.id.open_image_btn);
        imageBnt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent it = new Intent(MainActivity.this, ImageActivity.class);
                startActivity(it);
            }
        });

        Intent it = getIntent();
        String num = it.getStringExtra("num");
        String name = it.getStringExtra("name");
        System.out.println("num:"+ num +", name:"+name);
        if (num!=null && name!=null){
            this.numName.append("学号："+num + "  ");
            this.numName.append("姓名："+name);
        }


    }


}




