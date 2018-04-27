package com.zarudna.navigationwizard.ui.textfunction;

import android.content.Context;
import android.content.Intent;
import android.support.v4.app.Fragment;

import com.zarudna.navigationwizard.ui.NavigationActivity;

/**
 * Created by nsirobaba on 4/27/18.
 */

public class TextFunctionActivity extends NavigationActivity {

    private static final String TEXT_EXTRA = "TextActivity.text";

    public static Intent newIntent(Context context, String text) {
        Intent intent = new Intent(context, TextFunctionActivity.class);
        intent.putExtra(TEXT_EXTRA, text);
        return intent;
    }

    @Override
    protected Fragment getFragment() {
        String text = getIntent().getStringExtra(TEXT_EXTRA);
        return TextFunctionFragment.newInstance(text);
    }
}
