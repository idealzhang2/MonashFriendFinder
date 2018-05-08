package com.example.lenovo_pc.monashfriendfinder;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.content.Intent;
import android.graphics.Color;
import android.icu.util.Calendar;
import android.os.AsyncTask;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.example.lenovo_pc.monashfriendfinder.util.*;

import java.util.ArrayList;
import java.util.Date;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class UpdateActivity extends AppCompatActivity implements DatePicker.OnDateChangedListener{
    private final int DATE_DIALOG = 1;
    private int year;
    private int month;
    private int day;
    private Button btn;
    private TextView datedisplay;
    private CheckBox male;
    private CheckBox female;
    private Spinner funitsoinner;
    private EditText Text_ID;
    private EditText Text_Firstname;
    private EditText Text_Subname;
    private CheckBox checkBox;
    private CheckBox checkBox1;
    private TextView Text_DOB;
    private Spinner stumode;
    private Spinner suburb;
    private Spinner nation;
    private Spinner fsport;
    private Spinner nalanguage;
    private EditText Text_address;
    private EditText Text_fmovie;
    private EditText Text_Job;
    private EditText Text_email;
    private EditText Text_pwd;
    private EditText Text_repwd;
    private TextView note_ID;
    private TextView note_FirstName;
    private TextView note_SurName;
    private TextView note_Address;
    private TextView note_FMovie;
    private TextView note_CurJob;
    private TextView note_Email;
    private TextView note_PWD;
    private TextView note_REPWD;
    private String ID;
    private String FirstName;
    private String surname;
    private String Gender;
    private String Gender1;
    private String Course;
    private String DOB;
    private String StuMode;
    private String Suburb;
    private String Nation;
    private String Fsport;
    private String Nalanguage;
    private String address;
    private String fmovie;
    private String Job;
    private String email;
    private String pwd;
    private String repwd;
    private Button button;
    private Button cancel;
    private void initdata() {

        ID = Text_ID.getText().toString();
        FirstName = Text_Firstname.getText().toString();
        surname = Text_Subname.getText().toString();
        Gender = checkBox.getText().toString();
        Gender1 = checkBox1.getText().toString();
        Course = funitsoinner.getSelectedItem().toString();
        //    DOB = Text_DOB.getText().toString();
        StuMode = stumode.getSelectedItem().toString();
        Suburb = suburb.getSelectedItem().toString();
        Nation = nation.getSelectedItem().toString();
        Fsport = fsport.getSelectedItem().toString();
        Nalanguage = nalanguage.getSelectedItem().toString();
        address = Text_address.getText().toString();
        fmovie = Text_fmovie.getText().toString();
        Job = Text_Job.getText().toString();
        email = Text_email.getText().toString();
        pwd = Text_pwd.getText().toString();
        repwd = Text_repwd.getText().toString();

    }

    private DatePickerDialog.OnDateSetListener mdateListener = new DatePickerDialog.OnDateSetListener() {

        @Override
        public void onDateSet(DatePicker view, int myear, int monthOfYear,
                              int dayOfMonth) {
            year = myear;
            month = monthOfYear;
            day = dayOfMonth;
            display();
        }
    };
    @Override
    protected Dialog onCreateDialog(int id) {
        switch (id) {
            case DATE_DIALOG:
                return new DatePickerDialog(this, mdateListener, year, month, day);
        }
        return null;
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update);
        cancel = (Button)findViewById(R.id.cancel_button);
        cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                toCancel();
            }
        });
        button = (Button)findViewById(R.id.confirm_Button);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                toConfirm();
            }
        });

        male = (CheckBox) findViewById(R.id.checkbox);
        female = (CheckBox) findViewById(R.id.checkbox1);
        btn = (Button) findViewById(R.id.chooseDate);
        funitsoinner = (Spinner) findViewById(R.id.funitspinner);
        datedisplay = (TextView) findViewById(R.id.datedisplay);
        Text_ID = (EditText) findViewById(R.id.In_ID);
        Text_DOB = (TextView) findViewById(R.id.text_DOB);
        Text_Firstname = (EditText) findViewById(R.id.In_FirstName);
        Text_Subname = (EditText) findViewById(R.id.In_SurName);
        Text_address = (EditText) findViewById(R.id.In_Address);
        Text_fmovie = (EditText) findViewById(R.id.In_FMovie);
        Text_Job = (EditText) findViewById(R.id.In_CurJob);
        Text_email = (EditText) findViewById(R.id.In_Email);
        Text_pwd = (EditText) findViewById(R.id.In_PWD);
        Text_repwd = (EditText) findViewById(R.id.In_REPWD);
        checkBox = (CheckBox) findViewById(R.id.checkbox);
        checkBox1 = (CheckBox) findViewById(R.id.checkbox1);
        stumode = (Spinner) findViewById(R.id.StuModeSpinner);
        suburb = (Spinner) findViewById(R.id.SururbSpinner);
        nation = (Spinner) findViewById(R.id.NationalitySpinner);
        fsport = (Spinner) findViewById(R.id.FsportSpinner);

        nalanguage = (Spinner) findViewById(R.id.NalanguageSpinner);
        note_ID = (TextView) findViewById(R.id.note_ID);
        note_FirstName = (TextView) findViewById(R.id.note_FirstName);
        note_SurName = (TextView) findViewById(R.id.note_SurName);
        note_Address = (TextView) findViewById(R.id.note_Address);
        note_FMovie = (TextView) findViewById(R.id.note_FMovie);
        note_CurJob = (TextView) findViewById(R.id.note_CurJob);
        note_Email = (TextView) findViewById(R.id.note_Email);
        note_PWD = (TextView) findViewById(R.id.note_PWD);
        note_REPWD = (TextView)findViewById(R.id.note_REPWD);

        final Calendar ca = Calendar.getInstance();
        year = ca.get(Calendar.YEAR);
        month = ca.get(Calendar.MONTH);
        day = ca.get(Calendar.DAY_OF_MONTH);
        Text_repwd.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                note_REPWD.setText("");
            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
                if(Text_repwd.getText().toString().isEmpty()||!Text_repwd.getText().toString().equals(Text_pwd.getText().toString()))
                {
                    note_REPWD.setText("Cann't be Null or consistent with the pwd");
                }
            }
        });
        Text_pwd.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                note_PWD.setText("");
            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
                if(Text_pwd.getText().toString().isEmpty())
                {
                    note_PWD.setText("Cann't be Null");
                }else{

                }
            }
        });
        Text_email.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                note_Email.setText("");
            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
                if(Text_email.getText().toString().isEmpty())
                {
                    note_Email.setText("Cann't be Null");
                }else{
                    if(!RegExp.isEmail(Text_email.getText().toString())){
                        note_Email.setText("It not a Email Address");
                    }
                }
            }
        });
        Text_Job.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                note_CurJob.setText("");
            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
                if(Text_Job.getText().toString().isEmpty())
                {
                    note_CurJob.setText("Cann't be Null");
                }
            }
        });
        Text_fmovie.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                note_FMovie.setText("");
            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
                if(Text_fmovie.getText().toString().isEmpty())
                {
                    note_FMovie.setText("Cann't be Null");
                }
            }
        });
        Text_address.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                note_Address.setText("");
            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
                if(Text_address.getText().toString().isEmpty())
                {
                    note_Address.setText("Cann't be Null");
                }
            }
        });
        Text_Subname.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                note_SurName.setText("");
            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
                if(Text_Subname.getText().toString().isEmpty())
                {
                    note_SurName.setText("Cann't be Null");
                }
            }
        });
        Text_Firstname.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                note_FirstName.setText("");
            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
                if(Text_Firstname.getText().toString().isEmpty())
                {
                    note_FirstName.setText("Cann't be Null");
                }
            }
        });
        Text_ID.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                note_ID.setText("All should be 0-9 number");
            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
                Pattern p = Pattern.compile("/^\\d{8}$/");
                Matcher m = p.matcher(ID);
                if(m.find())
                {
                    note_ID.setText("Correct");
                }else{
                    note_ID.setBackgroundColor(Color.RED);
                    note_ID.setText("Error!");
                }
            }
        });


        male.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView,
                                         boolean isChecked) {
                // TODO Auto-generated method stub
                if (isChecked) {
                    if (female.isChecked()) {
                        female.setChecked(false);
                    }
                    Log.d("RegisterActivity", buttonView.getText() + "选中");
                } else {
                    Log.d("RegisterActivity", buttonView.getText() + "取消选中");
                }
            }
        });
        female.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView,
                                         boolean isChecked) {
                // TODO Auto-generated method stub
                if (isChecked) {
                    if (male.isChecked()) {
                        male.setChecked(false);
                    }
                    Log.d("RegisterActivity", buttonView.getText() + "选中");
                } else {
                    Log.d("RegisterActivity", buttonView.getText() + "取消选中");
                }
            }
        });

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showDialog(DATE_DIALOG);
            }
        });
        initdata();
    }

    @Override
    public void onDateChanged(DatePicker datePicker, int year, int month, int day) {
        this.year = year;
        this.month = month;
        this.day = day;
    }

    public void choosedate() {

    }

    public void toCancel() {
        this.finish();
    }

    public void toConfirm() {
        String gender = "Male";
        if(male.isChecked())
        {
            gender =  male.getText().toString();
        }
        if(female.isChecked())
        {
            gender = female.getText().toString();
        }
        java.text.SimpleDateFormat sdf = new java.text.SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ssZ");//注意格式化的表达式
        Date da = new Date();
        String time  = null;
        String funit = funitsoinner.getSelectedItem().toString();
        String date = datedisplay.getText().toString();
        String text_ID = Text_ID.getText().toString();
        String text_DOB = Text_DOB.getText().toString();
        String text_firname = Text_Firstname.getText().toString();
        String text_address =Text_address.getText().toString();
        String text_fmovie = Text_Job.getText().toString();
        String text_job = Text_Job.getText().toString();
        String text_email = Text_email.getText().toString();
        String text_pwd = Text_pwd.getText().toString();
        String  text_stumode = stumode.getSelectedItem().toString();
        String text_suburb = suburb.getSelectedItem().toString();
        String text_nation = nation.getSelectedItem().toString();
        String text_fsport = fsport.getSelectedItem().toString();
        String text_subname = Text_Subname.getText().toString();
        String text_nalanguage = nalanguage.getSelectedItem().toString();
        SharedPreferencesUtils helper = new SharedPreferencesUtils(UpdateActivity.this,"setting");
        String id = helper.getString("name");
        user tem = new user();
        tem.setSurname(text_subname);
        tem.setStuid(Integer.valueOf(id));
        tem.setAddress(text_address);
        tem.setCourse(funit);
        tem.setCurjob(text_job);
        tem.setDob(date);
        tem.setFirname(text_firname);
        tem.setSubtime(UTCDate.trans(sdf.format(da)));
        tem.setSuburb(text_suburb);
        tem.setFmovie(text_fmovie);
        tem.setNationality(text_nation);
        tem.setNalanguage(text_nalanguage);
        tem.setFsport(text_fsport);
        tem.setStumode(text_stumode);
        tem.setPwd(text_pwd);
        tem.setSubdate(UTCDate.trans(sdf.format(da)));
        tem.setMoemail(text_email);
        tem.setGender(gender);
        tem.setFunit(funit);
        String res= JSON.toJSONString(tem);
        MatchData mt = new MatchData();
        mt.execute(res);



    }

//    private class  MyThread  extends Thread {
//        private String res;
//        public MyThread(String s){
//            this.res = s;
//        }
//        public void setRes(String res ){
//            this.res = res;
//        }
//        @Override
//        public void run(){
//            try {
//                String url = SQLUtil.serverhost+ URLConfigUtil.getURLupdateprofile();
//                OkHttpClient client = new OkHttpClient();
//                RequestBody body = RequestBody.create( MediaType.parse("application/json; charset=utf-8"), res);
//                Request request = new Request.Builder()
//                        .url(url)
//                        .post(body)
//                        .build();
//                System.out.println(res);
//                Response response = client.newCall(request).execute();
//                Looper.prepare();
//                String r = response.body().string();
//                System.out.println(r);
//                if ("Success".equals(r)) {
//                    Message meg = new Message();
//                    meg.arg1 = 1;
//                    handler.sendMessage(meg);
//
//                } else {
//                    Message msg = new Message();
//                    msg.arg1 = 0;
//                    handler.sendMessage(msg);
//                }
//            } catch (Exception e) {
//                e.printStackTrace();
//            }
//        }
//    }
//
//    private Handler handler = new Handler() {
//
//        @Override
//        public void handleMessage(Message msg) {
//            // TODO Auto-generated method stub
//
//            if(1 == msg.arg1)
//            {
//                Toast.makeText(UpdateActivity.this,"Success",Toast.LENGTH_SHORT);
//            }else{
//                Toast.makeText(UpdateActivity.this,"Fail",Toast.LENGTH_SHORT);
//            }
//
//        }
//
//    };
    public void display() {
        datedisplay.setText(new StringBuffer().append(month + 1).append("-").append(day).append("-").append(year).append(" "));
    }
    private class MatchData extends AsyncTask<String, Void ,String> {
        @Override
        protected void onPostExecute(String value){
            super.onPostExecute(value);
            if ("Success".equals(value)) {
                Toast.makeText(UpdateActivity.this,"Success",Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(UpdateActivity.this,NavigateActivity.class);
                startActivity(intent);
            } else {
                Toast.makeText(UpdateActivity.this,"Fail",Toast.LENGTH_SHORT).show();
            }


        }


        @Override
        protected String doInBackground(String... strings) {
            String totalbyte = null;
            try {
                String url = SQLUtil.serverhost+ URLConfigUtil.getURLupdateprofile();
                OkHttpClient client = new OkHttpClient();
                RequestBody body = RequestBody.create( MediaType.parse("application/json; charset=utf-8"), strings[0]);
                Request request = new Request.Builder()
                        .url(url)
                        .post(body)
                        .build();
                System.out.println(strings[0]);
                Response response = client.newCall(request).execute();
                Looper.prepare();
                String r = response.body().string();
                System.out.println(r);
                return r;

            } catch (Exception e) {
                e.printStackTrace();
            }
            return totalbyte;
        }
    }
}
