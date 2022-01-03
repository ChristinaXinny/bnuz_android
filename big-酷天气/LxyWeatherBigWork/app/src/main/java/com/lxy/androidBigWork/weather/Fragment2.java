package com.lxy.androidBigWork.weather;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.database.DefaultDatabaseErrorHandler;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.lxy.androidBigWork.weather.Request.CityForecast;
import com.lxy.androidBigWork.weather.Request.HourlyWeatherForecast;
import com.lxy.androidBigWork.weather.Request.LifestyleForecast;
import com.google.gson.Gson;
import com.lxy.androidBigWork.weather.Request.NowWeatherForecast;
import com.lxy.androidBigWork.weather.mycity.CityOperator;
import com.lxy.androidBigWork.weather.util.GetData;
import com.lxy.androidBigWork.weather.util.HttpUtil;
import com.lxy.androidBigWork.weather.util.Utility;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import interfaces.heweather.com.interfacesmodule.bean.weather.Weather;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;


public class Fragment2 extends Fragment {

    // 背景更改
    private String nowWStr;

    private int[] bg_list = new int[]{
            R.drawable.bgpic3, R.drawable.bgpic4, R.drawable.bgpic5, R.drawable.bgpic31, R.drawable.bgpic32, R.drawable.bgpic33, R.drawable.bgpic34,
            R.drawable.bgpic11, R.drawable.bgpic30, R.drawable.bgpic13, R.drawable.bgpic14, R.drawable.bgpic15, R.drawable.bgpic16, R.drawable.bgpic17, R.drawable.bgpic18, R.drawable.bgpic19, R.drawable.bgpic20,
            R.drawable.bgpic21, R.drawable.bgpic22, R.drawable.bgpic35, R.drawable.bgpic24, R.drawable.bgpic25, R.drawable.bgpic26, R.drawable.bgpic27, R.drawable.bgpic28, R.drawable.bgpic29,
    };
    private String[] cl_list = new String[]{ "#ADCBFF", "#9DDDA0",  "#FFCEBE","#F3F3A4","#F6D0FF"};
    private int curr_bg_index = 12;
    private int curr_cl_index = 0;



    // 第一个框框的
    private TextView city_now_f2, degree_now_f2, info_now_f2;
    private String now_text;


    // 第二个框框的
    private TextView time0, time1, time2, time3, time4, time5, time6, time7;
    private TextView tmp0, tmp1, tmp2, tmp3, tmp4, tmp5, tmp6, tmp7;
    private TextView cond_txt0, cond_txt1, cond_txt2, cond_txt3, cond_txt4, cond_txt5, cond_txt6, cond_txt7;
    private TextView wind_dir0, wind_dir1, wind_dir2, wind_dir3, wind_dir4, wind_dir5, wind_dir6, wind_dir7;
    private TextView wind_sc0, wind_sc1, wind_sc2, wind_sc3, wind_sc4, wind_sc5, wind_sc6, wind_sc7;

    // 第三个框框的
    private TextView comf_type, comf_txt;
    private TextView drsg_type, drsg_txt;
    private TextView flu_type, flu_txt;
    private TextView sport_type, sport_txt;
    private TextView trav_type, trav_txt;
    private TextView uv_type, uv_txt;
    private TextView cw_type, cw_txt;
    private TextView air_type, air_txt;


    // 时间
    private TextView tv_update;

    // 城市
    private TextView tv_refresh;



    //接口绑定获取后
    private HourlyWeatherForecast hourlyWeatherForecast;
    private LifestyleForecast lifestyleForecast;
    private NowWeatherForecast nowWeatherForecast;
    private CityForecast cityForecast;
    private volatile String string_hourly_weather_forcast;
    private volatile String string_Lifestyle_forcast;
    private volatile String string_now_forcast;
    private volatile String getString_city_forecast;


    // 省、市、区
    String now_admin_area, now_parent_city, now_location;
    String string_city, string_city_id;
    View view;

    @SuppressLint("HandlerLeak")
    private Handler handler = new Handler() {
        public void handleMessage(android.os.Message msg) {
            switch (msg.what) {
                case 0x001:
                    parseData();
                    break;
                default:
                    break;
            }
        }
    };


    public Fragment2() {

    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment2, container, false);
        bindView();
        getData();
        //main_bg.setBackgroundDrawable(getResources().getDrawable(getWeatherText(now_text)));
        //int weatherText = getWeatherText(now_text);

        return view;
    }




    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);
        if (isVisibleToUser) {
            getData();
        }
    }






    // 获取到当前查询城市的名字
    String getCityName() {
        String[] liCt = string_city.split(",");
        now_location = liCt[0];
        now_parent_city = liCt[1];
        String now_city =  now_parent_city +" - " + now_location;
        System.out.println("now_citynow_citynow_citynow_city" + now_city);
        return now_city;
    }


    int getWeatherText(String ww){
        switch (ww){
            case "晴" :
                return bg_list[0];
            case "多云" :
            case "少云" :
            case "晴间多云" :
            case "阴" :
                return bg_list[1];
            case "雷阵雨" :
            case "强雷阵雨" :
            case "雷阵雨伴有冰雹" :
            case "小雨" :
            case "中雨" :
            case "大雨" :
            case "极端降雨" :
            case "毛毛雨/细雨" :
            case "暴雨" :
            case "大暴雨" :
            case "特大暴雨" :
            case "冻雨" :
            case "小到中雨" :
            case "中到大雨" :
            case "大到暴雨" :
            case "暴雨到大暴雨" :
            case "大暴雨到特大暴雨" :
            case "阵雨" :
            case "强阵雨" :
            case "雨" :
                return bg_list[2];
            case "小雪" :
            case "中雪" :
            case "大雪" :
            case "暴雪" :
            case "雨夹雪" :
            case "雨雪天气" :
            case "小到中雪" :
            case "中到大雪" :
            case "大到暴雪" :
            case "阵雨夹雪" :
            case "阵雪" :
            case "雪" :
                return bg_list[3];
            case "薄雾" :
            case "雾" :
            case "霾" :
                return bg_list[4];
            case "扬沙" :
            case "浮尘" :
            case "沙尘暴" :
            case "强沙尘暴" :
                return bg_list[5];
            case "浓雾" :
            case "强浓雾" :
            case "中度霾" :
            case "重度霾" :
            case "严重霾" :
            case "大雾" :
            case "特强浓雾" :
            case "新月" :
            case "蛾眉月" :
            case "上弦月" :
            case "盈凸月" :
            case "满月" :
            case "亏凸月" :
            case "下弦月" :
            case "残月" :
                return bg_list[6];
            case "热" :
            case "冷":
                return bg_list[7];
            default:
                throw new IllegalStateException("Unexpected value: " + ww);
        }

    }




    // 绑定值 全部的findById....
    void bindView() {


        //btn_image_main = main_bg.findViewById(R.id.btn_image_main);
        //btn_color_main = main_bg.findViewById(R.id.btn_color_main);
        //main_bg = main_bg.findViewById(R.id.main_bg);


        // 第一个框框
        city_now_f2 = view.findViewById(R.id.city_now_f2);
        degree_now_f2 = view.findViewById(R.id.degree_now_f2);
        info_now_f2 = view.findViewById(R.id.info_now_f2);

        // 第二个框框
        tv_refresh = view.findViewById(R.id.tv_refresh);
        tv_update = view.findViewById(R.id.tv_update);
        time0 = view.findViewById(R.id.time0);
        time1 = view.findViewById(R.id.time1);
        time2 = view.findViewById(R.id.time2);
        time3 = view.findViewById(R.id.time3);
        time4 = view.findViewById(R.id.time4);
        time5 = view.findViewById(R.id.time5);
        time6 = view.findViewById(R.id.time6);
        time7 = view.findViewById(R.id.time7);

        tmp0 = view.findViewById(R.id.tmp0);
        tmp1 = view.findViewById(R.id.tmp1);
        tmp2 = view.findViewById(R.id.tmp2);
        tmp3 = view.findViewById(R.id.tmp3);
        tmp4 = view.findViewById(R.id.tmp4);
        tmp5 = view.findViewById(R.id.tmp5);
        tmp6 = view.findViewById(R.id.tmp6);
        tmp7 = view.findViewById(R.id.tmp7);

        cond_txt0 = view.findViewById(R.id.cond_txt0);
        cond_txt1 = view.findViewById(R.id.cond_txt1);
        cond_txt2 = view.findViewById(R.id.cond_txt2);
        cond_txt3 = view.findViewById(R.id.cond_txt3);
        cond_txt4 = view.findViewById(R.id.cond_txt4);
        cond_txt5 = view.findViewById(R.id.cond_txt5);
        cond_txt6 = view.findViewById(R.id.cond_txt6);
        cond_txt7 = view.findViewById(R.id.cond_txt7);

        wind_dir0 = view.findViewById(R.id.wind_dir0);
        wind_dir1 = view.findViewById(R.id.wind_dir1);
        wind_dir2 = view.findViewById(R.id.wind_dir2);
        wind_dir3 = view.findViewById(R.id.wind_dir3);
        wind_dir4 = view.findViewById(R.id.wind_dir4);
        wind_dir5 = view.findViewById(R.id.wind_dir5);
        wind_dir6 = view.findViewById(R.id.wind_dir6);
        wind_dir7 = view.findViewById(R.id.wind_dir7);

        wind_sc0 = view.findViewById(R.id.wind_sc0);
        wind_sc1 = view.findViewById(R.id.wind_sc1);
        wind_sc2 = view.findViewById(R.id.wind_sc2);
        wind_sc3 = view.findViewById(R.id.wind_sc3);
        wind_sc4 = view.findViewById(R.id.wind_sc4);
        wind_sc5 = view.findViewById(R.id.wind_sc5);
        wind_sc6 = view.findViewById(R.id.wind_sc6);
        wind_sc7 = view.findViewById(R.id.wind_sc7);

        // 第三个框框
        comf_type = view.findViewById(R.id.comf_type);
        comf_txt = view.findViewById(R.id.comf_txt);
        drsg_type = view.findViewById(R.id.drsg_type);
        drsg_txt = view.findViewById(R.id.drsg_txt);
        flu_type = view.findViewById(R.id.flu_type);
        flu_txt = view.findViewById(R.id.flu_txt);
        sport_type = view.findViewById(R.id.sport_type);
        sport_txt = view.findViewById(R.id.sport_txt);
        trav_type = view.findViewById(R.id.trav_type);
        trav_txt = view.findViewById(R.id.trav_txt);
        uv_type = view.findViewById(R.id.uv_type);
        uv_txt = view.findViewById(R.id.uv_txt);
        cw_type = view.findViewById(R.id.cw_type);
        cw_txt = view.findViewById(R.id.cw_txt);
        air_type = view.findViewById(R.id.air_type);
        air_txt = view.findViewById(R.id.air_txt);
    }


    // 接口获取的信息set到界面显示
    void setSomeTip() {

        //  获取每小时的时间间断
        String tm;
        String[] s;
        tm = hourlyWeatherForecast.getHeWeather6().get(0).getHourly().get(0).getTime();
        s = tm.split(" ");
        time0.setText(s[1]);
        tm = hourlyWeatherForecast.getHeWeather6().get(0).getHourly().get(1).getTime();
        s = tm.split(" ");
        time1.setText(s[1]);
        tm = hourlyWeatherForecast.getHeWeather6().get(0).getHourly().get(2).getTime();
        s = tm.split(" ");
        time2.setText(s[1]);
        tm = hourlyWeatherForecast.getHeWeather6().get(0).getHourly().get(3).getTime();
        s = tm.split(" ");
        time3.setText(s[1]);
        tm = hourlyWeatherForecast.getHeWeather6().get(0).getHourly().get(4).getTime();
        s = tm.split(" ");
        time4.setText(s[1]);
        tm = hourlyWeatherForecast.getHeWeather6().get(0).getHourly().get(5).getTime();
        s = tm.split(" ");
        time5.setText(s[1]);
        tm = hourlyWeatherForecast.getHeWeather6().get(0).getHourly().get(6).getTime();
        s = tm.split(" ");
        time6.setText(s[1]);
        tm = hourlyWeatherForecast.getHeWeather6().get(0).getHourly().get(7).getTime();
        s = tm.split(" ");
        time7.setText(s[1]);

        // 温度后加°C
        String degree = "℃";

        // 获取城市id
        System.out.println("string_city_idstring_city_idstring_city_id"+string_city_id);
        city_now_f2.setText(getCityName());
        degree_now_f2.setText(nowWeatherForecast.getNow().getTemp()+ degree);
        now_text = nowWeatherForecast.getNow().getText();
        String now_windDir = nowWeatherForecast.getNow().getWindDir();
        String now_windSpeed = nowWeatherForecast.getNow().getWindScale() + "级";
        String now_detail = now_text + " " +now_windDir +" " + now_windSpeed ;
        info_now_f2.setText(now_detail);
        //System.out.println("string_citystring_citystring_citystring_city" + string_city);

        // 绑定到界面的元素 获取每小时的时间的温度
        tmp0.setText(hourlyWeatherForecast.getHeWeather6().get(0).getHourly().get(0).getTmp() + degree);
        tmp1.setText(hourlyWeatherForecast.getHeWeather6().get(0).getHourly().get(1).getTmp() + degree);
        tmp2.setText(hourlyWeatherForecast.getHeWeather6().get(0).getHourly().get(2).getTmp() + degree);
        tmp3.setText(hourlyWeatherForecast.getHeWeather6().get(0).getHourly().get(3).getTmp() + degree);
        tmp4.setText(hourlyWeatherForecast.getHeWeather6().get(0).getHourly().get(4).getTmp() + degree);
        tmp5.setText(hourlyWeatherForecast.getHeWeather6().get(0).getHourly().get(5).getTmp() + degree);
        tmp6.setText(hourlyWeatherForecast.getHeWeather6().get(0).getHourly().get(6).getTmp() + degree);
        tmp7.setText(hourlyWeatherForecast.getHeWeather6().get(0).getHourly().get(7).getTmp() + degree);

        // 获取到云量
        cond_txt0.setText(hourlyWeatherForecast.getHeWeather6().get(0).getHourly().get(0).getCond_txt());
        cond_txt1.setText(hourlyWeatherForecast.getHeWeather6().get(0).getHourly().get(1).getCond_txt());
        cond_txt2.setText(hourlyWeatherForecast.getHeWeather6().get(0).getHourly().get(2).getCond_txt());
        cond_txt3.setText(hourlyWeatherForecast.getHeWeather6().get(0).getHourly().get(3).getCond_txt());
        cond_txt4.setText(hourlyWeatherForecast.getHeWeather6().get(0).getHourly().get(4).getCond_txt());
        cond_txt5.setText(hourlyWeatherForecast.getHeWeather6().get(0).getHourly().get(5).getCond_txt());
        cond_txt6.setText(hourlyWeatherForecast.getHeWeather6().get(0).getHourly().get(6).getCond_txt());
        cond_txt7.setText(hourlyWeatherForecast.getHeWeather6().get(0).getHourly().get(7).getCond_txt());

        // 风向
        wind_dir0.setText(hourlyWeatherForecast.getHeWeather6().get(0).getHourly().get(0).getWind_dir());
        wind_dir1.setText(hourlyWeatherForecast.getHeWeather6().get(0).getHourly().get(1).getWind_dir());
        wind_dir2.setText(hourlyWeatherForecast.getHeWeather6().get(0).getHourly().get(2).getWind_dir());
        wind_dir3.setText(hourlyWeatherForecast.getHeWeather6().get(0).getHourly().get(3).getWind_dir());
        wind_dir4.setText(hourlyWeatherForecast.getHeWeather6().get(0).getHourly().get(4).getWind_dir());
        wind_dir5.setText(hourlyWeatherForecast.getHeWeather6().get(0).getHourly().get(5).getWind_dir());
        wind_dir6.setText(hourlyWeatherForecast.getHeWeather6().get(0).getHourly().get(6).getWind_dir());
        wind_dir7.setText(hourlyWeatherForecast.getHeWeather6().get(0).getHourly().get(7).getWind_dir());

        //风力等级
        wind_sc0.setText(hourlyWeatherForecast.getHeWeather6().get(0).getHourly().get(0).getWind_sc() + "级");
        wind_sc1.setText(hourlyWeatherForecast.getHeWeather6().get(0).getHourly().get(1).getWind_sc() + "级");
        wind_sc2.setText(hourlyWeatherForecast.getHeWeather6().get(0).getHourly().get(2).getWind_sc() + "级");
        wind_sc3.setText(hourlyWeatherForecast.getHeWeather6().get(0).getHourly().get(3).getWind_sc() + "级");
        wind_sc4.setText(hourlyWeatherForecast.getHeWeather6().get(0).getHourly().get(4).getWind_sc() + "级");
        wind_sc5.setText(hourlyWeatherForecast.getHeWeather6().get(0).getHourly().get(5).getWind_sc() + "级");
        wind_sc6.setText(hourlyWeatherForecast.getHeWeather6().get(0).getHourly().get(6).getWind_sc() + "级");
        wind_sc7.setText(hourlyWeatherForecast.getHeWeather6().get(0).getHourly().get(7).getWind_sc() + "级");


        // 天气生活指数
        comf_type.setText(lifestyleForecast.getHeWeather6().get(0).getLifestyle().get(0).getBrf());
        comf_txt.setText(lifestyleForecast.getHeWeather6().get(0).getLifestyle().get(0).getTxt());
        drsg_type.setText(lifestyleForecast.getHeWeather6().get(0).getLifestyle().get(1).getBrf());
        drsg_txt.setText(lifestyleForecast.getHeWeather6().get(0).getLifestyle().get(1).getTxt());
        flu_type.setText(lifestyleForecast.getHeWeather6().get(0).getLifestyle().get(2).getBrf());
        flu_txt.setText(lifestyleForecast.getHeWeather6().get(0).getLifestyle().get(2).getTxt());
        sport_type.setText(lifestyleForecast.getHeWeather6().get(0).getLifestyle().get(3).getBrf());
        sport_txt.setText(lifestyleForecast.getHeWeather6().get(0).getLifestyle().get(3).getTxt());
        trav_type.setText(lifestyleForecast.getHeWeather6().get(0).getLifestyle().get(4).getBrf());
        trav_txt.setText(lifestyleForecast.getHeWeather6().get(0).getLifestyle().get(4).getTxt());
        uv_type.setText(lifestyleForecast.getHeWeather6().get(0).getLifestyle().get(5).getBrf());
        uv_txt.setText(lifestyleForecast.getHeWeather6().get(0).getLifestyle().get(5).getTxt());
        cw_type.setText(lifestyleForecast.getHeWeather6().get(0).getLifestyle().get(6).getBrf());
        cw_txt.setText(lifestyleForecast.getHeWeather6().get(0).getLifestyle().get(6).getTxt());
        air_type.setText(lifestyleForecast.getHeWeather6().get(0).getLifestyle().get(7).getBrf());
        air_txt.setText(lifestyleForecast.getHeWeather6().get(0).getLifestyle().get(7).getTxt());
    }


    // 第二个框框 设置内容
    void draw() {
        if (hourlyWeatherForecast == null || lifestyleForecast == null || nowWeatherForecast == null)
            return;
        try {
            SimpleDateFormat sd1 = new SimpleDateFormat("yyyy-MM-dd HH:mm");
            SimpleDateFormat sd2 = new SimpleDateFormat("HH:mm");
            Date upd = sd1.parse(hourlyWeatherForecast.getHeWeather6().get(0).getUpdate().getLoc());
            tv_update.setText(sd2.format(upd) + " 更新");

            CityOperator cityOperator = new CityOperator(getContext());
            String string_city1 = cityOperator.getIsSelectCity().toString2();
            tv_refresh.setText("24小时预报");

        } catch (ParseException e) {
            e.printStackTrace();
        }
        setSomeTip();
    }



    // 获取接口信息 字符串
    void getData() {
        new Thread() {
            public void run() {
                try {
                    CityOperator cityOperator = new CityOperator(getContext());
                    string_city = cityOperator.getIsSelectCity().toString2();
                    getCityName();
                    getString_city_forecast = GetData.getJson("https://geoapi.qweather.com/v2/city/lookup?location=" + now_location + "&adm+=" + now_parent_city  + "&key=2d7b37b322a04de1ab17fca5f2e0f0ea");
                    string_hourly_weather_forcast = GetData.getJson("https://free-api.heweather.com/s6/weather/hourly?location=" + string_city + "&key=2d7b37b322a04de1ab17fca5f2e0f0ea");
                    string_Lifestyle_forcast = GetData.getJson("https://free-api.heweather.com/s6/weather/lifestyle?location=" + string_city + "&key=2d7b37b322a04de1ab17fca5f2e0f0ea");
                    string_city_id = cityForecast.getLocation().get(0).getId();
                    string_now_forcast = GetData.getJson("https://devapi.qweather.com/v7/weather/now?location=" + string_city_id + "&key=2d7b37b322a04de1ab17fca5f2e0f0ea");
                    System.out.println(string_now_forcast);
                    System.out.println(string_now_forcast);
                } catch (Exception e) {
                    e.printStackTrace();
                }
                handler.sendEmptyMessage(0x001);
            }

            ;
        }.start();
    }

    // 映射到对应类中
    void parseData() {
        Gson gson = new Gson();
        cityForecast = gson.fromJson(getString_city_forecast, CityForecast.class);
        hourlyWeatherForecast = gson.fromJson(string_hourly_weather_forcast, HourlyWeatherForecast.class);
        lifestyleForecast = gson.fromJson(string_Lifestyle_forcast, LifestyleForecast.class);
        nowWeatherForecast = gson.fromJson(string_now_forcast, NowWeatherForecast.class);
        draw();
    }
}
