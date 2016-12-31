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

import net.notfab.hubbasics.string.DefinitionSet;
import net.notfab.hubbasics.string.defaults.Property;
import net.notfab.hubbasics.string.defaults.error.MissingDefinitionError;
import org.bukkit.entity.Player;

public class PlayerNameComponent extends DynamicComponent {
    private static final String PLAYER_DEF_KEY = "player";

    public PlayerNameComponent(Property[] properties) {
        super(properties);
    }

    @Override
    public Object getValue(DefinitionSet definitions) {
        if (!definitions.isDefined(PLAYER_DEF_KEY)) {
            return new MissingDefinitionError(this.getClass(), PLAYER_DEF_KEY);
        }

        return definitions.<Player>getDefinition(PLAYER_DEF_KEY).getValue().getName();
    }
}
