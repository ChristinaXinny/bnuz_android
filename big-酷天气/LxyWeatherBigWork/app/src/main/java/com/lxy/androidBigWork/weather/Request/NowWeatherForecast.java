package com.lxy.androidBigWork.weather.Request;


// 实时天气预报 https://dev.qweather.com/docs/android-sdk/android-weather/#示例代码

import java.util.List;

public class NowWeatherForecast {


    /**
     * "code": "200",
     * "updateTime": "2021-12-26T20:32+08:00",
     * "fxLink": "http://hfx.link/2ax1",
     * "now": { "obsTime": "2021-12-26T20:19+08:00", "temp": "-5", "feelsLike": "-8", "icon": "150", "text": "晴", "wind360": "180", "windDir": "南风", "windScale": "1", "windSpeed": "4", "humidity": "25", "precip": "0.0", "pressure": "1034", "vis": "21", "cloud": "0", "dew": "-30" },
     * "refer": {	"sources": [ "QWeather", "NMC", "ECMWF ],	"license": [ "no commercial use" ]  }
     */

    private String code;
    private String updateTime;
    private String fxLink;
    private NowBean now;
    private ReferBean refer;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(String updateTime) {
        this.updateTime = updateTime;
    }

    public String getFxLink() {
        return fxLink;
    }

    public void setFxLink(String fxLink) {
        this.fxLink = fxLink;
    }

    public NowBean getNow() {
        return now;
    }

    public void setNow(NowBean now) {
        this.now = now;
    }

    public ReferBean getRefer() {
        return refer;
    }

    public void setRefer(ReferBean refer) {
        this.refer = refer;
    }

    public static class NowBean {
        /**
         * "obsTime": "2021-12-26T20:19+08:00",
         * "temp": "-5",
         * "feelsLike": "-8",
         * "icon": "150",
         * "text": "晴",
         * "wind360": "180",
         * "windDir": "南风",
         * "windScale": "1",
         * "windSpeed": "4",
         * "humidity": "25",
         * "precip": "0.0",
         * "pressure": "1034",
         * "vis": "21",
         * "cloud": "0",
         * "dew": "-30"
         */

        private String obsTime;
        private String temp;
        private String feelsLike;
        private String icon;
        private String text;
        private String wind360;
        private String windDir;
        private String windScale;
        private String windSpeed;
        private String humidity;
        private String precip;
        private String pressure;
        private String vis;
        private String cloud;
        private String dew;

        public String getObsTime() {
            return obsTime;
        }

        public void setObsTime(String obsTime) {
            this.obsTime = obsTime;
        }

        public String getTemp() {
            return temp;
        }

        public void setTemp(String temp) {
            this.temp = temp;
        }

        public String getFeelsLike() {
            return feelsLike;
        }

        public void setFeelsLike(String feelsLike) {
            this.feelsLike = feelsLike;
        }

        public String getIcon() {
            return icon;
        }

        public void setIcon(String icon) {
            this.icon = icon;
        }

        public String getText() {
            return text;
        }

        public void setText(String text) {
            this.text = text;
        }

        public String getWind360() {
            return wind360;
        }

        public void setWind360(String wind360) {
            this.wind360 = wind360;
        }

        public String getWindDir() {
            return windDir;
        }

        public void setWindDir(String windDir) {
            this.windDir = windDir;
        }

        public String getWindScale() {
            return windScale;
        }

        public void setWindScale(String windScale) {
            this.windScale = windScale;
        }

        public String getWindSpeed() {
            return windSpeed;
        }

        public void setWindSpeed(String windSpeed) {
            this.windSpeed = windSpeed;
        }

        public String getHumidity() {
            return humidity;
        }

        public void setHumidity(String humidity) {
            this.humidity = humidity;
        }

        public String getPrecip() {
            return precip;
        }

        public void setPrecip(String precip) {
            this.precip = precip;
        }

        public String getPressure() {
            return pressure;
        }

        public void setPressure(String pressure) {
            this.pressure = pressure;
        }

        public String getVis() {
            return vis;
        }

        public void setVis(String vis) {
            this.vis = vis;
        }

        public String getCloud() {
            return cloud;
        }

        public void setCloud(String cloud) {
            this.cloud = cloud;
        }

        public String getDew() {
            return dew;
        }

        public void setDew(String dew) {
            this.dew = dew;
        }
    }

    public static class ReferBean {
        /**
         * "sources": [
         * "QWeather",
         * "NMC",
         * "ECMWF"
         * ],
         * "license": [
         * "no commercial use"
         * ]
         */

        private List<String> sources;
        private List<String> license;

        public List<String> getSources() {
            return sources;
        }

        public void setSources(List<String> sources) {
            this.sources = sources;
        }

        public List<String> getLicense() {
            return license;
        }

        public void setLicense(List<String> license) {
            this.license = license;
        }
    }

    public static class HourlyBean {
        /**
         * cloud : 8
         * cond_code : 100
         * cond_txt : 晴
         * dew : -19
         * hum : 72
         * pop : 0
         * pres : 1028
         * time : 2018-12-14 22:00
         * tmp : -4
         * wind_deg : 190
         * wind_dir : 南风
         * wind_sc : 1-2
         * wind_spd : 3
         */

        private String cloud;
        private String cond_code;
        private String cond_txt;
        private String dew;
        private String hum;
        private String pop;
        private String pres;
        private String time;
        private String tmp;
        private String wind_deg;
        private String wind_dir;
        private String wind_sc;
        private String wind_spd;

        public String getCloud() {
            return cloud;
        }

        public void setCloud(String cloud) {
            this.cloud = cloud;
        }

        public String getCond_code() {
            return cond_code;
        }

        public void setCond_code(String cond_code) {
            this.cond_code = cond_code;
        }

        public String getCond_txt() {
            return cond_txt;
        }

        public void setCond_txt(String cond_txt) {
            this.cond_txt = cond_txt;
        }

        public String getDew() {
            return dew;
        }

        public void setDew(String dew) {
            this.dew = dew;
        }

        public String getHum() {
            return hum;
        }

        public void setHum(String hum) {
            this.hum = hum;
        }

        public String getPop() {
            return pop;
        }

        public void setPop(String pop) {
            this.pop = pop;
        }

        public String getPres() {
            return pres;
        }

        public void setPres(String pres) {
            this.pres = pres;
        }

        public String getTime() {
            return time;
        }

        public void setTime(String time) {
            this.time = time;
        }

        public String getTmp() {
            return tmp;
        }

        public void setTmp(String tmp) {
            this.tmp = tmp;
        }

        public String getWind_deg() {
            return wind_deg;
        }

        public void setWind_deg(String wind_deg) {
            this.wind_deg = wind_deg;
        }

        public String getWind_dir() {
            return wind_dir;
        }

        public void setWind_dir(String wind_dir) {
            this.wind_dir = wind_dir;
        }

        public String getWind_sc() {
            return wind_sc;
        }

        public void setWind_sc(String wind_sc) {
            this.wind_sc = wind_sc;
        }

        public String getWind_spd() {
            return wind_spd;
        }

        public void setWind_spd(String wind_spd) {
            this.wind_spd = wind_spd;
        }
    }

}
