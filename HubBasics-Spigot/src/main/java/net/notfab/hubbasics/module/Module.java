package net.notfab.hubbasics.module;

/*
 * Copyright (c) 2016.
 *
 * The contents of this project are licensed under a Creative Commons Attribution-NonCommercial-ShareAlike 4.0 International.
 * Please read the information linked below before you attempt to use this project or it's contents to make sure you are abiding
 * by it's terms.
 *
 * https://creativecommons.org/licenses/by-nc-sa/4.0/
 */

import net.notfab.hubbasics.command.BasicsCommand;

import org.bukkit.event.Listener;
import org.bukkit.plugin.Plugin;

import net.notfab.hubbasics.HubBasics;

import lombok.Getter;

import org.bukkit.plugin.java.JavaPlugin;

public abstract class Module {
    @Getter private String identifier;
    @Getter private String description;
    @Getter private JavaPlugin plugin;
    @Getter private Requirement[] requirements;

    public Module(String identifier, String description, Requirement... requirements) {
        this.identifier = identifier;
        this.description = description;
        this.requirements = requirements;
        this.plugin = HubBasics.getInstance();
    }

    protected void registerCommand(BasicsCommand command) {
        //TODO: Register command
    }

    protected void registerListener(Listener listener) {
        this.getPlugin().getServer().getPluginManager().registerEvents(listener, this.getPlugin());
    }

    public boolean isEnabled() {
        return HubBasics.getConfiguration().getBoolean(this.getIdentifier() + ".enabled");
    }

    abstract void onEnable();

    abstract void onDisable();
}
