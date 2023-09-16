package com.cobelpvp.hub.managers;

import com.cobelpvp.engine.util.ItemBuilder;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;

public class ItemManager {
    
    public void handleLobbyInventory(Player player) {
        player.getInventory().setItem(0, new ItemBuilder(Material.WATCH)
            .name(ChatColor.GOLD + "Server selector")
            .build());

        if (player.hasPermission("hub.vip")) {
            player.getInventory().setItem(3, new ItemBuilder(Material.FIREWORK)
                .name(ChatColor.GOLD + "Fireworks")
                .amount(5)
                .build());

            player.getInventory().setItem(4, new ItemBuilder(Material.FIREWORK)
                .name(ChatColor.GOLD + "Rocket")
                .amount(5)
                .build());
        }

        player.getInventory().setItem(8, new ItemBuilder(Material.CHEST)
            .name(ChatColor.GOLD + "Cosmetics")
            .build());

        player.getInventory().setItem(2, new ItemBuilder(Material.ENDER_PEARL)
            .amount(32)
            .build());
        
        player.updateInventory();
    }
}
