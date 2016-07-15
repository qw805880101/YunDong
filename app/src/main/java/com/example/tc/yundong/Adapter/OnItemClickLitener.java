package com.example.tc.yundong.Adapter;

import android.view.View;

/**
 * Created by tc on 2016/3/18.
 */
public interface OnItemClickLitener {
    void onItemClick(View view, int position, int viewType);
    void onItemLongClick(View view, int position);
}
