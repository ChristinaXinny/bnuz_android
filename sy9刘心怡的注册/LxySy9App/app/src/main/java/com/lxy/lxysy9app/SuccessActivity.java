package com.lxy.lxysy9app;

import android.content.Intent;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;



public class SuccessActivity extends AppCompatActivity {


    private TextView nameTo, passTo, genderTo, schoolTo, placeTo;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_success);

        Intent intent =  this.getIntent();

        String ss = intent.getStringExtra("info");
        String[] sss = ss.split(",");
        for (String s1 : sss) {
            System.out.println(s1);
        }

        nameTo = findViewById(R.id.name_to);
        passTo = findViewById(R.id.pass_to);
        genderTo =findViewById(R.id.gender_to);
        schoolTo = findViewById(R.id.school_to);
        placeTo = findViewById(R.id.place_to);

        nameTo.setText(sss[0]);
        passTo.setText(sss[1]);
        genderTo.setText(sss[2]);
        schoolTo.setText(sss[3]);
        placeTo.setText(sss[4]);







    }
}
