package com.biryanistudio.udacityapi.Service;

import java.io.IOException;

import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

/**
 * Created by hugo on 4/10/16.
 * <p/>
 * This helper class, intercepts the creation of the
 * client to automatically add the headers to the request.
 */
public class UdacityHttpClient {
    public static OkHttpClient getClient(final String API_TOKEN) {
        return new OkHttpClient.Builder()
                .addInterceptor(new Interceptor() {
                    @Override
                    public Response intercept(Chain chain) throws IOException {
                        Request request = chain.request().newBuilder()
                                .addHeader("Authorization", API_TOKEN)
                                .addHeader("Content-Length", "0")
                                .build();
                        return chain.proceed(request);
                    }
                })
                .build();
    }
}
