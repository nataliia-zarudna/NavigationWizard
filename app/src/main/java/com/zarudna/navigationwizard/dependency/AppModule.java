package com.zarudna.navigationwizard.dependency;

import com.zarudna.navigationwizard.InMemoryCache;
import com.zarudna.navigationwizard.model.menu.MenuAPI;
import com.zarudna.navigationwizard.model.menu.MenuRepository;
import com.zarudna.navigationwizard.model.persistence.db.MenuItemDao;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

/**
 * Created by nsirobaba on 4/27/18.
 */

@Module
public class AppModule {

    @Singleton
    @Provides
    public MenuAPI provideMenuAPI() {
        return new MenuAPI.Factory().create();
    }

    @Singleton
    @Provides
    public MenuRepository provideMenuRepository(MenuAPI menuAPI, InMemoryCache inMemoryCache, MenuItemDao menuItemDao) {
        return new MenuRepository(menuAPI, inMemoryCache, menuItemDao);
    }

    @Singleton
    @Provides
    public InMemoryCache provideInMemoryCache() {
        return new InMemoryCache();
    }

}
