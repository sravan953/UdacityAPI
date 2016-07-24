package com.biryanistudio.udacityapi.UI.Adapters;

import android.app.Fragment;
import android.app.FragmentManager;
import android.support.v13.app.FragmentPagerAdapter;

import com.biryanistudio.udacityapi.FragmentType;
import com.biryanistudio.udacityapi.UI.Fragments.GenericFragment;

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
        switch (position) {
            case 0:
                return "Assigned";
            case 1:
                return "Available";
            case 2:
                return "Feedback";
            default:
                return "Pager Item";
        }
    }

    @Override
    public Fragment getItem(int position) {
        switch (position) {
            case 0:
                return GenericFragment.newInstance(FragmentType.ASSIGNED);
            case 1:
                return GenericFragment.newInstance(FragmentType.AVAILABLE);
            case 2:
                return GenericFragment.newInstance(FragmentType.FEEDBACK);
            default:
                return null;
        }
    }
}
