package com.example.lenovo_pc.monashfriendfinder.util;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by lenovo-pc on 2018/4/23.
 */

public class UTCDate {
    public static String currentdate(){
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ssZ");//注意格式化的表达式
        String d = format.format(new Date());
        d = d.substring(0,d.length()-2);
        d += ":00";
        return d;
    }
    public static String trans (String  str ){
        str = str.substring(0,str.length()-2);
        str += ":00";
        return str;

    }
}
