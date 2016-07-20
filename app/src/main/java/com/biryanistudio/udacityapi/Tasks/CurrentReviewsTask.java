package com.biryanistudio.udacityapi.Tasks;

import android.os.AsyncTask;
import android.util.Log;

import com.biryanistudio.udacityapi.Interfaces.IUpdateCurrentReviews;
import com.biryanistudio.udacityapi.Models.Submission;
import com.biryanistudio.udacityapi.Service.RetrofitInstance;
import com.biryanistudio.udacityapi.Interfaces.UdacityService;
import com.biryanistudio.udacityapi.UI.Fragments.AssignedFragment;

import java.util.List;

import retrofit2.Call;
import retrofit2.Retrofit;

/**
 * Created by Sravan on 05-Apr-16.
 */


public class CurrentReviewsTask extends AsyncTask<AssignedFragment, Void, List<Submission>> {
    private final String TAG = getClass().getSimpleName();
    private IUpdateCurrentReviews updateUIInterface;

    @Override
    protected List<Submission> doInBackground(final AssignedFragment... params) {
        Log.d(TAG, "doInBackground");
        updateUIInterface = params[0];
        try {
            final Retrofit retrofit = RetrofitInstance.retrofit;
            final UdacityService udacityService = retrofit.create(UdacityService.class);
            final Call<List<Submission>> submissionsCall = udacityService.getSubmissions();
            return submissionsCall.execute().body();
        } catch (final Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    protected void onPostExecute(final List<Submission> submissions) {
        updateUIInterface.currentReviewsUI(submissions);
    }
}