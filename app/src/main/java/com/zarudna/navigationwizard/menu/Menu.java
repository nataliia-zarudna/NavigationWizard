package com.zarudna.navigationwizard.menu;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by nsirobaba on 4/27/18.
 */

public class Menu {

    @SerializedName("menu")
    private List<MenuItem> menuItems;

    public Menu(List<MenuItem> menuItems) {
        this.menuItems = menuItems;
    }

    public List<MenuItem> getMenuItems() {
        return menuItems;
    }
}
