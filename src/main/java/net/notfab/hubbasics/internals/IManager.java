package net.notfab.hubbasics.internals;

import net.notfab.hubbasics.HubBasics;

/**
 * Copyright (C) Fabricio20 2016
 */
public abstract class IManager {

    protected HubBasics HubBasics = net.notfab.hubbasics.HubBasics.getInstance();

    public abstract void onDisable();

}
