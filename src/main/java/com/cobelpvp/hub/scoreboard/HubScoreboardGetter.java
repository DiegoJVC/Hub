package com.cobelpvp.hub.scoreboard;

import com.cobelpvp.atheneum.autoreboot.AutoRebootHandler;
import com.cobelpvp.atheneum.scoreboard.ScoreGetter;
import com.cobelpvp.atheneum.util.LinkedList;
import com.cobelpvp.atheneum.util.TimeUtils;
import com.cobelpvp.engine.profile.Profile;
import com.cobelpvp.queue.shared.queue.Queue;
import com.cobelpvp.redstone.server.Server;
import com.cobelpvp.redstone.server.ServerHandler;
import com.cobelpvp.hub.threads.PlayerCountThread;
import org.bukkit.entity.Player;

public class HubScoreboardGetter implements ScoreGetter {

    @Override
    public void getScores(LinkedList<String> scores, Player player) {
        Profile profile = Profile.getByUsername(player.getName());
        Queue queue = Queue.getByPlayer(player.getUniqueId());

        scores.add("&6Players:");
        scores.add("&7" + PlayerCountThread.PLAYER_COUNT);
        scores.add("");
        scores.add("&6Rank:");
        scores.add("&7" + profile.getActiveGrant().getRank().getFormattedName());

        if (queue != null) {
            Server server = ServerHandler.getServer(queue.getName());
            if (server == null) return;

            scores.add("");
            scores.add("&6Queue of:");
            scores.add("&b" + server.getServerID());
            scores.add("&ePosition: #" + queue.getPosition(player.getUniqueId()) + " of " + queue.getPlayers().size());
            scores.add("");
            scores.add("&cYou want leave? &6/leavequeue");
        }

        if (AutoRebootHandler.isRebooting()) {
            scores.add("");
            scores.add("&4&lRebooting: " + TimeUtils.formatIntoMMSS(AutoRebootHandler.getRebootSecondsRemaining()));
        }

        scores.add("");
        scores.add("&7&owww.cobelpvp.com");

        if (!scores.isEmpty()) {
            scores.addFirst("&a&7&m--------------------");
            scores.add("&f&7&m--------------------");
        }
    }
}
