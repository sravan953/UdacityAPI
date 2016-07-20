package com.biryanistudio.udacityapi.Tasks;

import android.os.AsyncTask;
import android.util.Log;

import com.biryanistudio.udacityapi.Interfaces.IUpdateAvailableReviews;
import com.biryanistudio.udacityapi.Models.Submission;
import com.biryanistudio.udacityapi.Service.RetrofitInstance;
import com.biryanistudio.udacityapi.Interfaces.UdacityService;
import com.biryanistudio.udacityapi.UI.Fragments.AvailableFragment;

import retrofit2.Call;
import retrofit2.Response;
import retrofit2.Retrofit;

/**
 * Created by Sravan on 01-May-16.
 */


public class AssignProjectTask extends AsyncTask<AvailableFragment, Void, Response<Submission>> {
    private final String TAG = getClass().getSimpleName();
    private IUpdateAvailableReviews updateUIInterface;

    @Override
    protected Response<Submission> doInBackground(final AvailableFragment... params) {
        Log.d(TAG, "doInBackground");
        updateUIInterface = params[0];
        final int projectID = params[0].projectID;
        Log.d(TAG, String.valueOf(projectID));
        try {
            final Retrofit retrofit = RetrofitInstance.retrofit;
            final UdacityService udacityService = retrofit.create(UdacityService.class);
            final Call<Submission> assignProjectCall = udacityService.postAssignProject(projectID);
            return assignProjectCall.execute();
        } catch (final Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    protected void onPostExecute(final Response<Submission> response) {
        updateUIInterface.refreshAvailableReviewsUI(response.code());
    }
}
