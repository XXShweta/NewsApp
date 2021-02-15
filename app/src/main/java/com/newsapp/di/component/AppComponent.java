package com.newsapp.di.component;


import com.newsapp.MainApplication;
import com.newsapp.di.module.*;
import dagger.Component;
import dagger.android.AndroidInjector;
import dagger.android.support.AndroidSupportInjectionModule;
import javax.inject.Singleton;

@Singleton
@Component(modules = {
        AndroidSupportInjectionModule.class,
        AppModule.class,
        ActivityModule.class,
        ViewModelFactoryModule.class,
        ViewModelModule.class})

public interface AppComponent extends AndroidInjector<MainApplication>{

    @Component.Builder
    abstract class Builder extends  AndroidInjector.Builder<MainApplication> {
    }
}
