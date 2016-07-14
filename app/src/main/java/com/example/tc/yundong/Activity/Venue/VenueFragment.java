package com.example.tc.yundong.Activity.Venue;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.GridView;

import com.example.tc.yundong.Adapter.MyGridViewAdapter;
import com.example.tc.yundong.Adapter.MyPagerAdapter;
import com.example.tc.yundong.Adapter.MyRecyclerViewAdapter;
import com.example.tc.yundong.JavaBeen.SportsType;
import com.example.tc.yundong.JavaBeen.Stadiums;
import com.example.tc.yundong.R;
import com.example.tc.yundong.Util.SpacesItemDecoration;
import com.example.tc.yundong.Util.Utils;
import com.example.tc.yundong.View.CustomSwipeToRefresh;
import com.example.tc.yundong.View.KannerView;
import com.example.tc.yundong.View.MetaballView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by tc on 2016/7/12.
 */
public class VenueFragment extends Fragment {

    private String[] sportsName = {"羽毛球", "篮球", "足球", "网球", "游泳", "游泳"};
    private int[] sportsImage = {R.mipmap.badminton, R.mipmap.basketball, R.mipmap.football, R.mipmap.baseball, R
            .mipmap.swimming, R.mipmap.swimming};

    private int[] kanners = {R.mipmap.kanner_1};

    private String[] name = {"星秀足球场", "星秀足球场_1", "星秀足球场_2", "星秀足球场星秀足球场", "星秀足球场_3", "星秀足球场_4"};
    private String[] address = {"上海", "浦东", "浦西", "黄埔", "徐汇", "人民广场"};
    private String[] money = {"400", "300", "600", "111", "222", "333"};
    private String[] km = {"100", "23", "34", "12", "323", "32"};
    private int[] image = {R.mipmap.test, R.mipmap.test, R.mipmap.test, R.mipmap.test, R
            .mipmap.test, R.mipmap.test};

    private View view;

    private MetaballView viewPager;

    private KannerView kannView;

    private RecyclerView recyclerView;

    private CustomSwipeToRefresh customSwipeToRefresh;

    private int page;



    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle
            savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_venue, null);
        findView();
        initView();
        return view;
    }

    private void findView() {
        kannView = (KannerView) view.findViewById(R.id.kanner);
        viewPager = (MetaballView) view.findViewById(R.id.page_image);
        customSwipeToRefresh = (CustomSwipeToRefresh) view.findViewById(R.id.swipe_refresh);
        recyclerView = (RecyclerView) view.findViewById(R.id.recyclerview);

    }

    /**
     * 初始化
     */
    private void initView() {
        kannView.setFocusable(true);
        kannView.setImagesRes(kanners);
        kannView.setOnItemClickListener(new KannerView.OnItemClickListener() {
            @Override
            public void click(View v, int id) { //kanner——Item点击

            }
        });

        customSwipeToRefresh.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() { // 刷新

            }
        });

        /* 加载数据 */
        List<View> views = getAllView(getContext(), getAllDate());
        MyPagerAdapter myPagerAdapter = new MyPagerAdapter(getContext(), views);
        viewPager.setAdapter(myPagerAdapter);
        viewPager.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {

            @Override
            public void onPageSelected(int position) {
                page = position;
            }

            @Override
            public void onPageScrolled(int position, float arg1, int arg2) {

            }

            @Override
            public void onPageScrollStateChanged(int position) {

            }
        });

        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        int spacingInPixels = getResources().getDimensionPixelSize(R.dimen.RecyclerView_space);
        recyclerView.addItemDecoration(new SpacesItemDecoration(spacingInPixels));
        recyclerView.setHasFixedSize(true);
        recyclerView.setAdapter(new MyRecyclerViewAdapter(getContext(), getDate()));
    }

    private List<Stadiums> getDate() {
        Stadiums stadiums = null;
        List<Stadiums> arr = new ArrayList<Stadiums>();
        for (int i = 0; i < name.length; i++) {
            stadiums = new Stadiums();
            stadiums.setName(name[i]);
            stadiums.setAddress(address[i]);
            stadiums.setMoney(money[i]);
            stadiums.setKm(km[i]);
            stadiums.setImageUrl(image[i]);
            arr.add(stadiums);
        }
        return arr;
    }

    /**
     * 得到所有页数
     *
     * @return
     */
    private int getAllPages() {
        int totleSize = 0;
        List<SportsType> allDate = getAllDate();
        if (!allDate.isEmpty()) {
            int sizes = allDate.size() / 5;
            int size = allDate.size() % 5;
            if (sizes == 0 && size > 0) {
                totleSize = 1;
            } else if (sizes > 0 && size == 0) {
                totleSize = sizes;
            } else {
                totleSize = sizes + 1;
            }
        }
        return totleSize;
    }

    /**
     * 得到所有界面
     *
     * @param mContext
     * @param arrs
     * @return
     */
    private List<View> getAllView(Context mContext, List<SportsType> arrs) {
        List<View> views = new ArrayList<View>();
        int allPages = getAllPages();
        for (int i = 0; i < allPages; i++) {
            List<SportsType> arr = new ArrayList<SportsType>();
            for (int j = 5 * i; j < 5 * (i + 1); j++) {
                if (j < arrs.size()) {
                    arr.add(arrs.get(j));
                }
            }
            View view = createView(mContext, arr);
            views.add(view);
        }
        return views;
    }

    /**
     * 创建每个界面
     *
     * @param mContext
     * @param arr
     * @return
     */
    private View createView(Context mContext, List<SportsType> arr) {
        GridView view = new GridView(mContext);
        view.setNumColumns(5);
        MyGridViewAdapter adapter = new MyGridViewAdapter(mContext, arr);
        view.setAdapter(adapter);
        view.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Utils.Toast("position = " + (page * 5 + position));
            }
        });
        return view;
    }

    /**
     * 得到所有数据,暂时模拟数据，项目中自己设置数据源和List集合类型
     *
     * @return
     */
    private List<SportsType> getAllDate() {
        SportsType sportsType = null;
        List<SportsType> arr = new ArrayList<SportsType>();
        for (int i = 0; i < sportsName.length; i++) {
            sportsType = new SportsType();
            sportsType.setSportsName(sportsName[i]);
            sportsType.setSportsImage(sportsImage[i]);
            arr.add(sportsType);
        }
        return arr;
    }

}
