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

import net.md_5.bungee.api.ChatColor;

public class ColoredStringComponent extends FixedComponent {

    public ColoredStringComponent(String string) {
        super(ChatColor.translateAlternateColorCodes('&', string));
    }
}
