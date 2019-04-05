package com.itsharv.nomobgriefing.commands;

import com.itsharv.nomobgriefing.utils.Messages;
import java.util.Iterator;
import java.util.Set;
import org.apache.commons.lang.WordUtils;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;

public class InfoCommand extends BaseCommand {
    public InfoCommand(Plugin plugin) {
        super(plugin);
        this.name = "info";
        this.usage = this.usage + "/nmg info";
    }

    public boolean execute(Player player, String[] args) {
        if (!super.execute(player, args)) {
            return false;
        } else {

            player.sendMessage(ChatColor.GRAY + "Plugin made with ❤ by: " + ChatColor.GOLD + "Harv");
            player.sendMessage(ChatColor.GRAY + "This plugin can be used to enable/disable griefing " + "done by certain mobs, without having to set the doMobGriefing gamerule to true.");

            return true;
        }
    }
}
