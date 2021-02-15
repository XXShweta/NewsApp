package com.newsapp.ui.view.base;

import android.os.Bundle;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;
import dagger.android.support.DaggerAppCompatActivity;

public abstract class BaseActivity<T extends ViewDataBinding> extends DaggerAppCompatActivity {

    T viewDataBinding ;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        viewDataBinding = DataBindingUtil.setContentView(this,getLayoutId());
        viewDataBinding.setLifecycleOwner(this);
    }
    public abstract int getLayoutId();

    public T getActivityViewDataBinding(){
        return viewDataBinding;
    }
}
