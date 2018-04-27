package com.zarudna.navigationwizard.ui;

import com.zarudna.navigationwizard.menu.MenuItem;

import java.util.List;

public class MainActivity extends NavigationActivity {

    private static final String TAG = "MainActivity";

    @Override
    protected void onLoadMenuItems(List<MenuItem> menuItems) {

        android.view.MenuItem menuItem = mNavView.getMenu().getItem(0);
        if (menuItem != null) {
            startActivity(menuItem.getIntent());
        }
    }
}
