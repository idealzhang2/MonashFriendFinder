package com.example.lenovo_pc.monashfriendfinder;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.BitmapFactory;
import android.app.Activity;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;

import com.alibaba.fastjson.JSONArray;
import com.amap.api.maps.AMap;
import com.amap.api.maps.CameraUpdateFactory;
import com.amap.api.maps.MapView;
import com.amap.api.maps.model.BitmapDescriptor;
import com.amap.api.maps.model.BitmapDescriptorFactory;
import com.amap.api.maps.model.LatLng;
import com.amap.api.maps.model.Marker;
import com.amap.api.maps.model.MarkerOptions;

import java.util.ArrayList;
import java.util.List;

import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;
import android.widget.Toast;

import com.amap.api.maps.AMap;
import com.amap.api.maps.MapView;
import com.amap.api.maps.model.BitmapDescriptorFactory;
import com.amap.api.maps.model.LatLng;
import com.amap.api.maps.model.Marker;
import com.amap.api.maps.model.MarkerOptions;
import com.amap.api.maps.model.MyLocationStyle;
import com.example.lenovo_pc.monashfriendfinder.util.Base64Utils;
import com.example.lenovo_pc.monashfriendfinder.util.MarkerInfo;
import com.example.lenovo_pc.monashfriendfinder.util.SQLUtil;
import com.example.lenovo_pc.monashfriendfinder.util.ShowInfo;
import com.example.lenovo_pc.monashfriendfinder.util.Studentpr;
import com.example.lenovo_pc.monashfriendfinder.util.URLConfigUtil;


import org.w3c.dom.Text;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class MapActivity extends AppCompatActivity {
    private MapView mMapView;
    private AMap aMap;
    private  SharedPreferencesUtils helper;
    private Marker currentMarker;
    private List<MarkerInfo> infoList=new ArrayList<>();
    private MarkerInfo LOA=new MarkerInfo();
    private MarkerInfo LOB=new MarkerInfo();
    private MarkerInfo LOC=new MarkerInfo();
    private  ArrayList<Studentpr> result = new ArrayList<Studentpr>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_map);
        //定义了一个地图view
        helper =  new SharedPreferencesUtils(getApplicationContext(),"setting");
        Intent intent = this.getIntent();
        result = (ArrayList<Studentpr>) intent.getSerializableExtra("result");
        mMapView = (MapView) findViewById(R.id.map);
        mMapView.onCreate(savedInstanceState);// 此方法须覆写，虚拟机需要在很多情况下保存地图绘制的当前状态。
//初始化地图控制器对象
        if (aMap == null) {
            aMap = mMapView.getMap();
        }
        SharedPreferencesUtils helper = new SharedPreferencesUtils(this,"setting");
        LocationInfo locationInfo =  new  LocationInfo();
        locationInfo.execute(" ");


    }
    @Override
    protected void onDestroy() {
        super.onDestroy();
        //在activity执行onDestroy时执行mMapView.onDestroy()，销毁地图
        mMapView.onDestroy();
    }
    @Override
    protected void onResume() {
        super.onResume();
        //在activity执行onResume时执行mMapView.onResume ()，重新绘制加载地图
        mMapView.onResume();
    }
    @Override
    protected void onPause() {
        super.onPause();
        //在activity执行onPause时执行mMapView.onPause ()，暂停地图的绘制
        mMapView.onPause();
    }
    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        //在activity执行onSaveInstanceState时执行mMapView.onSaveInstanceState (outState)，保存地图当前的状态
        mMapView.onSaveInstanceState(outState);
    }

    private void addMarkerInfo(){
        SharedPreferencesUtils helper = new SharedPreferencesUtils(this,"setting");

        ArrayList<ShowInfo> showInfos = new ArrayList<ShowInfo>();
        if(infoList != null)
        {
            for(int i = 0; i < infoList.size();i++)
            {
                String detail= null;
                ShowInfo tem = new ShowInfo();
                tem.setLatitude(infoList.get(i).getLatitude());
                tem.setLongtude(infoList.get(i).getLongtude());
                for(int j = 0;j < result.size();j++){
                    if(result.get(j).getStuid().equals(infoList.get(i).getID()))
                    {
                        Studentpr stu = result.get(j);
                        detail  += stu.getStuid()+"-"+stu.getFirname()+ "-"+stu.getSurname()+"-"+stu.getDob()+"-"+stu.getGender()+"-"+stu.getCourse()+"-"+stu.getStumode()+"-"+stu.getMoemail()+"-"+stu.getSubdate()+"-"+stu.getSubtime()+"-"+stu.getSuburb()+"-"+stu.getNationality()+"-"+stu.getNalanguage()+"-"+stu.getFsport()+"-"+stu.getFmovie()+"-"+stu.getFunit()+"-"+stu.getCurjob()+"-"+stu.getAddress();
                    }
                }
                tem.setDetail(detail);
                showInfos.add(tem);
            }
            drawMarkers(showInfos);
        }


    }
    public void drawMarkers(ArrayList<ShowInfo>  showInfos) {
        ArrayList<LatLng> pointList = new ArrayList<LatLng>();
        String[] name = new String[showInfos.size()];
        for (int j = 0; j < showInfos.size(); j++) {
            System.out.println(showInfos.get(j).getLatitude() + "   "+showInfos.get(j).getLongtude());
            double lat = Double.valueOf( showInfos.get(j).getLatitude());
            double lng = Double.valueOf( showInfos.get(j).getLongtude());
            LatLng point = new LatLng(lat, lng);
            pointList.add(point);
        }
        for(int m=0;m<showInfos.size();m++){
            name[m]=  showInfos.get(m).getDetail();
        }

        aMap.moveCamera(CameraUpdateFactory.newLatLngZoom(pointList.get(0), 15));
        MarkerOptions markerOptions1 = new MarkerOptions();
        markerOptions1.position(pointList.get(0));
        markerOptions1.title(name[0]);
        markerOptions1.visible(true);
        BitmapDescriptor bitmapDescriptor1 = BitmapDescriptorFactory.fromBitmap(BitmapFactory.decodeResource(getResources(), R.drawable.marker_bg));
        markerOptions1.icon(bitmapDescriptor1);
        aMap.addMarker(markerOptions1);
        aMap.setOnMarkerClickListener(new AMap.OnMarkerClickListener() {
            @Override
            public boolean onMarkerClick(Marker marker) {
                currentMarker = marker;
                Log.e("marker", marker.getObject() + "marker: " + marker.getPosition().latitude + " ： " + marker);
                return false;
            }
        });

        for (int i = 1; i < pointList.size(); i++) {
            aMap.moveCamera(CameraUpdateFactory.newLatLngZoom(pointList.get(i), 15));
            MarkerOptions markerOptions = new MarkerOptions();
            markerOptions.position(pointList.get(i));
            markerOptions.title(name[i]);
            markerOptions.visible(true);
            BitmapDescriptor bitmapDescriptor = BitmapDescriptorFactory.fromBitmap(BitmapFactory.decodeResource(getResources(), R.drawable.markers_bg));
            markerOptions.icon(bitmapDescriptor);
            aMap.addMarker(markerOptions);
            aMap.setOnMarkerClickListener(new AMap.OnMarkerClickListener() {
                @Override
                public boolean onMarkerClick(Marker marker) {
                    currentMarker = marker;
                    Log.e("marker", marker.getObject() + "marker: " + marker.getPosition().latitude + " ： " + marker);
                    return false;
                }
            });
        }
    }
    public class LocationInfo extends AsyncTask<String,Void,String> {

        @Override
        protected void onPostExecute(String value) {
            System.out.println(value);
            JSONArray arr = JSONArray.parseArray(value);

            for(int i = 0 ;i<arr.size();i++){
                MarkerInfo tem = new MarkerInfo();
                tem.setID(arr.getJSONObject(i).getString("ID"));
                tem.setLatitude(arr.getJSONObject(i).getString("latitude"));
                tem.setLongtude(arr.getJSONObject(i).getString("longtude"));
                System.out.println(arr.getJSONObject(i).getString("ID")+"   "+arr.getJSONObject(i).getString("latitude")+"    "+arr.getJSONObject(i).getString("longtude"));
                infoList.add(tem);
            }
            addMarkerInfo();
        }
        @Override
        protected String doInBackground(String... strings) {
            String res = null;
            try{

                String locaid = helper.getString("LocaInfo");
                locaid = locaid.substring(4,locaid.length());
                System.out.println(locaid);
                if(locaid == null)
                {
                  return "NoRes";
                }

                String url = SQLUtil.serverhost+ URLConfigUtil.getURLMap()+"/"+ Base64Utils.encryptBASE64(locaid);
                OkHttpClient client = new OkHttpClient();
                Request request = new Request.Builder().url(url).build();
                System.out.println(url);
                Response response = client.newCall(request).execute();
                res = response.body().string();
                System.out.println(res);
            }catch(Exception e){

            }
            return res;
        }
    }


}



