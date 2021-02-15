package com.newsapp;

import com.newsapp.di.component.DaggerAppComponent;
import dagger.android.AndroidInjector;
import dagger.android.support.DaggerApplication;

public class MainApplication extends DaggerApplication {
    @Override
    protected AndroidInjector<? extends MainApplication> applicationInjector() {
        return DaggerAppComponent.builder().create(this);
    }
}