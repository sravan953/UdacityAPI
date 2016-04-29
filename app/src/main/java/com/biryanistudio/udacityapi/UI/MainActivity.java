package com.biryanistudio.udacityapi.UI;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import com.biryanistudio.udacityapi.R;
import com.biryanistudio.udacityapi.UI.Adapters.CustomViewPagerAdapter;
import com.biryanistudio.udacityapi.UI.SlidingLayout.SlidingTabLayout;
import com.biryanistudio.udacityapi.Utility;

public class MainActivity extends AppCompatActivity {
    private final String TAG = getClass().getSimpleName();
    private ViewPager viewPager;
    private CustomViewPagerAdapter customViewPagerAdapter;
    private SlidingTabLayout slidingTabLayout;
    private TextView errorTextView;

    public static boolean API_TOKEN_present = false;
    public static String API_TOKEN;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initUI();
        setUI();
    }

    @Override
    protected void onResume() {
        super.onResume();

        if(validAPIAccessToken()) hideErrorTextView();
        else showErrorTextView();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected (MenuItem item) {
        startActivity(new Intent(this, SettingsActivity.class));
        return super.onOptionsItemSelected(item);
    }

    private void initUI() {
        viewPager = (ViewPager) findViewById(R.id.view_pager);
        viewPager.setOffscreenPageLimit(2);
        slidingTabLayout = (SlidingTabLayout) findViewById(R.id.sliding_tabs);
        errorTextView = (TextView) findViewById(R.id.error_text_view);
    }

    private void setUI() {
        customViewPagerAdapter = new CustomViewPagerAdapter(getFragmentManager());
        viewPager.setAdapter(customViewPagerAdapter);
        slidingTabLayout.setViewPager(viewPager);
    }

    private boolean validAPIAccessToken() {
        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(this);
        String token = sharedPreferences.getString(getString(R.string.key_API_TOKEN), "null");
        if(token.equalsIgnoreCase("null")) {
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
        viewPager.setVisibility(View.INVISIBLE);
        slidingTabLayout.setVisibility(View.INVISIBLE);
        errorTextView.setVisibility(View.VISIBLE);
    }

    private void hideErrorTextView() {
        viewPager.setVisibility(View.VISIBLE);
        slidingTabLayout.setVisibility(View.VISIBLE);
        errorTextView.setVisibility(View.INVISIBLE);
        setUI();
    }

    public class ConnectivityChangeReceiver extends BroadcastReceiver {
        @Override
        public void onReceive(Context context, Intent intent) {
            Log.d(TAG, "CHANGE");
            String action = intent.getAction();
            if(action.equals("android.net.conn.CONNECTIVITY_CHANGE")) {
                if (Utility.checkNetworkConnectivity(context)) showErrorTextView();
                else hideErrorTextView();
            }
        }
    }
}
