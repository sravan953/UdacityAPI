package com.biryanistudio.udacityapi.UI.Adapters;

import android.app.Fragment;
import android.app.FragmentManager;
import android.support.v13.app.FragmentPagerAdapter;

import com.biryanistudio.udacityapi.UI.Fragments.FeedbackFragment;
import com.biryanistudio.udacityapi.UI.Fragments.CurrentReviewsFragment;
import com.biryanistudio.udacityapi.UI.Fragments.AvailableReviewsFragment;

/**
 * Created by Sravan on 07-Apr-16.
 */
public class CustomViewPagerAdapter extends FragmentPagerAdapter {
    private final String TAG = getClass().getSimpleName();

    public CustomViewPagerAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public int getCount() {
        return 3;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        switch(position) {
            case 0: return "Current Reviews";
            case 1: return "Available Reviews";
            case 2: return "Your Ratings";
            default: return "Pager Item";
        }
    }

    @Override
    public Fragment getItem(int position) {
        switch(position) {
            case 0: return new CurrentReviewsFragment();
            case 1: return new AvailableReviewsFragment();
            case 2: return new FeedbackFragment();
            default: return null;
        }
    }
}
