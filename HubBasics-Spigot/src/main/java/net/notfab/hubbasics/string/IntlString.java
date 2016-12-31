package net.notfab.hubbasics.string;

/*
 * Copyright (c) 2016.
 *
 * The contents of this project are licensed under a Creative Commons Attribution-NonCommercial-ShareAlike 4.0 International.
 * Please read the information linked below before you attempt to use this project or it's contents to make sure you are abiding
 * by it's terms.
 *
 * https://creativecommons.org/licenses/by-nc-sa/4.0/
 */

import org.bukkit.entity.Player;

public interface IntlString {
    String getVersion(Player player);
    String getVersion(DefinitionSet set);
}
