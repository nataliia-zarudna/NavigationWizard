package com.zarudna.navigationwizard.model.persistence.db;

import android.arch.lifecycle.LiveData;
import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;

import com.zarudna.navigationwizard.model.menu.MenuItem;

import java.util.List;

/**
 * Created by nsirobaba on 4/28/18.
 */
@Dao
public interface MenuItemDao {

    @Insert
    void insert(List<MenuItem> menuItems);

    @Query("SELECT * FROM menu_items")
    LiveData<List<MenuItem>> findAll();

    @Query("DELETE FROM menu_items")
    void deleteAll();
}
