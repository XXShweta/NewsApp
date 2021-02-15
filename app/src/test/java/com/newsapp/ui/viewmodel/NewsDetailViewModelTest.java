package com.newsapp.ui.viewmodel;

import android.content.Context;
import android.graphics.drawable.Drawable;

import com.newsapp.services.model.response.Article;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.TestRule;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import androidx.arch.core.executor.testing.InstantTaskExecutorRule;
import androidx.lifecycle.MutableLiveData;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class NewsDetailViewModelTest {

    @Rule
    public TestRule rule = new InstantTaskExecutorRule();

    public final MutableLiveData<Article> selectedRepo = new MutableLiveData<>();
    public final MutableLiveData<Drawable> backGroundImage = new MutableLiveData<>();

    @Mock
    public NewsDetailViewModel viewModel;
    Context context = mock(Context.class);

    @Mock
    public Article article;


    @Before
    public void setUp(){
        MockitoAnnotations.initMocks(this);
        doNothing().when(viewModel).setBackGroundImage(context);
        when(viewModel.getSelectedRepo()).thenReturn(selectedRepo);
    }

    @Test
    public void getSelectedRepo_test(){
        assertThat(viewModel.getSelectedRepo()).isNotNull();
    }

    @Test
    public void setSelectedRepo_test(){
        viewModel.setSelectedRepo(article,context);
    }


}
