package com.lxy.lxymyapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class ImageActivity extends AppCompatActivity {

    private ImageView img;
    private Button btn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_image);
        img = (ImageView) findViewById(R.id.img_head);
        btn = findViewById(R.id.img_btn);

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                btn.setOnClickListener(new View.OnClickListener(){
                    @Override
                    public void onClick(View v) {
                        Intent intent = new Intent(ImageActivity.this, MainActivity.class);
                        intent.putExtra("num","1901030043");
                        intent.putExtra("name","刘心怡");
                        startActivity(intent);
                    }
                });

            }
        });
    }


}