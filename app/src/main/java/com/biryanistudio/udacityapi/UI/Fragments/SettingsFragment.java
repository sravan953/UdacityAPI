package com.biryanistudio.udacityapi.UI.Fragments;

import android.net.Uri;
import android.os.Bundle;
import android.preference.Preference;
import android.preference.PreferenceFragment;
import android.support.customtabs.CustomTabsIntent;
import android.support.customtabs.CustomTabsSession;
import android.support.v4.content.ContextCompat;

import com.biryanistudio.udacityapi.R;
import com.biryanistudio.udacityapi.UI.SettingsActivity;

/**
 * Created by Aakaash Jois on 18-07-2016 at 01:28 AM.
 **/

public class SettingsFragment extends PreferenceFragment {
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		addPreferencesFromResource(R.xml.preferences);
		final CustomTabsSession session = (( SettingsActivity ) getActivity()).getSession();
		final Preference getAPIToken = findPreference(getString(R.string.pref_get_API_TOKEN));
		getAPIToken.setOnPreferenceClickListener(new Preference.OnPreferenceClickListener() {
			@Override
			public boolean onPreferenceClick(Preference preference) {
				CustomTabsIntent customTabsIntent = new CustomTabsIntent.Builder(session)
						.enableUrlBarHiding()
						.setToolbarColor(ContextCompat.getColor(getActivity(), R.color.colorPrimary))
						.setShowTitle(true)
						.build();
				customTabsIntent.launchUrl(getActivity(), Uri.parse(getString(R.string.API_URL)));
				return true;
			}
		});
	}
}