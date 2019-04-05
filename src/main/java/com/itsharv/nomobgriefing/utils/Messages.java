package com.itsharv.nomobgriefing.utils;

import org.bukkit.ChatColor;

public class Messages {
    public static final String prefix;
    public static final String longPrefixStart;
    public static final String longPrefixEnd;

    public Messages() {
    }

    static {
        prefix = ChatColor.YELLOW + "[" + ChatColor.WHITE + ChatColor.BOLD + "No Mob Griefing" + ChatColor.RESET + ChatColor.YELLOW + "] " + ChatColor.GRAY;
        longPrefixStart = null;
        longPrefixEnd = null;
    }
}
