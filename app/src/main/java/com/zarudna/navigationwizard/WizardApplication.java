package com.zarudna.navigationwizard;

import android.app.Application;

import com.zarudna.navigationwizard.dependency.AppComponent;
import com.zarudna.navigationwizard.dependency.DaggerAppComponent;

/**
 * Created by nsirobaba on 4/27/18.
 */

public class WizardApplication extends Application {

    private AppComponent mAppComponent;

    @Override
    public void onCreate() {
        super.onCreate();

        mAppComponent = DaggerAppComponent.create();
    }

    public AppComponent getAppComponent() {
        return mAppComponent;
    }

}
