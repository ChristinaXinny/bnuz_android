package com.lxy.lxyapachelist;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.loopj.android.image.SmartImageView;

import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import okhttp3.Call;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class MainActivity extends AppCompatActivity {
    public ListView listView;
    List<NewsBean> beanList;


    // 获取到返回的json
    String responseData;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        sendRequestWithOkHttpText();

    }

    private void sendRequestWithOkHttpText() {
        OkHttpClient client = new OkHttpClient();
        Request request = new Request.Builder().url("http://10.20.161.22/htdocs/NewsInfo.json").build();
        client.newCall(request).enqueue(new okhttp3.Callback() {
            @Override
            public void onResponse(Call call, Response response) throws IOException {
                responseData = response.body().string();
                System.out.println(responseData);
                parseJSONWithGson(responseData);
                showResponse(responseData);
            }

            @Override
            public void onFailure(Call call, IOException e) {
                System.out.println("aa");
            }
        });
    }


    private void showResponse(final String response) {
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                listView = findViewById(R.id.lv_news);
                listView.setAdapter(new Mybaseadapter());
            }
        });
    }

    private void parseJSONWithGson(String jsonData) {
        Gson gson = new Gson();
        beanList = gson.fromJson(jsonData, new TypeToken<List<NewsBean>>() {
        }.getType());
    }


    //listview 的适配器
    public class Mybaseadapter extends BaseAdapter {

        @Override
        public int getCount() {
            return beanList.size();
        }

        @Override
        public Object getItem(int position) {
            return beanList.get(position);
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            ViewHolder viewHolder = null;
            if (convertView == null) {
                viewHolder = new ViewHolder();
                convertView = View.inflate(getApplicationContext(), R.layout.listview_item, null);
                viewHolder.sivIcon = convertView.findViewById(R.id.siv_icon);
                viewHolder.tvTitle = convertView.findViewById(R.id.tv_title);
                viewHolder.tvContent = convertView.findViewById(R.id.tv_content);
                viewHolder.tcType = convertView.findViewById(R.id.tv_type);
                convertView.setTag(viewHolder);

            } else {
                viewHolder = (ViewHolder) convertView.getTag();
            }
            NewsBean bean = (NewsBean) getItem(position);
            viewHolder.sivIcon.setImageUrl(bean.getIcon());
            viewHolder.tvTitle.setText(bean.getTitle());
            viewHolder.tvContent.setText(bean.getContent());
            int type = bean.getType();
            String ty = "";
            switch (type) {
                case 1:
                    ty = "评论:" + bean.getComment();
                    break;
                case 2:
                    viewHolder.tcType.setTextColor(Color.RED);
                    ty = "专题";
                    break;
                case 3:
                    viewHolder.tcType.setTextColor(Color.BLUE);
                    ty = "LIVE";
                    break;
            }
            viewHolder.tcType.setText(ty);
            return convertView;
        }

    }

    final static class ViewHolder {
        private SmartImageView sivIcon;
        private TextView tvTitle;    // 题目
        private TextView tvContent;  // 内容
        private TextView tcType;     // 类型
    }


}