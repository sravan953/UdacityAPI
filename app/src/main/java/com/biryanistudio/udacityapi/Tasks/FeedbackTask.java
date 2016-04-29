package com.biryanistudio.udacityapi.Tasks;

import android.os.AsyncTask;

import com.biryanistudio.udacityapi.Interfaces.IUpdateFeedback;
import com.biryanistudio.udacityapi.Models.Feedback;
import com.biryanistudio.udacityapi.Service.RetrofitInstance;
import com.biryanistudio.udacityapi.Service.UdacityService;
import com.biryanistudio.udacityapi.UI.Fragments.FeedbackFragment;
import com.biryanistudio.udacityapi.UI.MainActivity;

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
    protected List<Feedback> doInBackground(FeedbackFragment... params) {
        updateUIInterface = params[0];
        try {
            Retrofit retrofit = RetrofitInstance.retrofit;
            UdacityService udacityService = retrofit.create(UdacityService.class);
            Call<List<Feedback>> feedbacksCall = udacityService.getFeedback();
            return feedbacksCall.execute().body();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    protected void onPostExecute(List<Feedback> feedbacks) {
        updateUIInterface.feedbackUI(feedbacks);
    }
}
