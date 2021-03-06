package net.notfab.hubbasics.plugin.utils;

/*
 * Copyright (c) 2016.
 *
 * The contents of this project are licensed under a Creative Commons Attribution-NonCommercial-ShareAlike 4.0 International.
 * Please read the information linked below before you attempt to use this project or it's contents to make sure you are abiding
 * by it's terms.
 *
 * https://creativecommons.org/licenses/by-nc-sa/4.0/
 */

import org.bukkit.permissions.Permission;

public class HPermissions {
	public static final Permission MESSAGE_DEBUG = new Permission("hubbasics.messages.debug");
	public static final Permission MESSAGE_ADMIN = new Permission("hubbasics.messages.admin");
	public static final Permission HAT_COMMAND = new Permission("hubbasics.commands.hat");
	public static final Permission HOLOGRAMS_COMMAND = new Permission("hubbasics.commands.holograms");
	public static final Permission HUB_COMMAND = new Permission("hubbasics.commands.hub");
	public static final Permission SETHUB_COMMAND = new Permission("hubbasics.commands.sethub");
	public static final Permission RELOAD_COMMAND = new Permission("hubbasics.commands.reload");
	public static final Permission WARP_COMMAND = new Permission("hubbasics.commands.warp");
	public static final Permission SETWARP_COMMAND = new Permission("hubbasics.commands.setwarp");
    public static final Permission DELWARP_COMMAND = new Permission("hubbasics.commands.delwarp");
    public static final Permission WARPS_COMMAND = new Permission("hubbasics.commands.warps");
}
