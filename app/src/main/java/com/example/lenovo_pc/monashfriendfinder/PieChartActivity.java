package com.example.lenovo_pc.monashfriendfinder;

import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.app.Activity;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.example.lenovo_pc.monashfriendfinder.util.SQLUtil;
import com.example.lenovo_pc.monashfriendfinder.util.Studentpr;
import com.example.lenovo_pc.monashfriendfinder.util.URLConfigUtil;
import com.example.lenovo_pc.monashfriendfinder.util.UnitFre;
import com.example.lenovo_pc.monashfriendfinder.util.infoUnits;
import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.components.Legend;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.data.PieDataSet;
import com.github.mikephil.charting.utils.ColorTemplate;
import com.github.mikephil.charting.utils.PercentFormatter;


import java.util.ArrayList;
import java.util.List;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class PieChartActivity extends AppCompatActivity {
    private PieChart chart;
    private ArrayList<String>  xVals;
    private ArrayList<Entry>  yVals;
    private TextView startview;
    private TextView endview;
    private EditText starttext;
    private EditText endtext;
    private List<infoUnits> units=new ArrayList<infoUnits>();
    private infoUnits u0=new infoUnits();
    private infoUnits u1=new infoUnits();
    private infoUnits u2=new infoUnits();
    private infoUnits u3=new infoUnits();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pie_chart);
        startview = (TextView)findViewById(R.id.text_start);
        endview = (TextView)findViewById(R.id.text_end);
        starttext = (EditText)findViewById(R.id.In_start);
        endtext = (EditText)findViewById(R.id.In_end);
        initChart();
        MatchData matchData = new MatchData();
        matchData.execute(" ");
    }


    private void initChart(){
        chart=new PieChart(this);
        setContentView(chart);
        chart.setUsePercentValues(true);
        chart.setDescription("Favourite Units");

        Legend mLegend = chart.getLegend();  //设置比例图
        mLegend.setPosition(Legend.LegendPosition.BELOW_CHART_LEFT);  //左下边显示
        mLegend.setFormSize(12f);//比例块字体大小
        mLegend.setXEntrySpace(2f);//设置距离饼图的距离，防止与饼图重合
        mLegend.setYEntrySpace(2f);
        mLegend.setDirection(Legend.LegendDirection.LEFT_TO_RIGHT);

        mLegend.setTextColor(Color.BLACK);
        mLegend.setForm(Legend.LegendForm.SQUARE);//设置比例块形状，默认为方块
    }

    private void setData(ArrayList<String> xVals,ArrayList<Entry> yVals) {
//        u0.setName("F83");
//        u1.setName("F86");
//        u2.setName("F87");
//        u3.setName("F90");
//        u0.setPerF(1);
//        u1.setPerF(2);
//        u2.setPerF(3);
//        u3.setPerF(4);
//
//        units.add(u0);
//        units.add(u1);
//        units.add(u2);
//        units.add(u3);
        yVals=new ArrayList<Entry>();
        xVals=new ArrayList<String>();

        for(int i=0;i<units.size();i++){
           // System.out.println(units.get(i).getPerF());
            yVals.add(new Entry(units.get(i).getPerF(),i));
        }

        for(int i=0;i<units.size();i++){
          //  System.out.println(units.get(i).getName());
            xVals.add(units.get(i).getName());
        }

        PieDataSet dataSet = new PieDataSet(yVals, "");
        dataSet.setSliceSpace(2f);
        dataSet.setSelectionShift(5f);

        ArrayList<Integer> colors = new ArrayList<Integer>();

        for (int c : ColorTemplate.JOYFUL_COLORS)
            colors.add(c);
        for (int c : ColorTemplate.COLORFUL_COLORS)
            colors.add(c);
        for (int c : ColorTemplate.LIBERTY_COLORS)
            colors.add(c);
        colors.add(ColorTemplate.getHoloBlue());

        dataSet.setColors(colors);
        PieData data = new PieData(xVals, dataSet);
        data.setValueFormatter(new PercentFormatter());
        data.setValueTextSize(12f);
        data.setValueTextColor(Color.WHITE);
        chart.setData(data);
        chart.highlightValues(null);
        chart.invalidate();
    }

    private class MatchData extends AsyncTask<String, Void ,String> {
        @Override
        protected void onPostExecute(String value){
            super.onPostExecute(value);
            ArrayList<infoUnits> result = new ArrayList<infoUnits>();
            try {
                JSONArray arr = JSONArray.parseArray(value);
                for(int i = 0;i < arr.size();i++){
                    JSONObject tem = arr.getJSONObject(i);
                    infoUnits stu = new infoUnits();
                    stu.setName(tem.getString("funit"));
                    stu.setPerF(Float.parseFloat(tem.getString("fre")));

                    result.add(stu);
                }
                units = result;
            //    addrows(result);
                setData(xVals,yVals);
            } catch (Exception e) {
                e.printStackTrace();
            }

        }


        @Override
        protected String doInBackground(String... strings) {
            String totalbyte = null;
            try {
                OkHttpClient client = new OkHttpClient();

                String url = SQLUtil.serverhost+ URLConfigUtil.getURLfindpiechart();
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
