package com.itsharv.nomobgriefing.commands;

import com.itsharv.nomobgriefing.utils.Messages;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import org.apache.commons.lang.WordUtils;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;

public class MobCommand extends BaseCommand {
    public MobCommand(String mobName, String description, Plugin plugin) {
        super(plugin);
        this.name = mobName;
        this.usage = this.usage + "/nmg " + mobName + " [enable|disable]";
        this.description = this.description + description;
        this.permissionNode = this.permissionNode + this.name;
        this.minArgs = 2;
    }

    public boolean execute(Player player, String[] args) {
        if (!super.execute(player, args)) {
            return false;
        } else {
            String message = Messages.prefix + WordUtils.capitalize(this.name) + " griefing has been ";
            if (args[1].equalsIgnoreCase("enable")) {
                player.sendMessage(message + ChatColor.GREEN + "enabled");
                this.plugin.getConfig().set("mob." + this.name, true);
            } else if (args[1].equalsIgnoreCase("disable")) {
                player.sendMessage(message + ChatColor.RED + "disabled");
                this.plugin.getConfig().set("mob." + this.name, false);
            } else {
                this.wrongUsage(player);
            }

            this.plugin.saveConfig();
            return true;
        }
    }

    public List<String> onTabComplete(Player player, String[] args) {
        List<String> strings = new ArrayList();
        List<String> result = new ArrayList();
        strings.add("enable");
        strings.add("disable");
        Iterator var5 = strings.iterator();

        while(var5.hasNext()) {
            String s = (String)var5.next();
            if (s.startsWith(args[1].toLowerCase())) {
                result.add(s);
            }
        }

        return result;
    }
}
