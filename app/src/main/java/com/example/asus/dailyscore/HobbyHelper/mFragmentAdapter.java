package com.example.asus.dailyscore.HobbyHelper;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import java.util.List;

import static android.R.id.list;

/**
 * Created by asus on 2017/8/8.
 */

public class mFragmentAdapter extends FragmentPagerAdapter {

    private List<Fragment>list;

    public mFragmentAdapter(FragmentManager fm, List<Fragment>list) {
        super(fm);
        this.list = list;
    }

    @Override
    public Fragment getItem(int position) {
        return list.get(position);
    }

    @Override
    public int getCount() {
        return list.size();
    }
}
