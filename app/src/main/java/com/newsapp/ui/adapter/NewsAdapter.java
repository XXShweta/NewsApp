package com.newsapp.ui.adapter;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;
import com.bumptech.glide.Glide;
import com.bumptech.glide.request.target.CustomTarget;
import com.bumptech.glide.request.transition.Transition;
import com.newsapp.R;
import com.newsapp.databinding.ItemNewsBinding;
import com.newsapp.services.model.response.Article;
import com.newsapp.services.model.response.NewsApiResponse;
import java.util.ArrayList;

public class NewsAdapter extends RecyclerView.Adapter<NewsAdapter.NewsViewHolder> {

    private ArrayList<Article> newsResponseArticle = new ArrayList<>();
    public OnNewsItemClickListener newsItemClickListener;
    Context context;

    public NewsAdapter(OnNewsItemClickListener newsItemClickListener, Context context) {
        this.newsItemClickListener = newsItemClickListener;
        this.context = context;
    }

    @Override
    public NewsViewHolder onCreateViewHolder(ViewGroup parent,
                                             int viewType) {
        ItemNewsBinding binding = DataBindingUtil.inflate(
                LayoutInflater.from(parent.getContext()),
                R.layout.item_news, parent, false);

        return new NewsViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(final NewsViewHolder holder, int position) {
        Article newsResponse = this.newsResponseArticle.get(position);
        loadBackgroundImage(newsResponse,holder);
        holder.itemNewsBinding.setArticleModel(newsResponse);
        holder.itemNewsBinding.setPosition(position);
        holder.itemNewsBinding.setItemClickListener(this);
    }

    private void loadBackgroundImage(Article newsResponse,final NewsViewHolder holder) {
        Glide.with(context).load(newsResponse.getUrlToImage()).into(new CustomTarget<Drawable>() {
            @Override
            public void onResourceReady(@NonNull Drawable resource, @Nullable Transition<? super Drawable> transition) {
                holder.itemNewsBinding.newsItem.setBackground(resource);
            }

            @Override
            public void onLoadCleared(@Nullable Drawable placeholder) {

            }
        });
    }

    public void onItemClickListener(int testId){
        newsItemClickListener.onItemClickListener(testId);
    }
    @Override
    public int getItemCount() {
        if (newsResponseArticle.size() > 0) {
             return newsResponseArticle.size();
        }else{
            return 0;
        }

    }
    public void setNewsList(NewsApiResponse newsRespons) {
        this.newsResponseArticle = newsRespons.getArticles();
        notifyDataSetChanged();
    }


    public class NewsViewHolder extends RecyclerView.ViewHolder {
        public ItemNewsBinding itemNewsBinding;
        public NewsViewHolder(ItemNewsBinding itemNewsBinding) {
            super(itemNewsBinding.getRoot());
            this.itemNewsBinding = itemNewsBinding;
        }
    }
    public interface OnNewsItemClickListener {
        void onItemClickListener(@NonNull int testId);
    }


}
