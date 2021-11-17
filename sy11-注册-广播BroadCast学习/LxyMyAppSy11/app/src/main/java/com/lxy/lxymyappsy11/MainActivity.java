package com.lxy.lxymyappsy11;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Button forceOffline = findViewById(R.id.force_offline);
        forceOffline.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent("com.lxy.lxymyappsy11.FORCE_OFFLINE");
                sendBroadcast(intent);
            }
        });
    }

    @Override
    public void onBackPressed() {
        Intent intent = new Intent("com.lxy.lxymyappsy11.FORCE_OFFLINE");
        sendBroadcast(intent);

    }
}