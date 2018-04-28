package com.zarudna.navigationwizard.model.menu;

import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.GET;

/**
 * Created by nsirobaba on 4/27/18.
 */

public interface MenuAPI {


    String BASE_URL = "https://www.dropbox.com/s/fk3d5kg6cptkpr6/";

    @GET("menu.json?dl=1")
    Call<Menu> getMenuItems();

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
