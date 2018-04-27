package com.zarudna.navigationwizard.dependency;

import com.zarudna.navigationwizard.menu.MenuAPI;
import com.zarudna.navigationwizard.menu.MenuRepository;

import dagger.Module;
import dagger.Provides;

/**
 * Created by nsirobaba on 4/27/18.
 */

@Module
public class AppModule {

    @Provides
    public static MenuAPI provideMenuAPI() {
        return new MenuAPI.Factory().create();
    }

    @Provides
    public static MenuRepository provideMenuRepository(MenuAPI menuAPI) {
        return new MenuRepository(menuAPI);
    }

}
