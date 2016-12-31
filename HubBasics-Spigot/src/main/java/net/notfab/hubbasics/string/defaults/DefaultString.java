package net.notfab.hubbasics.string.defaults;

/*
 * Copyright (c) 2016.
 *
 * The contents of this project are licensed under a Creative Commons Attribution-NonCommercial-ShareAlike 4.0 International.
 * Please read the information linked below before you attempt to use this project or it's contents to make sure you are abiding
 * by it's terms.
 *
 * https://creativecommons.org/licenses/by-nc-sa/4.0/
 */

import net.notfab.hubbasics.string.Definition;
import net.notfab.hubbasics.string.DefinitionSet;
import net.notfab.hubbasics.string.IntlString;
import net.notfab.hubbasics.string.defaults.components.Component;
import org.bukkit.entity.Player;

import java.util.Arrays;

public class DefaultString implements IntlString {
    private Component[] components;

    public DefaultString(Component[] components) {
        this.components = components;
    }

    public <T extends Component> T getComponent(Class<T> clazz) {
        return clazz.cast(Arrays.stream(this.components).filter(comp -> comp.getClass().equals(clazz)).findFirst().orElse(null));
    }

    @Override
    public String getVersion(Player player) {
        return this.getVersion(new DefinitionSet().addDefinition(new Definition<>("player", player)));
    }

    @Override
    public String getVersion(DefinitionSet set) {
        StringBuilder builder = new StringBuilder();
        for (Component component : this.components) {
            builder.append(component.getValue(set));
        }

        return builder.toString();
    }
}
