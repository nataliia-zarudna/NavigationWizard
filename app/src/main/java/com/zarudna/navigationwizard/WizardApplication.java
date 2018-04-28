package com.zarudna.navigationwizard;

import android.app.Application;

import com.zarudna.navigationwizard.dependency.AppComponent;
import com.zarudna.navigationwizard.dependency.AppModule;
import com.zarudna.navigationwizard.dependency.DaggerAppComponent;
import com.zarudna.navigationwizard.model.persistence.RoomModule;
import com.zarudna.navigationwizard.model.persistence.db.AppDatabase;

/**
 * Application
 */

public class WizardApplication extends Application {

    private AppComponent mAppComponent;

    @Override
    public void onCreate() {
        super.onCreate();

        AppDatabase appDatabase = AppDatabase.newInstance(this);

        mAppComponent = DaggerAppComponent.builder()
                .appModule(new AppModule())
                .roomModule(new RoomModule(appDatabase))
                .build();
    }

    public AppComponent getAppComponent() {
        return mAppComponent;
    }

}
