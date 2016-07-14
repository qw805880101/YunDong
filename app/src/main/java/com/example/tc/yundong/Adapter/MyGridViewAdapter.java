package com.example.tc.yundong.Adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.tc.yundong.JavaBeen.SportsType;
import com.example.tc.yundong.R;

import java.util.List;

/**
 * Created by tc on 2016/7/13.
 */
public class MyGridViewAdapter extends BaseAdapter{

    private Context mContext;
    private List<SportsType> arrs;

    public MyGridViewAdapter(Context mContext, List<SportsType> arrs) {
        this.mContext = mContext;
        this.arrs = arrs;
    }

    @Override
    public int getCount() {
        return arrs.size();
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View view = null;
        ViewHolder holder;
        if (convertView == null) {
            holder = new ViewHolder();
            view = View.inflate(mContext, R.layout.item_grid,
                    null);
            holder.iamge = (ImageView) view
                    .findViewById(R.id.ivIcon);
            holder.name = (TextView) view
                    .findViewById(R.id.tvTitle);
            view.setTag(holder);
        } else {
            view = convertView;
            holder = (ViewHolder) view.getTag();
        }
        holder.name.setText(arrs.get(position).getSportsName());
        holder.iamge.setImageResource(arrs.get(position).getSportsImage());
        return view;
    }

    private static class ViewHolder {
        ImageView iamge;
        TextView name;
    }

}
