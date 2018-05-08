package com.example.lenovo_pc.monashfriendfinder;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.CompoundButton;

public class MainActivity extends Activity implements View.OnClickListener,CompoundButton.OnCheckedChangeListener{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        startActivity(new Intent(this,LoginActivity.class));
        setContentView(R.layout.activity_main);

    }

    @Override
    public void onClick(View view) {

    }

    @Override
    public void onCheckedChanged(CompoundButton compoundButton, boolean b) {

    }
    public void login(View view) {
        startActivity(new Intent(this,LoginActivity.class));

    }

    public void exit(View view) {
        finish();
    }
}
