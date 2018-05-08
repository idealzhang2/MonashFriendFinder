package com.example.lenovo_pc.monashfriendfinder;

import android.graphics.drawable.Drawable;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.example.lenovo_pc.monashfriendfinder.util.*;

import java.util.ArrayList;
import java.util.List;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class FriendsActivity extends AppCompatActivity {
    private SharedPreferencesUtils helper;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_friends);
         helper = new SharedPreferencesUtils(this,"setting");
        String id = helper.getString("name");
        MatchData data = new MatchData();        
        data.execute(id);
    }
    public void detail(){
        SharedPreferencesUtils helper = new SharedPreferencesUtils(this,"setting");

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
            add.setText("Delete");
            add.setBackgroundResource(R.drawable.shape);
            add.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    deletefriend(t);
                }
            });
            tem.addView(add);
            TextView id = new TextView(this);
            id.setText(result.get(i).getStuid());
            id.setLayoutParams(llp);
            id.setBackgroundResource(R.drawable.shape);
           // id.setBackgroundResource(R.color.blue);
            tem.addView(id);

            TextView firname = new TextView(this);
            firname.setText(result.get(i).getFirname());
            firname.setLayoutParams(llp);
            firname.setBackgroundResource(R.drawable.shape);
           // firname.setBackgroundResource(R.color.blue);
            tem.addView(firname);

            TextView surname = new TextView(this);
            surname.setText(result.get(i).getSurname());
            surname.setLayoutParams(llp);
            surname.setBackgroundResource(R.drawable.shape);
           // surname.setBackgroundResource(R.color.blue);
            tem.addView(surname);

            TextView gender = new TextView(this);
            gender.setText(result.get(i).getGender());
            gender.setLayoutParams(llp);
            gender.setBackgroundResource(R.drawable.shape);
            //gender.setBackgroundResource(R.color.blue);
            tem.addView(gender);

            TextView email = new TextView(this);
            email.setText(result.get(i).getDob());
            email.setLayoutParams(llp);
            email.setBackgroundResource(R.drawable.shape);
           // email.setBackgroundResource(R.color.blue);
            tem.addView(email);
            context.addView(tem);
        }
    }
    public ArrayList<Studentpr> receivefriends(String id){
        ArrayList<Studentpr>  res = new ArrayList<Studentpr>();

        return res;
    }
    public void deletefriend(String id){
      DeleteData dt = new DeleteData();
        dt.execute(id);
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

                String url = SQLUtil.serverhost+ URLConfigUtil.getURLfriends()+"/"+ strings[0];
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
    private class DeleteData extends AsyncTask<String, Void ,String> {
        @Override
        protected void onPostExecute(String value){
            super.onPostExecute(value);
          if("Success".equals(value)){
              Toast.makeText(FriendsActivity.this,"Success!",Toast.LENGTH_SHORT).show();
          }else{
              Toast.makeText(FriendsActivity.this,"Fail",Toast.LENGTH_SHORT).show();

          }

        }


        @Override
        protected String doInBackground(String... strings) {
            String totalbyte = null;

            try{
                OkHttpClient client = new OkHttpClient();
                String name = helper.getString("name");
                String url = SQLUtil.serverhost+ URLConfigUtil.getURLdeletefriend()+"/"+ Integer.valueOf(strings[0])+"/"+Integer.valueOf(name);
                Log.d("TestActivity", url);
                Request request = new Request.Builder().url(url).build();
                Response response = client.newCall(request).execute();
                String responseData = response.body().string();
                totalbyte = responseData;
                System.out.println(responseData);
            }catch(Exception e){
                e.printStackTrace();
            }

            return totalbyte;
        }
    }
}
