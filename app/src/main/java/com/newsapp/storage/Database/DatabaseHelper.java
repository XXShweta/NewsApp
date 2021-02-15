package com.newsapp.storage.Database;

import com.newsapp.services.model.response.Article;
import java.util.ArrayList;
import java.util.List;
import io.reactivex.Completable;
import io.reactivex.CompletableObserver;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Action;
import io.reactivex.observers.DisposableMaybeObserver;
import io.reactivex.schedulers.Schedulers;

public class DatabaseHelper {
    private ArticleDao articleDao;

    public DatabaseHelper(ArticleDao articleDao){
        this.articleDao=articleDao;
    }

    public void addArticles(final ArrayList<Article> list, final DatabaseCallback databaseCallback){

        final List<ArticleEntity> articleEntityList = new ArrayList<>();

        for(int i=0;i<list.size();i++){
            ArticleEntity articleEntity = new ArticleEntity(i,list.get(i).getAuthor(),list.get(i).getTitle(),list.get(i).getDescription(),
                    list.get(i).getUrlToImage(),list.get(i).getPublishedAt().substring(0,10));
            articleEntityList.add(articleEntity);
        }

        Completable.fromAction(new Action() {
            @Override
            public void run() throws Exception {
                articleDao.insertArticle(articleEntityList);

            }
        }).observeOn(AndroidSchedulers.mainThread()).subscribeOn(
                Schedulers.io()
        ).subscribe(new CompletableObserver() {
            @Override
            public void onSubscribe(Disposable d) {

            }

            @Override
            public void onComplete() {
                databaseCallback.onArticleInserted();
            }

            @Override
            public void onError(Throwable e) {
                databaseCallback.onDataNotAvailable();
            }
        });
    }

    public void getAllArticle(final DatabaseCallback databaseCallback){
        articleDao.getAllArticle().
                observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(new DisposableMaybeObserver<List<ArticleEntity>>() {
                    @Override
                    public void onSuccess(List<ArticleEntity> o) {
                        databaseCallback.onArticleGet(o);
                    }

                    @Override
                    public void onError(Throwable e) {
                        databaseCallback.onDataNotAvailable();
                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }


    public void updateArticles(final ArrayList<Article> list, final DatabaseCallback databaseCallback){

        final List<ArticleEntity> articleEntityList = new ArrayList<>();

        for(int i=0;i<list.size();i++){
            ArticleEntity articleEntity = new ArticleEntity(i,list.get(i).getAuthor(),list.get(i).getTitle(),list.get(i).getDescription(),
                    list.get(i).getUrlToImage(),list.get(i).getPublishedAt().substring(0,10));
            articleEntityList.add(articleEntity);
        }

        Completable.fromAction(new Action() {
            @Override
            public void run() throws Exception {
                articleDao.updateArticle(articleEntityList);
            }
        }).observeOn(AndroidSchedulers.mainThread()).subscribeOn(
                Schedulers.io()
        ).subscribe(new CompletableObserver() {
            @Override
            public void onSubscribe(Disposable d) {

            }

            @Override
            public void onComplete() {
                databaseCallback.onArticleInserted();
            }

            @Override
            public void onError(Throwable e) {
                databaseCallback.onDataNotAvailable();
            }
        });
    }

}
