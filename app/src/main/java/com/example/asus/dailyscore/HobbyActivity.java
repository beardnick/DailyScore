package com.example.asus.dailyscore;

import android.app.Activity;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.example.asus.dailyscore.HobbyHelper.Hobby;
import com.example.asus.dailyscore.HobbyHelper.HobbyAdapter;
import com.example.asus.dailyscore.HobbyHelper.HobbyStore;

import java.util.ArrayList;
import java.util.List;

import static android.content.ContentValues.TAG;

/**
 * Created by asus on 2017/7/29.
 */

public class HobbyActivity extends Activity {
    private List<Hobby> testHobby = new ArrayList<>();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.hobbylayout);
        Log.d(TAG,"onCreate");
        getListView();
    }


    public void getListView() {
        if (getHobbyList()) {
            HobbyAdapter listAdapter = new HobbyAdapter(HobbyActivity.this,
                    R.layout.hobby_item,testHobby);
            ListView listView = (ListView) findViewById(R.id.hobby_list);
            listView.setAdapter(listAdapter);
            listView.setOnItemClickListener(new AdapterView.OnItemClickListener(){
                @Override
                public void onItemClick(AdapterView<?> parent, View view,
                                        int position, long id) {
                }
            });
        }
    }

    public boolean getHobbyList() {
        SharedPreferences preferences = getSharedPreferences("data", MODE_PRIVATE);
        HobbyStore hobbyStore =new HobbyStore();
        if (hobbyStore.getTotallHobby(preferences) != preferences.getInt("???", 0)) {
            int total = hobbyStore.getTotallHobby(preferences);
            Hobby tempHobby;
            String tempString;
            int tempInt;
                for (int i = 0; i < total; i++) {
                    tempString = preferences.getString("hobby" + (String.valueOf(i + 1)), "");
                    tempInt = hobbyStore.getPerScore(tempString,preferences);
                tempHobby = new Hobby(tempString,tempInt);
                    testHobby.add(tempHobby);
            }
            return true;
        } else return false;
    }

}
