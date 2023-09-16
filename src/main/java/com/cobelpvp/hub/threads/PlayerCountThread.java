package com.cobelpvp.hub.threads;

import lombok.Setter;
import org.bukkit.Bukkit;
import com.cobelpvp.hub.Hub;

import java.io.ByteArrayOutputStream;
import java.io.DataOutputStream;

public class PlayerCountThread extends Thread {

    @Setter public static int PLAYER_COUNT = 1;

    public PlayerCountThread(){
        setName("Hub-PlayerCount");
    }

    public static void setPLAYER_COUNT(int PLAYER_COUNT) {
        PlayerCountThread.PLAYER_COUNT = PLAYER_COUNT;
    }

    @Override
    public void run() {
        while(true) {
            pingBungee();
            try {
                sleep(250);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    private void pingBungee() {
        try {
            ByteArrayOutputStream b = new ByteArrayOutputStream();
            DataOutputStream out = new DataOutputStream(b);
            out.writeUTF("PlayerCount");
            out.writeUTF("ALL");
            Bukkit.getServer().sendPluginMessage(Hub.getProvidingPlugin(Hub.class), "BungeeCord", b.toByteArray());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
