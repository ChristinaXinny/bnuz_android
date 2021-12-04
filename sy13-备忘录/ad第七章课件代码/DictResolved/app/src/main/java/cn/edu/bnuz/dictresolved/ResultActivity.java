package cn.edu.bnuz.dictresolved;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ListView;
import android.widget.SimpleAdapter;

import androidx.appcompat.app.AppCompatActivity;

import java.util.List;
import java.util.Map;

public class ResultActivity extends AppCompatActivity {
    @Override
    public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.popup);
        ListView listView = (ListView) findViewById(R.id.show);
        Intent intent = getIntent();
        Bundle data = intent.getExtras();
       @SuppressWarnings("unchecked")
        List<Map<String, String>> list = (List<Map<String, String>>)data.getSerializable("data");
        SimpleAdapter adapter = new SimpleAdapter(ResultActivity.this,
                list, R.layout.line
                , new String[] { Words.Word.WORD, Words.Word.DETAIL }
                , new int[] { R.id.word, R.id.detail });
        listView.setAdapter(adapter);
    }
}

