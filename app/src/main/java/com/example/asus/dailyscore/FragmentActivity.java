package com.example.asus.dailyscore;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.widget.Button;

import com.example.asus.dailyscore.Fragment.HobbyFragment;
import com.example.asus.dailyscore.Fragment.PlantFragment;
import com.example.asus.dailyscore.Fragment.ScoreFragment;
import com.example.asus.dailyscore.HobbyHelper.Hobby;
import com.example.asus.dailyscore.HobbyHelper.mFragmentAdapter;

import java.util.List;

/**
 * Created by asus on 2017/8/8.
 */

public class FragmentActivity extends android.support.v4.app.FragmentActivity
        implements View.OnClickListener {
    private mFragmentAdapter mAdapter;
    private List<Fragment>list;
    private ViewPager viewPager;
    private HobbyFragment hobby;
    private ScoreFragment score;
    private PlantFragment plant;
    private Button hobbyButton;
    private Button scoreButton;
    private Button plantButton;
    private FragmentManager fragmentManager;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_main);
        initView();
    }

    public void initView(){
        fragmentManager = getSupportFragmentManager();
        hobby = new HobbyFragment();
        score = new ScoreFragment();
        plant = new PlantFragment();
        hobbyButton = (Button) findViewById(R.id.hobby_fragment);
        scoreButton = (Button) findViewById(R.id.score_fragment);
        plantButton = (Button) findViewById(R.id.plant_fragment);
        hobbyButton.setOnClickListener(this);
        scoreButton.setOnClickListener(this);
        plantButton.setOnClickListener(this);
        viewPager = (ViewPager) findViewById(R.id.viewpager);
        list.add(hobby);
        list.add(score);
        list.add(plant);
        mAdapter = new mFragmentAdapter(fragmentManager,list);
        viewPager.setAdapter(mAdapter);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.hobby:
                viewPager.setCurrentItem(0);
                break;
            case R.id.score:
                viewPager.setCurrentItem(1);
                break;
            case R.id.plant:
                viewPager.setCurrentItem(2);
                break;
            default:
                break;

        }
    }
}
