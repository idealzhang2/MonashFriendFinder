package com.example.lenovo_pc.monashfriendfinder.util;

import java.util.List;

/**
 * Created by lenovo-pc on 2018/4/19.
 */

public class Data{
    //  里面大括号的字段封装 setter getter toString
//  该类中包含有数组形和对象形，需要一并的封装在里面
    public String wendu;
    public String ganmao;
    public List<Forecast> forecast;
    public Yesterday yesterday;
    public String aqi;
    public String city;
    @Override
    public String toString() {
        return "Data [wendu=" + wendu + ", ganmao=" + ganmao + ", forecast="
                + forecast + ", yesterday=" + yesterday + ", aqi=" + aqi
                + ", city=" + city + "]";
    }


}
