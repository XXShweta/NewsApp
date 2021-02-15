package com.newsapp.services.di;


import androidx.annotation.NonNull;
import com.newsapp.services.retrofitclient.NewsApi;
import com.newsapp.utils.Constants;
import dagger.Module;
import dagger.Provides;
import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import javax.inject.Singleton;

@Module(includes = NetworkModule.class)
public class ApiServiceModule {

    @Provides
    @Singleton
    NewsApi provideNewsApiService(OkHttpClient client){
        return  createRetrofit(Constants.BASE_URL, client, GsonConverterFactory.create())
                .create(NewsApi.class);
    }

    private Retrofit createRetrofit(@NonNull String baseUrl, @NonNull OkHttpClient okHttpClient,
                                    @NonNull GsonConverterFactory gsonConverterFactory){
        return new Retrofit.Builder()
                .baseUrl(baseUrl)
                .addConverterFactory(gsonConverterFactory)
                .client(okHttpClient)
                .build();
    }
}
