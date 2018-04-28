package com.zarudna.navigationwizard.model.persistence;

import com.zarudna.navigationwizard.model.persistence.db.AppDatabase;
import com.zarudna.navigationwizard.model.persistence.db.MenuItemDao;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

/**
 * Created by nsirobaba on 4/28/18.
 */

@Module
public class RoomModule {

    private AppDatabase mAppDatabase;

    public RoomModule(AppDatabase appDatabase) {
        mAppDatabase = appDatabase;
    }

    @Singleton
    @Provides
    public MenuItemDao provideMenuItemDao() {
        return mAppDatabase.menuItemDao();
    }
}
