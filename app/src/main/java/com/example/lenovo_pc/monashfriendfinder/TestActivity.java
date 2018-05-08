package com.example.lenovo_pc.monashfriendfinder;

import android.app.Activity;
import android.app.DownloadManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup.LayoutParams;
import android.view.textservice.TextInfo;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TextView;

import com.example.lenovo_pc.monashfriendfinder.util.SQLUtil;
import com.example.lenovo_pc.monashfriendfinder.util.URLConfigUtil;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.InetAddress;
import java.net.URL;
import java.util.zip.InflaterOutputStream;

import javax.net.ssl.HttpsURLConnection;

import okhttp3.FormBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class TestActivity extends Activity implements View.OnClickListener {
    private  TextView textView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test);
        Button sendrequest = (Button)findViewById(R.id.send_request);
        LinearLayout container = (LinearLayout) findViewById(R.id.repeatlay);
        for(int  i = 0; i< 4;i++) {

            LinearLayout tem = new LinearLayout(this);
            tem.setLayoutParams(new LayoutParams(LayoutParams.FILL_PARENT,60));
            tem.setOrientation(LinearLayout.HORIZONTAL);
            for(int j = 0; j<4;j++) {

            }
            container.addView(tem);
        }
        textView = (TextView) findViewById(R.id.test);
        sendrequest.setOnClickListener(this);

    }
    private void URLConnection(){

    }

    @Override
    public void onClick(View view) {
        if(view.getId() == R.id.send_request){
            sendRequestWithURLConnection();
        }
    }

    private void showResponse(final String response){
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
               textView.setText(response);
            }
        });
    }


    private void sendRequestWithURLConnection(){
        new Thread(new Runnable() {
            @Override
            public void run() {

                try{
                    OkHttpClient  client = new OkHttpClient();
                    String url = SQLUtil.serverhost + URLConfigUtil.getURLfindByUNIT();
                    Log.d("TestActivity", url);
                    Request request = new Request.Builder().url(url).build();
                    Response response = client.newCall(request).execute();
                    String responseData = response.body().string();
                    Log.d("TestActivity",responseData);
                    showResponse(responseData);
                }catch(Exception e){
                    e.printStackTrace();
                }
            }
        }).start();
    }
    private void OkHttpPost(){
        OkHttpClient client = new OkHttpClient();
        RequestBody requestBody = new FormBody.Builder().build();
    }
}
