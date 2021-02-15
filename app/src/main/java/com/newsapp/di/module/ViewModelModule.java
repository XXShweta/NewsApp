package com.newsapp.di.module;


import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;
import com.newsapp.di.ViewModelProviderFactory;
import com.newsapp.di.key.ViewModelKey;
import com.newsapp.ui.viewmodel.*;
import dagger.Binds;
import dagger.Module;
import dagger.multibindings.IntoMap;

@Module
public abstract class ViewModelModule {

    @Binds
    public abstract ViewModelProvider.Factory bindViewModelProviderFactory(ViewModelProviderFactory factory);

    @Binds
    @IntoMap
    @ViewModelKey(NewsViewModel.class)
    public abstract ViewModel newNewsViewModel(NewsViewModel newsViewModel);

    @Binds
    @IntoMap
    @ViewModelKey(NewsDetailViewModel.class)
    public abstract ViewModel newNewsDetailViewModel(NewsDetailViewModel newsDetailViewModel);


}
