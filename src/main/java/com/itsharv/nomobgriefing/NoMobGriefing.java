package com.itsharv.nomobgriefing;

import com.itsharv.nomobgriefing.events.MobEvents;
import com.itsharv.nomobgriefing.commands.CmdExecutor;
import org.bukkit.plugin.java.JavaPlugin;

public final class NoMobGriefing extends JavaPlugin {
    public NoMobGriefing() {
    }

    public void onEnable() {
        this.getLogger().info("NoMobGriefing is now loading!");
        this.saveDefaultConfig();
        this.getConfig().options().copyDefaults(true);

        this.getServer().getPluginManager().registerEvents(new MobEvents(this), this);
        CmdExecutor executer = new CmdExecutor(this);
        this.getCommand("nmg").setExecutor(executer);
        this.getCommand("nmg").setTabCompleter(executer);



        this.getLogger().info("NoMobGriefing was loaded successfully!");
        this.getLogger().info("NoMobGriefing is made with ❤ by Harv");
    }

    public void onDisable() {
        this.getLogger().info("***NoMobGriefing plugin has been disabled***");
    }
}
