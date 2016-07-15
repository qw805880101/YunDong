package com.example.tc.yundong.Adapter;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.drawable.ColorDrawable;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.tc.yundong.JavaBeen.SportsType;
import com.example.tc.yundong.JavaBeen.Stadiums;
import com.example.tc.yundong.R;
import com.example.tc.yundong.Util.Info;
import com.example.tc.yundong.Util.Utils;
import com.example.tc.yundong.View.KannerView;
import com.example.tc.yundong.View.MetaballView;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.display.RoundedBitmapDisplayer;
import com.timehop.stickyheadersrecyclerview.StickyRecyclerHeadersAdapter;
import com.zhy.autolayout.utils.AutoUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by tc on 2016/7/14.
 */
public class MyRecyclerViewAdapter extends RecyclerView.Adapter<MyRecyclerViewAdapter.ViewHolder> implements
        StickyRecyclerHeadersAdapter {

    public final static int ITEM1 = 0;
    public final static int ITEM2 = 1;

    public final static int GRID_VIEW = 0;
    public final static int ITEM_VIEW = 1;

    private Context mContext;
    private List<Stadiums> arrs;
    private DisplayImageOptions options1;
    private OnItemClickLitener onItemClickLitener;

    private String[] sportsName = {"羽毛球", "篮球", "足球", "网球", "游泳", "游泳"};
    private int[] sportsImage = {R.mipmap.badminton, R.mipmap.basketball, R.mipmap.football, R.mipmap.baseball, R
            .mipmap.swimming, R.mipmap.swimming};

    private int[] kanners = {R.mipmap.kanner_1};

    private int page;

    public MyRecyclerViewAdapter(Context mContext, List<Stadiums> arrs) {
        this.mContext = mContext;
        this.arrs = arrs;
        //圆角图片
        options1 = new DisplayImageOptions.Builder()
//                .showStubImage(R.drawable.ic_stub) // 缓冲过程中图片
//                .showImageForEmptyUri(R.mipmap.ic_launcher)// 设置图片Uri为空或是错误的时候显示的图片
//                .showImageOnFail(R.drawable.ic_error) // 设置图片加载或解码过程中发生错误显示的图片
                .cacheInMemory(true)//缓存道内存
                .cacheOnDisc(true)//缓存到硬盘
                .bitmapConfig(Bitmap.Config.ARGB_8888)   //设置图片的解码类型
                .displayer(new RoundedBitmapDisplayer(20))
                .build();
    }

    public void setOnItemClickLitener(OnItemClickLitener onItemClickLitener) {
        this.onItemClickLitener = onItemClickLitener;
    }

    @Override
    public MyRecyclerViewAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        ViewHolder holder = null;
        if (viewType == ITEM1) {
            holder = new ViewHolder(View.inflate(mContext, R.layout.recyclerview_item_1, null), viewType);
        } else {
            holder = new ViewHolder(View.inflate(mContext, R.layout.item_stadium, null), viewType);
        }
        return holder;
    }

    @Override
    public int getItemViewType(int position) {
        if (position == 0) {
            return ITEM1;
        } else {
            return ITEM2;
        }
    }

    @Override
    public void onBindViewHolder(final MyRecyclerViewAdapter.ViewHolder holder, int position) {
        Stadiums stadiums = arrs.get(position);

        if (holder.viewType == ITEM1) {
            holder.kannView.setFocusable(true);
            holder.kannView.setImagesRes(kanners);
            holder.kannView.setOnItemClickListener(new KannerView.OnItemClickListener() {
                @Override
                public void click(View v, int id) { //kanner——Item点击

                }
            });
            /* 加载数据 */
            List<View> views = getAllView(mContext, getAllDate());
            MyPagerAdapter myPagerAdapter = new MyPagerAdapter(mContext, views);
            holder.viewPager.setAdapter(myPagerAdapter);
            holder.viewPager.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {

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

        } else {
            holder.tx_stadium_name.setText(stadiums.getName());
            holder.tx_stadium_address.setText("[" + stadiums.getAddress() + "]");
            holder.tx_stadium_money.setText("￥" + stadiums.getAvgprice());

            if (stadiums.getOrgprice() != 0) {
                holder.tx_stadium_oldmoney.setText("￥" + stadiums.getOrgprice());
                holder.tx_stadium_oldmoney.setPaintFlags(Paint.STRIKE_THRU_TEXT_FLAG | Paint.ANTI_ALIAS_FLAG);//
                // 设置中划线并加清晰
            }
            if (stadiums.getLat() != 0 && stadiums.getLon() != 0) {
                double km = Utils.getDistance(Info.myApp.getLat(), Info.myApp.getLon(), stadiums.getLat(), stadiums
                        .getLon());
                holder.tx_km.setText(km + "km");
            }
            Info.myApp.getLat();
            ImageLoader.getInstance().displayImage(Utils.getPhotoUrl(stadiums.getPic()), holder.stadiumIamge, options1);
            holder.bt_yuding.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                }
            });

            if (onItemClickLitener != null) {
                holder.itemView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        int pos = holder.getLayoutPosition();
                        onItemClickLitener.onItemClick(holder.itemView, pos, ITEM_VIEW);
                    }
                });
                holder.itemView.setOnLongClickListener(new View.OnLongClickListener() {
                    @Override
                    public boolean onLongClick(View v) {
                        int pos = holder.getLayoutPosition();
                        onItemClickLitener.onItemClick(holder.itemView, pos, ITEM_VIEW);
                        return false;
                    }
                });
            }
        }
    }

    @Override
    public long getHeaderId(int position) {
        if (position == 0) {
            //不添加 头部view
            return -1;
        } else {
            //添加 头部view
            return 0;
        }
    }

    @Override
    public RecyclerView.ViewHolder onCreateHeaderViewHolder(ViewGroup parent) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.recyclerview_head, parent, false);
        return new HeaderViewHolder(view);
    }

    @Override
    public void onBindHeaderViewHolder(RecyclerView.ViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return arrs.size();
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
        view.setSelector(new ColorDrawable(Color.TRANSPARENT));
        MyGridViewAdapter adapter = new MyGridViewAdapter(mContext, arr);
        view.setAdapter(adapter);
        view.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
//                Utils.Toast("position = " + (page * 5 + position));
                onItemClickLitener.onItemClick(view, (page * 5 + position), GRID_VIEW);
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

    class HeaderViewHolder extends RecyclerView.ViewHolder {

        public HeaderViewHolder(View itemView) {
            super(itemView);
        }
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        ImageView stadiumIamge;
        TextView tx_stadium_name;
        TextView tx_stadium_address;
        TextView tx_stadium_money;
        TextView tx_km;
        TextView tx_stadium_oldmoney;
        Button bt_yuding;

        MetaballView viewPager; // 运动类型

        KannerView kannView; //广告页

        int viewType;

        public ViewHolder(View itemView, int viewType) {
            super(itemView);
            AutoUtils.autoSize(itemView);
            if (viewType == ITEM1) {
                kannView = (KannerView) itemView.findViewById(R.id.kanner);
                viewPager = (MetaballView) itemView.findViewById(R.id.page_image);
            } else {
                stadiumIamge = (ImageView) itemView.findViewById(R.id.item_stadium_image);
                tx_stadium_name = (TextView) itemView.findViewById(R.id.tx_stadium_name);
                tx_stadium_address = (TextView) itemView.findViewById(R.id.tx_stadium_address);
                tx_stadium_money = (TextView) itemView.findViewById(R.id.tx_stadium_money);
                tx_stadium_oldmoney = (TextView) itemView.findViewById(R.id.tx_stadium_oldmoney);
                tx_km = (TextView) itemView.findViewById(R.id.tx_km);
                bt_yuding = (Button) itemView.findViewById(R.id.bt_yuding);
            }
            this.viewType = viewType;
        }
    }
}
