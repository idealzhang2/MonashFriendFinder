package com.example.lenovo_pc.monashfriendfinder.util;

/**
 * Created by lenovo-pc on 2018/4/19.
 */


import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;


import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;


public class Weather {
    public String desc;
    public int status;
    public Data data;
    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }


    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public static Weather parse(String json) throws JSONException {
        Weather weather = new Weather();
        //      首先看到的是一个{}所以用JSON Object来进行解析
//      获得外部的Weather
        if(json == null)
        {
            return null;
        }
        JSONObject obj = new JSONObject(json);
        String desc = obj.getString("desc");
        int status = obj.getInt("status");
        weather.status=status;
        weather.desc=desc;

//      获得内部Data的数据
        JSONObject obj1 = obj.getJSONObject("data");
        Data data=new Data();
        data.wendu=obj1.getString("wendu");
        data.ganmao=obj1.getString("ganmao");
        data.aqi=obj1.getString("aqi");
        data.city=obj1.getString("city");
        weather.data=data;
        List<Forecast> forecasts=new ArrayList<>();

//      获取forecast数组
        JSONArray jArr = obj1.getJSONArray("forecast");
        for (int i = 0; i < jArr.length(); i++) {
            JSONObject obj2 = jArr.getJSONObject(i);
            Forecast forecast=new Forecast();
            forecast.date=obj2.getString("date");
            forecast.fengxiang=obj2.getString("fengxiang");
            forecast.high=obj2.getString("high");
            forecast.low=obj2.getString("low");
            forecast.fengli=obj2.getString("fengli");
            forecast.type=obj2.getString("type");
            forecasts.add(forecast);
        }
        data.forecast=forecasts;
        JSONObject obj3 = obj1.getJSONObject("yesterday");
        Yesterday yesterday=new Yesterday();
        yesterday.fl=obj3.getString("fl");
        yesterday.fx=obj3.getString("fx");
        yesterday.high=obj3.getString("high");
        yesterday.type=obj3.getString("type");
        yesterday.low=obj3.getString("low");
        yesterday.date=obj3.getString("date");
        data.yesterday=yesterday;

//      输出字段
       return weather;
    }


}



