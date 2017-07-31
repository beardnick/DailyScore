package com.example.asus.dailyscore.DataClass;

import android.app.Activity;
import android.content.SharedPreferences;
import android.content.pm.ActivityInfo;

import java.util.Date;

import static android.content.Context.MODE_PRIVATE;

/**
 * Created by asus on 2017/7/30.
 */

public class HobbyStore extends Activity {
    public HobbyStore() {
    }

    public  void addHobby(Hobby hobby){
        String todayDate=(new Date(System.currentTimeMillis())).toString();
        SharedPreferences.Editor editor=getSharedPreferences("data",
                MODE_PRIVATE).edit();
        SharedPreferences preferences=getSharedPreferences("data", MODE_PRIVATE);
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

    public void storeHobby(Hobby hobby){
        String todayDate=(new Date(System.currentTimeMillis())).toString();
        SharedPreferences.Editor editor=getSharedPreferences("data",
                MODE_PRIVATE).edit();
        SharedPreferences preferences=getSharedPreferences("data",MODE_PRIVATE);
        editor.putInt(hobby.getName()+todayDate+"score",hobby.getScore());
        editor.putInt(hobby.getName()+"totalScore",preferences.getInt("totalScore",0)+hobby.getScore());
        editor.commit();
    }

}
