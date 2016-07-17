package com.biryanistudio.udacityapi.UI;

import android.content.ComponentName;
import android.net.Uri;
import android.os.Bundle;
import android.preference.PreferenceActivity;
import android.support.customtabs.CustomTabsClient;
import android.support.customtabs.CustomTabsServiceConnection;
import android.support.customtabs.CustomTabsSession;

import com.biryanistudio.udacityapi.R;
import com.biryanistudio.udacityapi.UI.Fragments.SettingsFragment;

public class SettingsActivity extends PreferenceActivity {
    private final String TAG = getClass().getSimpleName();

    private CustomTabsClient client;
    private CustomTabsSession session;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getFragmentManager().beginTransaction()
                .replace(android.R.id.content, new SettingsFragment())
                .commit();
        CustomTabsServiceConnection serviceConnection = new CustomTabsServiceConnection() {
            @Override
            public void onCustomTabsServiceConnected(ComponentName componentName, CustomTabsClient customTabsClient) {
                client = customTabsClient;
                client.warmup(0L);
                session = client.newSession(null);
                session.mayLaunchUrl(Uri.parse(getString(R.string.API_URL)), null, null);
            }

            @Override
            public void onServiceDisconnected(ComponentName componentName) {
                client = null;
            }
        };
        CustomTabsClient.bindCustomTabsService(this, "com.android.chrome", serviceConnection);
        //TODO: Chrome tabs needs to be tested on multiple Android Versions with different chrome versions
    }

    public CustomTabsSession getSession() {
        return session;
    }
}
