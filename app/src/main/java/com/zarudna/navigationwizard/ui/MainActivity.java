package com.zarudna.navigationwizard.ui;

import android.arch.lifecycle.ViewModelProviders;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.util.Log;

import com.zarudna.navigationwizard.R;
import com.zarudna.navigationwizard.WizardApplication;

public class MainActivity extends FragmentActivity {

    private static final String TAG = "MainActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        MainViewModel viewModel = ViewModelProviders.of(this).get(MainViewModel.class);
        ((WizardApplication) getApplication()).getAppComponent().inject(viewModel);

        viewModel.loadMenu().observe(this, menu -> {

            Log.d(TAG, "Menu items " + menu);

        });
    }
}
