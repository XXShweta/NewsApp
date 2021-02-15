package com.newsapp.ui.view.base;

import androidx.lifecycle.ViewModel;

import java.lang.ref.WeakReference;

public class BaseViewModel<T> extends ViewModel {
    WeakReference<T> mBaseViewNavigator;

    public void setBaseInterface(T baseInterface) {
        this.mBaseViewNavigator = new WeakReference<>(baseInterface);
    }

    public T getBaseInterface() {
        return mBaseViewNavigator.get();
    }

}
