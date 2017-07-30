package com.example.asus.dailyscore;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.ListViewCompat;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.List;

/**
 * Created by asus on 2017/7/29.
 */

public class HobbyActivity extends AppCompatActivity{
    private  String[] testHobby={"每日看书","早起","早睡","吃早餐","运动","喝水","聊天"};
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.hobbylayout);
        ArrayAdapter<String> listAdapter=new ArrayAdapter<String>(
                HobbyActivity.this,android.R.layout.simple_list_item_1,testHobby);
        ListView listView=(ListView)findViewById(R.id.hobby_list);
        listView.setAdapter(listAdapter);
    }
}
