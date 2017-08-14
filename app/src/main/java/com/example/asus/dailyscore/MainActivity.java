package com.example.asus.dailyscore;

import android.app.ActivityManager;
import android.app.LocalActivityManager;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.SharedPreferences;
import android.support.v4.app.ActivityManagerCompat;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.asus.dailyscore.HobbyHelper.Hobby;
import com.example.asus.dailyscore.HobbyHelper.HobbyStore;
import com.example.asus.dailyscore.HobbyHelper.MyPagerAdapter;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{
    private  View hobby;
    private  View score;
    private  View plant;
    private ViewPager mainPager;
    private List<View> list;
    private MyPagerAdapter viewPager;
    private LocalActivityManager manager;
    private ItemClickReceiver receiver;
    //private CustomViewPager viewPager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.mainlayout);
        manager = new LocalActivityManager(this, true);
        manager.dispatchCreate(savedInstanceState);
        initView();
        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction("com.example.asus.dailyscore.ITEMCLICK");
        receiver = new ItemClickReceiver();
        registerReceiver(receiver,intentFilter);
    }

    public void initView(){
        Intent hobbyIntent=new Intent(MainActivity.this,HobbyActivity.class);
        Intent scoreIntent=new Intent(MainActivity.this,ScoreActivity.class);
        Intent plantIntent=new Intent(MainActivity.this,PlantActivity.class);
        hobby = manager.startActivity(findViewById(R.id.hobby).toString(),hobbyIntent).getDecorView();
        score = manager.startActivity(findViewById(R.id.score).toString(),scoreIntent).getDecorView();
        plant = manager.startActivity(findViewById(R.id.plant).toString(),plantIntent).getDecorView();
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
                break;
            case R.id.score:
                mainPager.setCurrentItem(1);
                break;
            case R.id.plant:
                mainPager.setCurrentItem(2);
                break;
            default:
                break;
        }

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        unregisterReceiver(receiver);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.add_remove,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case R.id.action_add:
                addHobbyDialog();
                break;
//            case R.id.action_remove:
//                Toast.makeText(MainActivity.this,"你删除了一个习惯",Toast.LENGTH_LONG).show();
//                break;
            default:
                break;
        }
        return true;
    }

    private void addHobbyDialog(){
        AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
        builder.setTitle("新增习惯");
        View view = LayoutInflater.from(MainActivity.this).inflate(R.layout.add_hobby_layout,null);
        builder.setView(view);
        final EditText hobbyName=(EditText)view.findViewById(R.id.new_edit_hobby_name);
        final EditText hobbyScore=(EditText)view.findViewById(R.id.new_set_hobby_score);
        final SharedPreferences.Editor editor=getSharedPreferences("data",
                MODE_PRIVATE).edit();
        final SharedPreferences preferences=getSharedPreferences("data",MODE_PRIVATE);
        builder.setPositiveButton("确定", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which){
                if(hobbyName.getText().length() == 0){
                    Toast.makeText(MainActivity.this,"习惯名称不能为空",Toast.LENGTH_SHORT).show();
                }
                else if(hobbyScore.getText().length() == 0){
                    Toast.makeText(MainActivity.this,"习惯分数不能为空",Toast.LENGTH_SHORT).show();
                }
                else{
                    Hobby newHobby = new Hobby(
                            hobbyName.getText().toString(),Integer.parseInt(hobbyScore.getText().toString()));
                    HobbyStore hobbyStore = new HobbyStore(preferences,editor);
                    hobbyStore.addHobby(newHobby);
                    Toast.makeText(MainActivity.this,"成功创建习惯",Toast.LENGTH_LONG).show();
                    Intent intent = new
                            Intent("com.example.asus.dailyscore.HOBBYADD");
                    sendBroadcast(intent);
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

    public class ItemClickReceiver extends BroadcastReceiver{
        @Override
        public void onReceive(Context context, Intent intent) {
            try {
                Intent intent1 = new Intent(MainActivity.this,HobbyHistory.class);
                intent1.putExtra("position_from_main",intent.getIntExtra("position",0));
                startActivity(intent1);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public class CustomViewPager extends ViewPager {
        private boolean enabled = false;

        public CustomViewPager(Context context) {
            super(context);
        }

        public CustomViewPager(Context context, AttributeSet attrs) {
            super(context, attrs);
        }

        @Override
        public boolean onTouchEvent(MotionEvent event) {
            // 触摸事件不触发
            if (this.enabled) {
                return super.onTouchEvent(event);
            }
            return false;
        }

        @Override
        public boolean onInterceptTouchEvent(MotionEvent event) {
            // 不处理触摸拦截事件
            if (this.enabled) {
                return super.onInterceptTouchEvent(event);
            }
            return false;
        }

        @Override
        public boolean dispatchTouchEvent(MotionEvent event) {
            // 分发事件，这个是必须要的，如果把这个方法覆盖了，那么ViewPager的子View就接收不到事件了
            if (this.enabled) {
                return super.dispatchTouchEvent(event);
            }
            return super.dispatchTouchEvent(event);
        }

        public void setPagingEnabled(boolean enabled) {
            this.enabled = enabled;
        }
    }

}
