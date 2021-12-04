package cn.edu.bnuz.contentresolver;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentResolver;
import android.content.ContentValues;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    String newId;
    public final static String TAG="YlhAcitvity";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Button addData=(Button)findViewById(R.id.add_data);
        addData.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ContentResolver resolver;
                Uri uri=Uri.parse("content://cn.edu.bnuz.databasetest.provider/book");
                resolver=getContentResolver();
                ContentValues values=new ContentValues();
                values.put("name","A clash of kings");
                values.put("author","George Martin");
                values.put("pages",1040);
                values.put("price",22.85);
                Uri newUri=resolver.insert(uri,values);
                newId=newUri.getPathSegments().get(1);
                Toast.makeText(MainActivity.this,newId,Toast.LENGTH_SHORT).show();
                values.put("name","红楼梦");
                values.put("author","曹雪芹");
                values.put("pages",800);
                values.put("price",30);
                newUri=resolver.insert(uri,values);
                newId=newUri.getPathSegments().get(1);
                Toast.makeText(MainActivity.this,newId,Toast.LENGTH_SHORT).show();
            }
        });
        Button qureyData=(Button)findViewById(R.id.query_data);
        qureyData.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Uri uri=Uri.parse("content://cn.edu.bnuz.databasetest.provider/book");
                Cursor cursor= getContentResolver().query(uri, null, null,null, null);
                if(cursor!=null){
                    while(cursor.moveToNext()){
                        String name=cursor.getString(cursor.getColumnIndex("name"));
                        String author=cursor.getString(cursor.getColumnIndex("author"));
                        int pages=cursor.getInt(cursor.getColumnIndex("pages"));
                        double price=cursor.getDouble(cursor.getColumnIndex("price"));
                        Log.d(TAG,"book name is " +name);
                        Log.d(TAG,"book author is "+author);
                        Log.d(TAG,"book pages is "+pages);
                        Log.d(TAG, "book price is "+price);
                    }
                    cursor.close();
                }
            }
        });
        Button updataData=(Button)findViewById(R.id.updata_data);
        updataData.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Uri uri=Uri.parse("content://cn.edu.bnuz.databasetest.provider/book/"+newId);
                ContentValues values=new ContentValues();
                values.put("name","A Storm of Swords");
                values.put("pages",1216);
                values.put("price",24.05);
                getContentResolver().update(uri,values,null,null);
            }
        });
        Button deleteDate=(Button)findViewById(R.id.delete_data);
        deleteDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Uri uri=Uri.parse("content://cn.edu.bnuz.databasetest.provider/book");
                getContentResolver().delete(uri,null,null);
            }
        });
    }
}

