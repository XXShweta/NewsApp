package com.newsapp.di.module;

import com.newsapp.ui.view.activity.MainActivity;
import dagger.Module;
import dagger.android.ContributesAndroidInjector;

@Module
public abstract class ActivityModule {

    @ContributesAndroidInjector(modules = {MainActivityFragmentModule.class})
    abstract MainActivity bindMainActivity();

}
