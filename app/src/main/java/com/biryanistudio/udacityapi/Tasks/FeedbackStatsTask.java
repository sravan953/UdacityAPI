package com.biryanistudio.udacityapi.Tasks;

import android.os.AsyncTask;
import android.util.Log;

import com.biryanistudio.udacityapi.Interfaces.IUpdateFeedbackStats;
import com.biryanistudio.udacityapi.Models.FeedbackStats;
import com.biryanistudio.udacityapi.Service.RetrofitInstance;
import com.biryanistudio.udacityapi.Interfaces.UdacityService;
import com.biryanistudio.udacityapi.UI.Fragments.FeedbackStatsFragment;

import retrofit2.Call;
import retrofit2.Retrofit;

/**
 * Created by Sravan on 05-Apr-16.
 */


public class FeedbackStatsTask extends AsyncTask<FeedbackStatsFragment, Void, FeedbackStats> {
    private final String TAG = getClass().getSimpleName();
    private IUpdateFeedbackStats updateUIInterface;

    @Override
    protected FeedbackStats doInBackground(final FeedbackStatsFragment... params) {
        Log.d(TAG, "doInBackground");
        updateUIInterface = params[0];
        try {
            final Retrofit retrofit = RetrofitInstance.retrofit;
            final UdacityService udacityService = retrofit.create(UdacityService.class);
            final Call<FeedbackStats> feedbackStatsCall = udacityService.getFeedbackStats();
            return feedbackStatsCall.execute().body();
        } catch (final Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    protected void onPostExecute(final FeedbackStats feedbackStats) {
        updateUIInterface.feedbackStatsUI(feedbackStats);
    }
}
