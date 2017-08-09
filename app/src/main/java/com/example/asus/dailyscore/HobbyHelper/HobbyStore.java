package com.example.asus.dailyscore.HobbyHelper;

import android.content.SharedPreferences;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Locale;

/**
 * Created by asus on 2017/7/30.
 */

public class HobbyStore {

    private SharedPreferences preferences;
    private SharedPreferences.Editor editor;
    private String todayDate;
    private SimpleDateFormat format;
    private int totalHobby;

    public HobbyStore(SharedPreferences preferences,SharedPreferences.Editor editor) {
        this.preferences = preferences;
        this.editor = editor;
        this.format = new SimpleDateFormat("yyyy-MM-dd",Locale.CHINA);
        todayDate = format.format(new Date(System.currentTimeMillis()));
        totalHobby = preferences.getInt("totalHobby",0);
    }

    public int getPerScore(String hobbyName){
        return preferences.getInt(hobbyName+"perScore",0);
    }

    public int getTotalHobby(){
        return totalHobby;
    }

    public String getStringBeginDate(String hobbyName){
        return preferences.getString(hobbyName+"beginDate","?");
    }

    public Date getDateBeginDate(String hobbyName) throws ParseException {
        return format.parse(getStringBeginDate(hobbyName));
    }

    public boolean getFinish(String hobbyName){
        return  preferences.getBoolean(hobbyName+todayDate+"finish",false);
    }

    public int getScore(String hobbyName){
        return preferences.getInt(hobbyName+todayDate+"score",0);
    }

    public int getTotalScore(String hobbyName){
        return preferences.getInt(hobbyName+"totalScore",0);
    }
    public int getDateScore(String hobbyName,Date date){
        return preferences.getInt(hobbyName+format.format(date),0);
    }

    public  void addHobby(Hobby hobby){
        editor.putString(hobby.getName()+"beginDate",todayDate);
        editor.putInt(hobby.getName()+"perScore",hobby.getPerScore());
        editor.putInt(hobby.getName()+todayDate+"score",0);
        editor.putInt(hobby.getName()+"totalScore",0);
        editor.putBoolean(hobby.getName()+todayDate+"finish",false);
        editor.commit();
        if(totalHobby == 0){
            editor.putInt("totalHobby",1);
            editor.putString("hobby"+"1",hobby.getName());
            editor.commit();
        }
        else {
            totalHobby = totalHobby +1;
            editor.putInt("totalHobby",totalHobby);
            editor.putString("hobby"+(String.valueOf(totalHobby)),hobby.getName());
            editor.commit();
        }
    }

    private Date datePlus(Date date){
        Calendar calendar   =   new GregorianCalendar();
        calendar.setTime(date);
        calendar.add(calendar.DATE,1);
        date = calendar.getTime();
        return date;
    }

    public void removeHobby(String hobbyName) throws ParseException {
        editor.remove(hobbyName+"perScore");
        editor.remove(hobbyName+"totalScore");
        editor.commit();
        if(preferences.getInt("totalHobby",0)>0){
            int position = totalHobby;
            for(int i = 0;i < totalHobby; i++){
                if(hobbyName.equals(preferences.getString("hobby"+Integer.valueOf(i).toString(),"???"))){
                    position = i;
                    break;
                }
            }
            for(int i = position;i < totalHobby;i++){
                editor.putString("hobby"+Integer.valueOf(i).toString(),preferences.getString("hobby"+Integer.valueOf(i+1).toString(),"???"));
            }
            editor.commit();
            editor.remove("hobby"+Integer.valueOf(totalHobby));
            totalHobby = totalHobby -1;
            editor.putInt("totalHobby",totalHobby);
            editor.commit();
        }
        Date beginDate = getDateBeginDate(hobbyName);
        Date todayDate = format.parse(this.todayDate);
        while (beginDate != todayDate){
            editor.remove(hobbyName+format.format(beginDate)+"score");
            editor.remove(hobbyName+format.format(beginDate)+"finish");
            beginDate = datePlus(beginDate);
        }
        editor.remove(hobbyName+"beginDate");
        editor.commit();
    }

    public void storeHobby(Hobby hobby){
        HobbyStore hobbyStore = new HobbyStore(preferences,editor);
       if(hobby.getFinish() != hobbyStore.getFinish(hobby.getName())){
           if(hobby.getFinish())
               hobbyStore.finish(hobby);
           else
               hobbyStore.unFinish(hobby);
       }
    }

    public void finish(Hobby hobby){
        editor.putInt(hobby.getName()+todayDate+"score",hobby.getPerScore());
        editor.putInt(hobby.getName()+"totalScore",preferences.getInt(hobby.getName()+"totalScore",0)+hobby.getPerScore());
        editor.putBoolean(hobby.getName()+todayDate+"finish",true);
        editor.commit();
    }

    public void unFinish(Hobby hobby){
        editor.putInt(hobby.getName()+"totalScore",preferences.getInt(hobby.getName()+"totalScore",0)-hobby.getPerScore());
        editor.putInt(hobby.getName()+todayDate+"score",0);
        editor.putBoolean(hobby.getName()+todayDate+"finish",false);
        editor.commit();
    }

}
