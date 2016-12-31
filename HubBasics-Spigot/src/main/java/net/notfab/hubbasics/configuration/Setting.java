package net.notfab.hubbasics.configuration;

/*
 * Copyright (c) 2016.
 *
 * The contents of this project are licensed under a Creative Commons Attribution-NonCommercial-ShareAlike 4.0 International.
 * Please read the information linked below before you attempt to use this project or it's contents to make sure you are abiding
 * by it's terms.
 *
 * https://creativecommons.org/licenses/by-nc-sa/4.0/
 */

import lombok.Getter;
import net.notfab.hubbasics.HubBasics;

public class Setting<T> {
    @Getter private String path;
    @Getter private T defaultValue;

    public Setting(String path, T defaultValue) {
        this.path = path;
        this.defaultValue = defaultValue;
    }

    public T getValue() {
        return (T) HubBasics.getConfiguration().get(this.getPath(), this.getDefaultValue());
    }

    public void setValue(T value) {
        HubBasics.getConfiguration().set(this.getPath(), value);
    }
}
