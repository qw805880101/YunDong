package com.example.tc.yundong.Activity.Venue;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.tc.yundong.Adapter.MyRecyclerViewAdapter;
import com.example.tc.yundong.Adapter.OnItemClickLitener;
import com.example.tc.yundong.Async.Asyn_GetHomeData;
import com.example.tc.yundong.Async.Asyn_GetVenuesList;
import com.example.tc.yundong.JavaBeen.HomeData;
import com.example.tc.yundong.JavaBeen.VenuesList;
import com.example.tc.yundong.R;
import com.example.tc.yundong.Util.SpacesItemDecoration;
import com.example.tc.yundong.Util.Utils;
import com.example.tc.yundong.View.CustomSwipeToRefresh;
import com.timehop.stickyheadersrecyclerview.StickyRecyclerHeadersDecoration;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by tc on 2016/7/12.
 */
public class VenueFragment extends Fragment implements OnItemClickLitener {

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

    private RecyclerView recyclerView; //场馆列表

    private CustomSwipeToRefresh customSwipeToRefresh;

    private HomeData homeData;

    private VenuesList venuesList;

    private boolean isUpDate = true;

    private int page = 2;
    private int rows = 4;

    MyRecyclerViewAdapter myRecyclerViewAdapter;

    private Handler mHandler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            if (msg.what == 0) { // 首页数据
                homeData = (HomeData) msg.obj;
                findView();
                initView();
            } else if(msg.what == 1){ //场馆列表
                venuesList = (VenuesList) msg.obj;
                myRecyclerViewAdapter.upDate(venuesList.getData());
                isUpDate = true;
            }
        }
    };

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle
            savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_venue, null);
        new Asyn_GetHomeData(mHandler).execute();
        return view;
    }

    private void findView() {
        customSwipeToRefresh = (CustomSwipeToRefresh) view.findViewById(R.id.swipe_refresh);
        recyclerView = (RecyclerView) view.findViewById(R.id.recyclerview);
    }

    /**
     * 初始化
     */
    private void initView() {

        customSwipeToRefresh.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() { // 刷新
                customSwipeToRefresh.setRefreshing(false);
            }
        });

        recyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                RecyclerView.LayoutManager mLayoutManager = recyclerView.getLayoutManager();
                int lastVisibleItem = ((LinearLayoutManager) mLayoutManager).findLastVisibleItemPosition();
                int totalItemCount = mLayoutManager.getItemCount();
                //最后一项显示且是下滑的时候调用加载
                if (lastVisibleItem >= totalItemCount - 1 && dy > 0) {
                    //需要自己设置排除多次重复调用
                    Utils.Log("要加载了。");
                    if (isUpDate) { //要更新
                        isUpDate = false;
                        Map<String, String> map = new HashMap<String, String>();
                        map.put("page", "" + page);
                        map.put("rows", "" + rows);
                        new Asyn_GetVenuesList(map, mHandler).execute();
                    } else { //不更新

                    }
                    return;
                }
            }
        });

        myRecyclerViewAdapter = new MyRecyclerViewAdapter(getContext(), homeData);
        myRecyclerViewAdapter.setOnItemClickLitener(this);
        final StickyRecyclerHeadersDecoration headersDecor = new StickyRecyclerHeadersDecoration
                (myRecyclerViewAdapter); //绑定之前的adapter

        myRecyclerViewAdapter.registerAdapterDataObserver(new RecyclerView.AdapterDataObserver() {
            @Override
            public void onChanged() {
                headersDecor.invalidateHeaders();
            }
        });  //刷新数据的时候回刷新头部

        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        int spacingInPixels = getResources().getDimensionPixelSize(R.dimen.RecyclerView_space);
        recyclerView.addItemDecoration(new SpacesItemDecoration(spacingInPixels));
        recyclerView.setHasFixedSize(true);
        recyclerView.addItemDecoration(headersDecor);
        recyclerView.setAdapter(myRecyclerViewAdapter);
    }


    @Override
    public void onItemClick(View view, int position, int viewType) {
        if (viewType == MyRecyclerViewAdapter.ITEM_VIEW) {
            Utils.Toast("ItemPosition = " + position);
        } else if (viewType == MyRecyclerViewAdapter.GRID_VIEW) {
            Utils.Toast("GridViewPosition = " + position);
        }
    }

    @Override
    public void onItemLongClick(View view, int position) {

    }
}
