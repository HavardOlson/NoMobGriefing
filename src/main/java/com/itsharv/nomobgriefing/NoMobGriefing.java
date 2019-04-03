package com.itsharv.nomobgriefing;

import org.bukkit.plugin.java.JavaPlugin;

public final class NoMobGriefing extends JavaPlugin {
    public NoMobGriefing() {
    }

    public void onEnable() {
        this.getLogger().info("NoMobGriefing is now loading!");
        this.saveDefaultConfig();
        this.getConfig().options().copyDefaults(true);




        this.getLogger().info("NoMobGriefing was loaded successfully!");
        this.getLogger().info("NoMobGriefing is made with <3 by Harv");
    }

    public void onDisable() {
        this.getLogger().info("***NoMobGriefing plugin has been disabled***");
    }
}
