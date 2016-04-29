package com.biryanistudio.udacityapi.Service;

import com.biryanistudio.udacityapi.UI.MainActivity;

import retrofit2.GsonConverterFactory;
import retrofit2.Retrofit;

/**
 * Created by Sravan on 27-Apr-16.
 */
public class RetrofitInstance {
    private static String API_TOKEN = MainActivity.API_TOKEN;
    public static final Retrofit retrofit = getRetrofitInstance();

    private static Retrofit getRetrofitInstance() {
        final String BASE_URL = "https://review-api.udacity.com/";
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .client(UdacityHttpClient.getClient(API_TOKEN))
                .build();
        return retrofit;
    }
}
