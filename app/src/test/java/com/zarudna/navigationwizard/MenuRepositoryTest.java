package com.zarudna.navigationwizard;

import android.arch.core.executor.testing.InstantTaskExecutorRule;
import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;

import com.zarudna.navigationwizard.model.cache.InMemoryCache;
import com.zarudna.navigationwizard.model.menu.Menu;
import com.zarudna.navigationwizard.model.menu.MenuAPI;
import com.zarudna.navigationwizard.model.menu.MenuItem;
import com.zarudna.navigationwizard.model.menu.MenuRepository;
import com.zarudna.navigationwizard.model.persistence.db.MenuItemDao;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.TestRule;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.when;

/**
 * Created by nsirobaba on 4/30/18.
 */
@RunWith(MockitoJUnitRunner.class)
public class MenuRepositoryTest {

    private MenuRepository mSubject;

    @Rule
    public TestRule rule = new InstantTaskExecutorRule();

    @Mock
    private MenuAPI mMenuAPI;
    @Mock
    private InMemoryCache mCache;
    @Mock
    private MenuItemDao mMenuItemDao;

    @Before
    public void setUp() {

        mSubject = new MenuRepository(mMenuAPI, mCache, mMenuItemDao);
    }

    @Test
    public void testGetMenuItems_fromAPI() {

        List<MenuItem> expectedMenuItems = new ArrayList<>();
        for (int i = 0; i < 3; i++) {
            MenuItem item = new MenuItem("Item " + i, "some func", "some param");
            expectedMenuItems.add(item);
        }
        Menu expectedMenu = new Menu(expectedMenuItems);

        final Call<Menu> mockedCall = Mockito.mock(Call.class);
        when(mMenuAPI.getMenuItems()).thenReturn(mockedCall);

        Mockito.doAnswer(invocation -> {

            Callback<Menu> callback = (Callback<Menu>) invocation.getArguments()[0];
            callback.onResponse(mockedCall, Response.success(expectedMenu));

            return null;
        }).when(mockedCall).enqueue(any(Callback.class));

        LiveData<List<MenuItem>> menuItems = mSubject.getMenuItems(null);

        List<MenuItem> actualMenuItems = menuItems.getValue();

        assertEquals(expectedMenuItems, actualMenuItems);
    }

    @Test
    public void testGetMenuItems_fromCache() {

        MutableLiveData<List<MenuItem>> expectedLiveData = new MutableLiveData<>();

        when(mCache.get(MenuRepository.MENU_CACHE_KEY)).thenReturn(expectedLiveData);

        LiveData<List<MenuItem>> actualMenuItems = mSubject.getMenuItems(null);

        assertEquals(expectedLiveData, actualMenuItems);
    }

    @Test
    public void testGetMenuItems_fromDB() {

        List<MenuItem> expectedMenuItems = new ArrayList<>();
        for (int i = 0; i < 3; i++) {
            MenuItem item = new MenuItem("Item " + i, "some func", "some param");
            expectedMenuItems.add(item);
        }
        MutableLiveData<List<MenuItem>> expectedLiveData = new MutableLiveData<>();
        expectedLiveData.postValue(expectedMenuItems);

        when(mMenuItemDao.findAll()).thenReturn(expectedLiveData);

        final Call<Menu> mockedCall = Mockito.mock(Call.class);
        when(mMenuAPI.getMenuItems()).thenReturn(mockedCall);

        Mockito.doAnswer(invocation -> {

            Callback<Menu> callback = (Callback<Menu>) invocation.getArguments()[0];
            callback.onFailure(mockedCall, null);

            return null;
        }).when(mockedCall).enqueue(any(Callback.class));

        LiveData<List<MenuItem>> menuItems = mSubject.getMenuItems(null);
        List<MenuItem> actualMenuItems = menuItems.getValue();

        assertEquals(expectedMenuItems, actualMenuItems);
    }

    @Test
    public void testGetMenuItems_withErrorListener() throws InterruptedException {

        List<MenuItem> expectedMenuItems = new ArrayList<>();
        for (int i = 0; i < 3; i++) {
            MenuItem item = new MenuItem("Item " + i, "some func", "some param");
            expectedMenuItems.add(item);
        }
        MutableLiveData<List<MenuItem>> expectedLiveData = new MutableLiveData<>();
        expectedLiveData.postValue(expectedMenuItems);

        when(mMenuItemDao.findAll()).thenReturn(expectedLiveData);

        final Call<Menu> mockedCall = Mockito.mock(Call.class);
        when(mMenuAPI.getMenuItems()).thenReturn(mockedCall);

        CountDownLatch countDownLatch = new CountDownLatch(1);
        List errorResults = new ArrayList();
        MenuRepository.LoadMenuErrorListener listener = (message, e) -> {
            errorResults.add(message);
            errorResults.add(e);
            countDownLatch.countDown();
        };

        Throwable throwable = new Throwable();
        Mockito.doAnswer(invocation -> {

            Callback<Menu> callback = (Callback<Menu>) invocation.getArguments()[0];
            callback.onFailure(mockedCall, throwable);

            return null;
        }).when(mockedCall).enqueue(any(Callback.class));

        mSubject.getMenuItems(listener);

        countDownLatch.await(3000, TimeUnit.MILLISECONDS);

        assertNotNull(errorResults.get(0));
        assertEquals(throwable, errorResults.get(1));
    }
}
