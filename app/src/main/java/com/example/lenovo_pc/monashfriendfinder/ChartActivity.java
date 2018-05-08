package com.example.lenovo_pc.monashfriendfinder;

import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.EditText;
import android.widget.TextView;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.example.lenovo_pc.monashfriendfinder.util.SQLUtil;
import com.example.lenovo_pc.monashfriendfinder.util.URLConfigUtil;
import com.example.lenovo_pc.monashfriendfinder.util.infoUnits;
import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.utils.ColorTemplate;

import java.util.ArrayList;
import java.util.List;
import com.example.lenovo_pc.monashfriendfinder.util.infoLo;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class ChartActivity extends AppCompatActivity {
    private BarChart chart;
    private TextView startview;
    private TextView endview;
    private EditText starttext;
    private EditText endtext;
    private ArrayList<String> xVals;
    private ArrayList<BarEntry> entries;
    private List<infoLo> los=new ArrayList<infoLo>();
    private infoLo l0=new infoLo();
    private infoLo l1=new infoLo();
    private infoLo l2=new infoLo();
    private String startdate;
    private String enddate;
    private String ID;
    private  SharedPreferencesUtils helper;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chart);
        helper =  new SharedPreferencesUtils(getApplicationContext(),"setting");
        startview = (TextView)findViewById(R.id.text_start);
        endview = (TextView)findViewById(R.id.text_end);
        starttext = (EditText)findViewById(R.id.In_start);
        endtext = (EditText)findViewById(R.id.In_end);
        Intent intent = getIntent();
         startdate = intent.getStringExtra("startdate");
         enddate = intent.getStringExtra("enddate");
        ID = helper.getString("name");
        initChart();
      MatchData matchData = new MatchData();
        matchData.execute(" ");
    }

    private void initChart(){
        chart=new BarChart(this);
        setContentView(chart);
        chart.setDescription("Location Bar Chart");
    }

    private void setData(ArrayList<String> xVals,ArrayList<BarEntry> entries) {
//        l0.setMonth("1月");
//        l1.setMonth("2月");
//        l2.setMonth("3月");
//
//        l0.setFre(3);
//        l1.setFre(8);
//        l2.setFre(6);
//        ;
//
//        los.add(l0);
//        los.add(l1);
//        los.add(l2);

        entries=new ArrayList<BarEntry>();
        xVals=new ArrayList<String>();

        for(int i=0;i<los.size();i++){
            entries.add(new BarEntry(los.get(i).getFre(),i));
        }

        for(int i=0;i<los.size();i++){
            xVals.add(los.get(i).getMonth());
        }

        BarDataSet dataSet = new BarDataSet(entries, "");
        BarData data=new BarData();

        ArrayList<Integer> colors = new ArrayList<Integer>();

        for (int c : ColorTemplate.JOYFUL_COLORS)
            colors.add(c);
        for (int c : ColorTemplate.COLORFUL_COLORS)
            colors.add(c);
        for (int c : ColorTemplate.LIBERTY_COLORS)
            colors.add(c);
        colors.add(ColorTemplate.getHoloBlue());

        dataSet.setColors(colors);
        data = new BarData(xVals, dataSet);
        chart.setData(data);
        chart.animateY(3000);
        chart.setDescription("Location Bar Chart");
    }
    private class MatchData extends AsyncTask<String, Void ,String> {
        @Override
        protected void onPostExecute(String value){
            super.onPostExecute(value);
            List<infoLo> result = new ArrayList<infoLo>();
            try {
                JSONArray arr = JSONArray.parseArray(value);
                for(int i = 0;i < arr.size();i++){
                    JSONObject tem = arr.getJSONObject(i);
                    infoLo stu = new infoLo();
                    stu.setMonth(tem.getString("placename"));
                    stu.setFre(tem.getInteger("num"));

                    result.add(stu);
                }
                los = result;
                //    addrows(result);
                setData(xVals,entries);
            } catch (Exception e) {
                e.printStackTrace();
            }

        }


        @Override
        protected String doInBackground(String... strings) {
            String totalbyte = null;
            try {
                OkHttpClient client = new OkHttpClient();

                String url = SQLUtil.serverhost+ URLConfigUtil.getURLfindByVISIT()+"/"+startdate+"/"+enddate+"/"+ID;
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
