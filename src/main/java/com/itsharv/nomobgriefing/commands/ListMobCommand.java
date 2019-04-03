package com.itsharv.nomobgriefing.commands;

import com.itsharv.nomobgriefing.utils.Messages;
import java.util.Iterator;
import java.util.Set;
import org.apache.commons.lang.WordUtils;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;

public class ListMobCommand extends BaseCommand {
    public ListMobCommand(Plugin plugin) {
        super(plugin);
        this.name = "list";
        this.usage = this.usage + "/nmg list";
        this.description = this.description + "Shows which mob griefing has been enabled or disabled.";
        this.permissionNode = this.permissionNode + this.name;
    }

    public boolean execute(Player player, String[] args) {
        if (!super.execute(player, args)) {
            return false;
        } else {
            player.sendMessage(Messages.prefix + "Below is a list of mobs and whether they can grief or not:");
            Set<String> mobs = this.plugin.getConfig().getConfigurationSection("mob").getKeys(false);

            String message;
            for(Iterator var4 = mobs.iterator(); var4.hasNext(); player.sendMessage(message)) {
                String s = (String)var4.next();
                message = ChatColor.GOLD + WordUtils.capitalize(s) + ": ";
                if (this.plugin.getConfig().getBoolean("mob." + s)) {
                    message = message + ChatColor.GREEN + "enabled";
                } else {
                    message = message + ChatColor.RED + "disabled";
                }
            }

            return true;
        }
    }
}
