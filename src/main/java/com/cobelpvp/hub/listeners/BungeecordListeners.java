package com.cobelpvp.hub.listeners;

import com.cobelpvp.hub.threads.PlayerCountThread;
import com.google.common.io.ByteArrayDataInput;
import com.google.common.io.ByteStreams;
import org.bukkit.entity.Player;
import org.bukkit.plugin.messaging.PluginMessageListener;

public class BungeecordListeners implements PluginMessageListener {

    @Override
    public void onPluginMessageReceived(String s, Player player, byte[] bytes) {
        if (!s.equalsIgnoreCase("BungeeCord")) {
            return;
        }
        try {
            ByteArrayDataInput in = ByteStreams.newDataInput(bytes);
            String subchannel = in.readUTF();

            if (subchannel.equals("PlayerCount")) {
                String server = in.readUTF();
                int playerCount = in.readInt();

                PlayerCountThread.setPLAYER_COUNT(playerCount);

            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
