package com.example.lenovo_pc.monashfriendfinder;

import android.content.Intent;
import android.icu.text.SimpleDateFormat;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.widget.Button;
import android.widget.TextView;

import com.baidu.location.LocationClient;
import com.example.lenovo_pc.monashfriendfinder.util.SQLUtil;
import com.example.lenovo_pc.monashfriendfinder.util.Weather;
import com.example.lenovo_pc.monashfriendfinder.util.Yesterday;

import org.json.JSONException;

import java.util.Date;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

/**
 * Created by lenovo-pc on 2018/4/22.
 */

public class homefragment extends Fragment implements View.OnClickListener,Runnable {
    private View view ;
    private Handler handler;
    private TextView textView;
    private WebView webView;
    private TextView testtextview;
    private TextView testView;
    private LocationClient mlocationClient;
    private TextView yes_date;
    private TextView yes_fx;
    private TextView yes_fl;
    private TextView yes_high;
    private TextView yes_low;
    private TextView yes_type;
    private TextView fore1_date;
    private TextView fore1_fx;
    private TextView fore1_fl;
    private TextView fore1_high;
    private TextView fore1_low;
    private TextView fore1_type;
    private TextView fore2_date;
    private TextView fore2_fx;
    private TextView fore2_fl;
    private TextView fore2_high;
    private TextView fore2_low;
    private TextView fore2_type;
    private TextView fore3_date;
    private TextView fore3_fx;
    private TextView fore3_fl;
    private TextView fore3_high;
    private TextView fore3_low;
    private TextView fore3_type;
    private TextView fore4_date;
    private TextView fore4_fx;
    private TextView fore4_fl;
    private TextView fore4_high;
    private TextView fore4_low;
    private TextView fore4_type;
    private TextView fore5_date;
    private TextView fore5_fx;
    private TextView fore5_fl;
    private TextView fore5_high;
    private TextView fore5_low;
    private TextView fore5_type;
    private TextView pro;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceSatte){
         view  = inflater.inflate(R.layout.home_fragment,container,false);
        LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity());
        Button button = (Button) view.findViewById(R.id.home);
        textView = (TextView) view.findViewById(R.id.time);
        testtextview = (TextView)  view.findViewById(R.id.profile);
        pro = (TextView) view.findViewById(R.id.pro);
        pro.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(homefragment.this.getActivity(),UpdateActivity.class);
                startActivity(intent);
            }
        });
        initview();


        WeatherTask weatherTask = new WeatherTask();
        weatherTask.execute("苏州");
        handler = new Handler() {
            public void handleMessage(Message msg) {
                textView.setText((String) msg.obj);
            }
        };
        new Thread(this).start();
        return view;
    }


    @Override
    public void run() {
        try {

            while (true) {
                SimpleDateFormat sdf = new SimpleDateFormat("yyyy年MM月dd日  HH:mm:ss");
                Date date = new Date();
                String str = sdf.format(date);
                handler.sendMessage(handler.obtainMessage(100, str));
                Thread.sleep(1000);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    private void initview(){
        yes_date = (TextView)view.findViewById(R.id.yesterday_date);
        yes_fl = (TextView) view.findViewById(R.id.yesterday_fengli);
        yes_fx = (TextView)view.findViewById(R.id.yesterday_fengxiang);
        yes_high = (TextView)view.findViewById(R.id.yesterday_high);
        yes_low = (TextView)view.findViewById(R.id.yesterday_low);
        yes_type = (TextView)view.findViewById(R.id.yesterday_type);
        fore1_date = (TextView)view.findViewById(R.id.fore1_date);
        fore1_date = (TextView)view.findViewById(R.id.fore1_date);
        fore1_fl = (TextView)view.findViewById(R.id.fore1_fengli);
        fore1_fx = (TextView)view.findViewById(R.id.fore1_fengxiang);
        fore1_high = (TextView)view.findViewById(R.id.fore1_high);
        fore1_low = (TextView)view.findViewById(R.id.fore1_low);
        fore1_type = (TextView)view.findViewById(R.id.fore1_type);
        fore2_date = (TextView)view.findViewById(R.id.fore2_date);
        fore2_fl = (TextView)view.findViewById(R.id.fore2_fengli);
        fore2_fx = (TextView)view.findViewById(R.id.fore2_fengxiang);
        fore2_high = (TextView)view.findViewById(R.id.fore2_high);
        fore2_low = (TextView)view.findViewById(R.id.fore2_low);
        fore2_type = (TextView)view.findViewById(R.id.fore2_type);

        fore3_date = (TextView)view.findViewById(R.id.fore3_date);
        fore3_fl = (TextView)view.findViewById(R.id.fore3_fengli);
        fore3_fx = (TextView)view.findViewById(R.id.fore3_fengxiang);
        fore3_high = (TextView)view.findViewById(R.id.fore3_high);
        fore3_low = (TextView)view.findViewById(R.id.fore3_low);
        fore3_type = (TextView)view.findViewById(R.id.fore3_type);

        fore4_date = (TextView)view.findViewById(R.id.fore4_date);
        fore4_fl = (TextView)view.findViewById(R.id.fore4_fengli);
        fore4_fx = (TextView)view.findViewById(R.id.fore4_fengxiang);
        fore4_high = (TextView)view.findViewById(R.id.fore4_high);
        fore4_low = (TextView)view.findViewById(R.id.fore4_low);
        fore4_type = (TextView)view.findViewById(R.id.fore4_type);

        fore5_date = (TextView)view.findViewById(R.id.fore5_date);
        fore5_fl = (TextView)view.findViewById(R.id.fore5_fengli);
        fore5_fx = (TextView)view.findViewById(R.id.fore5_fengxiang);
        fore5_high = (TextView)view.findViewById(R.id.fore5_high);
        fore5_low = (TextView)view.findViewById(R.id.fore5_low);
        fore5_type = (TextView)view.findViewById(R.id.fore5_type);
        SharedPreferencesUtils profile = new SharedPreferencesUtils(homefragment.this.getActivity(),"setting");
        String name = profile.getString("name");
        name = "Welcome "+name;
        testtextview.setText(name);
    }
    private void  update(Weather weather){
        if(weather != null){
            Yesterday yes = weather.data.yesterday;
            yes_date.setText(yes.date);
            yes_type.setText(yes.getType());
            yes_fl.setText(yes.fl);
            yes_fx.setText(yes.fx);
            yes_high.setText(yes.high);
            yes_low.setText(yes.low);

            fore1_date.setText(weather.data.forecast.get(0).date);
            fore1_type.setText(weather.data.forecast.get(0).type);
            fore1_low.setText(weather.data.forecast.get(0).low);
            fore1_high.setText(weather.data.forecast.get(0).high);
            fore1_fx.setText(weather.data.forecast.get(0).fengxiang);
            fore1_fl.setText(weather.data.forecast.get(0).fengli);

            fore2_date.setText(weather.data.forecast.get(1).date);
            fore2_type.setText(weather.data.forecast.get(1).type);
            fore2_low.setText(weather.data.forecast.get(1).low);
            fore2_high.setText(weather.data.forecast.get(1).high);
            fore2_fx.setText(weather.data.forecast.get(1).fengxiang);
            fore2_fl.setText(weather.data.forecast.get(1).fengli);

            fore3_date.setText(weather.data.forecast.get(2).date);
            fore3_type.setText(weather.data.forecast.get(2).type);
            fore3_low.setText(weather.data.forecast.get(2).low);
            fore3_high.setText(weather.data.forecast.get(2).high);
            fore3_fx.setText(weather.data.forecast.get(2).fengxiang);
            fore3_fl.setText(weather.data.forecast.get(2).fengli);

            fore4_date.setText(weather.data.forecast.get(3).date);
            fore4_type.setText(weather.data.forecast.get(3).type);
            fore4_low.setText(weather.data.forecast.get(3).low);
            fore4_high.setText(weather.data.forecast.get(3).high);
            fore4_fx.setText(weather.data.forecast.get(3).fengxiang);
            fore4_fl.setText(weather.data.forecast.get(3).fengli);

            fore5_date.setText(weather.data.forecast.get(4).date);
            fore5_type.setText(weather.data.forecast.get(4).type);
            fore5_low.setText(weather.data.forecast.get(4).low);
            fore5_high.setText(weather.data.forecast.get(4).high);
            fore5_fx.setText(weather.data.forecast.get(4).fengxiang);
            fore5_fl.setText(weather.data.forecast.get(4).fengli);
        }
    }
    @Override
    public void onClick(View view) {

    }

    private class WeatherTask extends AsyncTask<String, Void ,String> {
        @Override
        protected void onPostExecute(String value){
            super.onPostExecute(value);

            try {
                Weather weather = new Weather();
                weather = Weather.parse(value);
                if(weather != null)
                update(weather);

            } catch (JSONException e) {
                e.printStackTrace();
            }

        }


        @Override
        protected String doInBackground(String... strings) {
            String totalbyte = null;
            try {
                OkHttpClient client = new OkHttpClient();
                String url = SQLUtil.address + strings[0];
                Log.d("TestActivity", url);
                Request request = new Request.Builder().url(url).header("User-Agent", "OkHttp Headers.java")
                        .addHeader("Accept", "application/json; q=0.5")
                        .addHeader("Accept", "application/vnd.github.v3+json").build();
                Response response = client.newCall(request).execute();
                String responseData = response.body().string();
                totalbyte = responseData;
                System.out.println(totalbyte);
            } catch (Exception e) {
                e.printStackTrace();
            }
            return totalbyte;
        }
    }
}
