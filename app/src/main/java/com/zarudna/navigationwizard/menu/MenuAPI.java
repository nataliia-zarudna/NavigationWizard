package com.zarudna.navigationwizard.menu;

import android.view.Menu;

import javax.inject.Singleton;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by nsirobaba on 4/27/18.
 */

@Singleton
public interface MenuAPI {


    String BASE_URL = "https://www.dropbox.com/s/fk3d5kg6cptkpr6/";

    // https://www.dropbox.com/s/fk3d5kg6cptkpr6/menu.json?dl=1

    class Factory {

        public MenuAPI create() {

            Retrofit retrofit = new Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();

            return retrofit.create(MenuAPI.class);
        }
    }

}
