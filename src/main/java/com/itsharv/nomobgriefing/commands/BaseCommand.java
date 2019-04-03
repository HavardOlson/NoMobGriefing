package com.itsharv.nomobgriefing.commands;

import com.itsharv.nomobgriefing.utils.Messages;
import java.util.List;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;

public class BaseCommand {
    protected Plugin plugin;
    protected String name;
    protected String usage;
    protected String description;
    protected String permissionNode;
    protected int minArgs;

    public BaseCommand(Plugin plugin) {
        this.plugin = plugin;
        this.name = "base";
        this.usage = "";
        this.description = ChatColor.WHITE + "";
        this.permissionNode = "nmg.";
        this.minArgs = 0;
    }

    public boolean execute(Player player, String[] args) {
        if (!player.hasPermission(this.permissionNode)) {
            player.sendMessage(Messages.prefix + ChatColor.RED + "You do not have permission to perform this command.");
            return false;
        } else if (args.length < this.minArgs) {
            this.wrongUsage(player);
            return false;
        } else {
            return true;
        }
    }

    public List<String> onTabComplete(Player player, String[] args) {
        return null;
    }

    public void wrongUsage(Player player) {
        player.sendMessage(Messages.prefix + ChatColor.RED + "Wrong use of command, " + ChatColor.GRAY + "usage: " + ChatColor.GOLD + this.usage);
    }

    public String getName() {
        return this.name;
    }

    public String getUsage() {
        return this.usage;
    }

    public String getDescription() {
        return this.description;
    }

    public String getPermissionNode() {
        return this.permissionNode;
    }

    public int getMinArgs() {
        return this.minArgs;
    }
}

