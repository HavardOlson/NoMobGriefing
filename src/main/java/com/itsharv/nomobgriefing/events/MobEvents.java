package com.itsharv.nomobgriefing.events;

import org.bukkit.entity.Creeper;
import org.bukkit.entity.EnderDragon;
import org.bukkit.entity.Enderman;
import org.bukkit.entity.Fireball;
import org.bukkit.entity.Snowman;
import org.bukkit.entity.TNTPrimed;
import org.bukkit.entity.Wither;
import org.bukkit.entity.WitherSkull;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.EntityBlockFormEvent;
import org.bukkit.event.entity.EntityBreakDoorEvent;
import org.bukkit.event.entity.EntityChangeBlockEvent;
import org.bukkit.event.entity.EntityExplodeEvent;
import org.bukkit.plugin.Plugin;

public final class MobEvents implements Listener {
    private final Plugin plugin;

    public MobEvents(Plugin plugin) {
        this.plugin = plugin;
    }

    @EventHandler
    public void onCreeperExplode(EntityExplodeEvent event) {
        if (event.getEntity() instanceof Creeper && !this.plugin.getConfig().getBoolean("mob.creeper")) {
            event.blockList().clear();
        }
    }

    @EventHandler
    public void onZombieDoorBreak(EntityBreakDoorEvent event) {
        if (!this.plugin.getConfig().getBoolean("mob.zombie")) {
            event.setCancelled(true);
        }
    }

    @EventHandler
    public void onEndermanPickup(EntityChangeBlockEvent event) {
        if (event.getEntity() instanceof Enderman && !this.plugin.getConfig().getBoolean("mob.enderman")) {
            event.setCancelled(true);
        }
    }

    @EventHandler
    public void onSnowgolemStep(EntityBlockFormEvent event) {
        if (event.getEntity() instanceof Snowman && !this.plugin.getConfig().getBoolean("mob.snowgolem")) {
            event.setCancelled(true);
        }
    }

    @EventHandler
    public void onGhastballExplode(EntityExplodeEvent event) {
        if (event.getEntity().getClass().equals(Fireball.class) && !this.plugin.getConfig().getBoolean("mob.ghast")) {
            event.blockList().clear();
        }
    }

    @EventHandler
    public void onWitherskullExplode(EntityExplodeEvent event) {
        if ((event.getEntity() instanceof WitherSkull || event.getEntity() instanceof Wither) && !this.plugin.getConfig().getBoolean("mob.wither")) {
            event.blockList().clear();
        }
    }

    @EventHandler
    public void onEnderdragonBlockDestroy(EntityExplodeEvent event) {
        if (event.getEntity() instanceof EnderDragon && !this.plugin.getConfig().getBoolean("mob.enderdragon")) {
            if (!event.blockList().isEmpty()) {
                event.blockList().clear();
            }
        }
    }

    @EventHandler
    public void onTNTExplode(EntityExplodeEvent event) {
        if (event.getEntity() instanceof TNTPrimed && !this.plugin.getConfig().getBoolean("mob.tnt")) {
            event.blockList().clear();
        }
    }
}
