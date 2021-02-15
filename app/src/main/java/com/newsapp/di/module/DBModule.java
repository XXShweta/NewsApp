package com.newsapp.di.module;


import android.content.Context;
import androidx.room.Room;
import com.newsapp.storage.Database.ArticleDao;
import com.newsapp.storage.Database.DatabaseHelper;
import com.newsapp.storage.Database.EBDatabase;
import dagger.Module;
import dagger.Provides;
import javax.inject.Singleton;

@Module
public class DBModule {

    @Singleton
    @Provides
    public EBDatabase provideEBDatabase(Context context){
        return Room.databaseBuilder(context,EBDatabase.class, "my-db").fallbackToDestructiveMigration().allowMainThreadQueries().build();
    }

    @Singleton
    @Provides
    public ArticleDao provideArticleDao(EBDatabase myDatabase){
        return myDatabase.getArticleDao();
    }

    @Singleton
    @Provides
    DatabaseHelper provideDatabaseHelper(ArticleDao articleDao) {
        return new DatabaseHelper(articleDao);
    }

}
