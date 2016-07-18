package com.biryanistudio.udacityapi.UI;

import android.os.Bundle;
import android.preference.PreferenceActivity;

import com.biryanistudio.udacityapi.UI.Fragments.SettingsFragment;

public class SettingsActivity extends PreferenceActivity {
    private final String TAG = getClass().getSimpleName();

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getFragmentManager().beginTransaction()
                .replace(android.R.id.content, new SettingsFragment())
                .commit();

        //TODO: Chrome tabs needs to be tested on multiple Android Versions with different chrome versions
    }
}
