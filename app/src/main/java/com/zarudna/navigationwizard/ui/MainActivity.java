package com.zarudna.navigationwizard.ui;

import android.support.v4.app.Fragment;

import com.zarudna.navigationwizard.model.menu.MenuItem;

import java.util.List;

public class MainActivity extends NavigationActivity {

    private static final String TAG = "MainActivity";

    @Override
    protected Fragment getFragment() {
        // TODO: add fragment with loader
        return null;
    }

    @Override
    protected void onLoadMenuItems(List<MenuItem> menuItems) {

        android.view.MenuItem menuItem = mNavView.getMenu().getItem(0);
        if (menuItem != null) {
            startActivity(menuItem.getIntent());
        }
    }
}
