package com.example.asus.dailyscore.HobbyHelper;

import android.content.SharedPreferences;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.logging.SimpleFormatter;

/**
 * Created by asus on 2017/7/30.
 */

public class HobbyStore {

    private SharedPreferences preferences;
    private SharedPreferences.Editor editor;
    private String todayDate;

    public HobbyStore(SharedPreferences preferences,SharedPreferences.Editor editor) {
        this.preferences = preferences;
        this.editor = editor;
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd",Locale.CHINA);
        todayDate = format.format(new Date(System.currentTimeMillis()));
    }

    public int getPerScore(String hobbyName){
        return preferences.getInt(hobbyName+"perScore",0);
    }

    public int getTotalHobby(){
        return preferences.getInt("totalHobby",0);
    }

    public String getBeginDate(String hobbyName){
        return preferences.getString(hobbyName+"beginDate","");
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

    public  void addHobby(Hobby hobby){
        editor.putString(hobby.getName()+"beginDate",todayDate);
        editor.putInt(hobby.getName()+"perScore",hobby.getPerScore());
        editor.putInt(hobby.getName()+todayDate+"score",0);
        editor.putInt(hobby.getName()+"totalScore",0);
        editor.putBoolean(hobby.getName()+"finish",false);
        editor.commit();
        if(preferences.getInt("totalHobby",0)==preferences.getInt("???",0)){
            editor.putInt("totalHobby",1);
            editor.putString("hobby"+"1",hobby.getName());
            editor.commit();
        }
        else {
            int total=preferences.getInt("totalHobby",0)+1;
            editor.putInt("totalHobby",total);
            editor.putString("hobby"+(String.valueOf(total)),hobby.getName());
            editor.commit();
        }
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
