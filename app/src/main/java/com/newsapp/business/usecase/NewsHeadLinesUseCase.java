package com.newsapp.business.usecase;

import com.newsapp.services.model.response.Article;
import com.newsapp.services.model.response.NewsApiResponse;
import com.newsapp.services.retrofitclient.NewsApiClient;
import com.newsapp.storage.Database.DatabaseCallback;
import com.newsapp.storage.Database.DatabaseHelper;
import com.newsapp.ui.interfaces.ApiResponseCallBack;
import java.util.ArrayList;
import javax.inject.Inject;
import androidx.annotation.NonNull;

public class NewsHeadLinesUseCase {

    private DatabaseHelper databaseHelper;

    @Inject
    NewsApiClient newsApiClient;

    @Inject
    public NewsHeadLinesUseCase(DatabaseHelper databaseHelper) {
        this.databaseHelper = databaseHelper;
    }

    public void getNewsList(@NonNull final ApiResponseCallBack<NewsApiResponse> callback){
        newsApiClient.getNewsList(callback);
    }

    public void insertArticle(ArrayList<Article> list, DatabaseCallback databaseCallback){
        databaseHelper.addArticles(list,databaseCallback);
    }

    public void getAllArticles(DatabaseCallback databaseCallback){
        databaseHelper.getAllArticle(databaseCallback);
    }

    public void updateArticle(ArrayList<Article> list, DatabaseCallback databaseCallback){
        databaseHelper.updateArticles(list,databaseCallback);
    }

}
