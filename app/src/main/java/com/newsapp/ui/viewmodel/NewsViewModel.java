package com.newsapp.ui.viewmodel;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

import androidx.lifecycle.MutableLiveData;

import com.newsapp.business.repository.NewsHeadLinesRepository;
import com.newsapp.business.usecase.NewsHeadLinesUseCase;
import com.newsapp.services.model.response.Article;
import com.newsapp.services.model.response.NewsApiResponse;
import com.newsapp.storage.Database.ArticleEntity;
import com.newsapp.storage.Database.DatabaseCallback;
import com.newsapp.ui.interfaces.ApiResponseCallBack;
import com.newsapp.ui.view.base.BaseViewModel;
import java.util.ArrayList;
import java.util.List;
import javax.inject.Inject;

public class NewsViewModel extends BaseViewModel implements ApiResponseCallBack , DatabaseCallback {

    public MutableLiveData<Boolean> mLoading = new MutableLiveData<>();
    private NewsHeadLinesUseCase newsUseCase;
    private NewsApiResponse newAssignmentArrayList ;
    public  MutableLiveData<NewsApiResponse> mutableLiveDataList= new MutableLiveData<>();
    private NewsHeadLinesRepository newsRepository;
    public MutableLiveData<Boolean> mCheck = new MutableLiveData<>();


    public Article getRepo(int id){
        return newAssignmentArrayList.getArticles().get(id);
    }


    @Inject
    public NewsViewModel(NewsHeadLinesUseCase newsUseCase,
                         NewsHeadLinesRepository newsRepository){
        this.newsUseCase = newsUseCase;
        this.newsRepository = newsRepository;
        mLoading.setValue(false);
        mCheck.setValue(false);
    }

    public void getNewAssesments(Context context) {
        if(isNetworkAvailable(context)) {
            mLoading.setValue(true);
            newsUseCase.getNewsList(this);

        } else {
            newsUseCase.getAllArticles(this);
        }
    }

    @Override
    public void onSuccess(Object value) {
        newAssignmentArrayList= (NewsApiResponse) value;
        mutableLiveDataList.setValue(newAssignmentArrayList);
        mLoading.setValue(false);
        if(mCheck.getValue()){
            newsUseCase.updateArticle(newAssignmentArrayList.getArticles(),this);
        }
        else {
            newsUseCase.insertArticle(newAssignmentArrayList.getArticles(),this);
        }
    }

    @Override
    public void onError(Object error) {
        mLoading.setValue(false);
    }


    public boolean isNetworkAvailable(Context context) {
        ConnectivityManager cm =
                (ConnectivityManager)context.getSystemService(Context.CONNECTIVITY_SERVICE);

        NetworkInfo activeNetwork = cm.getActiveNetworkInfo();
        boolean isConnected = activeNetwork != null &&
                activeNetwork.isConnectedOrConnecting();

        return isConnected;
    }

    @Override
    public void onArticleInserted() {
        mCheck.setValue(true);
    }

    @Override
    public void onArticleDelete() {

    }

    @Override
    public void onArticleGet(List<ArticleEntity> o) {
        NewsApiResponse newsApiResponse = new NewsApiResponse();
        ArrayList<Article> list = new ArrayList<>();

        for(int i=0;i<o.size();i++){
            ArticleEntity articleEntity = o.get(i);
            Article article = new Article();
            article.setAuthor(articleEntity.getAuthor());
            article.setDescription(articleEntity.getDescription());
            article.setTitle(articleEntity.getPublishedAt());
            article.setUrlToImage(articleEntity.getUrlToImage());
            article.setTitle(articleEntity.getTitle());
            list.add(article);
        }
        newsApiResponse.setArticles(list);

        newAssignmentArrayList = newsApiResponse;
        mutableLiveDataList.setValue(newAssignmentArrayList);
        mLoading.setValue(false);
    }

    @Override
    public void onDataNotAvailable() {
    }
}
