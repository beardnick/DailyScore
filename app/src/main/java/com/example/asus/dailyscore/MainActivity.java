package com.example.asus.dailyscore;

import android.content.Intent;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{
    private  View hobby;
    private  View score;
    private  View plant;
    private ViewPager mainPager;
    private List<View> list;
    private MyPagerAdapter viewPager;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.mainlayout);
        hobby=View.inflate(MainActivity.this,R.layout.hobbylayout,null);
        plant=View.inflate(MainActivity.this,R.layout.plantlayout,null);
        score=View.inflate(MainActivity.this,R.layout.scorelayout,null);
        list=new ArrayList<View>();
        list.add(hobby);
        list.add(score);
        list.add(plant);
        viewPager=new MyPagerAdapter(list);
        mainPager=(ViewPager) findViewById(R.id.viewpager);
        mainPager.setAdapter(viewPager);
        Button hobby=(Button)findViewById(R.id.hobby);
        Button score=(Button)findViewById(R.id.score);
        Button plant=(Button)findViewById(R.id.plant);
        hobby.setOnClickListener(this);
        score.setOnClickListener(this);
        plant.setOnClickListener(this);
        mainPager.setCurrentItem(0);
    }
    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.hobby:
                mainPager.setCurrentItem(0);
                hobby.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent intent=new Intent(MainActivity.this,HobbyActivity.class);
                        startActivity(intent);
                    }
                });
                break;
            case R.id.score:
                mainPager.setCurrentItem(1);
                score.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent intent=new Intent(MainActivity.this,ScoreActivity.class);
                        startActivity(intent);
                    }
                });
                break;
            case R.id.plant:
                mainPager.setCurrentItem(2);
                plant.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent intent=new Intent(MainActivity.this,PlantActivity.class);
                        startActivity(intent);
                    }
                });
                break;
            default:
                break;
        }
    }
}
