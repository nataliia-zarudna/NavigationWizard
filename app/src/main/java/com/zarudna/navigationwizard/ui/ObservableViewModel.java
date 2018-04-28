package com.zarudna.navigationwizard.ui;

import android.arch.lifecycle.ViewModel;
import android.databinding.Observable;
import android.databinding.PropertyChangeRegistry;

/**
 * Base class for viewModels used in dataBinding
 */

public abstract class ObservableViewModel extends ViewModel implements Observable {

    protected PropertyChangeRegistry mRegistry = new PropertyChangeRegistry();

    @Override
    public void addOnPropertyChangedCallback(OnPropertyChangedCallback onPropertyChangedCallback) {
        mRegistry.add(onPropertyChangedCallback);
    }

    @Override
    public void removeOnPropertyChangedCallback(OnPropertyChangedCallback onPropertyChangedCallback) {
        mRegistry.remove(onPropertyChangedCallback);
    }
}