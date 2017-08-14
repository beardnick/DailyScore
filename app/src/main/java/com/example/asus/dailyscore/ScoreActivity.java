package com.example.asus.dailyscore;

import android.content.SharedPreferences;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.shapes.Shape;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.view.KeyEvent;
import android.view.View;

import com.example.asus.dailyscore.HobbyHelper.HobbyStore;
import com.prolificinteractive.materialcalendarview.CalendarDay;
import com.prolificinteractive.materialcalendarview.DayViewDecorator;
import com.prolificinteractive.materialcalendarview.DayViewFacade;
import com.prolificinteractive.materialcalendarview.MaterialCalendarView;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collection;
import java.util.Date;
import java.util.HashSet;
import java.util.List;

import lecho.lib.hellocharts.gesture.ZoomType;
import lecho.lib.hellocharts.model.Axis;
import lecho.lib.hellocharts.model.AxisValue;
import lecho.lib.hellocharts.model.Line;
import lecho.lib.hellocharts.model.LineChartData;
import lecho.lib.hellocharts.model.PointValue;
import lecho.lib.hellocharts.model.ValueShape;
import lecho.lib.hellocharts.model.Viewport;
import lecho.lib.hellocharts.view.LineChartView;

/**
 * Created by asus on 2017/7/29.
 */

public class ScoreActivity extends AppCompatActivity {
    private LineChartView mLineChartView;
    private List<PointValue> mPointValues = new ArrayList<PointValue>();
    private List<AxisValue> mAxisValueList = new ArrayList<AxisValue>();
    private Calendar mCalendar ;
    private SharedPreferences preferences;
    private SharedPreferences.Editor editor;
    private HobbyStore mHobbyStore;
    private int [][] a = {{31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31},
            {31, 29, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31}};

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.scorelayout);
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) actionBar.hide();
        preferences = getSharedPreferences("data",MODE_PRIVATE);
        editor = getSharedPreferences("data",MODE_PRIVATE).edit();
        mLineChartView = (LineChartView) findViewById(R.id.score_chart);
        mHobbyStore = new HobbyStore(preferences,editor);
        getX();
        getScore();
        initLineChart();
        }

        private void getX() {
            mCalendar = Calendar.getInstance();
            Date date = mCalendar.getTime();
            int year = date.getYear();
            int month = date.getMonth();
            if (((year % 4 == 0) || (year % 400 == 0)) && (year % 100 != 0)) {
                for (int i = 0; i < a[1][month - 1]; i++) {
                    mAxisValueList.add(new AxisValue(i).setLabel(Integer.valueOf(i + 1).toString()));
                }
            } else {
                for (int i = 0; i < a[0][month - 1]; i++) {
                    mAxisValueList.add(new AxisValue(i).setLabel(Integer.valueOf(i + 1).toString()));
                }
            }
        }

        private void getScore(){
            Date date = mCalendar.getTime();
            int year = date.getYear();
            int month = date.getMonth();
            if (((year % 4 == 0) || (year % 400 == 0)) && (year % 100 != 0)) {
                for (int i = 0; i < a[1][month - 1]; i++) {
                    date = new Date(year,month,i+1);
                    mPointValues.add(new PointValue(i,mHobbyStore.getTodayTotalScore(date)));
                }
            } else {
                for (int i = 0; i < a[0][month - 1]; i++) {
                    date = new Date(year,month,i+1);
                    mPointValues.add(new PointValue(i,mHobbyStore.getTodayTotalScore(date)));
                }
            }

        }

        private void  initLineChart() {
            Line line = new Line(mPointValues).setColor(getResources().getColor(R.color.blue_700));
            List<Line> lines = new ArrayList<Line>();
            line.setShape(ValueShape.CIRCLE);
            line.setCubic(true);
            line.setHasLabels(true);
            line.setHasLines(true);
            lines.add(line);
            line.setHasPoints(false);

            LineChartData lineChartData = new LineChartData();
            lineChartData.setLines(lines);
            Axis axisX = new Axis();
            axisX.setTextColor(getResources().getColor(R.color.blue_700));
            axisX.setTextSize(10);
            axisX.setMaxLabelChars(31);
            lineChartData.setAxisXBottom(axisX);
            Axis axisY = new Axis();
            axisY.setName("分数");
            axisY.setTextColor(getResources().getColor(R.color.blue_700));
            lineChartData.setAxisYLeft(axisY);
            mLineChartView.setInteractive(false);
            mLineChartView.setInteractive(true);
            mLineChartView.setZoomType(ZoomType.HORIZONTAL);
            mLineChartView.setLineChartData(lineChartData);
            mLineChartView.setVisibility(View.VISIBLE);
//            Viewport v = new Viewport(mLineChartView.getMaximumViewport());
//            v.left = 0;
//            v.right = 7;
//           mLineChartView.setCurrentViewport(v);

        }
}