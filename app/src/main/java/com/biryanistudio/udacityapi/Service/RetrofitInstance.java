package com.biryanistudio.udacityapi.Service;

import retrofit2.GsonConverterFactory;
import retrofit2.Retrofit;

/**
 * Created by Sravan on 27-Apr-16.
 */
public class RetrofitInstance {
    public static final Retrofit retrofit = getRetrofitInstance();
    private static final String BASE_URL = "https://review-api.udacity.com/";

    private static Retrofit getRetrofitInstance() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .client(UdacityHttpClient.getClient())
                .build();
        return retrofit;
    }
}
