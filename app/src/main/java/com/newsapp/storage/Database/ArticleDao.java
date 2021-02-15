package com.newsapp.storage.Database;

import java.util.List;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;
import io.reactivex.Maybe;

@Dao
public interface ArticleDao {

    @Insert
    void insertArticle(List<ArticleEntity> articleEntityList);

    @Query("SELECT * FROM article_entry ")
    Maybe<List<ArticleEntity>> getAllArticle();

    @Delete
    void deleteArticles(List<ArticleEntity> articleEntityList);

    @Update
    void updateArticle(List<ArticleEntity> articleEntityList);

}
