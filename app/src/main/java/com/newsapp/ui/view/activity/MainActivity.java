package com.newsapp.ui.view.activity;

import android.os.Bundle;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.navigation.NavController;
import androidx.navigation.NavDestination;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import com.newsapp.R;
import com.newsapp.databinding.ActivityMainBinding;
import com.newsapp.ui.component.EBDialogProgressBar;
import com.newsapp.ui.view.base.BaseActivity;

import static androidx.navigation.Navigation.findNavController;


public class MainActivity extends BaseActivity<ActivityMainBinding>
        implements AppBarConfiguration.OnNavigateUpListener {
    private NavController navController;
    private AppBarConfiguration appBarConfiguration;
    public EBDialogProgressBar ebDialogProgressBar;
    @Override
    public int getLayoutId() {
        return R.layout.activity_main;
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (savedInstanceState == null) {
            initializeView();
            ebDialogProgressBar = findViewById(R.id.ebProgressbar);
        }
       }

    private void initializeView() {
        navController = Navigation.findNavController(this,R.id.main_fragment );
        setupNavigation();
    }


    private void setupNavigation() {
        appBarConfiguration =
                new AppBarConfiguration.Builder(navController.getGraph())
                        .build();
        setSupportActionBar(getActivityViewDataBinding().toolbarLayout.toolbar);
        NavigationUI.setupActionBarWithNavController(this, navController,appBarConfiguration);
        NavigationUI.setupWithNavController( getActivityViewDataBinding().toolbarLayout.toolbar,navController,appBarConfiguration);
        navController.addOnDestinationChangedListener(new NavController.OnDestinationChangedListener() {
            @Override
            public void onDestinationChanged(@NonNull NavController controller, @NonNull NavDestination destination, @Nullable Bundle arguments) {
                getActivityViewDataBinding().toolbarLayout.toolbar.setVisibility(controller.getCurrentDestination().getLabel().toString().equalsIgnoreCase("News Detail")? View.GONE : View.VISIBLE);
            }
        });

    }



    @Override
    public boolean onSupportNavigateUp() {
        return NavigationUI.navigateUp(navController,appBarConfiguration)
                || super.onSupportNavigateUp();
    }
}
