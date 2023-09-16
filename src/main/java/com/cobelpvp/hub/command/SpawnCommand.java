package com.cobelpvp.hub.command;

import com.cobelpvp.atheneum.command.Command;
import com.cobelpvp.atheneum.util.ColorText;
import com.cobelpvp.hub.Hub;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

public class SpawnCommand {

    private static Hub plugin = Hub.getInstance();

    @Command(names = {"spawn"})
    public static void spawnCommand(Player sender) {
        sender.teleport(Hub.getInstance().getSpawnLocation());
        plugin.getItemManager().handleLobbyInventory(sender);

        sender.sendMessage(ColorText.translate("&aTeleported to the Spawn!"));
    }
}
