package com.example.tc.yundong.Activity.Venue;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.GridView;

import com.example.tc.yundong.Adapter.MyAdapter;
import com.example.tc.yundong.Adapter.MyPagerAdapter;
import com.example.tc.yundong.R;
import com.example.tc.yundong.Util.Utils;
import com.example.tc.yundong.View.MetaballView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by tc on 2016/7/12.
 */
public class VenueFragment extends Fragment {


    private List<String> dataList = null;

    private MetaballView viewPager;

    private int page;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle
            savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_venue, null);

        viewPager = (MetaballView) view.findViewById(R.id.page_image);
        initView();
        return view;
    }

    /**
     * 初始化
     */
    private void initView() {

		/* 默认选择首页 */
        int allPages = getAllPages(getAllDate());
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
    }

    /**
     * 得到所有页数
     *
     * @param arrs
     * @return
     */
    private int getAllPages(List<String> arrs) {
        int totleSize = 0;
        List<String> allDate = getAllDate();
        if (!allDate.isEmpty()) {
            int sizes = allDate.size() / 4;
            int size = allDate.size() % 4;
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
    private List<View> getAllView(Context mContext, List<String> arrs) {
        List<View> views = new ArrayList<View>();
        int allPages = getAllPages(arrs);
        for (int i = 0; i < allPages; i++) {
            List<String> arr = new ArrayList<String>();
            for (int j = 4 * i; j < 4 * (i + 1); j++) {
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
    private View createView(Context mContext, List<String> arr) {
        GridView view = new GridView(mContext);
        view.setNumColumns(4);
        MyAdapter adapter = new MyAdapter(mContext, arr);
        view.setAdapter(adapter);
        view.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Utils.Toast("position = " + (page * 4 + position));
            }
        });
        return view;
    }

    /**
     * 得到所有数据,暂时模拟数据，项目中自己设置数据源和List集合类型
     *
     * @return
     */
    private List<String> getAllDate() {

        List<String> arr = new ArrayList<String>();
        for (int i = 0; i < 10; i++) {
            arr.add("hmiss_" + i + "组");
        }
        return arr;
    }

}
