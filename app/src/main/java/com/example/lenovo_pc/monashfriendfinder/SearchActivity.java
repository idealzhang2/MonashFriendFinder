package com.example.lenovo_pc.monashfriendfinder;


import android.graphics.Color;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.example.lenovo_pc.monashfriendfinder.util.*;

import org.json.JSONException;

import java.util.ArrayList;
import java.util.List;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class SearchActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);
        Button matchbtn = (Button)findViewById(R.id.match);
        matchbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                match();
            }
        });

    }

    public void match(){
        SharedPreferencesUtils helper = new SharedPreferencesUtils(SearchActivity.this,"setting");
        String attr = null,value= null;
        String stuid = helper.getString("name");
        String  firname =  helper.getString("firname");
        String   surname = helper.getString("surname");
        String  fsport = helper.getString("fsport");
        String fmovie = helper.getString("fmovie");
        String gener = helper.getString("gender");
        String  course = helper.getString("course");
        String stumode = helper.getString("stumode");
        String suburb = helper.getString("suburb");
        String nation = helper.getString("nation");
        String language = helper.getString("nalanguage");
        CheckBox fircheck = (CheckBox)findViewById(R.id.firstname);
        CheckBox surcheck = (CheckBox)findViewById(R.id.subname);
        CheckBox gendercheck = (CheckBox)findViewById(R.id.gender);
        CheckBox sportcheck = (CheckBox)findViewById(R.id.fsport);
        CheckBox moviecheck = (CheckBox)findViewById(R.id.fmovie);
        CheckBox coursecheck = (CheckBox)findViewById(R.id.course);
        CheckBox stumodecheck = (CheckBox)findViewById(R.id.stumode);
        CheckBox suburbcheck = (CheckBox)findViewById(R.id.suburb);
        CheckBox nationcheck = (CheckBox)findViewById(R.id.nation);
        CheckBox nalanguagecheck = (CheckBox)findViewById(R.id.nalanguage);
        if(fircheck.isChecked()&&(firname != null)){
            attr += "|"+"firname";
            value += "|" + firname;
        }
        if(surcheck.isChecked()&& (surname != null)){
            attr += "|"+"surname";
            value += "|" + surname;
        }
        if(gendercheck.isChecked()&& (gener != null)){
            attr += "|"+"gender";
            value += "|" + gener;
        }
        if(sportcheck.isChecked()&&(fsport != null)){
            attr += "|"+"fsport";
            value += "|" + fsport;
        }
        if(moviecheck.isChecked()&&(fmovie != null)){
            attr += "|"+"fmovie";
            value += "|" + fmovie;
        }
        if(coursecheck.isChecked()&&(course != null)){
            attr += "|"+"course";
            value += "|" + course;
        }
        if(stumodecheck.isChecked()&&(stumode != null)){
            attr += "|"+"stumode";
            value += "|" + stumode;
        }
        if(suburbcheck.isChecked()&&(suburb != null)){
            attr += "|"+"suburb";
            value += "|" + suburb;
        }
        if(nationcheck.isChecked()&&(nation != null)){
            attr += "|"+"nation";
            value += "|" + nation;
        }
        if(nalanguagecheck.isChecked()&&(language != null)){
            attr += "|"+"nalanguage";
            value += "|" + language;
        }

        if(attr.length()>0){
            attr = attr.substring(5,attr.length());
        }
        if(value.length()>0){
            value = value.substring(5,value.length());
        }
        String q;
        if(stuid==null && attr==null&&value==null){
            q = null;
        }else{
            q = "/" + stuid+"/"+attr+"/"+value;
        }
       // System.out.println(q);
        if(q == null){
            Toast.makeText(SearchActivity.this,"缓存信息不全",Toast.LENGTH_SHORT).show();
        }else{
            MatchData task = new MatchData();
            task.execute(q);
        }

    }
    //根据给定的ID号添加friend关系到数据库中
    public  boolean addfriend(String FriID){
            try{
//                OkHttpClient client = new OkHttpClient();
//
//                String url = SQLUtil.serverhost+ URLConfigUtil.getURLfindByManyAttributes()+ strings[0];
//                Log.d("TestActivity", url);
//                Request request = new Request.Builder().url(url).header("User-Agent", "OkHttp Headers.java")
//                        .addHeader("Accept", "application/json; q=0.5")
//                        .addHeader("Accept", "application/vnd.github.v3+json").build();
//                Response response = client.newCall(request).execute();
//                String responseData = response.body().string();
//                totalbyte = responseData;
//                System.out.println(totalbyte);

            }catch(Exception e){

            }
        return true;
    }
    public void addrows(final List<Studentpr> result){
        LinearLayout context = (LinearLayout)findViewById(R.id.context);
        for(int i = 0;i < result.size();i++){
            LinearLayout tem = new LinearLayout(this);
            tem.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.FILL_PARENT,60));
            tem.setOrientation(LinearLayout.HORIZONTAL);

            ViewGroup.LayoutParams llp = new ViewGroup.LayoutParams(140, 60);
            final String t = result.get(i).getStuid();
            Button add = new Button(this);
            add.setLayoutParams(llp);
            add.setText("Add");
            add.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if(addfriend(t)){
                        Toast.makeText(SearchActivity.this,"Now,He/She is your friend!",Toast.LENGTH_SHORT).show();
                    }else{
                        Toast.makeText(SearchActivity.this,"He/She has been your friend",Toast.LENGTH_SHORT).show();
                    }
                }
            });
            tem.addView(add);
            TextView id = new TextView(this);
            id.setText(result.get(i).getStuid());
            id.setLayoutParams(llp);
            id.setBackgroundResource(R.color.blue);
            tem.addView(id);

            TextView firname = new TextView(this);
            firname.setText(result.get(i).getFirname());
            firname.setLayoutParams(llp);
            firname.setBackgroundResource(R.color.blue);
            tem.addView(firname);

            TextView surname = new TextView(this);
            surname.setText(result.get(i).getSurname());
            surname.setLayoutParams(llp);
            surname.setBackgroundResource(R.color.blue);
            tem.addView(surname);

            TextView gender = new TextView(this);
            gender.setText(result.get(i).getGender());
            gender.setLayoutParams(llp);
            gender.setBackgroundResource(R.color.blue);
            tem.addView(gender);

            TextView email = new TextView(this);
            email.setText(result.get(i).getDob());
            email.setLayoutParams(llp);
            email.setBackgroundResource(R.color.blue);
            tem.addView(email);
            context.addView(tem);
        }



    }

    private class MatchData extends AsyncTask<String, Void ,String> {
        @Override
        protected void onPostExecute(String value){
            super.onPostExecute(value);
            ArrayList<Studentpr> result = new ArrayList<Studentpr>();
            try {
                JSONArray arr = JSONArray.parseArray(value);
                for(int i = 0;i < arr.size();i++){
                    JSONObject tem = arr.getJSONObject(i);
                        Studentpr stu = new Studentpr();
                        stu.setStuid(tem.getString("stuid"));
                        stu.setFirname(tem.getString("firname"));
                        stu.setSurname(tem.getString("surname"));
                        stu.setDob(tem.getString("dob"));
                        stu.setGender(tem.getString("gender"));
                        stu.setCourse(tem.getString("course"));
                        stu.setStumode(tem.getString("stumode"));
                        stu.setMoemail(tem.getString("moemail"));
                        stu.setSubdate(tem.getString("subdate"));
                        stu.setSubtime(tem.getString("subtime"));
                        stu.setSuburb(tem.getString("suburb"));
                        stu.setNationality(tem.getString("nationality"));
                        stu.setNalanguage(tem.getString("nalanguage"));
                        stu.setFsport(tem.getString("fsport"));
                        stu.setFmovie(tem.getString("fmovie"));
                        stu.setFunit(tem.getString("funit"));
                        stu.setCurjob(tem.getString("curjob"));
                        stu.setAddress(tem.getString("address"));
                        result.add(stu);
                }
                    addrows(result);
            } catch (Exception e) {
                e.printStackTrace();
            }

        }


        @Override
        protected String doInBackground(String... strings) {
            String totalbyte = null;
            try {
                OkHttpClient client = new OkHttpClient();

                String url = SQLUtil.serverhost+ URLConfigUtil.getURLfindByManyAttributes()+ strings[0];
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
