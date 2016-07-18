package com.example.tc.yundong.Activity;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.tc.yundong.Activity.Venue.VenueFragment;
import com.example.tc.yundong.R;
import com.zhy.autolayout.AutoRelativeLayout;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    private Fragment[] fragments;
    private ImageView[] imagebuttons;
    private TextView[] textviews;
    private VenueFragment homeFragment;
//    private VenueFragment showFragment;
//    private VenueFragment sportsFragment;
//    private VenueFragment myFragment;

    private AutoRelativeLayout rl_home_title, rl_show_title, rl_sports_title, rl_my_title;

    private AutoRelativeLayout rl_city;

    private int index;
    // 当前fragment的index
    private int currentTabIndex;

    private TextView textView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initTitleBar();
        initFragment();
        init();
    }

    private void initFragment() {
        //初始化 各个模块
        homeFragment = new VenueFragment();
//        showFragment = new VenueFragment();
//        sportsFragment = new VenueFragment();
//        myFragment = new VenueFragment();
        fragments = new Fragment[]{homeFragment};
    }

    private void initTitleBar() {
        rl_home_title = (AutoRelativeLayout) findViewById(R.id.rl_main_home_title);
        rl_home_title.setVisibility(View.VISIBLE);
        textView = (TextView) findViewById(R.id.tx_title);
        rl_city = (AutoRelativeLayout) findViewById(R.id.rl_city);

    }

    private void init() {
        imagebuttons = new ImageView[4];
        imagebuttons[0] = (ImageView) findViewById(R.id.ib_home);
        imagebuttons[1] = (ImageView) findViewById(R.id.ib_show);
        imagebuttons[2] = (ImageView) findViewById(R.id.ib_sports);
        imagebuttons[3] = (ImageView) findViewById(R.id.ib_my);
        imagebuttons[0].setSelected(true);

        textviews = new TextView[4];
        textviews[0] = (TextView) findViewById(R.id.tv_weixin);
        textviews[1] = (TextView) findViewById(R.id.tv_contact_list);
        textviews[2] = (TextView) findViewById(R.id.tv_find);
        textviews[3] = (TextView) findViewById(R.id.tv_profile);
        textviews[0].setTextColor(0xFF486BA5);
        findViewById(R.id.rl_home).setOnClickListener(this);
        findViewById(R.id.rl_show).setOnClickListener(this);
        findViewById(R.id.rl_sports).setOnClickListener(this);
        findViewById(R.id.rl_my).setOnClickListener(this);

        // 添加显示第一个fragment
        getSupportFragmentManager().beginTransaction()
                .add(R.id.realtabcontent, homeFragment)
                .show(homeFragment).commit();

        rl_city.setOnClickListener(this);
    }

    public void onTabClicked() {
        if (currentTabIndex != index) {
            FragmentTransaction trx = getSupportFragmentManager()
                    .beginTransaction();
            trx.hide(fragments[currentTabIndex]);
            if (!fragments[index].isAdded()) {
                trx.add(R.id.realtabcontent, fragments[index]);
            }
            trx.show(fragments[index]).commit();
        }
        imagebuttons[currentTabIndex].setSelected(false);
        // 把当前tab设为选中状态
        imagebuttons[index].setSelected(true);
        textviews[currentTabIndex].setTextColor(0xFF999999);
        textviews[index].setTextColor(0xFF486BA5);
        currentTabIndex = index;
    }


    public void onClick(View v) {

        switch (v.getId()) {
            case R.id.rl_home:
                index = 0;
                rl_home_title.setVisibility(View.VISIBLE);
                break;
            case R.id.rl_show:
                index = 1;
                rl_home_title.setVisibility(View.GONE);
                break;
            case R.id.rl_sports:
                index = 2;
                break;
            case R.id.rl_my:
                index = 3;
                break;
            case R.id.rl_city:

                break;



        }
        onTabClicked();
    }
}
