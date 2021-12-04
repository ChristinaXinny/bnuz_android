package cn.edu.bnuz.dictresolved;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentResolver;
import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class MainActivity extends AppCompatActivity{
    ContentResolver contentResolver;
    Button insert = null;
    Button search = null;
    @Override
    public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);




        // 获取系统的ContentResolver对象
        contentResolver = getContentResolver();
        insert = (Button) findViewById(R.id.insert);
        search = (Button) findViewById(R.id.search);
        // 为insert按钮的单击事件绑定事件监听器
        insert.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View source){
                String word = ((EditText) findViewById(R.id.word)).getText().toString();
                String detail = ((EditText) findViewById(R.id.detail)).getText().toString();
                ContentValues values = new ContentValues();
                values.put(Words.Word.WORD, word);
                values.put(Words.Word.DETAIL, detail);
                contentResolver.insert(Words.Word.DICT_CONTENT_URI, values);
                Toast.makeText(MainActivity.this, "添加生词成功！"
                        , Toast.LENGTH_SHORT).show();
            }
        });


        search.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View source)
            {
                String key = ((EditText) findViewById(R.id.key))
                        .getText().toString();
                Cursor cursor = contentResolver.query(
                        Words.Word.DICT_CONTENT_URI, null,
                        "word like ? or detail like ?", new String[] {
                                "%" + key + "%", "%" + key + "%" }, null);

                Bundle data = new Bundle();
                data.putSerializable("data", converCursorToList(cursor));
                // 创建一个Intent
                Intent intent = new Intent(MainActivity.this,ResultActivity.class);
                intent.putExtras(data);
                // 启动Activity
                startActivity(intent);
            }
        });
    }


    private ArrayList<Map<String, String>> converCursorToList(Cursor cursor){
        ArrayList<Map<String, String>> result = new ArrayList<>();
        while (cursor.moveToNext()){
            Map<String, String> map = new HashMap<>();
            map.put(Words.Word.WORD, cursor.getString(1));
            map.put(Words.Word.DETAIL, cursor.getString(2));
            result.add(map);
        }
        return result;
    }

    public void init(){
        String key = ((EditText) findViewById(R.id.key))
                .getText().toString();
        Cursor cursor = contentResolver.query(
                Words.Word.DICT_CONTENT_URI, null,
                "word like ? or detail like ?", new String[] {
                        "%" + key + "%", "%" + key + "%" }, null);

    }
}

