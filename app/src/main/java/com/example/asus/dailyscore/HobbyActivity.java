package com.example.asus.dailyscore;

import android.app.Activity;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.ListViewCompat;
import android.view.Window;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.List;

/**
 * Created by asus on 2017/7/29.
 */

public class HobbyActivity extends Activity {
    private  String[] testHobby;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.hobbylayout);


        try {
            if(getHobbyList()){
                ArrayAdapter<String> listAdapter=new ArrayAdapter<String>(
                        HobbyActivity.this,android.R.layout.simple_list_item_1,testHobby);
                ListView listView=(ListView)findViewById(R.id.hobby_list);
                listView.setAdapter(listAdapter);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public boolean getHobbyList(){
        SharedPreferences preferences=getSharedPreferences("data",MODE_PRIVATE);
        if(preferences.getInt("totalHobby",0)!=preferences.getInt("???",0)){
            int total=preferences.getInt("totalHobby",0);
            testHobby=new String[total];
            for(int i=0;i<total;i++){
                testHobby[i]=preferences.getString("hobby"+(String.valueOf(i+1)),"");
            }
            return  true;
        }
        else return false;

    }
}
