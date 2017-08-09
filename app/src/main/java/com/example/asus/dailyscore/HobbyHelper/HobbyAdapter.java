package com.example.asus.dailyscore.HobbyHelper;

import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.drawable.Drawable;
import android.media.Image;
import android.support.annotation.LayoutRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.asus.dailyscore.R;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Locale;

/**
 * Created by asus on 2017/8/7.
 */

public class HobbyAdapter extends ArrayAdapter<Hobby>{

    private  int resourceId;
    private  HobbyStore hobbyStore;

    public  HobbyAdapter(Context context, int textViewResourceId,
                         List<Hobby>objects,HobbyStore hobbyStore){
        super(context,textViewResourceId,objects);
        resourceId = textViewResourceId;
        this.hobbyStore = hobbyStore;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        final Hobby hobby = getItem(position);
        final View view;
        final ViewHolder viewHolder;
        final String temp = hobby.getName();
        SimpleDateFormat format = null;
        if(convertView == null){
            view = LayoutInflater.from(getContext()).inflate(resourceId,parent,
                    false);
            viewHolder = new ViewHolder();
            viewHolder.hobbyImage = (ImageButton) view.findViewById(R.id.image_item);
            viewHolder.hobbyName = (TextView) view.findViewById(R.id.hobby_name_item);
            viewHolder.perScore = (TextView) view.findViewById(R.id.totalscore_item);
            view.setTag(viewHolder);
        }
        else{
            view = convertView;
            viewHolder = (ViewHolder)view.getTag();
        }
        viewHolder.hobbyName.setText(temp);
        if(hobbyStore.getFinish(temp)){
            if(hobbyStore.getPerScore(temp) >= 0)
                viewHolder.hobbyImage.setImageResource(R.drawable.ic_wb_sunny_amber_300_24dp);
            else
                viewHolder.hobbyImage.setImageResource(R.drawable.ic_wb_cloudy_black_24dp);
        }
        else{
            if(hobbyStore.getPerScore(temp) >= 0)
                viewHolder.hobbyImage.setImageResource(R.drawable.ic_wb_sunny_grey_400_24dp);
            else
                viewHolder.hobbyImage.setImageResource(R.drawable.ic_wb_cloudy_grey_400_24dp);
        }
        viewHolder.perScore.setText(Integer.valueOf(hobbyStore.getTotalScore(temp)).toString());
        viewHolder.hobbyImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                boolean finish = !hobbyStore.getFinish(temp);
                    if(finish){
                        if(hobbyStore.getPerScore(temp) >= 0)
                        viewHolder.hobbyImage.setImageResource(R.drawable.ic_wb_sunny_amber_300_24dp);
                        else
                            viewHolder.hobbyImage.setImageResource(R.drawable.ic_wb_cloudy_black_24dp);
                        hobbyStore.finish(hobby);
                        viewHolder.perScore.setText(Integer.valueOf(hobbyStore.getTotalScore(temp)).toString());
                    }
                    else{
                        if(hobbyStore.getPerScore(temp) >= 0)
                            viewHolder.hobbyImage.setImageResource(R.drawable.ic_wb_sunny_grey_400_24dp);
                        else
                            viewHolder.hobbyImage.setImageResource(R.drawable.ic_wb_cloudy_grey_400_24dp);
                        hobbyStore.unFinish(hobby);
                        viewHolder.perScore.setText(Integer.valueOf(hobbyStore.getTotalScore(temp)).toString());
                    }
            }
        });
        return  view;
    }

    class ViewHolder {
        ImageButton hobbyImage;
        TextView hobbyName;
        TextView perScore;
    }
}
