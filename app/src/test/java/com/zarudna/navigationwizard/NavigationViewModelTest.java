package com.zarudna.navigationwizard;

import android.arch.lifecycle.LiveData;

import com.zarudna.navigationwizard.model.menu.MenuItem;
import com.zarudna.navigationwizard.model.menu.MenuRepository;
import com.zarudna.navigationwizard.ui.NavigationViewModel;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

/**
 * Created by nsirobaba on 4/30/18.
 */
@RunWith(MockitoJUnitRunner.class)
public class NavigationViewModelTest {

    private NavigationViewModel mSubject;

    @Mock
    private MenuRepository mMenuRepository;

    @Before
    public void setUp() {

        mSubject = new NavigationViewModel();
        mSubject.setMenuRepository(mMenuRepository);
    }

    @Test
    public void testLoadMenu() {

        LiveData<List<MenuItem>> expectedList = mock(LiveData.class);
        when(mMenuRepository.getMenuItems(null)).thenReturn(expectedList);

        LiveData<List<MenuItem>> actualList = mSubject.loadMenu();

        assertEquals(expectedList, expectedList);
    }
}
