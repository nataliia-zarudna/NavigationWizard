package com.zarudna.navigationwizard.ui.textfunction;

import android.arch.lifecycle.ViewModel;
import android.databinding.Observable;
import android.databinding.PropertyChangeRegistry;

import com.zarudna.navigationwizard.BR;

/**
 * Created by nsirobaba on 4/27/18.
 */

public class TextFunctionViewModel extends ViewModel implements Observable {

    private PropertyChangeRegistry mRegistry = new PropertyChangeRegistry();

    private String mText;

    public String getText() {
        return mText;
    }

    public void setText(String text) {
        this.mText = text;
        mRegistry.notifyChange(this, BR.viewModel);
    }

    @Override
    public void addOnPropertyChangedCallback(OnPropertyChangedCallback onPropertyChangedCallback) {
        mRegistry.add(onPropertyChangedCallback);
    }

    @Override
    public void removeOnPropertyChangedCallback(OnPropertyChangedCallback onPropertyChangedCallback) {
        mRegistry.remove(onPropertyChangedCallback);
    }
}
