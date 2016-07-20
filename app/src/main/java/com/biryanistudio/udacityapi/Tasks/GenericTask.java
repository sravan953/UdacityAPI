package com.biryanistudio.udacityapi.Tasks;

import android.os.AsyncTask;
import android.util.Log;

import com.biryanistudio.udacityapi.FragmentType;
import com.biryanistudio.udacityapi.Interfaces.IGenericUpdateUI;
import com.biryanistudio.udacityapi.Models.Certification;
import com.biryanistudio.udacityapi.Models.Feedback;
import com.biryanistudio.udacityapi.Models.Submission;
import com.biryanistudio.udacityapi.Service.RetrofitInstance;
import com.biryanistudio.udacityapi.Service.UdacityService;
import com.biryanistudio.udacityapi.UI.Fragments.GenericFragment;

import java.util.List;

import retrofit2.Call;
import retrofit2.Retrofit;

/**
 * Created by Sravan on 20-Jul-16.
 */
public class GenericTask extends AsyncTask<GenericFragment, Void, List> {
    private final String TAG = getClass().getSimpleName();
    private IGenericUpdateUI mUpdateUIInterface;

    @Override
    protected List doInBackground(GenericFragment... genericFragments) {
        Log.d(TAG, "doInBackground");
        mUpdateUIInterface = genericFragments[0];
        FragmentType mFragmentType = genericFragments[0].getFragmentType();
        try {
            Retrofit retrofit = RetrofitInstance.retrofit;
            UdacityService udacityService = retrofit.create(UdacityService.class);
            switch(mFragmentType) {
                case AVAILABLE:
                    Call<List<Certification>> certificationsCall = udacityService.getCertificatons();
                    return certificationsCall.execute().body();
                case ASSIGNED:
                    Call<List<Submission>> submissionsCall = udacityService.getSubmissions();
                    return submissionsCall.execute().body();
                case FEEDBACK:
                    Call<List<Feedback>> feedbackCall = udacityService.getFeedback();
                    return feedbackCall.execute().body();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    protected void onPostExecute(List data) {
        mUpdateUIInterface.newData(data);
    }
}
