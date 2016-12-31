package net.notfab.hubbasics;

import lombok.Getter;
import org.bukkit.plugin.java.JavaPlugin;

/**
 * Copyright (C) Fabricio20 2016
 */
public class HubBasics extends JavaPlugin {

    @Getter private static HubBasics Instance;

    /* ---------------------------- */

    @Override
    public void onEnable() {
        Instance = this;
    }

    @Override
    public void onDisable() {
        Instance = null;
    }

}
