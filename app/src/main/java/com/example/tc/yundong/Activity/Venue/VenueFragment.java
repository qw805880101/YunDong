package com.example.tc.yundong.Activity.Venue;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.tc.yundong.R;

/**
 * Created by tc on 2016/7/12.
 */
public class VenueFragment extends Fragment {

    private ViewPager viewPager;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle
            savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_venue, null);
        initView(view);

        return view;
    }

    private void initView(View view) {
        viewPager = (ViewPager) view.findViewById(R.id.page_image);
    }
}
