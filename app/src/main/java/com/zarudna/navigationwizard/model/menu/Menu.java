package com.zarudna.navigationwizard.model.menu;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by nsirobaba on 4/27/18.
 */

//@Entity(tableName = "menu")
public class Menu {

    /*@PrimaryKey
    @ColumnInfo(name = "menu_id")*/
    private int menuId;

    @SerializedName("menu")
    private List<MenuItem> menuItems;

    /*public int getMenuId() {
        return menuId;
    }

    public void setMenuId(int menuId) {
        this.menuId = menuId;
    }*/

    public Menu(List<MenuItem> menuItems) {
        this.menuItems = menuItems;
    }

    public List<MenuItem> getMenuItems() {
        return menuItems;
    }
}
