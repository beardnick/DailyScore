package com.example.asus.dailyscore;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import com.example.asus.dailyscore.HobbyHelper.Hobby;
import com.example.asus.dailyscore.HobbyHelper.HobbyStore;
import com.prolificinteractive.materialcalendarview.CalendarDay;
import com.prolificinteractive.materialcalendarview.DayViewDecorator;
import com.prolificinteractive.materialcalendarview.DayViewFacade;
import com.prolificinteractive.materialcalendarview.MaterialCalendarView;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Collection;
import java.util.HashSet;
import java.util.Locale;

/**
 * Created by asus on 2017/8/11.
 */

public class HobbyHistory extends AppCompatActivity {
    private MaterialCalendarView calendarView;
    private Calendar beginDate;
    private Calendar todayDate;
    private Hobby hobby;
    private SimpleDateFormat format;
    private HobbyStore hobbyStore;
    private SharedPreferences preferences;
    private SharedPreferences.Editor editor;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.hobby_history);
        initDate();
        initCalendar();
    }

    public void initDate(){
        calendarView = (MaterialCalendarView) findViewById(R.id.history_calendar);
        preferences = getSharedPreferences("data",MODE_PRIVATE);
        editor = getSharedPreferences("data",MODE_PRIVATE).edit();
        format = new SimpleDateFormat("yyyy-MM-dd", Locale.CHINA);
        hobbyStore = new HobbyStore(preferences,editor);
        Intent intent =  getIntent();
        hobby = hobbyStore.getHobby(1+intent.getIntExtra("position_from_main",-1));
        beginDate = Calendar.getInstance();
        try {
            String name = hobby.getName();
            beginDate.setTime(format.parse(hobbyStore.getStringBeginDate(hobby.getName())));
        } catch (ParseException e) {
            e.printStackTrace();
        }
        todayDate = Calendar.getInstance();
    }

    public void initCalendar(){
        String tempTodaDate = format.format(todayDate.getTime());
        calendarView.setSelectionMode(MaterialCalendarView.SELECTION_MODE_NONE);
        calendarView.setBackgroundColor(getResources().getColor(R.color.white_a0));
        String tempBeginDate;
        String tempHobbyName = hobby.getName();
        for(;;beginDate.add(Calendar.DATE,1)){
            tempBeginDate = format.format(beginDate.getTime());
            if(hobbyStore.getFinish(tempHobbyName,tempBeginDate))
                calendarView.setDateSelected(beginDate,true);
            if(tempBeginDate.equals(tempTodaDate)) break;
        }
        calendarView.addDecorator(new EventDecorator(calendarView.getSelectedDates()));
    }

    public class EventDecorator implements DayViewDecorator {

        private final HashSet<CalendarDay> dates;

        public EventDecorator(Collection<CalendarDay> dates) {
            this.dates = new HashSet<>(dates);
        }

        @Override
        public boolean shouldDecorate(CalendarDay day) {
            return dates.contains(day);
        }

        @Override
        public void decorate(DayViewFacade view) {
            if(hobby.getPerScore() > 0)
                view.setBackgroundDrawable(getResources().getDrawable(R.drawable.circle_amber_700));
            else
                view.setBackgroundDrawable(getResources().getDrawable(R.drawable.circl_black_ae));

        }
    }
}
