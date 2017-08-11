package com.example.asus.dailyscore;

import android.graphics.drawable.Drawable;
import android.graphics.drawable.shapes.Shape;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;

import com.prolificinteractive.materialcalendarview.CalendarDay;
import com.prolificinteractive.materialcalendarview.DayViewDecorator;
import com.prolificinteractive.materialcalendarview.DayViewFacade;
import com.prolificinteractive.materialcalendarview.MaterialCalendarView;

import java.util.Calendar;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;

/**
 * Created by asus on 2017/7/29.
 */

public class ScoreActivity extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.scorelayout);
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) actionBar.hide();

        }
    }