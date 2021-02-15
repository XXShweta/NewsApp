package com.newsapp.ui.viewmodel;

import android.content.Context;
import android.graphics.drawable.Drawable;
import com.bumptech.glide.Glide;
import com.bumptech.glide.request.target.CustomTarget;
import com.bumptech.glide.request.transition.Transition;
import com.newsapp.services.model.response.Article;
import com.newsapp.ui.view.base.BaseViewModel;
import javax.inject.Inject;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.lifecycle.MutableLiveData;

public class NewsDetailViewModel extends BaseViewModel {

    private final MutableLiveData<Article> selectedRepo = new MutableLiveData<>();
    public final MutableLiveData<Drawable> backGroundImage = new MutableLiveData<>();

    @Inject
    public NewsDetailViewModel( ) {
    }


    public MutableLiveData<Article> getSelectedRepo() {
        return selectedRepo;
    }

    public void setSelectedRepo(Article repo,Context context) {
        selectedRepo.setValue(repo);
        setBackGroundImage(context);
    }

    public void setBackGroundImage(Context context){
        Glide.with(context).load(selectedRepo.getValue().getUrlToImage()).into(new CustomTarget<Drawable>() {
            @Override
            public void onResourceReady(@NonNull Drawable resource, @Nullable Transition<? super Drawable> transition) {
                backGroundImage.setValue(resource);
            }
            @Override
            public void onLoadCleared(@Nullable Drawable placeholder) {
            }
        });
    }

}
