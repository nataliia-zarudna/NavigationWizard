package com.zarudna.navigationwizard.ui;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.ViewModel;

import com.zarudna.navigationwizard.menu.Menu;
import com.zarudna.navigationwizard.menu.MenuRepository;

import javax.inject.Inject;

/**
 * Created by nsirobaba on 4/27/18.
 */

public class MainViewModel extends ViewModel {

    @Inject
    MenuRepository mMenuRepository;

    public LiveData<Menu> loadMenu() {
        return mMenuRepository.getMenuItems();
    }
}
