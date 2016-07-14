package com.example.tc.yundong.Adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.tc.yundong.JavaBeen.Stadiums;
import com.example.tc.yundong.R;
import com.example.tc.yundong.Util.Utils;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.zhy.autolayout.utils.AutoUtils;

import java.util.List;

/**
 * Created by tc on 2016/7/14.
 */
public class MyRecyclerViewAdapter extends RecyclerView.Adapter<MyRecyclerViewAdapter.ViewHolder> {
    private Context mContext;
    private List<Stadiums> arrs;

    public MyRecyclerViewAdapter(Context mContext, List<Stadiums> arrs) {
        this.mContext = mContext;
        this.arrs = arrs;
    }

    @Override
    public MyRecyclerViewAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        ViewHolder holder = new ViewHolder(View.inflate(mContext, R.layout.item_stadium, null));
        return holder;
    }

    @Override
    public void onBindViewHolder(MyRecyclerViewAdapter.ViewHolder holder, int position) {
        Stadiums stadiums = arrs.get(position);
        holder.tx_stadium_name.setText(stadiums.getName());
        holder.tx_stadium_address.setText("[" + stadiums.getAddress() + "]");
        holder.tx_stadium_money.setText("￥" + stadiums.getAvgprice());
//        holder.tx_km.setText(">" + arrs.get(position).getKm());
        ImageLoader.getInstance().displayImage(Utils.getPhotoUrl(stadiums.getPic()), holder.stadiumIamge);
        holder.bt_yuding.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
    }

    @Override
    public int getItemCount() {
        return arrs.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        ImageView stadiumIamge;
        TextView tx_stadium_name;
        TextView tx_stadium_address;
        TextView tx_stadium_money;
        TextView tx_km;
        Button bt_yuding;

        public ViewHolder(View itemView) {
            super(itemView);
            AutoUtils.autoSize(itemView);
            stadiumIamge = (ImageView) itemView.findViewById(R.id.item_stadium_image);
            tx_stadium_name = (TextView) itemView.findViewById(R.id.tx_stadium_name);
            tx_stadium_address = (TextView) itemView.findViewById(R.id.tx_stadium_address);
            tx_stadium_money = (TextView) itemView.findViewById(R.id.tx_stadium_money);
            tx_km = (TextView) itemView.findViewById(R.id.tx_km);
            bt_yuding = (Button) itemView.findViewById(R.id.bt_yuding);
        }
    }
}
