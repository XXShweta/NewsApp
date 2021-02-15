package com.newsapp.ui.interfaces;

public interface ApiResponseCallBack<S> {

    void onSuccess(S value);

    void onError(S error);
}
