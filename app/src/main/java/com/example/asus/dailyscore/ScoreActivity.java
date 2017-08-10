package com.example.asus.dailyscore;

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

/**
 * Created by asus on 2017/7/29.
 */

public class ScoreActivity extends AppCompatActivity {
    MaterialCalendarView calendarView;
    Calendar calendar;
    int a[]={1,2,3,4,5,6,7,8,9,10};

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.scorelayout);
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) actionBar.hide();
        calendar = Calendar.getInstance();
        calendarView = (MaterialCalendarView) findViewById(R.id.history_calendar);
        calendarView.setSelectionMode(MaterialCalendarView.SELECTION_MODE_NONE);
        calendarView.setSelectionColor(getResources().getColor(R.color.white_a0));
        for (int i = 0; i < 10; i++) {
            calendarView.setDateSelected(calendar, true);
            calendar.add(calendar.DATE, 1);
        }
        try {
           calendarView.addDecorators();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    class EventDecoratoramber100 implements DayViewDecorator {


        private int a[];
        private final HashSet<CalendarDay> dates;

        public EventDecoratoramber100(int a[], Collection<CalendarDay> dates) {
            this.dates = new HashSet<>(dates);
            this.a = a;
        }

        @Override
        public boolean shouldDecorate(CalendarDay day) {
            return dates.contains(day);
        }

        @Override
        public void decorate(DayViewFacade view) {
            view.setBackgroundDrawable(getResources().getDrawable(R.drawable.circle_amber_100));
        }

    }

    class EventDecoratoramber300 implements DayViewDecorator {



        private int a[];
        private final HashSet<CalendarDay> dates;

        public EventDecoratoramber300(int a[], Collection<CalendarDay> dates) {
            this.dates = new HashSet<>(dates);
            this.a = a;
        }

        @Override
        public boolean shouldDecorate(CalendarDay day) {
            return dates.contains(day);
        }

        @Override
        public void decorate(DayViewFacade view) {
            view.setBackgroundDrawable(getResources().getDrawable(R.drawable.circle_amber_100));
        }

    }

    class EventDecoratoramber700 implements DayViewDecorator {


        private int a[];
        private final HashSet<CalendarDay> dates;

        public EventDecoratoramber700(int a[], Collection<CalendarDay> dates) {
            this.dates = new HashSet<>(dates);
            this.a = a;
        }

        @Override
        public boolean shouldDecorate(CalendarDay day) {
            return dates.contains(day);
        }

        @Override
        public void decorate(DayViewFacade view) {
            view.setBackgroundDrawable(getResources().getDrawable(R.drawable.circle_amber_100));
        }

    }

    class EventDecorator900 implements DayViewDecorator {


        private int a[];
        private final HashSet<CalendarDay> dates;

        public EventDecorator900(int a[], Collection<CalendarDay> dates) {
            this.dates = new HashSet<>(dates);
            this.a = a;
        }

        @Override
        public boolean shouldDecorate(CalendarDay day) {
            return dates.contains(day);
        }

        @Override
        public void decorate(DayViewFacade view) {
            view.setBackgroundDrawable(getResources().getDrawable(R.drawable.circle_amber_100));
        }

    }

    class EventDecoratorblack100 implements DayViewDecorator {


        private int a[];
        private final HashSet<CalendarDay> dates;

        public EventDecoratorblack100(int a[], Collection<CalendarDay> dates) {
            this.dates = new HashSet<>(dates);
            this.a = a;
        }

        @Override
        public boolean shouldDecorate(CalendarDay day) {
            return dates.contains(day);
        }

        @Override
        public void decorate(DayViewFacade view) {
            view.setBackgroundDrawable(getResources().getDrawable(R.drawable.circle_amber_100));
        }

    }

    class EventDecorator100 implements DayViewDecorator {


        private int a[];
        private final HashSet<CalendarDay> dates;

        public EventDecorator100( int a[],Collection<CalendarDay> dates) {
            this.dates = new HashSet<>(dates);
            this.a = a;
        }

        @Override
        public boolean shouldDecorate(CalendarDay day) {
            return dates.contains(day);
        }

        @Override
        public void decorate(DayViewFacade view) {
            view.setBackgroundDrawable(getResources().getDrawable(R.drawable.circle_amber_100));
        }

    }

//    class EventDecorator100 implements DayViewDecorator {
//
//
//        private int a[];
//        private final HashSet<CalendarDay> dates;
//
//        public EventDecorator100( int a[],Collection<CalendarDay> dates) {
//            this.dates = new HashSet<>(dates);
//            this.a = a;
//        }
//
//        @Override
//        public boolean shouldDecorate(CalendarDay day) {
//            return dates.contains(day);
//        }
//
//        @Override
//        public void decorate(DayViewFacade view) {
//            view.setBackgroundDrawable(getResources().getDrawable(R.drawable.circle_amber_100));
//        }
//
//    }

}
