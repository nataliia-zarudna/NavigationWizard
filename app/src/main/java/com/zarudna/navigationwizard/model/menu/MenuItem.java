package com.zarudna.navigationwizard.model.menu;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;
import android.support.annotation.StringDef;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

/**
 * Menu Item POJO
 */
@Entity(tableName = "menu_items"/*,
        foreignKeys = {@ForeignKey(entity = Menu.class,
                                   parentColumns = "menu_id",
                                   childColumns = "menu_id")}*/)
public class MenuItem {

    public static final String FUNCTION_TYPE_TEXT = "text";
    public static final String FUNCTION_TYPE_IMAGE = "image";
    public static final String FUNCTION_TYPE_URL = "url";

    @Retention(RetentionPolicy.SOURCE)
    @StringDef({FUNCTION_TYPE_TEXT, FUNCTION_TYPE_IMAGE, FUNCTION_TYPE_URL})
    @interface FunctionTypes {
    }

    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "item_id")
    private int itemID;

    @ColumnInfo(name = "menu_id")
    private int menuID;

    private String name;

    @FunctionTypes
    private String function;

    private String param;

    public MenuItem(String name, String function, String param) {
        this.name = name;
        this.function = function;
        this.param = param;
    }

    public int getItemID() {
        return itemID;
    }

    public void setItemID(int itemID) {
        this.itemID = itemID;
    }

    public int getMenuID() {
        return menuID;
    }

    public void setMenuID(int menuID) {
        this.menuID = menuID;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @FunctionTypes
    public String getFunction() {
        return function;
    }

    public void setFunction(@FunctionTypes String function) {
        this.function = function;
    }

    public String getParam() {
        return param;
    }

    public void setParam(String param) {
        this.param = param;
    }

    @Override
    public String toString() {
        return "MenuItem{" +
                "name='" + name + '\'' +
                ", function='" + function + '\'' +
                ", param='" + param + '\'' +
                '}';
    }
}
