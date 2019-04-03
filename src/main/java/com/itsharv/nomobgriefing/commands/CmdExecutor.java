package com.itsharv.nomobgriefing.commands;

import io.github.meonstudios.nomobgriefing.MessageHelper;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;
import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;

public class CmdExecutor implements CommandExecutor, TabCompleter {
    private final Plugin plugin;
    LinkedHashMap<String, BaseCommand> commands;

    public CmdExecutor(Plugin plugin) {
        this.plugin = plugin;
        this.commands = new LinkedHashMap();
        this.commands.put("list", new ListMobCommand(plugin));
        this.commands.put("creeper", new MobCommand("creeper", "Enables/disables creepers exploding blocks.", plugin));
        this.commands.put("zombie", new MobCommand("zombie", "Enables/disables zombies from breaking doors.", plugin));
        this.commands.put("enderman", new MobCommand("enderman", "Enables/disables endermen picking up blocks.", plugin));
        this.commands.put("snowgolem", new MobCommand("snowgolem", "Enables/disables snowgolems creating snowblocks.", plugin));
        this.commands.put("ghast", new MobCommand("ghast", "Enables/disables ghasts exploding blocks.", plugin));
        this.commands.put("wither", new MobCommand("wither", "Enables/disables withers destroying blocks.", plugin));
        this.commands.put("enderdragon", new MobCommand("enderdragon", "Enables/disables the enderdragon destroying blocks.", plugin));
        this.commands.put("tnt", new MobCommand("tnt", "Enables/disables tnt from destroying blocks", plugin));
    }

    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        if (!(sender instanceof Player)) {
            return false;
        } else {
            Player player = (Player)sender;
            if (cmd.getName().equalsIgnoreCase("nmg")) {
                if (args.length == 0) {
                    this.SendInfoToPlayer(player);
                    return true;
                }

                if (this.commands.containsKey(args[0].toLowerCase())) {
                    ((BaseCommand)this.commands.get(args[0])).execute(player, args);
                } else {
                    player.sendMessage(MessageHelper.prefix + ChatColor.RED + "This is not a supported command.");
                }
            }

            return true;
        }
    }

    public List<String> onTabComplete(CommandSender sender, Command cmd, String label, String[] args) {
        if (!(sender instanceof Player)) {
            return null;
        } else {
            Player player = (Player)sender;
            if (cmd.getName().equalsIgnoreCase("nmg")) {
                if (args.length == 1) {
                    List<String> result = new ArrayList();
                    Iterator var7 = (new ArrayList(this.commands.keySet())).iterator();

                    while(var7.hasNext()) {
                        String s = (String)var7.next();
                        if (s.startsWith(args[0].toLowerCase()) && player.hasPermission(((BaseCommand)this.commands.get(s)).getPermissionNode())) {
                            result.add(s);
                        }
                    }

                    return result;
                }

                if (this.commands.containsKey(args[0].toLowerCase())) {
                    return ((BaseCommand)this.commands.get(args[0])).onTabComplete(player, args);
                }
            }

            return null;
        }
    }

    private void SendInfoToPlayer(Player player) {
        player.sendMessage(MessageHelper.longPrefixStart);
        player.sendMessage(ChatColor.GRAY + "Plugin made by: " + ChatColor.GOLD + "PinkNeonDinosaur");
        player.sendMessage(ChatColor.GRAY + "This plugin can be used to enable/disable griefing " + "done by certain mobs, without having to set the doMobGriefing gamerule to true.");
        player.sendMessage(ChatColor.GRAY + "Below is a list of all commands you can use:");
        player.sendMessage(ChatColor.GOLD + "/nmg: " + ChatColor.WHITE + "Shows help and info about this plugin.");
        Iterator var2 = this.commands.values().iterator();

        while(var2.hasNext()) {
            BaseCommand cmd = (BaseCommand)var2.next();
            if (player.hasPermission(cmd.getPermissionNode())) {
                player.sendMessage(ChatColor.GOLD + cmd.getUsage() + ": " + cmd.getDescription());
            }
        }

        player.sendMessage(MessageHelper.longPrefixEnd);
    }
}
