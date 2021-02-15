package com.newsapp.storage.Database;


import androidx.room.Database;
import androidx.room.RoomDatabase;

@Database(entities = { ArticleEntity.class },  exportSchema = false,version = EBDatabase.VERSION)
public abstract class EBDatabase extends RoomDatabase {
    static final int VERSION = 1;
    public abstract ArticleDao getArticleDao();
}
