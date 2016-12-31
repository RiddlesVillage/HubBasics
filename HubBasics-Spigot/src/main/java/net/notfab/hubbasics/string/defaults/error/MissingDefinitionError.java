package net.notfab.hubbasics.string.defaults.error;


/*
 * Copyright (c) 2016.
 *
 * The contents of this project are licensed under a Creative Commons Attribution-NonCommercial-ShareAlike 4.0 International.
 * Please read the information linked below before you attempt to use this project or it's contents to make sure you are abiding
 * by it's terms.
 *
 * https://creativecommons.org/licenses/by-nc-sa/4.0/
 */

import net.notfab.hubbasics.string.defaults.components.Component;

public class MissingDefinitionError {
    private String definition;
    private Class<? extends Component> clazz;

    public MissingDefinitionError(Class<? extends Component> clazz, String definition) {
        this.definition = definition;
        this.clazz = clazz;
    }

    public String toString() {
        return "{" + this.clazz.getSimpleName() + "[error=missing_definition,definition=" + this.definition + "]}";
    }
}
