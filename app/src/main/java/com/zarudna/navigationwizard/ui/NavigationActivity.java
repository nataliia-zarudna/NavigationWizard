package com.zarudna.navigationwizard.ui;

import android.arch.lifecycle.ViewModelProviders;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Gravity;
import android.view.Menu;
import android.widget.Toast;

import com.zarudna.navigationwizard.R;
import com.zarudna.navigationwizard.WizardApplication;
import com.zarudna.navigationwizard.model.menu.MenuItem;
import com.zarudna.navigationwizard.ui.imagefunction.ImageFunctionActivity;
import com.zarudna.navigationwizard.ui.textfunction.TextFunctionActivity;
import com.zarudna.navigationwizard.ui.urlfunction.UrlFunctionActivity;

import java.util.List;

/**
 * Created by nsirobaba on 4/27/18.
 */

public abstract class NavigationActivity extends AppCompatActivity implements NavigationViewModel.NavigationViewModelListener {

    private static final String TAG = "NavigationActivity";

    private NavigationViewModel mViewModel;
    private DrawerLayout mDrawerLayout;
    protected NavigationView mNavView;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_navigation);

        mDrawerLayout = findViewById(R.id.drawer_layout);
        mNavView = findViewById(R.id.nav_view);

        mViewModel = ViewModelProviders.of(this).get(NavigationViewModel.class);
        ((WizardApplication) getApplication()).getAppComponent().inject(mViewModel);
        mViewModel.setListener(this);

        initToolbar();

        addFragment();

        mViewModel.loadMenu().observe(this, menuItems -> {
            Log.d(TAG, "Menu items " + menuItems);

            fillNavMenu(menuItems);

            onLoadMenuItems(menuItems);
        });
    }

    private void addFragment() {

        Fragment fragment = getSupportFragmentManager().findFragmentById(R.id.fragment_container);
        if (fragment == null) {
            fragment = getFragment();
            if (fragment != null) {
                getSupportFragmentManager()
                        .beginTransaction()
                        .add(R.id.fragment_container, fragment)
                        .commit();
            }
        }
    }

    private void initToolbar() {
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);
        actionBar.setHomeAsUpIndicator(R.drawable.ic_menu);
    }

    protected abstract Fragment getFragment();

    @Override
    public boolean onOptionsItemSelected(android.view.MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                mDrawerLayout.openDrawer(Gravity.START);
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    private void fillNavMenu(List<com.zarudna.navigationwizard.model.menu.MenuItem> menuItems) {
        Menu navMenu = mNavView.getMenu();
        for (MenuItem menuItem : menuItems) {

            Intent menuItemIntent = null;
            switch (menuItem.getFunction()) {
                case MenuItem.FUNCTION_TYPE_TEXT:
                    menuItemIntent = TextFunctionActivity.newIntent(NavigationActivity.this, menuItem.getParam());
                    break;
                case MenuItem.FUNCTION_TYPE_URL:
                    menuItemIntent = UrlFunctionActivity.newIntent(NavigationActivity.this, menuItem.getParam());
                    break;
                case MenuItem.FUNCTION_TYPE_IMAGE:
                    menuItemIntent = ImageFunctionActivity.newIntent(NavigationActivity.this, menuItem.getParam());
                    break;
            }

            if (menuItemIntent != null) {
                android.view.MenuItem viewMenuItem = navMenu.add(menuItem.getName());
                viewMenuItem.setIntent(menuItemIntent);
            } else {
                Log.e(TAG, "Unknown menu item " + menuItem);
            }
        }
    }

    protected void onLoadMenuItems(List<MenuItem> menuItems) {
    }

    @Override
    public void showErrorMessage(int message) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }
}
