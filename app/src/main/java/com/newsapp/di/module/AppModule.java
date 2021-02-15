package com.newsapp.di.module;

import android.content.Context;
import com.newsapp.MainApplication;
import com.newsapp.services.di.ApiClientModule;
import dagger.Module;
import dagger.Provides;

@Module(includes = {ApiClientModule.class,DBModule.class})
public class AppModule {

    @Provides
    Context provideAppContext(MainApplication application) {
        return application;
    }

}
