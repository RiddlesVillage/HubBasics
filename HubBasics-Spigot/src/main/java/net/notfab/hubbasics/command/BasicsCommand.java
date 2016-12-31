package net.notfab.hubbasics.command;

/*
 * Copyright (c) 2016.
 *
 * The contents of this project are licensed under a Creative Commons Attribution-NonCommercial-ShareAlike 4.0 International.
 * Please read the information linked below before you attempt to use this project or it's contents to make sure you are abiding
 * by it's terms.
 *
 * https://creativecommons.org/licenses/by-nc-sa/4.0/
 */

import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.permissions.Permission;

import java.util.Arrays;

public abstract class BasicsCommand extends Command {

    public BasicsCommand(String command, String description, Permission permission, String... aliases) {
        super(command, description, "/", Arrays.asList(aliases));
        super.setPermission(permission.getName());
    }

    @Override
    public boolean execute(CommandSender commandSender, String s, String[] strings) {
        this.execute(commandSender, strings);
        return false;
    }

    public abstract void execute(CommandSender sender, String[] args);

    public boolean testPermission(CommandSender sender) {
        if (sender.hasPermission(this.getPermission())) {
            return true;
        }
    }

    public boolean testPermissionSilent(CommandSender sender) {

    }
}
