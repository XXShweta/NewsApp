package com.newsapp.di.module;



import com.newsapp.ui.view.fragment.*;
import dagger.Module;
import dagger.android.ContributesAndroidInjector;


@Module
abstract class MainActivityFragmentModule {


    @ContributesAndroidInjector
    abstract NewsFragment provideNewAssignmentFragment();

    @ContributesAndroidInjector
    abstract NewsDetailFragment provideNewsDeatilFragment();

}
