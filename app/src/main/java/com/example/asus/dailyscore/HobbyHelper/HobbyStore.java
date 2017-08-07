package com.example.asus.dailyscore.HobbyHelper;

import android.content.SharedPreferences;

import java.util.Date;

/**
 * Created by asus on 2017/7/30.
 */

public class HobbyStore{
    public HobbyStore() {
    }

    public int getPerScore(String hobbyName, SharedPreferences preferences){
        if(preferences.getInt(hobbyName+"perScore",0) == preferences.getInt("???",0))
            return 0;
        else
        return preferences.getInt(hobbyName+"perScore",0);
    }

    public int getTotallHobby(SharedPreferences preferences){
        return preferences.getInt("totalHobby",0);
    }

    public String getBeginDate(String hobbyName,SharedPreferences preferences){
        return preferences.getString(hobbyName+"beginDate","");
    }


    public  void addHobby(Hobby hobby,SharedPreferences.Editor editor,SharedPreferences preferences){
        String todayDate=(new Date(System.currentTimeMillis())).toString();
        editor.putString(hobby.getName()+"beginDate",todayDate);
        editor.putInt(hobby.getName()+"perScore",hobby.getPerScore());
        editor.putInt(hobby.getName()+todayDate+"score",0);
        editor.putInt(hobby.getName()+"totalScore",0);
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
        int total=preferences.getInt("totalHobby",0);

        total=preferences.getInt("totalHobby",0);
    }

    public void storeHobby(Hobby hobby,SharedPreferences.Editor editor,SharedPreferences preferences){
        String todayDate=(new Date(System.currentTimeMillis())).toString();
        editor.putInt(hobby.getName()+todayDate+"score",hobby.getScore());
        editor.putInt(hobby.getName()+"totalScore",preferences.getInt("totalScore",0)+hobby.getScore());
        editor.commit();
    }

}
