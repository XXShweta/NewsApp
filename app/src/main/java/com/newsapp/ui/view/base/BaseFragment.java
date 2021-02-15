package com.newsapp.ui.view.base;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModel;

import com.google.android.material.snackbar.Snackbar;
import dagger.android.support.DaggerFragment;


public abstract class BaseFragment<T extends ViewDataBinding,V extends ViewModel> extends DaggerFragment {
    private T viewDataBinding;
    public  BaseActivity baseActivity;
    public  V viewModel;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        viewModel = getViewModel();

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        viewDataBinding = DataBindingUtil.inflate(inflater, getLayoutId(), container, false);
        return viewDataBinding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        viewDataBinding.setLifecycleOwner(this);
        viewDataBinding.executePendingBindings();
        viewDataBinding.setVariable(getLayoutModelReference(),viewModel);
    }

    public abstract int getLayoutId() ;
    public abstract  V getViewModel();
    public abstract  int getLayoutModelReference();


    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        baseActivity = (BaseActivity) context;
    }

    @Override
    public void onDetach() {
        super.onDetach();
    }

    public T getFragmentDataBinding(){
      return viewDataBinding;
    }

    public void goToNextActivityByFinish(Class currentClass){
        if(baseActivity!=null){
            Intent intent = new Intent(baseActivity,currentClass);
            getActivity().startActivity(intent);
            getActivity().finish();
        }
    }
    public void goToNextActivity(Class currentClass){
        if(baseActivity!=null){
            Intent intent = new Intent(baseActivity,currentClass);
            getActivity().startActivity(intent);
        }
    }

    public void showToast(View view,String message){
        Snackbar snackbar = Snackbar
                .make(view, message, Snackbar.LENGTH_LONG);
        snackbar.show();
    }

    public void performChildFragmentSwaping(int fragmentId, Fragment fragment) {
        getChildFragmentManager()
                .beginTransaction()
                .replace(fragmentId, fragment)
                .commitNow();
    }

}
