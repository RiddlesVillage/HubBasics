package net.notfab.hubbasics.string.defaults.components;

/*
 * Copyright (c) 2016.
 *
 * The contents of this project are licensed under a Creative Commons Attribution-NonCommercial-ShareAlike 4.0 International.
 * Please read the information linked below before you attempt to use this project or it's contents to make sure you are abiding
 * by it's terms.
 *
 * https://creativecommons.org/licenses/by-nc-sa/4.0/
 */

import net.notfab.hubbasics.string.defaults.Property;

import java.util.Arrays;

public abstract class DynamicComponent implements Component {
    private Property[] properties;

    public DynamicComponent(Property[] properties) {
        this.properties = properties;
    }

    public Property[] getProperties() {
        return properties;
    }

    public Property getProperty(String property) {
        return Arrays.stream(this.getProperties()).filter(prop -> prop.getProperty().equalsIgnoreCase(property)).findFirst().orElse(null);
    }
}
