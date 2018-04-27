package com.zarudna.navigationwizard.menu;

import javax.inject.Inject;

/**
 * Created by nsirobaba on 4/27/18.
 */

public class MenuRepository {

    private MenuAPI mMenuAPI;

    @Inject
    public MenuRepository(MenuAPI menuAPI) {
        mMenuAPI = menuAPI;
    }

}
