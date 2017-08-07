package com.example.asus.dailyscore.HobbyHelper;

import android.content.Context;
import android.media.Image;
import android.support.annotation.LayoutRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.asus.dailyscore.R;

import java.util.List;

/**
 * Created by asus on 2017/8/7.
 */

public class HobbyAdapter extends ArrayAdapter<Hobby>{

    private  int resourceId;

    public  HobbyAdapter(Context context, int textViewResourceId,
                         List<Hobby>objects){
        super(context,textViewResourceId,objects);
        resourceId = textViewResourceId;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
         Hobby hobby = getItem(position);
        View view;
        ViewHolder viewHolder;
        if(convertView == null){
            view = LayoutInflater.from(getContext()).inflate(resourceId,parent,
                    false);
            viewHolder = new ViewHolder();
            viewHolder.hobbyImage = (ImageView) view.findViewById(R.id.image_item);
            viewHolder.hobbyName = (TextView) view.findViewById(R.id.hobby_name_item);
            viewHolder.perScore = (TextView) view.findViewById(R.id.perscore_item);
            view.setTag(viewHolder);
        }
        else{
            view = convertView;
            viewHolder = (ViewHolder)view.getTag();
        }
        viewHolder.hobbyName.setText(hobby.getName());
        viewHolder.hobbyImage.setImageResource(R.drawable.ic_wb_sunny_amber_300_24dp);
        viewHolder.perScore.setText(Integer.valueOf(hobby.getPerScore()).toString());

        return  view;
    }

    class ViewHolder {
        ImageView hobbyImage;
        TextView hobbyName;
        TextView perScore;
    }
}
