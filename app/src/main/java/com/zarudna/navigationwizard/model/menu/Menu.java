package com.zarudna.navigationwizard.model.menu;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Menu POJO
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
