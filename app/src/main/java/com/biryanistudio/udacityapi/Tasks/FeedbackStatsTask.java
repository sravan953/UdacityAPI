package com.biryanistudio.udacityapi.Tasks;

import android.os.AsyncTask;
import android.util.Log;

import com.biryanistudio.udacityapi.Interfaces.IUpdateFeedbackStats;
import com.biryanistudio.udacityapi.Models.FeedbackStats;
import com.biryanistudio.udacityapi.Service.UdacityService;
import com.biryanistudio.udacityapi.UI.Fragments.FeedbackStatsFragment;

import java.io.IOException;

import retrofit2.Call;
import retrofit2.GsonConverterFactory;
import retrofit2.Retrofit;

/**
 * Created by Sravan on 05-Apr-16.
 */


public class FeedbackStatsTask extends AsyncTask<FeedbackStatsFragment, Void, FeedbackStats> {
    private final String TAG = getClass().getSimpleName();
    private final String BASE_URL = "https://review-api.udacity.com/";
    private IUpdateFeedbackStats updateUIInterface;

    @Override
    protected FeedbackStats doInBackground(FeedbackStatsFragment... params) {
        updateUIInterface = params[0];
        try {
            Retrofit retrofit = new Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();

            UdacityService udacityService = retrofit.create(UdacityService.class);
            Call<FeedbackStats> feedbackStatsCall = udacityService.getFeedbackStats();
            FeedbackStats feedbackStats = feedbackStatsCall.execute().body();
            Log.i(TAG, feedbackStats.toString());
            return feedbackStats;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    protected void onPostExecute (FeedbackStats feedbackStats) {
        updateUIInterface.feedbackStatsUI(feedbackStats);
    }
}
