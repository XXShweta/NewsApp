package com.newsapp.ui.viewmodel;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import com.newsapp.business.repository.NewsHeadLinesRepository;
import com.newsapp.business.usecase.NewsHeadLinesUseCase;
import com.newsapp.services.model.response.Article;
import com.newsapp.services.model.response.NewsApiResponse;
import com.newsapp.storage.Database.ArticleEntity;
import com.newsapp.storage.Database.DatabaseCallback;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.TestRule;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import java.util.ArrayList;
import java.util.List;
import androidx.arch.core.executor.testing.InstantTaskExecutorRule;
import androidx.lifecycle.MutableLiveData;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

public class NewsViewModelTest {
    @Rule
    public TestRule rule = new InstantTaskExecutorRule();

    public MutableLiveData<Boolean> mLoading = new MutableLiveData<>();

    @Mock
    public NewsHeadLinesUseCase newsUseCase;

    @Mock
    Object error;

    @Mock
    public NewsApiResponse newAssignmentArrayList ;

    public  MutableLiveData<NewsApiResponse> mutableLiveDataList= new MutableLiveData<>();

    @Mock
    public NewsHeadLinesRepository newsRepository;

    private MutableLiveData<Boolean> mCheck = new MutableLiveData<>();

    @Mock
    public NewsViewModel viewModel;

    @Mock
    public Article article;

    @Mock
    public ArrayList<Article> list;
    Context context = mock(Context.class);

    @Mock
    public NetworkInfo activeNetwork;

    @Mock
    public ConnectivityManager cm;

    @Mock
    public DatabaseCallback databaseCallback;

    @Before
    public void setUp(){
        MockitoAnnotations.initMocks(this);
        viewModel = new NewsViewModel(newsUseCase,newsRepository);

    }

    @Test
    public void onSuccess(){
        ArrayList<Article> arry = new ArrayList<>();
        viewModel.onSuccess(newAssignmentArrayList);
        verify(newsUseCase).insertArticle(arry,viewModel);
    }

    @Test
    public void onSuccess_update(){
        viewModel.mCheck.setValue(true);
        ArrayList<Article> arry = new ArrayList<>();
        viewModel.onSuccess(newAssignmentArrayList);
        verify(newsUseCase).updateArticle(arry,viewModel);
    }

    @Test
    public void onArticleInserted_test(){
        viewModel.onArticleInserted();
        assertTrue(viewModel.mCheck.getValue());
    }

    @Test
    public void onArticleGet_test(){
        List<ArticleEntity> o = new ArrayList<>();
        viewModel.onArticleGet(o);
        assertFalse(viewModel.mLoading.getValue());
    }

    @Test
    public void onError_test(){
        viewModel.onError(error);
        assertFalse(viewModel.mLoading.getValue());
    }


}
