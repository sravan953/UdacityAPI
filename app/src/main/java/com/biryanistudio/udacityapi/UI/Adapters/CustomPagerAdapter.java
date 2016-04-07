package com.biryanistudio.udacityapi.UI.Adapters;

import android.app.Fragment;
import android.app.FragmentManager;
import android.support.v13.app.FragmentPagerAdapter;

import com.biryanistudio.udacityapi.UI.Fragments.FeedbackFragment;
import com.biryanistudio.udacityapi.UI.Fragments.SubmissionsFragment;

/**
 * Created by Sravan on 07-Apr-16.
 */
public class CustomPagerAdapter extends FragmentPagerAdapter {
    private final String TAG = getClass().getSimpleName();

    public CustomPagerAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public int getCount() {
        return 2;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        switch(position) {
            case 0: return "Available Reviews";
            case 1: return "Current Reviews";
            case 2: return "Your Ratings";
            default: return "Pager Item";
        }
    }

    @Override
    public Fragment getItem(int position) {
        switch(position) {
            case 0: return new SubmissionsFragment();
            case 1: return new FeedbackFragment();
            case 2: return null;
            default: return null;
        }
    }
}
