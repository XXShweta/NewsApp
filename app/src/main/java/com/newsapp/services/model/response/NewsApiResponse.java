package com.newsapp.services.model.response;

import java.io.Serializable;
import java.util.ArrayList;

public class NewsApiResponse implements Serializable {
    String status ;
    ArrayList<Article> articles;

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public ArrayList<Article> getArticles() {
        return articles;
    }

    public void setArticles(ArrayList<Article> articles) {
        this.articles = articles;
    }
}
