package com.example.lenovo_pc.monashfriendfinder;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.amap.api.maps.AMap;
import com.amap.api.maps.MapView;
import com.amap.api.maps.model.Marker;
import com.example.lenovo_pc.monashfriendfinder.util.MarkerInfo;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by lenovo-pc on 2018/4/24.
 */

public class FriendMapFragment  extends Fragment {
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
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceSatte){
        view  = inflater.inflate(R.layout.friendmap_fragment,container,false);

        mMapView = (MapView) view.findViewById(R.id.map);
        mMapView.onCreate(savedInstanceSatte);// 此方法须覆写，虚拟机需要在很多情况下保存地图绘制的当前状态。
//初始化地图控制器对象
        if (aMap == null) {
            aMap = mMapView.getMap();
        }



        return view;
    }
}
