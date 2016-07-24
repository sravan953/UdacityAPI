package com.biryanistudio.udacityapi.UI.Fragments;

import android.content.ComponentName;
import android.net.Uri;
import android.os.Bundle;
import android.preference.Preference;
import android.preference.PreferenceFragment;
import android.support.customtabs.CustomTabsClient;
import android.support.customtabs.CustomTabsIntent;
import android.support.customtabs.CustomTabsServiceConnection;
import android.support.customtabs.CustomTabsSession;
import android.support.v4.content.ContextCompat;
import android.util.Log;

import com.biryanistudio.udacityapi.R;

/**
 * Created by Aakaash Jois on 18-07-2016 at 01:28 AM.
 **/

public class SettingsFragment extends PreferenceFragment {
    private final String TAG = getClass().getSimpleName();
	private CustomTabsClient mCustomTabsClient;
	private CustomTabsSession mCustomTabsSession;
    private Uri uri;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		addPreferencesFromResource(R.xml.preferences);
        uri = Uri.parse(getString(R.string.API_URL));
		final Preference getAPIToken = findPreference(getString(R.string.pref_get_API_TOKEN));
		getAPIToken.setOnPreferenceClickListener(new Preference.OnPreferenceClickListener() {
			@Override
			public boolean onPreferenceClick(Preference preference) {
				CustomTabsIntent customTabsIntent = new CustomTabsIntent.Builder(mCustomTabsSession)
						.enableUrlBarHiding()
						.setToolbarColor(ContextCompat.getColor(getActivity(), R.color.colorPrimary))
						.setShowTitle(true)
						.build();
				customTabsIntent.launchUrl(getActivity(), uri);
				return true;
			}
		});
	}

    private void bindChromeCustomService() {
        // TODO: Fix this
        final String CHROME_STABLE_PACKAGE = "com.android.chrome";
        CustomTabsServiceConnection connection = new CustomTabsServiceConnection() {
            @Override
            public void onCustomTabsServiceConnected(ComponentName name, CustomTabsClient client) {
                mCustomTabsClient = client;
                mCustomTabsClient.warmup(0L);
                mCustomTabsSession = mCustomTabsClient.newSession(null);
                mCustomTabsSession.mayLaunchUrl(uri, null, null);
                Log.i(TAG, "Custom tabs service connected");
            }

            @Override
            public void onServiceDisconnected(ComponentName name) {
            }
        };
        CustomTabsClient.bindCustomTabsService(getActivity(), CHROME_STABLE_PACKAGE, connection);
    }
}