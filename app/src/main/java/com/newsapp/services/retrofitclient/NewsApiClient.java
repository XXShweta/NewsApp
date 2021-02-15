package com.newsapp.services.retrofitclient;

import android.util.Log;
import com.newsapp.services.model.response.NewsApiResponse;
import com.newsapp.ui.interfaces.ApiResponseCallBack;
import com.newsapp.utils.Constants;

import javax.inject.Inject;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class NewsApiClient {
    private static final String TAG = NewsApiClient.class.getSimpleName();
    private static final String COUNTRY = "us";
    private static final String CATEGORY = "business";
    private static final String SUB_URL = "top-headlines";

    NewsApi api;

    @Inject
    public NewsApiClient(NewsApi api) {
        this.api = api;
    }

    public void getNewsList(final ApiResponseCallBack callBack){
        Call<NewsApiResponse> call = api.getNewsList(SUB_URL,COUNTRY,CATEGORY, Constants.API_KEY);
        call.enqueue(new Callback<NewsApiResponse>() {
            @Override
            public void onResponse(Call<NewsApiResponse> call, Response<NewsApiResponse> response) {
                callBack.onSuccess(response.body());
            }

            @Override
            public void onFailure(Call<NewsApiResponse> call, Throwable t) {
                Log.e(TAG, "onFailure: "+t.getMessage() );
                callBack.onError(t);
            }
        });

    }
}
