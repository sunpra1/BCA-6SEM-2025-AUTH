package com.sunpra.auth.utility;

import com.sunpra.auth.BuildConfig;
import com.sunpra.auth.data.AuthService;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ServiceProvider {

    private static OkHttpClient getOkHttpClient() {
        HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
        interceptor.setLevel(
                BuildConfig.DEBUG ? HttpLoggingInterceptor.Level.BODY : HttpLoggingInterceptor.Level.NONE
        );
        return new OkHttpClient.Builder()
                .addInterceptor(interceptor)
                .build();
    }

    public static AuthService getService() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(AuthService.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                // This this is for logging API calls. Other use case could be refreshing token and
                // adding auth headers during API calls.
                .client(getOkHttpClient())
                .build();

        return retrofit.create(AuthService.class);
    }
}
