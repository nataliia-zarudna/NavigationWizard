package com.zarudna.navigationwizard.ui.urlfunction;

import android.content.Context;
import android.content.Intent;
import android.support.v4.app.Fragment;

import com.zarudna.navigationwizard.ui.NavigationActivity;

/**
 * Activity to display url from loaded menu
 */

public class UrlFunctionActivity extends NavigationActivity {

    private static final String URL_EXTRA = "UrlActivity.text";

    public static Intent newIntent(Context context, String text) {
        Intent intent = new Intent(context, UrlFunctionActivity.class);
        intent.putExtra(URL_EXTRA, text);
        return intent;
    }

    @Override
    protected Fragment getFragment() {
        String text = getIntent().getStringExtra(URL_EXTRA);
        return UrlFunctionFragment.newInstance(text);
    }
}
