package com.zarudna.navigationwizard.ui.textfunction;

import com.zarudna.navigationwizard.BR;
import com.zarudna.navigationwizard.ui.ObservableViewModel;

/**
 * ViewModel for text display
 */

public class TextFunctionViewModel extends ObservableViewModel {

    private String mText;

    public String getText() {
        return mText;
    }

    public void setText(String text) {
        this.mText = text;
        mRegistry.notifyChange(this, BR.viewModel);
    }
}
