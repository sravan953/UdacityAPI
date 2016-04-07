package com.biryanistudio.udacityapi.Tasks;

import android.os.AsyncTask;
import android.util.Log;

import com.biryanistudio.udacityapi.UI.Fragments.FeedbackFragment;
import com.biryanistudio.udacityapi.Interfaces.IUpdateFeedback;
import com.biryanistudio.udacityapi.Models.Feedback;
import com.biryanistudio.udacityapi.Service.UdacityService;

import java.io.IOException;
import java.util.List;

import retrofit2.Call;
import retrofit2.GsonConverterFactory;
import retrofit2.Retrofit;

/**
 * Created by Sravan on 05-Apr-16.
 */


public class FeedbackTask extends AsyncTask<FeedbackFragment, Void, List<Feedback>> {
    private final String TAG = getClass().getSimpleName();
    private final String BASE_URL = "https://review-api.udacity.com/";
    private IUpdateFeedback updateUIInterface;

    @Override
    protected List<Feedback> doInBackground(FeedbackFragment... params) {
        updateUIInterface = params[0];
        try {
            Retrofit retrofit = new Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();

            UdacityService udacityService = retrofit.create(UdacityService.class);
            Call<List<Feedback>> feedbacksCall = udacityService.getFeedback();
            List<Feedback> feedbacks = feedbacksCall.execute().body();
            for(Feedback feedback : feedbacks) {
                Log.i(TAG, feedback.toString());
            }
            return feedbacks;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    protected void onPostExecute (List<Feedback> feedbacks) {
        updateUIInterface.feedbackUI(feedbacks);
    }
}
