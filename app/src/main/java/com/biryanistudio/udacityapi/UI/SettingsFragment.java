package com.biryanistudio.udacityapi.UI;

import android.app.Dialog;
import android.content.ComponentName;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.customtabs.CustomTabsClient;
import android.support.customtabs.CustomTabsIntent;
import android.support.customtabs.CustomTabsServiceConnection;
import android.support.customtabs.CustomTabsSession;
import android.support.design.widget.BottomSheetDialog;
import android.support.design.widget.BottomSheetDialogFragment;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.support.v7.preference.PreferenceFragmentCompat;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.biryanistudio.udacityapi.R;

/**
 * Created by Aakaash Jois on 18-07-2016 at 01:28 AM.
 **/


public class SettingsFragment extends BottomSheetDialogFragment {

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_settings, container, false);
    }

    @NonNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        return new BottomSheetDialog(getActivity(), getTheme());
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        if (savedInstanceState != null) {
            Fragment fragment = Fragment.instantiate(getActivity(), PreferenceSettingsFragment.class.getName(), getArguments());
            getChildFragmentManager()
                    .beginTransaction()
                    .add(R.id.settingsHolder, fragment)
                    .commit();
        }
    }

    public static class PreferenceSettingsFragment extends PreferenceFragmentCompat {
        private final String TAG = getClass().getSimpleName();
        private CustomTabsClient mCustomTabsClient;
        private CustomTabsSession mCustomTabsSession;
        private Uri uri;

        @Override
        public void onCreatePreferences(Bundle savedInstanceState, String rootKey) {
            addPreferencesFromResource(R.xml.preferences);
            uri = Uri.parse(getString(R.string.API_URL));
            final android.support.v7.preference.Preference getAPIToken = findPreference(getString(R.string.pref_get_API_TOKEN));
            getAPIToken.setOnPreferenceChangeListener(new android.support.v7.preference.Preference.OnPreferenceChangeListener() {
                @Override
                public boolean onPreferenceChange(android.support.v7.preference.Preference preference, Object newValue) {
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
}