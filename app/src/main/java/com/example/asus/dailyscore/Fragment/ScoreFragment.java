package com.example.asus.dailyscore.Fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.asus.dailyscore.R;

/**
 * Created by asus on 2017/8/8.
 */

public class ScoreFragment extends Fragment {

    public ScoreFragment() {
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_score,container,false);
        return view;
    }
}
