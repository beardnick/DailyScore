package com.example.asus.dailyscore;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.view.Window;

/**
 * Created by asus on 2017/7/29.
 */

public class PlantActivity extends AppCompatActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.plantlayout);
        ActionBar actionBar = getSupportActionBar();
        if(actionBar != null)actionBar.hide();
    }
}
