package com.sunpra.auth.model;

import androidx.annotation.DrawableRes;
import androidx.annotation.StringRes;

public class Country {

    private int flag;
    private int name;

    public Country(@DrawableRes int flag, @StringRes int name) {
        this.flag = flag;
        this.name = name;
    }

    public int getFlag() {
        return flag;
    }

    public void setFlag(int flag) {
        this.flag = flag;
    }

    public int getName() {
        return name;
    }

    public void setName(int name) {
        this.name = name;
    }
}
