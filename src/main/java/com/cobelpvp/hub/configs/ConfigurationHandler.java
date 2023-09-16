package com.cobelpvp.hub.configs;

import com.cobelpvp.redstone.utils.ConfigHelper;
import com.cobelpvp.hub.Hub;
import lombok.Getter;

public class ConfigurationHandler {

    @Getter private static ConfigHelper settings = new SettingsConfig("config", Hub.getProvidingPlugin(Hub.class).getDataFolder().getAbsolutePath());

    public static ConfigHelper getSettings() {
        return settings;
    }
}
