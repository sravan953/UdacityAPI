package com.biryanistudio.udacityapi.UI;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Build;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.TabLayout;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import com.biryanistudio.udacityapi.Alarm.AlarmHelper;
import com.biryanistudio.udacityapi.R;
import com.biryanistudio.udacityapi.UI.Adapters.CustomViewPagerAdapter;
import com.biryanistudio.udacityapi.Utility;

public class MainActivity extends AppCompatActivity {
    public static boolean API_TOKEN_present = false;
    public static String API_TOKEN;
    private final String TAG = getClass().getSimpleName();
    private ViewPager viewPager;
    private TextView errorTextView;
    private TabLayout tabLayout;
    private Toolbar toolbar;
    private AppBarLayout appbar;

    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initUI();
        setUI();
        setNotifs();
    }

    @Override
    protected void onStart() {
        super.onStart();
        if (validateAPIAccessToken()) {
            hideErrorTextView();
        } else {
            showErrorTextView();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(final Menu menu) {
        getMenuInflater().inflate(R.menu.main, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(final MenuItem item) {
        startActivity(new Intent(this, SettingsActivity.class));
        return super.onOptionsItemSelected(item);
    }

    private void initUI() {
        viewPager = (ViewPager) findViewById(R.id.view_pager);
        errorTextView = (TextView) findViewById(R.id.error_text_view);
        tabLayout = (TabLayout) findViewById(R.id.tabs);
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        appbar = (AppBarLayout) findViewById(R.id.appbar);
    }

    private void setUI() {
        setSupportActionBar(toolbar);
        final CustomViewPagerAdapter customViewPagerAdapter = new CustomViewPagerAdapter(getFragmentManager());
        viewPager.setOffscreenPageLimit(2);
        viewPager.setAdapter(customViewPagerAdapter);
        tabLayout.setupWithViewPager(viewPager);
    }

    private void setNotifs() {
        final SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(this);
        final boolean isNotifEnabled = sharedPreferences.getBoolean(getString(R.string.key_NOTIF_SET_MONTHLY), true);
        final boolean isNotifSet = sharedPreferences.getBoolean(getString(R.string.key_NOTIF_ALREADY_SET), false);
        if (isNotifEnabled) {
            Log.i(TAG, "Monthly alarms enabled");
            if (!isNotifSet) {
                Log.i(TAG, "Monthly alarms not set");
                setMonthlyAlarms();
                sharedPreferences.edit().putBoolean(getString(R.string.key_NOTIF_ALREADY_SET), true).apply();
            }
        } else {
            Log.i(TAG, "Monthly alarms disabled");
        }
    }

    private void setMonthlyAlarms() {
        new AlarmHelper(this).setMonthlyAlarms();
    }

    private boolean validateAPIAccessToken() {
        final SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(this);
        final String token = sharedPreferences.getString(getString(R.string.key_API_TOKEN), "");
        if (token.equalsIgnoreCase("")) {
            Log.d(TAG, "Token not found!");
            API_TOKEN_present = false;
            return false;
        } else {
            API_TOKEN = token;
            Log.d(TAG, "Token found: " + API_TOKEN);
            API_TOKEN_present = true;
            return true;
        }
    }

    private void showErrorTextView() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            getWindow().setNavigationBarColor(ContextCompat.getColor(getApplicationContext(), R.color.colorErrorDark));
            getWindow().setStatusBarColor(ContextCompat.getColor(getApplicationContext(), R.color.colorErrorDark));
        }
        appbar.setBackgroundColor(ContextCompat.getColor(getApplicationContext(), R.color.colorError));
        toolbar.setBackgroundColor(ContextCompat.getColor(getApplicationContext(), R.color.colorError));
        viewPager.setVisibility(View.INVISIBLE);
        tabLayout.setVisibility(View.GONE);
        errorTextView.setVisibility(View.VISIBLE);
    }

    private void hideErrorTextView() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            getWindow().setNavigationBarColor(ContextCompat.getColor(getApplicationContext(), R.color.colorPrimaryDark));
            getWindow().setStatusBarColor(ContextCompat.getColor(getApplicationContext(), R.color.colorPrimaryDark));
        }
        appbar.setBackgroundColor(ContextCompat.getColor(getApplicationContext(), R.color.colorPrimary));
        toolbar.setBackgroundColor(ContextCompat.getColor(getApplicationContext(), R.color.colorPrimary));
        viewPager.setVisibility(View.VISIBLE);
        tabLayout.setVisibility(View.VISIBLE);
        errorTextView.setVisibility(View.INVISIBLE);
        setUI();
    }

    public class ConnectivityChangeReceiver extends BroadcastReceiver {
        @Override
        public void onReceive(final Context context, final Intent intent) {
            Log.d(TAG, "CONNECTIVITY CHANGE");
            if (Utility.checkNetworkConnectivity(context)) showErrorTextView();
            else hideErrorTextView();
        }
    }
}