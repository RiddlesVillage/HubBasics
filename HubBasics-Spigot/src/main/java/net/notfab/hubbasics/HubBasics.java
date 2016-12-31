package net.notfab.hubbasics;

/*
 * Copyright (c) 2016.
 *
 * The contents of this project are licensed under a Creative Commons Attribution-NonCommercial-ShareAlike 4.0 International.
 * Please read the information linked below before you attempt to use this project or it's contents to make sure you are abiding
 * by it's terms.
 *
 * https://creativecommons.org/licenses/by-nc-sa/4.0/
 */

import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.plugin.java.JavaPlugin;

import lombok.Getter;

import java.io.File;
import java.io.IOException;

public class HubBasics extends JavaPlugin {
    @Getter private static HubBasics instance;
    @Getter private static FileConfiguration configuration;

    public static void saveConfiguration() {
        HubBasics.getInstance().saveConfig();
    }

    public static void reloadConfiguration() {
        HubBasics.getInstance().reloadConfig();
    }

    public void onEnable() {
        instance = this;

        this.saveDefaultConfig();
        configuration = this.getConfig();
    }

    public void onDisable() {

        instance = null;
    }
}
