package com.newsapp.services.di;


import com.newsapp.services.retrofitclient.NewsApi;
import com.newsapp.services.retrofitclient.NewsApiClient;
import dagger.Module;
import dagger.Provides;
import javax.inject.Singleton;

@Module(includes = ApiServiceModule.class)
public class ApiClientModule {

    @Provides
    @Singleton
    NewsApiClient provideNewsApiClient(NewsApi api){
        return new NewsApiClient(api);
    }
}
