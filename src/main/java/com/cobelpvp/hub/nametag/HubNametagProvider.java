package com.cobelpvp.hub.nametag;

import com.cobelpvp.atheneum.nametag.NametagInfo;
import com.cobelpvp.atheneum.nametag.NametagProvider;
import com.cobelpvp.engine.profile.Profile;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;

public class HubNametagProvider extends NametagProvider {

    public HubNametagProvider() {
        super("Hub Provider", 5);
    }

    @Override
    public NametagInfo fetchNametag(Player toRefresh, Player refreshFor) {
        NametagInfo nametagInfo = null;

        Profile profile = Profile.getByUsername(toRefresh.getName());

        if (nametagInfo == null) {
            if (toRefresh.isOp()) {
                nametagInfo = createNametag(ChatColor.GRAY + "✪ " + profile.getActiveGrant().getRank().getGameColor() + "", "");
            } else {
                nametagInfo = createNametag(profile.getActiveGrant().getRank().getGameColor() + "", "");
            }
        }

        return (nametagInfo == null ? createNametag(ChatColor.WHITE.toString(), "") : nametagInfo);
    }
}
