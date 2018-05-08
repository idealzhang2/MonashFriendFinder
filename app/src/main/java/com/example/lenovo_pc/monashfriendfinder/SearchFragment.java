package com.example.lenovo_pc.monashfriendfinder;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.os.Parcelable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.widget.LinearLayoutManager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.example.lenovo_pc.monashfriendfinder.util.Base64Utils;
import com.example.lenovo_pc.monashfriendfinder.util.SQLUtil;
import com.example.lenovo_pc.monashfriendfinder.util.Studentpr;
import com.example.lenovo_pc.monashfriendfinder.util.URLConfigUtil;
import com.example.lenovo_pc.monashfriendfinder.util.friendship;

import java.io.Serializable;
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
 * Created by lenovo-pc on 2018/4/22.
 */

public class SearchFragment extends Fragment {
    private View view;
    private String t;
    private ArrayList<Studentpr> transres = new ArrayList<Studentpr>();
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceSatte){
        view  = inflater.inflate(R.layout.search_fragment,container,false);
        LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity());
        Button matchbtn = (Button) view.findViewById(R.id.match);
        matchbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                match();
            }
        });
        Button mapdetail = (Button) view.findViewById(R.id.mapdetial);

        mapdetail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(),MapActivity.class);

                intent.putExtra("result", (Serializable) transres);
                startActivity(intent);
            }
        });
        return view;
    }
    public void match(){
        SharedPreferencesUtils helper = new SharedPreferencesUtils(SearchFragment.this.getActivity(),"setting");
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
        CheckBox fircheck = (CheckBox)view.findViewById(R.id.firstname);
        CheckBox surcheck = (CheckBox)view.findViewById(R.id.subname);
        CheckBox gendercheck = (CheckBox)view.findViewById(R.id.gender);
        CheckBox sportcheck = (CheckBox)view.findViewById(R.id.fsport);
        CheckBox moviecheck = (CheckBox)view.findViewById(R.id.fmovie);
        CheckBox coursecheck = (CheckBox)view.findViewById(R.id.course);
        CheckBox stumodecheck = (CheckBox)view.findViewById(R.id.stumode);
        CheckBox suburbcheck = (CheckBox)view.findViewById(R.id.suburb);
        CheckBox nationcheck = (CheckBox)view.findViewById(R.id.nation);
        CheckBox nalanguagecheck = (CheckBox)view.findViewById(R.id.nalanguage);
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
            Toast.makeText(SearchFragment.this.getActivity(),"缓存信息不全",Toast.LENGTH_SHORT).show();
        }else{
            MatchData task = new MatchData();
            task.execute(q);
        }

    }
    //根据给定的ID号添加friend关系到数据库中
    public  void addfriend(String FriID){
                Addfriend addfriend = new Addfriend();
        addfriend.execute(FriID);
    }

    public void addrows(final List<Studentpr> result){
        transres =(ArrayList<Studentpr>) result;
        LinearLayout context = (LinearLayout)view.findViewById(R.id.context);
        for(int i = 0;i < result.size();i++){
            LinearLayout tem = new LinearLayout(SearchFragment.this.getContext());
            tem.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.FILL_PARENT,60));
            tem.setOrientation(LinearLayout.HORIZONTAL);
            final Studentpr communicate = result.get(i);
            ViewGroup.LayoutParams llp = new ViewGroup.LayoutParams(80, 60);
            Button detail = new Button(SearchFragment.this.getContext());
            detail.setLayoutParams(llp);
            detail.setText("More");
            detail.setBackgroundResource(R.drawable.shape);
            detail.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Bundle bundle = new Bundle();
                    bundle.putSerializable("key",communicate);
                    DetailFragment detailFragment = new DetailFragment();
                    detailFragment.setArguments(bundle);
                    FragmentTransaction transaction = getFragmentManager().beginTransaction();
                    if(!detailFragment.isAdded()){
                        transaction.add(R.id.Main_Frame,detailFragment).hide(SearchFragment.this).addToBackStack(null).commitAllowingStateLoss();
                    }else{
                        transaction.hide(SearchFragment.this).show(detailFragment).addToBackStack(null).commitAllowingStateLoss();
                    }
                }
            });
            tem.addView(detail);
            Button add = new Button(SearchFragment.this.getContext());
            add.setLayoutParams(llp);
            add.setText("Add");
            add.setBackgroundResource(R.drawable.shape);
            add.setOnClickListener(new MyMonTvOnLongClickListener(Integer.valueOf(result.get(i).getStuid())));
            tem.addView(add);
            TextView id = new TextView(SearchFragment.this.getContext());
            id.setText(result.get(i).getStuid());
            id.setLayoutParams(llp);
            id.setBackgroundResource(R.drawable.shape);
            tem.addView(id);

            TextView firname = new TextView(SearchFragment.this.getContext());
            firname.setText(result.get(i).getFirname());
            firname.setLayoutParams(llp);
            firname.setBackgroundResource(R.drawable.shape);
            tem.addView(firname);
            TextView fm = new TextView(SearchFragment.this.getContext());
            fm.setText(result.get(i).getFmovie());
            fm.setLayoutParams(llp);
            fm.setBackgroundResource(R.drawable.shape);
            fm.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent = new Intent(SearchFragment.this.getActivity(),FmovieActivity.class);
                    startActivity(intent);
                }
            });
            tem.addView(fm);



            TextView surname = new TextView(SearchFragment.this.getContext());
            surname.setText(result.get(i).getSurname());
            surname.setLayoutParams(llp);
            surname.setBackgroundResource(R.drawable.shape);
            tem.addView(surname);


            TextView gender = new TextView(SearchFragment.this.getContext());
            gender.setText(result.get(i).getGender());
            gender.setLayoutParams(llp);
            gender.setBackgroundResource(R.drawable.shape);
            tem.addView(gender);

            TextView email = new TextView(SearchFragment.this.getContext());
            email.setText(result.get(i).getDob());
            email.setLayoutParams(llp);
            email.setBackgroundResource(R.drawable.shape);
            tem.addView(email);



            context.addView(tem);
        }

        SharedPreferencesUtils helper = new SharedPreferencesUtils(SearchFragment.this.getContext(),"setting");
        ArrayList<String>   LocaID = new ArrayList<String>();
        String Locainfo = null;
        for(int j = 0; j < result.size(); j++)
        {
            if(Locainfo == null){
                Locainfo += "("+result.get(j).getStuid();
            }else if(j == result.size()-1){
                Locainfo += ","+ result.get(j).getStuid()+")";
            }else{
                Locainfo += ","+ result.get(j).getStuid();
            }

        }
//        for(int t = 0;t<result.size();t++ ){
//            Studentpr stu = result.get(t);
//            String tem = null;
//            tem += stu.getStuid()+"-"+stu.getFirname()+ "-"+stu.getSurname()+"-"+stu.getDob()+"-"+stu.getGender()+"-"+stu.getCourse()+"-"+stu.getStumode()+"-"+stu.getMoemail()+"-"+stu.getSubdate()+"-"+stu.getSubtime()+"-"+stu.getSuburb()+"-"+stu.getNationality()+"-"+stu.getNalanguage()+"-"+stu.getFsport()+"-"+stu.getFmovie()+"-"+stu.getFunit()+"-"+stu.getCurjob()+"-"+stu.getAddress();
//            helper.putValues(new SharedPreferencesUtils.ContentValue(stu.getStuid(), tem));
//        }
        System.out.println(Locainfo);
        helper.putValues(new SharedPreferencesUtils.ContentValue("LocaInfo", Locainfo));

    }
    private class MyMonTvOnLongClickListener implements View.OnClickListener {
        private int temp;

        public MyMonTvOnLongClickListener(int i) {
// TODO Auto-generated constructor stub
            temp = i;
        }

        @Override
        public void onClick(View view) {
            addfriend(String.valueOf(temp));
        }
    }

        private class Addfriend extends AsyncTask<String,Void,String>{
        @Override
        protected void onPostExecute(String value) {
            if("Success".equals(value))
            {
                Toast.makeText(SearchFragment.this.getActivity(),"Success",Toast.LENGTH_SHORT).show();

            }else if("Fail".equals(value)){
                Toast.makeText(SearchFragment.this.getActivity(),"Fail",Toast.LENGTH_SHORT).show();
            }
            else if("Exist".equals(value)){
                Toast.makeText(SearchFragment.this.getActivity(),"Exist",Toast.LENGTH_SHORT).show();
            }
        }
        @Override
        protected String doInBackground(String... strings) {
            String res = null;
            try{
                SharedPreferencesUtils helper = new SharedPreferencesUtils(SearchFragment.this.getActivity(),"setting");
                String name = helper.getString("name");
                if(name == null)
                {
                    Toast.makeText(SearchFragment.this.getActivity(),"Haven't login",Toast.LENGTH_SHORT);
                }
                SimpleDateFormat format1 = new SimpleDateFormat("yyyy-MM-dd");//注意格式化的表达式
                String d = format1.format(new Date());

                d += " 00:00:00'";
                d = "'" + d;
                friendship  ship = new friendship();
                ship.setFriendshipid("9");
                ship.setSid(name);
                ship.setFriendid(strings[0]);
                ship.setSdate(d);
                ship.setEdate(d);
                String  obj = JSONObject.toJSONString(ship);
                String url = SQLUtil.serverhost+URLConfigUtil.getURLfriend();
                OkHttpClient client = new OkHttpClient();
                RequestBody body = RequestBody.create( MediaType.parse("application/json; charset=utf-8"), obj);
                Request request = new Request.Builder()
                        .url(url)
                        .post(body)
                        .build();
                System.out.println(url +"   "+ obj);
                Response response = client.newCall(request).execute();
                res = response.body().string();
                System.out.println(res);
            }catch(Exception e){

            }
            return res;
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
