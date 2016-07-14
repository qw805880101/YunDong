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
        ViewHolder holder = new ViewHolder(View.inflate(mContext, R.layout.item_changguan, null));
        return holder;
    }

    @Override
    public void onBindViewHolder(MyRecyclerViewAdapter.ViewHolder holder, int position) {
//        holder.tx_changdi_name.setText(arrs.get(position).getName());
//        holder.tx_changdi_address.setText("[" + arrs.get(position).getAddress() + "]");
//        holder.tx_changdi_money.setText("￥" + arrs.get(position).getMoney());
//        holder.tx_km.setText(">" + arrs.get(position).getKm());
        holder.iamge.setImageResource(arrs.get(position).getImageUrl());
//        holder.bt_yuding.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//
//            }
//        });
    }

    @Override
    public int getItemCount() {
        return arrs.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        ImageView iamge;
        TextView tx_changdi_name;
        TextView tx_changdi_address;
        TextView tx_changdi_money;
        TextView tx_km;
        Button bt_yuding;

        public ViewHolder(View itemView) {
            super(itemView);
            AutoUtils.autoSize(itemView);
            iamge = (ImageView) itemView.findViewById(R.id.item_image);
//            tx_changdi_name = (TextView) itemView.findViewById(R.id.tx_changdi_name);
//            tx_changdi_address = (TextView) itemView.findViewById(R.id.tx_changdi_address);
//            tx_changdi_money = (TextView) itemView.findViewById(R.id.tx_changdi_money);
//            tx_km = (TextView) itemView.findViewById(R.id.tx_km);
//            bt_yuding = (Button) itemView.findViewById(R.id.bt_yuding);
        }
    }
}
