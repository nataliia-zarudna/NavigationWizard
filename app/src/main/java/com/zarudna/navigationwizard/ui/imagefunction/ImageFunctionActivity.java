package com.zarudna.navigationwizard.ui.imagefunction;

import android.content.Context;
import android.content.Intent;
import android.support.v4.app.Fragment;

import com.zarudna.navigationwizard.ui.NavigationActivity;

/**
 * Activity to display image from loaded menu
 */

public class ImageFunctionActivity extends NavigationActivity {

    private static final String IMAGE_EXTRA = "ImageActivity.text";

    public static Intent newIntent(Context context, String text) {
        Intent intent = new Intent(context, ImageFunctionActivity.class);
        intent.putExtra(IMAGE_EXTRA, text);
        return intent;
    }

    @Override
    protected Fragment getFragment() {
        String text = getIntent().getStringExtra(IMAGE_EXTRA);
        return ImageFunctionFragment.newInstance(text);
    }
}
