package com.lxy.lxyappsy15;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.Window;
import android.widget.FrameLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Random;

public class MainActivity extends AppCompatActivity {

    private FrameLayout frameLayout;

    private String[] colors = new String[]{ "#ADCBFF", "#9DDDA0",  "#FFCEBE","#F3F3A4","#F6D0FF"};

    private int[] textIds = new int[] { R.id.text01, R.id.text02, R.id.text03,
            R.id.text04, R.id.text05 };

    private TextView[] views = new TextView[textIds.length];
    private int current = 0;
    private boolean flag=true;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_main);

        frameLayout = findViewById(R.id.frame);


        for (int i = 0; i < textIds.length; i++) {
            views[i] = (TextView) findViewById(textIds[i]);
        }
        new MyAsynTask().execute(2000);





    }

    class MyAsynTask extends AsyncTask<Integer,Integer,Integer> {

        @Override
        protected Integer doInBackground(Integer... params) {
            while (flag) {
                try {
                    Thread.sleep(params[0]);
                    publishProgress(views.length);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            return 1;
        }

        @Override
        protected void onPreExecute() {
            super.onPreExecute();

        }

        @Override
        protected void onProgressUpdate(Integer... values) {
            super.onProgressUpdate(values);
            //int p=pb.getMax()/10*values[0];
            //pb.setProgress(p);
            for (int i = 0; i < values[0]; i++) {
                views[i].setBackgroundColor(Color.parseColor(colors[(i + current) % colors.length]));
            }
            current = (current + 1) % colors.length;
        }

        @Override
        protected void onPostExecute(Integer s) {
            super.onPostExecute(s);
        }


    }
}