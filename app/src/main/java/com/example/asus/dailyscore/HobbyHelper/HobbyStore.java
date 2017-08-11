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
    private SimpleDateFormat format;

    public HobbyStore(SharedPreferences preferences,SharedPreferences.Editor editor) {
        this.preferences = preferences;
        this.editor = editor;
        this.format = new SimpleDateFormat("yyyy-MM-dd",Locale.CHINA);
    }

    public int getPerScore(String hobbyName){
        return preferences.getInt(hobbyName+"perScore",0);
    }

    public int getTotalHobby(){
        return preferences.getInt("totalHobby",0);
    }

    public String getStringBeginDate(String hobbyName){
        return preferences.getString(hobbyName+"beginDate","?");
    }

    public Date getDateBeginDate(String hobbyName) throws ParseException {
        return format.parse(getStringBeginDate(hobbyName));
    }

    public boolean getFinish(String hobbyName,String date){
        return  preferences.getBoolean(hobbyName+date+"finish",false);
    }
    public String getStringTodayDate(){
        return format.format(new Date(System.currentTimeMillis()));
    }

    public boolean getFinish(String hobbyName){
        return  preferences.getBoolean(hobbyName+getStringTodayDate()+"finish",false);
    }

    public Date getDateTodayDate(){
        return  new Date(System.currentTimeMillis());
    }

    public int getScore(String hobbyName){
        return preferences.getInt(hobbyName+getStringTodayDate()+"score",0);
    }

    public int getTotalScore(String hobbyName){
        return preferences.getInt(hobbyName+"totalScore",0);
    }
    public int getDateScore(String hobbyName,Date date){
        return preferences.getInt(hobbyName+format.format(date),0);
    }

    public Hobby getLastHobby(){
        String temp = preferences.getString("hobby"+Integer.valueOf(getTotalHobby()).toString(),"");
        return new Hobby(temp,getPerScore(temp));
    }

    public  void addHobby(Hobby hobby){
        String todayDate = getStringTodayDate();
        editor.putString(hobby.getName()+"beginDate",todayDate);
        editor.putInt(hobby.getName()+"perScore",hobby.getPerScore());
        editor.putInt(hobby.getName()+todayDate+"score",0);
        editor.putInt(hobby.getName()+"totalScore",0);
        editor.putBoolean(hobby.getName()+todayDate+"finish",false);
        editor.commit();
        if(getTotalHobby() == 0){
            editor.putInt("totalHobby",1);
            editor.putString("hobby"+"1",hobby.getName());
            editor.putInt(hobby.getName()+"position",1);
            editor.commit();
        }
        else {
            int totalHobby = getTotalHobby() + 1;
            editor.putInt("totalHobby",totalHobby);
            editor.putString("hobby"+(String.valueOf(totalHobby)),hobby.getName());
            editor.putInt(hobby.getName()+"position",totalHobby);
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

    public  Hobby getHobby(int position){
        String tempString;
        int tempInt;
        tempString = preferences.getString("hobby" + (String.valueOf(position)), "");
        tempInt = getPerScore(tempString);
        return  new Hobby(tempString,tempInt);
    }

    private int getPosition(String hobbyName){
        return preferences.getInt(hobbyName+"position",0);
    }

    public void removeHobby(String hobbyName) throws ParseException {
        editor.remove(hobbyName+"perScore");
        editor.remove(hobbyName+"totalScore");
        editor.remove(hobbyName+"position");
        editor.commit();
        int totalHobby = getTotalHobby();
        if(preferences.getInt("totalHobby",0)>0){
            int position = totalHobby;
            if(getPosition(hobbyName) == 0){
                String temp;
                for(int i = 0;i < totalHobby; i++){
                    temp = preferences.getString("hobby"+Integer.valueOf(i).toString(),"???");
                    if(hobbyName.equals(temp)){
                        position = i;
                        break;
                    }
             }
            }
            else
                position = getPosition(hobbyName);
            for(int i = position;i < totalHobby;i++){
                editor.putString("hobby"+Integer.valueOf(i).toString(),preferences.getString("hobby"+Integer.valueOf(i).toString(),"???"));
            }
            editor.commit();
            editor.remove("hobby"+Integer.valueOf(totalHobby));
            totalHobby = totalHobby -1;
            editor.putInt("totalHobby",totalHobby);
            editor.commit();
        }
        String temp;
        String todayDate = getStringTodayDate();
        for ( Date beginDate = getDateBeginDate(hobbyName);;datePlus(beginDate)) {
            temp = format.format(beginDate);
            editor.remove(hobbyName+temp+"score");
            editor.remove(hobbyName+temp+"finish");
            if(todayDate.equals(temp))break;
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
        String todayDate = getStringTodayDate();
        editor.putInt(hobby.getName()+todayDate+"score",hobby.getPerScore());
        editor.putInt(hobby.getName()+"totalScore",preferences.getInt(hobby.getName()+"totalScore",0)+hobby.getPerScore());
        editor.putBoolean(hobby.getName()+todayDate+"finish",true);
        editor.commit();
    }

    public void unFinish(Hobby hobby){
        String todayDate = getStringTodayDate();
        editor.putInt(hobby.getName()+"totalScore",preferences.getInt(hobby.getName()+"totalScore",0)-hobby.getPerScore());
        editor.putInt(hobby.getName()+todayDate+"score",0);
        editor.putBoolean(hobby.getName()+todayDate+"finish",false);
        editor.commit();
    }

}
