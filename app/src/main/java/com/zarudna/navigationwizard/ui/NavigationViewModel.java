package com.zarudna.navigationwizard.ui;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.ViewModel;

import com.zarudna.navigationwizard.R;
import com.zarudna.navigationwizard.model.menu.MenuItem;
import com.zarudna.navigationwizard.model.menu.MenuRepository;

import java.util.List;

import javax.inject.Inject;

/**
 * ViewModel for loading navigation menu
 */

public class NavigationViewModel extends ViewModel {

    @Inject
    MenuRepository mMenuRepository;

    private NavigationViewModelListener mListener;

    public void setListener(NavigationViewModelListener listener) {
        this.mListener = listener;
    }

    public LiveData<List<MenuItem>> loadMenu() {
        return mMenuRepository.getMenuItems((message, e) -> {
            if (mListener != null) {
                mListener.showErrorMessage(R.string.fetch_menu_items_error_message);
            }
        });
    }

    public interface NavigationViewModelListener {
        void showErrorMessage(int message);
    }
}
