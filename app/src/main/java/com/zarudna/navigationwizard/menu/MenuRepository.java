package com.zarudna.navigationwizard.menu;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.util.Log;

import javax.inject.Inject;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by nsirobaba on 4/27/18.
 */

public class MenuRepository {

    private static final String TAG = "MenuRepository";

    private MenuAPI mMenuAPI;

    @Inject
    public MenuRepository(MenuAPI menuAPI) {
        mMenuAPI = menuAPI;
    }

    public LiveData<Menu> getMenuItems() {

        final MutableLiveData<Menu> menuLiveData = new MutableLiveData<>();

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
