package com.newsapp.ui.view.fragment;

import android.os.Bundle;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.newsapp.BR;
import com.newsapp.R;
import com.newsapp.databinding.FragmentNewsBinding;
import com.newsapp.di.ViewModelProviderFactory;
import com.newsapp.services.model.response.Article;
import com.newsapp.services.model.response.NewsApiResponse;
import com.newsapp.ui.adapter.NewsAdapter;
import com.newsapp.ui.component.EBDialogProgressBar;
import com.newsapp.ui.view.base.BaseFragment;
import com.newsapp.ui.viewmodel.NewsViewModel;
import javax.inject.Inject;
import static androidx.navigation.Navigation.findNavController;

public class NewsFragment extends BaseFragment<FragmentNewsBinding, NewsViewModel>
        implements NewsAdapter.OnNewsItemClickListener {

    private NewsViewModel newsViewModel;
    private LinearLayoutManager linearLayoutManager;
    private NewsAdapter newsAdapter;
    EBDialogProgressBar ebDialogProgressBar;
    @Inject
    ViewModelProviderFactory providerFactory;
    @Override

    public int getLayoutId() {
        return R.layout.fragment_news;
    }

    @Override
    public NewsViewModel getViewModel() {
        newsViewModel = ViewModelProviders.of(this,providerFactory).get(NewsViewModel.class);
        newsViewModel.setBaseInterface(this);
        return newsViewModel;    }

    @Override
    public int getLayoutModelReference() {
        return BR.assignmentViewModel;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState){
        if(getFragmentDataBinding()==null)
            return;
        initializeViews();
        setUpRecyclerView();
        getAssignmentListLiveData();
    }

    private void setUpRecyclerView() {
        newsAdapter = new NewsAdapter(this, getContext());
        getViewModel().getNewAssesments(getContext());

        linearLayoutManager = new LinearLayoutManager(baseActivity,  RecyclerView.VERTICAL, false);
        getFragmentDataBinding().newsNewRecyclerView.setLayoutManager(linearLayoutManager);
        getFragmentDataBinding().setNewsAdapter(newsAdapter);
    }

    private void getAssignmentListLiveData() {
        getViewModel().mutableLiveDataList.observe(this, new Observer<NewsApiResponse>() {
            @Override
            public void onChanged(NewsApiResponse newsListResponces) {
                ebDialogProgressBar.hideEBProgress();
                newsAdapter.setNewsList(newsListResponces);

            }
        });
    }


    private void initializeViews() {
        ebDialogProgressBar = baseActivity.getActivityViewDataBinding().getRoot().findViewById(R.id.ebProgressbar);
        ebDialogProgressBar.setVlOutsideTouchDismissProgress(false);
        getViewModel().mLoading.setValue(true);
        getViewModel().mLoading.observeForever(new Observer<Boolean>() {
            @Override
            public void onChanged(@Nullable Boolean aBoolean) {
                setUpProgressBar();
            }
        });
    }


    private void setUpProgressBar() {
        if (getFragmentDataBinding() == null) {
            return;
        }
        if (!getViewModel().mLoading.getValue()) {
            ebDialogProgressBar.hideEBProgress();
        }else{
            ebDialogProgressBar.showEBProgress();
        }

    }

    @Override
    public void onItemClickListener(@NonNull int testId) {
        Article repo = getViewModel().getRepo(testId);
        findNavController(getView()).navigate(NewsFragmentDirections.actionNewsFragmentToNewsDetailFragment(repo));
    }



}

