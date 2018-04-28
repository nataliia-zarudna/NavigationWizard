package com.zarudna.navigationwizard.model.persistence;

import com.zarudna.navigationwizard.model.persistence.db.AppDatabase;
import com.zarudna.navigationwizard.model.persistence.db.MenuItemDao;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

/**
 * Dagger module for room objects
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
