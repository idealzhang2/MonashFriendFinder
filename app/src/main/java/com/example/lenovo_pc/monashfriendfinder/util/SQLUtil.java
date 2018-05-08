package com.example.lenovo_pc.monashfriendfinder.util;

import android.util.Log;

import org.json.JSONArray;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;

import java.util.Map;

import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

/**
 * Created by lenovo-pc on 2018/4/14.
 */

public class SQLUtil {
        public static final MediaType JSON = MediaType.parse("application/json; charset=utf-8");
        public static final MediaType  XML  = MediaType.parse("application/json; charset=utf-8");
      // public static  String serverhost = "http://192.168.1.102:8080";
        public static String serverhost = "http://172.16.120.49:8080";
        public static String hskserver = "http://s201732b04.51mypc.cn:47896";
        public static String address = "http://wthrcdn.etouch.cn/weather_mini?city=";
        public static JSONArray  SqlResult(String sql){
            JSONArray jsonArray = new JSONArray();
            new Thread(new Runnable() {
                @Override
                public void run() {

                    try{
                        OkHttpClient  client = new OkHttpClient();
                        String url = SQLUtil.serverhost + URLConfigUtil.getURLid()+"/"+29184444;
                        Log.d("TestActivity", url);

                        Request request = new Request.Builder().url(url).header("User-Agent", "OkHttp Headers.java")
                                .addHeader("Accept", "application/json; q=0.5")
                                .addHeader("Accept", "application/vnd.github.v3+json").build();
                        Response response = client.newCall(request).execute();
                        String responseData = response.body().string();
                        Map maps = (Map)JSONObject.parse(responseData.toString());
                        System.out.println("这个是用JSON类来解析JSON字符串!!!");
                        for (Object map : maps.entrySet()){
                            System.out.println(((Map.Entry)map).getKey()+"     " + ((Map.Entry)map).getValue());
                        }
                        Log.d("TestActivity",responseData);
                      //  showResponse(responseData);
                    }catch(Exception e){
                        e.printStackTrace();
                    }
                }
            }).start();

            return  jsonArray;
        }
}
