package com.biryanistudio.udacityapi.UI;

import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import com.biryanistudio.udacityapi.BuildConfig;
import com.biryanistudio.udacityapi.R;
import com.biryanistudio.udacityapi.UI.Adapters.CustomViewPager;
import com.biryanistudio.udacityapi.UI.SlidingLayout.SlidingTabLayout;

public class MainActivity extends AppCompatActivity {
    private final String TAG = getClass().getSimpleName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ViewPager viewPager = (ViewPager) findViewById(R.id.viewpager);
        viewPager.setOffscreenPageLimit(2);
        viewPager.setAdapter(new CustomViewPager(getFragmentManager()));
        SlidingTabLayout slidingTabLayout = (SlidingTabLayout) findViewById(R.id.sliding_tabs);
        slidingTabLayout.setViewPager(viewPager);

        Log.d(TAG, BuildConfig.API_KEY);
    }
}
