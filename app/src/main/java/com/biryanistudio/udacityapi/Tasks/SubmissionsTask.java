package com.biryanistudio.udacityapi.Tasks;

import android.os.AsyncTask;
import android.util.Log;

import com.biryanistudio.udacityapi.UI.Fragments.SubmissionsFragment;
import com.biryanistudio.udacityapi.Interfaces.IUpdateSubmissions;
import com.biryanistudio.udacityapi.Models.Submissions;
import com.biryanistudio.udacityapi.Service.UdacityService;

import java.io.IOException;
import java.util.List;

import retrofit2.Call;
import retrofit2.GsonConverterFactory;
import retrofit2.Retrofit;

/**
 * Created by Sravan on 05-Apr-16.
 */


public class SubmissionsTask extends AsyncTask<SubmissionsFragment, Void, List<Submissions>> {
    private final String TAG = getClass().getSimpleName();
    private final String BASE_URL = "https://review-api.udacity.com/";
    private IUpdateSubmissions updateUIInterface;

    @Override
    protected List<Submissions> doInBackground(SubmissionsFragment... params) {
        updateUIInterface = params[0];
        try {
            Retrofit retrofit = new Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();

            UdacityService udacityService = retrofit.create(UdacityService.class);
            Call<List<Submissions>> submissionsCall = udacityService.getSubmissions();
            List<Submissions> submissions = submissionsCall.execute().body();
            if(submissions != null) {
                for (Submissions submission : submissions) {
                    Log.i(TAG, submission.toString());
                }
            }
            return submissions;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    protected void onPostExecute (List<Submissions> submissions) {
        updateUIInterface.submissionsUI(submissions);
    }
}
