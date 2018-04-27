package com.zarudna.navigationwizard.dependency;

import com.zarudna.navigationwizard.InMemoryCache;
import com.zarudna.navigationwizard.menu.MenuAPI;
import com.zarudna.navigationwizard.menu.MenuRepository;

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
    public static MenuAPI provideMenuAPI() {
        return new MenuAPI.Factory().create();
    }

    @Singleton
    @Provides
    public static MenuRepository provideMenuRepository(MenuAPI menuAPI, InMemoryCache inMemoryCache) {
        return new MenuRepository(menuAPI, inMemoryCache);
    }

    @Singleton
    @Provides
    public static InMemoryCache provideInMemoryCache() {
        return new InMemoryCache();
    }

}
