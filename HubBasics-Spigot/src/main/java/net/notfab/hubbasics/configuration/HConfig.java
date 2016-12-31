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

import java.io.File;
import java.io.IOException;

import org.bukkit.configuration.InvalidConfigurationException;
import org.bukkit.configuration.file.YamlConfiguration;

public class HConfig extends YamlConfiguration {

    private File file;

    public HConfig(File file) throws IOException, InvalidConfigurationException {
        this.load(file);
        this.file = file;
    }

    public void saveConfig() {
        try {
            this.save(this.file);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void reloadConfig() {
        try {
            for (String str : this.getKeys(true)) {
                this.set(str, null);
            }

            this.load(this.file);
        } catch (IOException | InvalidConfigurationException e) {
            e.printStackTrace();
        }
    }

    //Uncomment if you don't want sections
    /*
    @Override
    protected void convertMapsToSections(Map<?, ?> input, ConfigurationSection section) {
        // I dont want any stupid memory sections kthx
        for (Entry<?, ?> entry : input.entrySet()) {
            section.set(entry.getKey().toString(), entry.getValue());
        }
    }
    */
}
