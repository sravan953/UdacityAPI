package com.biryanistudio.udacityapi.Tasks;

import android.os.AsyncTask;

import com.biryanistudio.udacityapi.Interfaces.IUpdateAvailableReviews;
import com.biryanistudio.udacityapi.Models.Certification;
import com.biryanistudio.udacityapi.Service.RetrofitInstance;
import com.biryanistudio.udacityapi.Service.UdacityService;
import com.biryanistudio.udacityapi.UI.Fragments.AvailableReviewsFragment;

import java.util.List;

import retrofit2.Call;
import retrofit2.Retrofit;

/**
 * Created by Sravan on 05-Apr-16.
 */


public class AvailableReviewsTask extends AsyncTask<AvailableReviewsFragment, Void, List<Certification>> {
    private final String TAG = getClass().getSimpleName();
    private IUpdateAvailableReviews updateUIInterface;

    @Override
    protected List<Certification> doInBackground(AvailableReviewsFragment... params) {
        updateUIInterface = params[0];
        try {
            Retrofit retrofit = RetrofitInstance.retrofit;
            UdacityService udacityService = retrofit.create(UdacityService.class);
            Call<List<Certification>> certificationsCall = udacityService.getCertificatons();
            return certificationsCall.execute().body();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    protected void onPostExecute(List<Certification> certifications) {
        updateUIInterface.availableReviews(certifications);
    }
}
