package com.lxy.lxymemoclient;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.app.DatePickerDialog;
import android.content.ContentResolver;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.SimpleAdapter;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MainActivity extends AppCompatActivity {

    private FloatingActionButton btnNew;
    private Button btnFind;
    private ImageButton tbnDelete;
    private EditText etFind;
    private ListView listView;
    private ContentResolver contentResolver;
    private SimpleAdapter adapter;
    private List<Map<String, String>> list;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //去除标题
        //requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_main);
        this.getSupportActionBar().hide();

        contentResolver = this.getContentResolver();

        btnNew = findViewById(R.id.btn_new);
        btnNew.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //跳转界面
                Intent intent = new Intent(MainActivity.this, NewActivity.class);
                MainActivity.this.startActivity(intent);
            }
        });


        init();




        btnFind = findViewById(R.id.btn_find);
        etFind = findViewById(R.id.et_find);
        btnFind.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String key = etFind.getText().toString();
                System.out.println(key);
                Cursor cursor = contentResolver.query(
                        Memos.Memo.DICT_CONTENT_URI, null,
                        "title like ? or date like ? or context like ?", new String[] {
                                "%" + key + "%", "%" + key + "%", "%" + key + "%" }, null);
                list = converCursorToList(cursor);
                adapter = new SimpleAdapter(MainActivity.this,
                        list, R.layout.line
                        , new String[] { Memos.Memo.TITLE, Memos.Memo.DATE, Memos.Memo.CONTEXT}
                        , new int[] { R.id.et_one_title, R.id.et_one_date, R.id.et_one_context });
                // 自动更新页面
                adapter.notifyDataSetChanged();
                listView.setAdapter(adapter);
                listView.invalidateViews();
            }
        });

    }


    public void init() {
        Cursor cursor = contentResolver.query(
                Memos.Memo.DICT_CONTENT_URI, null,
                null, null, null);
        listView = findViewById(R.id.show);
        Bundle data = new Bundle();
        data.putSerializable("data", converCursorToList(cursor));
        //@SuppressWarnings("unchecked")
        list = (List<Map<String, String>>)data.getSerializable("data");
        adapter = new SimpleAdapter(MainActivity.this,
                list, R.layout.line
                , new String[] { Memos.Memo.TITLE, Memos.Memo.DATE, Memos.Memo.CONTEXT}
                , new int[] { R.id.et_one_title, R.id.et_one_date, R.id.et_one_context });
        listView.setAdapter(adapter);
    }

    private ArrayList<Map<String, String>> converCursorToList(Cursor cursor){
        ArrayList<Map<String, String>> result = new ArrayList<>();
        while (cursor.moveToNext()){
            Map<String, String> map = new HashMap<>();
            map.put(Memos.Memo.TITLE, cursor.getString(1));
            map.put(Memos.Memo.DATE, cursor.getString(2));
            map.put(Memos.Memo.CONTEXT, cursor.getString(3));
            result.add(map);
        }
        return result;
    }


}