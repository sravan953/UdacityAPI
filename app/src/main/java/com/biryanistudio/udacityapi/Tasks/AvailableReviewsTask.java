package com.biryanistudio.udacityapi.Tasks;

import android.os.AsyncTask;
import android.util.Log;

import com.biryanistudio.udacityapi.Interfaces.IUpdateAvailableReviews;
import com.biryanistudio.udacityapi.Models.Certification;
import com.biryanistudio.udacityapi.Service.RetrofitInstance;
import com.biryanistudio.udacityapi.Interfaces.UdacityService;
import com.biryanistudio.udacityapi.UI.Fragments.AvailableFragment;

import java.util.List;

import retrofit2.Call;
import retrofit2.Retrofit;

/**
 * Created by Sravan on 05-Apr-16.
 */


public class AvailableReviewsTask extends AsyncTask<AvailableFragment, Void, List<Certification>> {
    private final String TAG = getClass().getSimpleName();
    private IUpdateAvailableReviews updateUIInterface;

    @Override
    protected List<Certification> doInBackground(final AvailableFragment... params) {
        Log.d(TAG, "doInBackground");
        updateUIInterface = params[0];
        try {
            final Retrofit retrofit = RetrofitInstance.retrofit;
            final UdacityService udacityService = retrofit.create(UdacityService.class);
            final Call<List<Certification>> certificationsCall = udacityService.getCertificatons();
            return certificationsCall.execute().body();
        } catch (final Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    protected void onPostExecute(final List<Certification> certifications) {
        updateUIInterface.availableReviewsUI(certifications);
    }
}
