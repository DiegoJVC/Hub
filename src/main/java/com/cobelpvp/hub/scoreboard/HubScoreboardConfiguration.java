package com.cobelpvp.hub.scoreboard;

import com.cobelpvp.atheneum.scoreboard.ScoreboardConfiguration;
import com.cobelpvp.atheneum.scoreboard.TitleGetter;
import com.cobelpvp.atheneum.util.ColorText;

public class HubScoreboardConfiguration {

    public ScoreboardConfiguration create() {
        ScoreboardConfiguration configuration = new ScoreboardConfiguration();

        configuration.setTitleGetter(new TitleGetter(ColorText.translate("&6&lCobelPvP &7┃ &fNetwork")));
        configuration.setScoreGetter(new HubScoreboardGetter());
        
        return configuration;
    }
}
