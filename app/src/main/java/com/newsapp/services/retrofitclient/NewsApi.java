package com.newsapp.services.retrofitclient;


import com.newsapp.services.model.response.NewsApiResponse;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface NewsApi {

    @GET("{Id}")
    Call<NewsApiResponse> getNewsList(
            @Path("Id") String id,@Query("country")String country ,@Query("category")String category, @Query("apiKey") String key);
}
