package com.cobelpvp.hub.listeners;

import com.cobelpvp.atheneum.util.ColorText;
import com.cobelpvp.engine.util.ItemBuilder;
import com.cobelpvp.hub.cosmetics.profile.Profile;
import com.cobelpvp.hub.Hub;
import com.cobelpvp.hub.managers.ArmorManager;
import org.bukkit.*;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.BookMeta;

public class ConnectionListeners implements Listener {

    private final Hub plugin = Hub.getInstance();

    @EventHandler
    public void onJoin(PlayerJoinEvent event) {
        Player player = event.getPlayer();

        player.setHealth(20);
        player.setFoodLevel(40);

        player.getInventory().setArmorContents(null);
        player.getInventory().clear();
        player.getInventory().setHeldItemSlot(0);

        Profile profile = Profile.getByPlayerName(player.getName());

        if (player.getVehicle() != null) {
            player.getVehicle().leaveVehicle();
        }

        player.teleport(Bukkit.getWorlds().get(0).getSpawnLocation().add(0.5, 0, 0.5));

        plugin.getItemManager().handleLobbyInventory(player);

        ItemStack book = new ItemBuilder(Material.WRITTEN_BOOK).build();
        BookMeta bookMeta = (BookMeta) book.getItemMeta();
        bookMeta.setAuthor("CobelPvP");
        bookMeta.setTitle(ChatColor.GOLD + "Welcome to Cobel Network");
        bookMeta.setPages(
                ChatColor.BLUE + "Welcome " + player.getName() + " to Cobel Network, you can use the watch to navigate to the network."
        );
        book.setItemMeta(bookMeta);
        player.getInventory().setItem(1, book);

        event.setJoinMessage(null);

        player.setWalkSpeed(0.5f);

        if (player.hasPermission("hub.vip")) {
            player.setAllowFlight(true);
            player.setFlying(true);
            player.sendMessage(ColorText.translate("&6Your VIP flight mode have been enabled."));
        }

        if (profile == null || !profile.isLoaded()) return;
        ArmorManager.handleArmor(player, profile.getArmors());
    }

    @EventHandler
    public void onQuit(PlayerQuitEvent event) {
        Player player = event.getPlayer();

        event.setQuitMessage(null);

        if (player.getVehicle() != null) {
            player.getVehicle().leaveVehicle();
        }
    }
}
