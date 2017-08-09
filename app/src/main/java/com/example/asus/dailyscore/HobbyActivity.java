package com.example.asus.dailyscore;

import android.app.Activity;
import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AlertDialog;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import com.example.asus.dailyscore.HobbyHelper.Hobby;
import com.example.asus.dailyscore.HobbyHelper.HobbyAdapter;
import com.example.asus.dailyscore.HobbyHelper.HobbyStore;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

import static android.content.ContentValues.TAG;

/**
 * Created by asus on 2017/7/29.
 */

public class HobbyActivity extends Activity {
    private List<Hobby> hobbyList = new ArrayList<>();
    private HobbyStore hobbyStore;
    private SharedPreferences preferences;
    private SharedPreferences.Editor editor;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.hobbylayout);
        preferences = getSharedPreferences("data", MODE_PRIVATE);
        editor = getSharedPreferences("data",MODE_PRIVATE).edit();
        hobbyStore = new HobbyStore(preferences,editor);
        Log.d(TAG,"onCreate");
        getListView();
    }


    public void getListView() {
        if (getHobbyList()) {
            HobbyAdapter listAdapter = new HobbyAdapter(HobbyActivity.this,
                    R.layout.hobby_item, hobbyList,hobbyStore);
            ListView listView = (ListView) findViewById(R.id.hobby_list);
            listView.setAdapter(listAdapter);
//            listView.setOnItemClickListener(new AdapterView.OnItemClickListener(){
//                @Override
//                public void onItemClick(AdapterView<?> parent, View view,
//                                        int position, long id) {
//                    Hobby hobby = hobbyList.get(position);
//                    Toast.makeText(HobbyActivity.this,hobby.getName(),
//                            Toast.LENGTH_SHORT).show();
//                }
//            });
            listView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
                @Override
                public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                    removeHobby(hobbyList.get(position));
                    return false;
                }
            });
        }
    }

    protected void removeHobby(final Hobby hobby){
        AlertDialog.Builder builder = new AlertDialog.Builder(HobbyActivity.this);
        builder.setTitle("删除习惯");
        builder.setPositiveButton("确定", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                try {
                    hobbyStore.removeHobby(hobby.getName());
                } catch (ParseException e) {
                    e.printStackTrace();
                }
            }
        });
        builder.setNegativeButton("取消", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

            }
        });
        builder.show();
    }

    public boolean getHobbyList() {
        if (hobbyStore.getTotalHobby() != 0) {
            int total = hobbyStore.getTotalHobby();
            Hobby tempHobby;
            String tempString;
            int tempInt;
                for (int i = 0; i < total; i++) {
                    tempString = preferences.getString("hobby" + (String.valueOf(i + 1)), "");
                    tempInt = hobbyStore.getPerScore(tempString);
                tempHobby = new Hobby(tempString,tempInt);
                    hobbyList.add(tempHobby);
            }
            return true;
        } else return false;
    }

}
