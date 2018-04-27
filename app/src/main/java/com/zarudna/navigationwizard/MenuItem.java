package com.zarudna.navigationwizard;

import android.support.annotation.StringDef;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

/**
 * Menu Item POJO
 */

public class MenuItem {

    public static final String FUNCTION_TYPE_TEXT = "text";
    public static final String FUNCTION_TYPE_IMAGE = "image";
    public static final String FUNCTION_TYPE_URL = "url";

    @Retention(RetentionPolicy.SOURCE)
    @StringDef({FUNCTION_TYPE_TEXT, FUNCTION_TYPE_IMAGE, FUNCTION_TYPE_URL})
    @interface FunctionTypes {
    }

    private String name;

    @FunctionTypes
    private String function;

    private String param;

    public MenuItem(String name, String function, String param) {
        this.name = name;
        this.function = function;
        this.param = param;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getFunction() {
        return function;
    }

    public void setFunction(String function) {
        this.function = function;
    }

    public String getParam() {
        return param;
    }

    public void setParam(String param) {
        this.param = param;
    }
}
