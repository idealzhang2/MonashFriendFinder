package com.example.lenovo_pc.monashfriendfinder;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.icu.util.Calendar;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.TextView;
import android.widget.Toast;

public class NavigateActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener,View.OnClickListener, DatePicker.OnDateChangedListener,FriendsFragment.OnFragmentInteractionListener {
    private ProgressDialog p_dialog;
    private TextView  starttext;
    private TextView  endtext;
    private final int DATE_DIALOG = 1;
    private final int DATE_DIALOG1 = 2;
    private int year;
    private int month;
    private int day;
    private int eyear;
    private int emonth;
    private int eday;
    private TextView profile;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_navigate);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
        final Calendar ca = Calendar.getInstance();
        year = ca.get(Calendar.YEAR);
        month = ca.get(Calendar.MONTH);
        day = ca.get(Calendar.DAY_OF_MONTH);

        replaceFragment(new homefragment());
    }
     private void Chart(){
         LayoutInflater factory = LayoutInflater
                 .from(NavigateActivity.this);
         final View DialogView = factory.inflate(
                 R.layout.dialog, null);
         final Button  startbtn = (Button) DialogView.findViewById(R.id.choosestart);
         Button endate = (Button) DialogView.findViewById(R.id.chooseEndate);
         starttext =(TextView) DialogView.findViewById(R.id.startdate);
         endtext =(TextView) DialogView.findViewById(R.id.enddate_text);
         startbtn.setOnClickListener(new View.OnClickListener() {
             @Override
             public void onClick(View view) {
                 showDialog(1);
             }
         });

         endate.setOnClickListener(new View.OnClickListener() {
             @Override
             public void onClick(View view) {
                 showDialog(DATE_DIALOG1);
             }
         });
         AlertDialog dlg = new AlertDialog.Builder(
                 NavigateActivity.this)
                 .setTitle("输入日期范围")
                 .setView(DialogView)
                 .setPositiveButton("确定",
                         new DialogInterface.OnClickListener() {

                             @Override
                             public void onClick(
                                     DialogInterface dialog,
                                     int which) {
                                 if(starttext == null || endtext == null)
                                 {
                                     Toast.makeText(NavigateActivity.this,"Init Fail",Toast.LENGTH_SHORT);
                                 }else {
                                     String stbtn = starttext.getText().toString();
                                     String enbtn = endtext.getText().toString();
                                     if(startbtn == null || enbtn == null){
                                         Toast.makeText(NavigateActivity.this,"No Time",Toast.LENGTH_SHORT);
                                     }else{
                                         Intent intent = new Intent(NavigateActivity.this,ChartActivity.class);
                                         intent.putExtra("startdate",stbtn);
                                         intent.putExtra("enddate",enbtn);
                                         startActivity(intent);
                                     }
                                 }

                             }
                         })
                 .setNegativeButton("取消",
                         new DialogInterface.OnClickListener() {

                             @Override
                             public void onClick(
                                     DialogInterface dialog,
                                     int which) {
                                 // TODO Auto-generated method
                                 // stub
                                 dialog.dismiss();
                             }
                         }).create();
         dlg.show();

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

    private DatePickerDialog.OnDateSetListener edateListener = new DatePickerDialog.OnDateSetListener() {

        @Override
        public void onDateSet(DatePicker view, int myear, int monthOfYear,
                              int dayOfMonth) {
            year = myear;
            month = monthOfYear;
            day = dayOfMonth;
            display1();
        }
    };
    public void display1(){

        endtext.setText(new StringBuffer().append(year).append("-").append(month + 1).append("-").append(day).append(" "));
    }
    public void display() {
        starttext.setText(new StringBuffer().append(year).append("-").append(month + 1).append("-").append(day).append(" "));
    }
    @Override
    protected Dialog onCreateDialog(int id) {
        switch (id) {
            case DATE_DIALOG:
                return new DatePickerDialog(this, mdateListener, year, month, day);
            case 2:  return new DatePickerDialog(this,edateListener,year,month,day);
        }
        return null;
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.navigate, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_camera) {
           replaceFragment(new homefragment());
        } else if (id == R.id.nav_gallery) {
            replaceFragment(new SearchFragment());
        } else if (id == R.id.nav_slideshow) {
            Intent intent = new Intent(NavigateActivity.this,FriendMapActivity.class);
            startActivity(intent);
        } else if (id == R.id.nav_manage) {
            Intent intent = new Intent(NavigateActivity.this,PieChartActivity.class);
            startActivity(intent);
        } else if (id == R.id.nav_share) {
            Intent intent = new Intent(NavigateActivity.this,FriendsActivity.class);
            startActivity(intent);
           // replaceFragment(new FriendsFragment());
        } else if (id == R.id.nav_send) {
            Chart();
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
    private void replaceFragment(Fragment  fragment){
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction transaction = fragmentManager.beginTransaction();
        transaction.replace(R.id.Main_Frame,fragment);
        transaction.addToBackStack(null);
        transaction.commit();
    }
    @Override
    public void onClick(View view) {

    }

    @Override
    public void onDateChanged(DatePicker datePicker, int i, int i1, int i2) {
        this.year = i;
        this.month = i1;
        this.day = i2;
    }

    @Override
    public void onFragmentInteraction(Uri uri) {

    }
}
