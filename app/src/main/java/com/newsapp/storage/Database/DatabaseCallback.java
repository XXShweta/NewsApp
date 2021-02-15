package com.newsapp.storage.Database;

import java.util.List;

public interface DatabaseCallback {

    void onArticleInserted();

    void onArticleDelete();

    void onArticleGet(List<ArticleEntity> o);

    void onDataNotAvailable();

}
