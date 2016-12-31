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

import java.util.HashMap;
import java.util.Map;

import org.reflections.Reflections;

public class ModuleManager {
    private Map<Class<? extends Module>, Module> modules;

    public ModuleManager() {
        this.modules = new HashMap<>();
    }

    public void loadModules() {
        Reflections reflections = new Reflections("net.notfab.hubbasics.modules");

        for (Class<? extends Module> moduleClass : reflections.getSubTypesOf(Module.class)) {
            try {
                this.modules.put(moduleClass, moduleClass.newInstance());
                // TODO: Debug message
            } catch (InstantiationException | IllegalAccessException e) {
                e.printStackTrace();
            }
        }

        // TODO: Debug message
    }

    public void enableModules() {
        for (Module module : this.modules.values()) {
            if (!module.isEnabled()) {
                continue;
            }

            // TODO: Debug message
            module.onEnable();
        }
    }

    public void disableModules() {
        for (Module module : this.modules.values()) {
            if (!module.isEnabled()) {
                continue;
            }

            // TODO: Debug message
            module.onDisable();
        }
    }

    public <T extends Module> T getModule(Class<T> moduleClass) {
        return moduleClass.cast(this.modules.get(moduleClass));
    }
}
