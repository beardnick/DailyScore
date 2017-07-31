package com.example.asus.dailyscore;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.asus.dailyscore.DataClass.Hobby;
import com.example.asus.dailyscore.DataClass.HobbyStore;

import java.util.Date;

/**
 * Created by asus on 2017/7/30.
 */

public class AddHobby extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.addhobby);
        final EditText hobbyName=(EditText)findViewById(R.id.edit_hobby_name);
        final EditText hobbyScore=(EditText)findViewById(R.id.set_hobby_score);
        final Button addHobby=(Button)findViewById(R.id.add_hobby);
        addHobby.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Hobby newHobby=new Hobby(hobbyName.getText().toString(),Integer.parseInt(hobbyScore.getText().toString()));
                addHobby(newHobby);
                Toast.makeText(AddHobby.this,"成功创建习惯",Toast.LENGTH_LONG).show();
            }
        });
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
}
