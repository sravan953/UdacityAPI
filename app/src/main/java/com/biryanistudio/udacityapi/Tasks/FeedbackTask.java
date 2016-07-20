package com.biryanistudio.udacityapi.Tasks;

import android.os.AsyncTask;
import android.util.Log;

import com.biryanistudio.udacityapi.Interfaces.IUpdateFeedback;
import com.biryanistudio.udacityapi.Models.Feedback;
import com.biryanistudio.udacityapi.Service.RetrofitInstance;
import com.biryanistudio.udacityapi.Interfaces.UdacityService;
import com.biryanistudio.udacityapi.UI.Fragments.FeedbackFragment;

import java.util.List;

import retrofit2.Call;
import retrofit2.Retrofit;

/**
 * Created by Sravan on 05-Apr-16.
 */


public class FeedbackTask extends AsyncTask<FeedbackFragment, Void, List<Feedback>> {
    private final String TAG = getClass().getSimpleName();
    private IUpdateFeedback updateUIInterface;

    @Override
    protected List<Feedback> doInBackground(final FeedbackFragment... params) {
        Log.d(TAG, "doInBackground");
        updateUIInterface = params[0];
        try {
            final Retrofit retrofit = RetrofitInstance.retrofit;
            final UdacityService udacityService = retrofit.create(UdacityService.class);
            final Call<List<Feedback>> feedbacksCall = udacityService.getFeedback();
            return feedbacksCall.execute().body();
        } catch (final Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    protected void onPostExecute(final List<Feedback> feedbacks) {
        updateUIInterface.feedbackUI(feedbacks);
    }
}
