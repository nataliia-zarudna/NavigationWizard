package com.zarudna.navigationwizard.dao;

import android.arch.lifecycle.LiveData;
import android.support.test.runner.AndroidJUnit4;
import android.test.MoreAsserts;

import com.zarudna.navigationwizard.TestUtils;
import com.zarudna.navigationwizard.model.menu.MenuItem;
import com.zarudna.navigationwizard.model.persistence.db.AppDatabase;
import com.zarudna.navigationwizard.model.persistence.db.MenuItemDao;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;

/**
 * Created by nsirobaba on 4/28/18.
 */
@RunWith(AndroidJUnit4.class)
public class TestMenuItemDao {

    private AppDatabase mAppDatabase;
    private MenuItemDao mSubjectDao;

    @Before
    public void createDB() {

        mAppDatabase = TestUtils.buildInMemoryDB();
        mSubjectDao = mAppDatabase.menuItemDao();
    }

    @Test
    public void create() {

        createAndInsertMenuItems(3);
    }

    @Test
    public void createAndRead() throws InterruptedException {

        List<MenuItem> menuItems = createAndInsertMenuItems(3);

        LiveData<List<MenuItem>> itemsLiveData = mSubjectDao.findAll();
        List<MenuItem> foundItems = TestUtils.getLiveDataValueSync(itemsLiveData);

        assertEquals(menuItems, foundItems);
    }

    @Test
    public void deleteAll() throws InterruptedException {

        createAndInsertMenuItems(3);

        mSubjectDao.deleteAll();

        LiveData<List<MenuItem>> itemsLiveData = mSubjectDao.findAll();
        List<MenuItem> foundItems = TestUtils.getLiveDataValueSync(itemsLiveData);

        MoreAsserts.assertEmpty(foundItems);
    }

    @After
    public void closeDB() {
        mAppDatabase.close();
    }

    private List<MenuItem> createAndInsertMenuItems(int count) {
        List<MenuItem> menuItems = new ArrayList<>();
        for (int i = 0; i < count; i++) {
            MenuItem item = new MenuItem("Item " + i, "some func", "some param");
            menuItems.add(item);
        }

        mSubjectDao.insert(menuItems);

        return menuItems;
    }
}
