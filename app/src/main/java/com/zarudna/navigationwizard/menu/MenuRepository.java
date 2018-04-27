package com.zarudna.navigationwizard.menu;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.util.Log;

import com.zarudna.navigationwizard.InMemoryCache;

import javax.inject.Inject;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by nsirobaba on 4/27/18.
 */

public class MenuRepository {

    private static final String TAG = "MenuRepository";
    private static final String MENU_CACHE_KEY = "com.zarudna.navigationwizard.menu.cached_menu";

    private MenuAPI mMenuAPI;
    private InMemoryCache mCache;

    @Inject
    public MenuRepository(MenuAPI menuAPI, InMemoryCache cache) {
        mMenuAPI = menuAPI;
        mCache = cache;
    }

    public LiveData<Menu> getMenuItems() {
        Object cachedMenu = mCache.get(MENU_CACHE_KEY);
        if (cachedMenu != null && cachedMenu instanceof LiveData) {
            return (LiveData<Menu>) cachedMenu;
        }

        final MutableLiveData<Menu> menuLiveData = new MutableLiveData<>();
        mCache.put(MENU_CACHE_KEY, menuLiveData);

        mMenuAPI.getMenuItems().enqueue(new Callback<Menu>() {

            @Override
            public void onResponse(Call<Menu> call, Response<Menu> response) {
                menuLiveData.setValue(response.body());
            }

            @Override
            public void onFailure(Call<Menu> call, Throwable t) {
                Log.e(TAG, "Error on fetch menu items", t);
                // TODO: add error listener to params
            }
        });

        return menuLiveData;
    }
}
