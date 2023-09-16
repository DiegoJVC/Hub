package com.cobelpvp.hub.listeners;

import com.cobelpvp.hub.cosmetics.profile.Profile;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.entity.*;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.entity.FoodLevelChangeEvent;
import org.bukkit.event.player.*;

public class PlayerListeners implements Listener {

    @EventHandler(priority = EventPriority.MONITOR)
    public void onBlockBreak(BlockBreakEvent event) {
        /* Player Object */
        Player player = event.getPlayer();

        if (player.hasMetadata("Build")) return;

        event.setCancelled(true);
    }

    @EventHandler(priority = EventPriority.MONITOR)
    public void onBlockPlace(BlockPlaceEvent event) {
        /* Player Object */
        Player player = event.getPlayer();

        if (player.hasMetadata("Build")) return;

        event.setCancelled(true);
    }

    @EventHandler(priority = EventPriority.MONITOR)
    public void onEntityDamage(EntityDamageEvent event) {
        if (event.getEntity() instanceof Player) {
            Player player = (Player) event.getEntity();
            if (event.getCause() == EntityDamageEvent.DamageCause.VOID) {
                player.teleport(Bukkit.getWorld("world").getSpawnLocation().add(0.0F, 0.5F, 0.0F));
            }
        }
        event.setCancelled(true);
    }

    @EventHandler
    public void onPlayerQuit(PlayerQuitEvent event) {
        // player object
        Player player = event.getPlayer();

        // profile object
        Profile profile = Profile.getByPlayerName(player.getName());
        assert profile != null;
        profile.save();
    }

    @EventHandler
    public void onPlayerMove(PlayerMoveEvent event) {
        Location to = event.getTo();
        Player player = event.getPlayer();

        if (to.getX() >= 1000 || to.getZ() >= 1000 || to.getY() >= 1000) {
            player.sendMessage(ChatColor.RED + "You cannot pass the border.");
            player.teleport(Bukkit.getWorld("world").getSpawnLocation());
        }
    }

    @EventHandler
    public void onItemPikcup(PlayerPickupItemEvent event) {
        event.setCancelled(true);
    }

    @EventHandler(priority = EventPriority.MONITOR)
    public void onItemDrop(PlayerDropItemEvent event) {
        /* Player Object */
        Player player = event.getPlayer();

        if (player.hasMetadata("Build")) return;

        event.setCancelled(true);
    }

    @EventHandler(priority = EventPriority.MONITOR)
    public void onFoodLevelChange(FoodLevelChangeEvent event) {
        if (!(event.getEntity() instanceof Player)) {
            return;
        }

        event.setFoodLevel(40);
    }
}
