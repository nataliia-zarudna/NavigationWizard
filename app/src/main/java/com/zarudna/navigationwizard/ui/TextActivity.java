package com.zarudna.navigationwizard.ui;

import android.content.Context;
import android.content.Intent;

/**
 * Created by nsirobaba on 4/27/18.
 */

public class TextActivity extends NavigationActivity {

    private static final String TEXT_EXTRA = "TextActivity.text";

    public static Intent newIntent(Context context, String text) {
        Intent intent = new Intent(context, TextActivity.class);
        intent.putExtra(TEXT_EXTRA, text);
        return intent;
    }
}
