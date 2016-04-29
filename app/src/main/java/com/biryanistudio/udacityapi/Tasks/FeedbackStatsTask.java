package com.biryanistudio.udacityapi.Tasks;

import android.os.AsyncTask;

import com.biryanistudio.udacityapi.Interfaces.IUpdateFeedbackStats;
import com.biryanistudio.udacityapi.Models.FeedbackStats;
import com.biryanistudio.udacityapi.Service.RetrofitInstance;
import com.biryanistudio.udacityapi.Service.UdacityService;
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
    protected FeedbackStats doInBackground(FeedbackStatsFragment... params) {
        updateUIInterface = params[0];
        try {
            Retrofit retrofit = RetrofitInstance.retrofit;
            UdacityService udacityService = retrofit.create(UdacityService.class);
            Call<FeedbackStats> feedbackStatsCall = udacityService.getFeedbackStats();
            // TODO Add Log msg
            return feedbackStatsCall.execute().body();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    protected void onPostExecute(FeedbackStats feedbackStats) {
        updateUIInterface.feedbackStatsUI(feedbackStats);
    }
}
