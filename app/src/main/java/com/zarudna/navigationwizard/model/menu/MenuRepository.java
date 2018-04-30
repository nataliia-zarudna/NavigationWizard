package com.zarudna.navigationwizard.model.menu;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.Observer;
import android.os.AsyncTask;
import android.support.annotation.Nullable;
import android.util.Log;

import com.zarudna.navigationwizard.model.cache.InMemoryCache;
import com.zarudna.navigationwizard.model.persistence.db.MenuItemDao;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Repository for menu objects
 */

public class MenuRepository {

    private static final String TAG = "MenuRepository";
    public static final String MENU_CACHE_KEY = "com.zarudna.navigationwizard.model.menu.cached_menu";

    private MenuAPI mMenuAPI;
    private InMemoryCache mCache;
    private MenuItemDao mMenuItemDao;

    @Inject
    public MenuRepository(MenuAPI menuAPI, InMemoryCache cache, MenuItemDao menuItemDao) {
        mMenuAPI = menuAPI;
        mCache = cache;
        mMenuItemDao = menuItemDao;
    }

    /**
     * Load menu items from api or return cached value if exists
     * @param errorListener error listener
     * @return menu items
     */
    public LiveData<List<MenuItem>> getMenuItems(@Nullable LoadMenuErrorListener errorListener) {
        Object cachedMenu = mCache.get(MENU_CACHE_KEY);
        if (cachedMenu != null && cachedMenu instanceof LiveData) {
            return (LiveData<List<MenuItem>>) cachedMenu;
        }

        final MutableLiveData<List<MenuItem>> menuLiveData = new MutableLiveData<>();
        mCache.put(MENU_CACHE_KEY, menuLiveData);

        mMenuAPI.getMenuItems().enqueue(new Callback<Menu>() {

            @Override
            public void onResponse(Call<Menu> call, Response<Menu> response) {
                Menu menu = response.body();
                menuLiveData.setValue(menu.getMenuItems());

                saveToDB(menu.getMenuItems());
            }

            @Override
            public void onFailure(Call<Menu> call, Throwable t) {
                Log.e(TAG, "Error on fetch menu items", t);

                loadItemsFromDB();

                if (errorListener != null) {
                    errorListener.onError("Error on fetch menu items", t);
                }
            }

            private void loadItemsFromDB() {
                LiveData<List<MenuItem>> savedMenuItems = mMenuItemDao.findAll();
                final List<Observer<List<MenuItem>>> observers = new ArrayList<>();
                observers.add(menuItems -> {
                        menuLiveData.setValue(menuItems);

                        savedMenuItems.removeObserver(observers.get(0));
                    });
                savedMenuItems.observeForever(observers.get(0));
            }
        });

        return menuLiveData;
    }

    private static class SaveAsyncTask extends AsyncTask<List<MenuItem>, Void, Void> {

        private MenuItemDao mMenuItemDao;

        SaveAsyncTask(MenuItemDao menuItemDao) {
            mMenuItemDao = menuItemDao;
        }

        @Override
        protected Void doInBackground(List<MenuItem>... menuItems) {
            mMenuItemDao.deleteAll();
            mMenuItemDao.insert(menuItems[0]);

            return null;
        }
    };

    private void saveToDB(List<MenuItem> menuItems) {
        new SaveAsyncTask(mMenuItemDao).execute(menuItems);
    }

    public interface LoadMenuErrorListener {
        void onError(String message, Throwable e);
    }
}
