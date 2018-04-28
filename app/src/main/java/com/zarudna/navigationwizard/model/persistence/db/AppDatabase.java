package com.zarudna.navigationwizard.model.persistence.db;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.Room;
import android.arch.persistence.room.RoomDatabase;
import android.content.Context;

import com.zarudna.navigationwizard.model.menu.MenuItem;

/**
 * Application room database
 */

@Database(entities = {MenuItem.class}, version = 1)
public abstract class AppDatabase extends RoomDatabase {

    private static final String DATABASE_NAME = "navigation_wizard_db";

    public static AppDatabase newInstance(Context context) {
        return Room.databaseBuilder(context, AppDatabase.class, DATABASE_NAME).build();
    }

    public abstract MenuItemDao menuItemDao();

}
