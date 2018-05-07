package com.zarudna.navigationwizard.model.menu;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;
import android.support.annotation.NonNull;
import android.support.annotation.StringDef;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.util.UUID;

/**
 * Menu Item POJO
 */
@Entity(tableName = "menu_items")
public class MenuItem {

    public static final String FUNCTION_TYPE_TEXT = "text";
    public static final String FUNCTION_TYPE_IMAGE = "image";
    public static final String FUNCTION_TYPE_URL = "url";

    @Retention(RetentionPolicy.SOURCE)
    @StringDef({FUNCTION_TYPE_TEXT, FUNCTION_TYPE_IMAGE, FUNCTION_TYPE_URL})
    @interface FunctionTypes {
    }

    @PrimaryKey()
    @NonNull
    @ColumnInfo(name = "item_id")
    private UUID itemID;

    private String name;

    @FunctionTypes
    private String function;

    private String param;

    public MenuItem() {
        this.itemID = UUID.randomUUID();
    }

    public MenuItem(String name, String function, String param) {
        this();
        this.name = name;
        this.function = function;
        this.param = param;
    }

    public UUID getItemID() {
        return itemID;
    }

    public void setItemID(UUID itemID) {
        this.itemID = itemID;
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
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        MenuItem item = (MenuItem) o;

        if (itemID != null ? !itemID.equals(item.itemID) : item.itemID != null) return false;
        if (name != null ? !name.equals(item.name) : item.name != null) return false;
        if (function != null ? !function.equals(item.function) : item.function != null)
            return false;
        return param != null ? param.equals(item.param) : item.param == null;
    }

    @Override
    public int hashCode() {
        int result = itemID != null ? itemID.hashCode() : 0;
        result = 31 * result + (name != null ? name.hashCode() : 0);
        result = 31 * result + (function != null ? function.hashCode() : 0);
        result = 31 * result + (param != null ? param.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "MenuItem{" +
                "itemID=" + itemID +
                ", name='" + name + '\'' +
                ", function='" + function + '\'' +
                ", param='" + param + '\'' +
                '}';
    }
}
