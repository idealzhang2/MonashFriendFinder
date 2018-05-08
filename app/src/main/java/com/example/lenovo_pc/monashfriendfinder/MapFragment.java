package com.example.lenovo_pc.monashfriendfinder;

import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.amap.api.maps.AMap;
import com.amap.api.maps.CameraUpdateFactory;
import com.amap.api.maps.MapView;
import com.amap.api.maps.SupportMapFragment;
import com.amap.api.maps.model.BitmapDescriptor;
import com.amap.api.maps.model.BitmapDescriptorFactory;
import com.amap.api.maps.model.LatLng;
import com.amap.api.maps.model.Marker;
import com.amap.api.maps.model.MarkerOptions;
import com.example.lenovo_pc.monashfriendfinder.util.Base64Utils;
import com.example.lenovo_pc.monashfriendfinder.util.MarkerInfo;
import com.example.lenovo_pc.monashfriendfinder.util.SQLUtil;
import com.example.lenovo_pc.monashfriendfinder.util.ShowInfo;
import com.example.lenovo_pc.monashfriendfinder.util.Studentpr;
import com.example.lenovo_pc.monashfriendfinder.util.URLConfigUtil;
import com.example.lenovo_pc.monashfriendfinder.util.friendship;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

/**
 * Created by lenovo-pc on 2018/4/23.
 */

public class MapFragment extends SupportMapFragment {
    private View view;
    private String t;
    private MapView mMapView;
    private AMap aMap;

    private Marker currentMarker;
    private List<MarkerInfo> infoList=new ArrayList<MarkerInfo>();
    private MarkerInfo LOA=new MarkerInfo();
    private MarkerInfo LOB=new MarkerInfo();
    private MarkerInfo LOC=new MarkerInfo();

    @Override
    public void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);

    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceSatte){
        view  = inflater.inflate(R.layout.map_fragment,container,false);
        initview(savedInstanceSatte,view);

        return view;
    }
         public void  initview (Bundle  savedInstanceState, View view){
             mMapView = (MapView) view.findViewById(R.id.map);
           //  mMapView.onCreate(savedInstanceState);// 此方法须覆写，虚拟机需要在很多情况下保存地图绘制的当前状态。
//初始化地图控制器对象
             if (aMap == null) {
                 aMap = mMapView.getMap();
             }
             SharedPreferencesUtils helper = new SharedPreferencesUtils(MapFragment.this.getContext(),"setting");
             LocationInfo  locationInfo = new LocationInfo();
             locationInfo.execute(" ");


         }
    private void addMarkerInfo(){
        SharedPreferencesUtils helper = new SharedPreferencesUtils(MapFragment.this.getContext(),"setting");
        ArrayList<Studentpr> result = (ArrayList<Studentpr>) getArguments().getSerializable("result");
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
            double lat = Double.parseDouble( showInfos.get(j).getLatitude());
            double lng = Double.parseDouble( showInfos.get(j).getLongtude());
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
    public class LocationInfo extends AsyncTask<String,Void,String>{

        @Override
        protected void onPostExecute(String value) {
            System.out.println(value);
            JSONArray arr = JSONArray.parseArray(value);

            for(int i = 0 ;i<arr.size();i++){
                MarkerInfo tem = new MarkerInfo();
                tem.setID(arr.getJSONObject(i).getString("ID"));
                tem.setLatitude(arr.getJSONObject(i).getString("Latitude"));
                tem.setLongtude(arr.getJSONObject(i).getString("Longtude"));
                infoList.add(tem);
            }
            addMarkerInfo();
        }
        @Override
        protected String doInBackground(String... strings) {
            String res = null;
            try{
                SharedPreferencesUtils helper = new SharedPreferencesUtils(MapFragment.this.getContext(),"setting");
                String locaid = helper.getString("LocaInfo");
                locaid = locaid.substring(4,locaid.length());
                System.out.println(locaid);
                if(locaid == null)
                {
                    Toast.makeText(MapFragment.this.getActivity(),"No Stored Info",Toast.LENGTH_SHORT);
                }

                String url = SQLUtil.serverhost+ URLConfigUtil.getURLMap()+"/"+ locaid;
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



    @Override
    public void onResume(){
        super.onResume();
        mMapView.onResume();
    }

    @Override
    public void onPause() {
        super.onPause();
        mMapView.onPause();
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        mMapView.onSaveInstanceState(outState);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        mMapView.onDestroy();

    }


}
