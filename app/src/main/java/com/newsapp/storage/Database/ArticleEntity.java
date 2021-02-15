package com.newsapp.storage.Database;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;
import io.reactivex.annotations.NonNull;

@Entity(tableName = "article_entry")
public class ArticleEntity {


    @PrimaryKey
    @NonNull
    @ColumnInfo(name = "ar_id")
    public int id;

    @ColumnInfo(name = "ar_author")
    public String author;

    @ColumnInfo(name = "ar_title")
    public String title;

    @ColumnInfo(name = "ar_des")
    public String description;

    @ColumnInfo(name = "ar_url")
    public String urlToImage;

    @ColumnInfo(name = "ar_date")
    public String publishedAt;

    public ArticleEntity(int id, String author, String title, String description, String urlToImage, String publishedAt) {
        this.id = id;
        this.author = author;
        this.title = title;
        this.description = description;
        this.urlToImage = urlToImage;
        this.publishedAt = publishedAt;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getUrlToImage() {
        return urlToImage;
    }

    public void setUrlToImage(String urlToImage) {
        this.urlToImage = urlToImage;
    }

    public String getPublishedAt() {
        return publishedAt;
    }

    public void setPublishedAt(String publishedAt) {
        this.publishedAt = publishedAt;
    }
}
