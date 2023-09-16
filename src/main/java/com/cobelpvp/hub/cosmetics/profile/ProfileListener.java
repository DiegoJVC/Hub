package com.cobelpvp.hub.cosmetics.profile;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerPreLoginEvent;

public class ProfileListener implements Listener {

    @EventHandler
    public void onAsyncPlayerPreLogin(AsyncPlayerPreLoginEvent event) {
        Player player = Bukkit.getPlayer(event.getUniqueId());

        Profile profile = null;

        try {
            profile = new Profile(event.getName(), event.getUniqueId());

            if (!profile.isLoaded()) {
                event.setLoginResult(AsyncPlayerPreLoginEvent.Result.KICK_OTHER);
                event.setKickMessage(ChatColor.RED + "Failed to load your profile.");
                return;
            }

            profile.setName(event.getName());

            profile.save();
        } catch (Exception e) {
            e.printStackTrace();
        }

        if (!profile.isLoaded()) {
            event.setLoginResult(AsyncPlayerPreLoginEvent.Result.KICK_OTHER);
            event.setKickMessage(ChatColor.RED + "Failed to load your profile.");
            return;
        }

        Profile.getProfileMap().put(profile.getUuid(), profile);
    }
}
