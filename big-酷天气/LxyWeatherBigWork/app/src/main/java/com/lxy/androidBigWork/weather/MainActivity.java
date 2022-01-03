package com.lxy.androidBigWork.weather;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.graphics.Paint;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.AsyncTask;
import android.preference.PreferenceManager;
import android.provider.Settings;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RelativeLayout;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;


import com.google.gson.Gson;
import com.lxy.androidBigWork.weather.Request.CityForecast;
import com.lxy.androidBigWork.weather.service.AutoUpdateService;
import com.lxy.androidBigWork.weather.util.GetData;
import com.lxy.androidBigWork.weather.util.HttpUtil;
import com.lxy.androidBigWork.weather.util.Utility;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import interfaces.heweather.com.interfacesmodule.bean.weather.Weather;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;


public class MainActivity extends AppCompatActivity implements RadioGroup.OnCheckedChangeListener,
        ViewPager.OnPageChangeListener {


    private Button aSwitch;
    public SwipeRefreshLayout swipeRefresh;

    private RadioGroup rg_tab_bar;
    private RadioButton rb0;
    private RadioButton rb1;
    private RadioButton rb2;
    private ViewPager vpager;
    private TextView textView;
    private TextView time0, time1, time2, time3, time4, time5, time6, time7;

    private MyFragmentPagerAdapter mAdapter;
    private List<Fragment> fragments;

    // 页面切换
    public static final int PAGE_ONE = 0;
    public static final int PAGE_TWO = 1;
    public static final int PAGE_THREE = 2;

    // 背景更改
    public LinearLayout main_bg;
    private Button btn_image_main, btn_color_main;
    private int[] bg_list = new int[]{
            R.drawable.bgpic3, R.drawable.bgpic4, R.drawable.bgpic5, R.drawable.bgpic31, R.drawable.bgpic32, R.drawable.bgpic33, R.drawable.bgpic34,
            R.drawable.bgpic11, R.drawable.bgpic30, R.drawable.bgpic13, R.drawable.bgpic14, R.drawable.bgpic15, R.drawable.bgpic16, R.drawable.bgpic17, R.drawable.bgpic18, R.drawable.bgpic19, R.drawable.bgpic20,
            R.drawable.bgpic21, R.drawable.bgpic22, R.drawable.bgpic35, R.drawable.bgpic24, R.drawable.bgpic25, R.drawable.bgpic26, R.drawable.bgpic27, R.drawable.bgpic28, R.drawable.bgpic29,
    };
    private String[] cl_list = new String[]{ "#ADCBFF", "#9DDDA0",  "#FFCEBE","#F3F3A4","#F6D0FF"};
    private int curr_bg_index = 12;
    private int curr_cl_index = 0;
    private int curr_sw_count = 0;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        if (!isNetworkConnected()) {
            showDialog();
        }
        initObj();

        //
        Fragment1 f1 = new Fragment1();
        Fragment2 f2 = new Fragment2();
        Fragment3 f3 = new Fragment3();
        fragments = new ArrayList<>();
        bindViews();
        fragments.add(f1);
        fragments.add(f2);
        fragments.add(f3);
        mAdapter = new MyFragmentPagerAdapter(getSupportFragmentManager(), fragments);
        //可以支持左右滑动
        vpager = findViewById(R.id.vpager);
        vpager.setAdapter(mAdapter);
        vpager.addOnPageChangeListener(this);
        // 一开始 加载界面 是中间的界面
        rb1.setChecked(true);
        vpager.setCurrentItem(1);




        btn_image_main.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                System.out.println( "curr_bg_indexcurr_bg_index ： " + curr_bg_index);
                main_bg.setBackgroundDrawable(getResources().getDrawable(bg_list[curr_bg_index]));
                curr_bg_index++;
                if(curr_bg_index >= bg_list.length){
                    curr_bg_index = 0;
                }
            }
        });


        btn_color_main.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                main_bg.setBackgroundColor(Color.parseColor(cl_list[curr_cl_index]));
                curr_cl_index++;
                System.out.println("curr_bg_indexcurr_bg_index ：" + curr_cl_index );
                if(curr_cl_index >= cl_list.length){
                    curr_cl_index = 0;
                }
            }
        });

        aSwitch.setOnClickListener(new View.OnClickListener() {
            AlertDialog mDialog;
            @Override
            public void onClick(View v) {
                System.out.println("curr_sw_countcurr_sw_count"+curr_sw_count);
                curr_sw_count++;
                if(curr_sw_count%2!=0){
                    aSwitch.setBackgroundDrawable(getResources().getDrawable(R.drawable.switch_true));
                    final EditText inputServer = new EditText(MainActivity.this);
                    //mDialog.getButton(0).setTextColor(Color.BLACK);
                    //mDialog.getButton(1).setTextColor(Color.BLACK);
                    mDialog = new AlertDialog.Builder(MainActivity.this)
                            .setTitle("打开自动更新天气？")//设置对话框标题
                            .setMessage("设置几小时进行更新？？？？？")
                            .setIcon(android.R.drawable.ic_dialog_info)
                            .setView(inputServer)
                            .setPositiveButton("是", new DialogInterface.OnClickListener() {//添加确定按钮

                                @Override
                                public void onClick(DialogInterface dialog, int which) {//确定按钮的响应
                                    String time = inputServer.getText().toString();
                                    String showText = "更新成功 将在" + time + "小时后更新";
                                    changeWeather();
                                    Toast.makeText(MainActivity.this, showText, Toast.LENGTH_SHORT).show();

                                }
                            })
                            .setNegativeButton("否", new DialogInterface.OnClickListener() {//添加返回按钮

                                @Override
                                public void onClick(DialogInterface dialog, int which) {//响应事件

                                    Toast.makeText(MainActivity.this, "", Toast.LENGTH_SHORT).show();

                                }

                            })
                            .show();//在按键响应事件中显示此对话框


                }
                else {
                    Toast.makeText(MainActivity.this, "取消更新", Toast.LENGTH_SHORT).show();
                    aSwitch.setBackgroundDrawable(getResources().getDrawable(R.drawable.switch_false));

                }


            }
        });



        Intent intent = new Intent(this, AutoUpdateService.class);
        startService(intent);

        swipeRefresh = (SwipeRefreshLayout) findViewById(R.id.swipe_refresh);
        swipeRefresh.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                requestWeather("101280604");

            }
        });

    }

    public void changeWeather(){
        String string_hourly_weather_forcast = null;
        try {
            string_hourly_weather_forcast = GetData.getJson("https://free-api.heweather.com/s6/weather/hourly?location=tring_city&key=2d7b37b322a04de1ab17fca5f2e0f0ea");

            Gson gson = new Gson();
            CityForecast hourlyWeatherForecast = gson.fromJson(string_hourly_weather_forcast, CityForecast.class);
            time0 = findViewById(R.id.time0);
            time1 = findViewById(R.id.time1);
            time2 = findViewById(R.id.time2);
            time3 = findViewById(R.id.time3);
            time4 = findViewById(R.id.time4);
            time5 = findViewById(R.id.time5);
            time6 = findViewById(R.id.time6);
            time7 = findViewById(R.id.time7);
            //  获取每小时的时间间断
            String tm;
            String[] s;
            tm = hourlyWeatherForecast.getLocation().get(0).getAdm1();
            s = tm.split(" ");
            time0.setText(s[1]);
            tm = hourlyWeatherForecast.getLocation().get(1).getAdm1();
            s = tm.split(" ");
            time1.setText(s[1]);
            tm = hourlyWeatherForecast.getLocation().get(2).getAdm1();
            s = tm.split(" ");
            time2.setText(s[1]);
            tm = hourlyWeatherForecast.getLocation().get(3).getAdm1();
            s = tm.split(" ");
            time3.setText(s[1]);
            tm = hourlyWeatherForecast.getLocation().get(4).getAdm1();
            s = tm.split(" ");
            time4.setText(s[1]);
            tm = hourlyWeatherForecast.getLocation().get(5).getAdm1();
            s = tm.split(" ");
            time5.setText(s[1]);
            tm = hourlyWeatherForecast.getLocation().get(6).getAdm1();
            s = tm.split(" ");
            time6.setText(s[1]);
            tm = hourlyWeatherForecast.getLocation().get(7).getAdm1();
            s = tm.split(" ");
            time7.setText(s[1]);
        } catch (Exception e) {
            e.printStackTrace();
        }


    }







    public void requestWeather(final String weatherId) {
        String weatherUrl = "http://guolin.tech/api/weather?cityid=" + weatherId + "&key=bc0418b57b2d4918819d3974ac1285d9";
        HttpUtil.sendOkHttpRequest(weatherUrl, new Callback() {
            @Override
            public void onResponse(Call call, Response response) throws IOException {
                final String responseText = response.body().string();
                final Weather weather = Utility.handleWeatherResponse(responseText);
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        if (1 == 1 ) {
                            System.out.println( "curr_bg_indexcurr_bg_index ： " + curr_bg_index);

                            main_bg.setBackgroundDrawable(getResources().getDrawable(bg_list[curr_bg_index]));
                            curr_bg_index++;
                            if(curr_bg_index >= bg_list.length){
                                curr_bg_index = 0;
                            }
                            Toast.makeText(MainActivity.this, "获取天气信息成功", Toast.LENGTH_SHORT).show();
                        } else {
                            Toast.makeText(MainActivity.this, "获取天气信息失败", Toast.LENGTH_SHORT).show();
                        }
                        swipeRefresh.setRefreshing(false);
                    }
                });
            }

            @Override
            public void onFailure(Call call, IOException e) {
                e.printStackTrace();
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        System.out.println( "curr_bg_indexcurr_bg_index ： " + curr_bg_index);
                        main_bg.setBackgroundDrawable(getResources().getDrawable(bg_list[curr_bg_index]));
                        curr_bg_index++;
                        if(curr_bg_index >= bg_list.length){
                            curr_bg_index = 0;
                        }
                        Toast.makeText(MainActivity.this, "获取天气信息成功", Toast.LENGTH_SHORT).show();
                        swipeRefresh.setRefreshing(false);
                    }
                });
            }
        });
    }






    public void initObj() { //初始化方法

    }


    // 绑定界面和对象
    private void bindViews() {
        // 页面切换
        rg_tab_bar = findViewById(R.id.rg_tab_bar);
        rb0 = findViewById(R.id.rb0);
        rb1 = findViewById(R.id.rb1);
        rb2 = findViewById(R.id.rb2);
        rg_tab_bar.setOnCheckedChangeListener(this);
        textView = findViewById(R.id.textView);

        btn_image_main = (Button) findViewById(R.id.btn_image_main);
        btn_color_main = (Button) findViewById(R.id.btn_color_main);
        main_bg = (LinearLayout) findViewById(R.id.main_bg);

        aSwitch = findViewById(R.id.sw);



    }

    // 下面的点击跳转
    @Override
    public void onCheckedChanged(RadioGroup group, int checkedId) {
        switch (checkedId) {
            case R.id.rb0:
                vpager.setCurrentItem(PAGE_ONE);
                //textView.setText("多天预报");
                break;
            case R.id.rb1:
                vpager.setCurrentItem(PAGE_TWO);
                //textView.setText("今日详情");
                break;
            case R.id.rb2:
                vpager.setCurrentItem(PAGE_THREE);
                //textView.setText("选择你的城市");
                break;
        }
    }

    @Override
    public void onPageScrolled(int i, float v, int i1) {

    }

    @Override
    public void onPageSelected(int i) {

    }

    //
    @Override
    public void onPageScrollStateChanged(int state) {
        //state的状态有三个，0表示什么都没做，1正在滑动，2滑动完毕
        if (state == 2) {
            switch (vpager.getCurrentItem()) {
                case PAGE_ONE:
                    rb0.setChecked(true);
                    break;
                case PAGE_TWO:
                    rb1.setChecked(true);
                    break;
                case PAGE_THREE:
                    rb2.setChecked(true);
                    break;
            }
        }
    }

    // 判断是否链接网
    private boolean isNetworkConnected() {
        ConnectivityManager manager = (ConnectivityManager) getSystemService(CONNECTIVITY_SERVICE);
        NetworkInfo info = manager.getActiveNetworkInfo();
        return (info != null && info.isConnected());
    }

    // 联网设置
    private void showDialog() {
        View view = LayoutInflater.from(this).inflate(R.layout.my_dialog, null, false);
        final AlertDialog dialog = new AlertDialog.Builder(this).setView(view).create();
        getWindow().setBackgroundDrawableResource(android.R.color.transparent);
        Button btn_setting = view.findViewById(R.id.btn_setting);
        Button btn_cancel = view.findViewById(R.id.btn_cancel);

        btn_setting.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.setAction(Settings.ACTION_DATA_ROAMING_SETTINGS);
                startActivityForResult(intent, 0);
                finish();
            }
        });

        btn_cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        dialog.setCancelable(false);
        dialog.show();
    }


}
