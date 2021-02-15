package com.newsapp.ui.view.fragment;

import android.os.Bundle;
import android.view.View;
import com.newsapp.R;
import com.newsapp.databinding.FragmentNewsDetailBinding;
import com.newsapp.di.ViewModelProviderFactory;
import com.newsapp.services.model.response.Article;
import com.newsapp.ui.view.base.BaseFragment;
import com.newsapp.ui.viewmodel.NewsDetailViewModel;
import com.newsapp.BR;

import javax.inject.Inject;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.lifecycle.ViewModelProviders;

import static androidx.navigation.Navigation.findNavController;

public class NewsDetailFragment extends BaseFragment<FragmentNewsDetailBinding, NewsDetailViewModel> {

    private NewsDetailViewModel viewModel;

    @Inject
    ViewModelProviderFactory providerFactory;

    @Override
    public int getLayoutId() {
        return R.layout.fragment_news_detail;
    }

    @Override
    public NewsDetailViewModel getViewModel() {
        viewModel = ViewModelProviders.of(this,providerFactory).get(NewsDetailViewModel.class);
        viewModel.setBaseInterface(this);
        return viewModel;
    }

    @Override
    public int getLayoutModelReference() {
        return BR.newsDetailViewModel;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        Article responce = NewsDetailFragmentArgs.fromBundle(getArguments()).getRepo();
        getViewModel().setSelectedRepo(responce,getContext());
        getFragmentDataBinding().backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                findNavController(getView()).navigate(NewsDetailFragmentDirections.actionNewsDetailFragmentToNewsFragment());
            }
        });
    }
}
