package com.example.lenovo_pc.monashfriendfinder;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.graphics.Color;
import android.icu.util.Calendar;
import android.location.GnssClock;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.widget.LinearLayoutManager;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.alibaba.fastjson.JSON;
import com.example.lenovo_pc.monashfriendfinder.util.RegExp;
import com.example.lenovo_pc.monashfriendfinder.util.SQLUtil;
import com.example.lenovo_pc.monashfriendfinder.util.Studentpr;
import com.example.lenovo_pc.monashfriendfinder.util.URLConfigUtil;
import com.example.lenovo_pc.monashfriendfinder.util.UTCDate;
import com.example.lenovo_pc.monashfriendfinder.util.user;

import java.util.Date;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

/**
 * Created by lenovo-pc on 2018/4/23.
 */

public class DetailFragment extends Fragment{
    private View view;
    private TextView ID;
    private TextView Firname;
    private TextView Surname;
    private TextView DOB;
    private TextView Gender;
    private TextView Course;
    private TextView Stumode;
    private TextView Moemail;
    private TextView Subdate;
    private TextView Subtime;
    private TextView Suburb;
    private TextView Nationality;
    private TextView Nalanguage;
    private TextView Fsport;
    private TextView Fmovie;
    private TextView Funit;
    private TextView Curjob;
    private TextView Address;



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceSatte){
        view  = inflater.inflate(R.layout.detail_fragment,container,false);
        initview();
        setview();
        return view;
    }
    public void setview(){
        Studentpr tem = (Studentpr) getArguments().getSerializable("key");
        if(tem != null){
            ID.setText(tem.getStuid());
            Firname.setText(tem.getFirname());
            Surname.setText(tem.getSurname());
            DOB.setText(tem.getDob());
            Gender.setText(tem.getGender());
            Course.setText(tem.getCourse());
            Stumode.setText(tem.getStumode());
            Moemail.setText(tem.getMoemail());
            Subdate.setText(tem.getSubdate());
            Subtime.setText(tem.getSubtime());
            Suburb.setText(tem.getSuburb());
            Nationality.setText(tem.getNationality());
            Nalanguage.setText(tem.getNalanguage());
            Fsport.setText(tem.getFsport());
            Fmovie.setText(tem.getFmovie());
            Funit.setText(tem.getFunit());
            Curjob.setText(tem.getCurjob());
            Address.setText(tem.getAddress());
        }

    }
    public void initview(){
        ID = (TextView) view.findViewById(R.id.Text_ID);
        Firname = (TextView) view.findViewById(R.id.Text_Firname);
        Surname = (TextView) view.findViewById(R.id.Text_Surname);
        DOB = (TextView) view.findViewById(R.id.Text_DOB);
        Gender = (TextView) view.findViewById(R.id.Text_Gender);
        Course = (TextView) view.findViewById(R.id.Text_Course);
         Stumode = (TextView) view.findViewById(R.id.Text_Stumode);
        Moemail = (TextView) view.findViewById(R.id.Text_Moemail);
        Subdate = (TextView) view.findViewById(R.id.Text_Subdate);
        Subtime = (TextView) view.findViewById(R.id.Text_Subtime);
        Suburb = (TextView) view.findViewById(R.id.Text_Suburb);
        Nationality = (TextView) view.findViewById(R.id.Text_Nationality);
        Nalanguage = (TextView) view.findViewById(R.id.Text_Nalanguage);
        Fsport = (TextView) view.findViewById(R.id.Text_Fsport);
        Fmovie = (TextView) view.findViewById(R.id.Text_Fmovie);
        Funit = (TextView) view.findViewById(R.id.Text_Funit);
        Curjob = (TextView) view.findViewById(R.id.Text_Curjob);
        Address = (TextView) view.findViewById(R.id.Text_Address);





    }
}
