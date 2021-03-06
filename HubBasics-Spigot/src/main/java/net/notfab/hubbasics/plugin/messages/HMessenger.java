package net.notfab.hubbasics.plugin.messages;

/*
 * Copyright (c) 2016.
 *
 * The contents of this project are licensed under a Creative Commons Attribution-NonCommercial-ShareAlike 4.0 International.
 * Please read the information linked below before you attempt to use this project or it's contents to make sure you are abiding
 * by it's terms.
 *
 * https://creativecommons.org/licenses/by-nc-sa/4.0/
 */

import java.util.Arrays;

import org.bukkit.Bukkit;
import org.bukkit.World;
import org.bukkit.command.CommandSender;
import org.bukkit.permissions.Permission;

import net.md_5.bungee.api.ChatColor;
import net.notfab.hubbasics.HubBasics;
import net.notfab.hubbasics.plugin.settings.ConfigurationKey;
import net.notfab.hubbasics.plugin.utils.HPermissions;

public class HMessenger {

    /**
     * Sends the given message
     *
     * @param commandSender The receiver of the message
     * @param message       The message
     */
    public static void sendMessage(CommandSender commandSender, String... message) {
        String msg = arrayToString(message);
        if (msg.equalsIgnoreCase("null")) return;
        msg = format(msg, commandSender);
        commandSender.sendMessage(ChatColor.GREEN + msg);
    }

    /**
     * Sends the given HubBasicsMessage
     *
     * @param commandSender The receiver of the message
     * @param message       The message
     */
    public static void sendMessage(CommandSender commandSender, HubBasicsMessage message) {
        sendMessage(commandSender, message.getMessage());
    }

    /**
     * Sends the given ConfigurationKey message
     * PS: Make sure this ConfigurationKey is a String, else it will silentely fail.
     *
     * @param commandSender    The receiver of the message
     * @param configurationKey The message
     */
    public static void sendMessage(CommandSender commandSender, ConfigurationKey configurationKey) {
        String message;
        try {
            message = HubBasics.getInstance().getPluginConfiguration().getString(configurationKey);
        } catch (IllegalArgumentException ex) {
            return;
        }
        sendMessage(commandSender, message);
    }

    /**
     * Sends the given message formatted as an error
     *
     * @param sender The receiver of the message
     * @param msg    The message
     */
    public static void sendErrorMessage(CommandSender sender, String... msg) {
        sender.sendMessage(ChatColor.RED + HubBasicsMessage.COMMAND_ERROR_OCCURRED.getMessage() + ": " + arrayToString(msg));
    }

    /**
     * Sends the given message to all players with the given permission
     *
     * @param permission The permission
     * @param msg        The message
     */
    public static void sendSelectiveBroadcast(Permission permission, String... msg) {
        Bukkit.getOnlinePlayers().parallelStream().filter(target -> target.hasPermission(permission)).forEach(target -> target.sendMessage(msg));
    }

    /**
     * Sends the given message to all players in the given world
     *
     * @param world The world
     * @param msg   The message
     */
    public static void sendSelectiveBroadcast(World world, String... msg) {
        Bukkit.getOnlinePlayers().parallelStream().filter(target -> target.getWorld().equals(world)).forEach(target -> target.sendMessage(msg));
    }

    /**
     * Sends the given message using the debug format to all players with the permission MESSAGE_DEBUG in @{@link HPermissions}
     *
     * @param message The message
     */
    public static void sendDebugMessage(String message) {
        if (!HubBasics.getInstance().getConfig().getBoolean(ConfigurationKey.ENABLE_DEBUG.getPath())) return;
        sendSelectiveBroadcast(HPermissions.MESSAGE_DEBUG, ChatColor.YELLOW + "" + ChatColor.ITALIC + "HubBasics debug >> ", ChatColor.GRAY + message);
        System.out.println("HubBasics Debug >> " + message);
    }

    /**
     * Sends the given message using the admin alert format to  all players with the permission MESSAGE_ADMIN in @{@link HPermissions}
     *
     * @param message - The message
     */
    public static void notifyAdmins(String... message) {
        System.out.println("[HubBasics] " + arrayToString(message));
        sendSelectiveBroadcast(HPermissions.MESSAGE_ADMIN, ChatColor.RED + "" + ChatColor.BOLD + "(HB Admin) " + ChatColor.GOLD + arrayToString(message));
    }

    /**
     * Display the correct usage of a command
     *
     * @param sender  The receiver
     * @param msg     The usage message
     */
    private static void sendCommandUsageMessageSingle(CommandSender sender, String msg) {
        String format = ChatColor.BLUE + "" + ChatColor.ITALIC;
        String prefix = format + HubBasicsMessage.COMMAND_USAGE_PREFIX + ": ";
        sender.sendMessage(prefix + ChatColor.DARK_AQUA + "" + ChatColor.ITALIC + msg);
    }

    /**
     * Display the correct usage of a command
     *
     * @param sender  The receiver
     * @param msgs    The usage messages, if command has multiple subcommands
     */
    public static void sendCommandUsageMessage(CommandSender sender, String... msgs) {
        if (msgs.length == 1) {
            sendCommandUsageMessageSingle(sender, msgs[0]);
            return;
        }

        sender.sendMessage(ChatColor.BLUE + "" + ChatColor.ITALIC + HubBasicsMessage.COMMAND_USAGE_PREFIX + ": ");
        for (String str : msgs) {
            sender.sendMessage(ChatColor.DARK_AQUA + "" + ChatColor.ITALIC + "  " + str);
        }
    }

    /**
     * Default message when a player does not have permission to perform an action
     *
     * @param sender The receiver
     */
    public static void errorNoPerms(CommandSender sender) {
        sender.sendMessage(ChatColor.RED + "You don't have permission to execute this command!");
    }

    /**
     * Default message when the console tries to perform a player-only command
     *
     * @param sender The receiver
     */
    public static void errorPlayersOnly(CommandSender sender) {
        sender.sendMessage(ChatColor.RED + "This command is limited to players only!");
    }

    /**
     * Default message when a player attempts to target a player, and the player is not found
     *
     * @param sender The receiver
     * @param player Player that wasn't found
     */
    public static void errorPlayerNotFound(CommandSender sender, String player) {
        sender.sendMessage(ChatColor.RED + "Player " + ChatColor.DARK_RED + player + ChatColor.RED + " was not found.");
    }

    public static void printStackTrace(Exception exc) {
        exc.printStackTrace();
        if (!HubBasics.getInstance().getConfig().getBoolean(ConfigurationKey.ENABLE_DEBUG.getPath())) return;
        sendDebugMessage("Printing stacktrace elements for caught " + exc.getClass().getName() + "...");
        sendDebugMessage("Message: " + exc.getMessage());
        int skipped = 0;
        int index = 0;
        for (StackTraceElement element : exc.getStackTrace()) {
            index++;
            String clazz = element.getClass().getName();
            if (!clazz.startsWith("net.notfab.hubbasics")) {
                skipped++;
                continue;
            } else {
                int lineNumber = element.getLineNumber();
                String method = element.getMethodName();
                if (skipped > 0) sendDebugMessage("Stacktrace skipped " + skipped + " non-HubBasics classes.");
                sendDebugMessage("Stacktrace " + index + ": " + clazz + " (" + lineNumber + ") -> " + method);
                skipped = 0;
            }
        }
    }

    /**
     * Formats the given message (can return null if message equals to "null")
     *
     * @param message       The message
     * @param commandSender (Optional) CommandSender
     * @return The formatted message
     */
    public static String format(String message, CommandSender commandSender) {
        if (message == null || message.equalsIgnoreCase("null")) return null;
        message = org.bukkit.ChatColor.translateAlternateColorCodes('&', message);
        if (commandSender != null) {
            message = message.replaceAll("<displayname>", commandSender.getName());
            message = message.replaceAll("<name>", commandSender.getName());
        }
        return message;
    }

    /**
     * Easy-to-notice update notification
     */
    public static void notifyUpdate() {
        Bukkit.getConsoleSender().sendMessage(ChatColor.YELLOW + "======================================");
        Bukkit.getConsoleSender().sendMessage("");
        Bukkit.getConsoleSender().sendMessage(ChatColor.YELLOW + "An update is available for HubBasics!");
        Bukkit.getConsoleSender().sendMessage("");
        Bukkit.getConsoleSender().sendMessage(ChatColor.YELLOW + "======================================");
    }

    private static String arrayToString(String... strings) {
        StringBuilder builder = new StringBuilder();
        Arrays.stream(strings).forEach(s -> builder.append(s + " "));
        return builder.toString().trim();
    }

	public static void unknownCommand(CommandSender sender) {
		sendMessage(sender, HubBasicsMessage.COMMAND_ERROR_NOTFOUND);
	}
}
