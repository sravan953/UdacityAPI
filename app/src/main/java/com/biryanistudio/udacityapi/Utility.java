package com.biryanistudio.udacityapi;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

/**
 * Created by Sravan on 29-Apr-16.
 */
public class Utility {
    //public static String API_TOKEN = "";

    public static boolean checkNetworkConnectivity(Context context) {
        ConnectivityManager connMgr = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = connMgr.getActiveNetworkInfo();
        return networkInfo != null && networkInfo.isConnected();
    }
}
