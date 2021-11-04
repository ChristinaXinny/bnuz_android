package com.lxy.lxysy9app;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.Menu;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.annotation.BinderThread;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class PlaceActivity extends Activity {


    private Spinner spinner1, spinner2, spinner3;

    private String[] arrayPro = new String[]{"选择省", "广东省"};
    private String[] arrayCit = new String[]{"选择市", "珠海市", "深圳市"};
    private String[] arrayZh = new String[]{"选择区", "香洲区", "斗门区", "金湾区"};
    private String[] arraySz = new String[]{"选择区", "龙岗区", "南山区", "福田区", "罗湖区", "盐田区", "宝安区", "龙华区", "光明区"};


    private String strPro = "广东省", strCit = "珠海市", strAre = "香洲区";

    private Button qdBtn;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_place);

        spinner1 = (Spinner) findViewById(R.id.spinner1);
        spinner2 = (Spinner) findViewById(R.id.spinner2);
        spinner3 = (Spinner) findViewById(R.id.spinner3);

        SpinnerAdapter adapterPro = new SpinnerAdapter(this, arrayPro, R.layout.activity_item);
        spinner1.setAdapter(adapterPro);
        SpinnerAdapter modelCit = new SpinnerAdapter(this, arrayCit, R.layout.activity_item);
        spinner2.setAdapter(modelCit);
        SpinnerAdapter modelSz = new SpinnerAdapter(this, arraySz, R.layout.activity_item);
        spinner3.setAdapter(modelSz);

        spinner1.setOnItemSelectedListener(new Spinner1ClickListener());
        spinner2.setOnItemSelectedListener(new Spinner2ClickListener());
        spinner3.setOnItemSelectedListener(new Spinner3ClickListener());


        qdBtn = findViewById(R.id.qd_btn);
        // 返回按钮
        qdBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Intent intentt=getIntent();
                String pl = getIntent().getStringExtra("place");
                Intent intent = new Intent(PlaceActivity.this, MainActivity.class);
                String pll = "" + strPro + " " + strCit + " " + strAre;
                intent.putExtra("place", pll);
                setResult(RESULT_OK, intent);
                finish();
            }
        });
    }
    @Override
    public void onBackPressed() {
        Intent intent = getIntent();
        setResult(4, intent);
        super.onBackPressed();
    }




    private void changeAr(String str) {
        if (str.equals("深圳市")) {
            //加载深圳
            SpinnerAdapter modelSz = new SpinnerAdapter(this, arraySz, R.layout.activity_item);
            spinner3.setAdapter(modelSz);
        } else if (str.equals("珠海市")) {
            //加载珠海
            SpinnerAdapter modelZh = new SpinnerAdapter(this, arrayZh, R.layout.activity_item);
            spinner3.setAdapter(modelZh);
        }

    }


    public class Spinner1ClickListener implements AdapterView.OnItemSelectedListener {
        @Override
        public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
            String str = (String) adapterView.getItemAtPosition(i);
            //判断是否选择城市，如果没有选择那么就隐藏Spinner2,Spinner3两个下拉框，否则显示Spinner2下拉框，继续隐藏Spinner3
            if (str.equals("选择省")) {
                spinner2.setVisibility(View.INVISIBLE);
                spinner3.setVisibility(View.INVISIBLE);
            } else {
                spinner2.setVisibility(View.VISIBLE);
                //将第二个下拉框的选项重新设置为选中“请选择”这个选项。
                spinner2.setSelection(0);
            }
            if (!str.equals("选择省")) {
                strPro = str;
            }
            Toast.makeText(getApplicationContext(), str, Toast.LENGTH_SHORT).show();
        }
        @Override
        public void onNothingSelected(AdapterView<?> adapterView) {
        }
    }

    public class Spinner2ClickListener implements AdapterView.OnItemSelectedListener {
        @Override
        public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
            String str = (String) adapterView.getItemAtPosition(i);
            if (str.equals("选择市")) {
                spinner3.setVisibility(View.INVISIBLE);
            } else {
                //显示第三个Spinner3
                spinner3.setVisibility(View.VISIBLE);
                strCit = str;
                changeAr(str);
            }
            if (!str.equals("选择市")) {
                strCit = str;
            }
            Toast.makeText(getApplicationContext(), str, Toast.LENGTH_SHORT).show();
        }
        @Override
        public void onNothingSelected(AdapterView<?> adapterView) {
        }
    }

    public class Spinner3ClickListener implements AdapterView.OnItemSelectedListener {
        @Override
        public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
            String str = (String) adapterView.getItemAtPosition(i);
            if (!str.equals("选择区")) {
                strAre = str;
            }
            Toast.makeText(getApplicationContext(), str, Toast.LENGTH_SHORT).show();
        }
        @Override
        public void onNothingSelected(AdapterView<?> adapterView) {

        }
    }


}

