package com.cobelpvp.hub.configs;

import com.cobelpvp.redstone.utils.ConfigHelper;
import com.cobelpvp.hub.Hub;

public class SettingsConfig extends ConfigHelper {

    public SettingsConfig(String name, String directory) {
        super(Hub.getProvidingPlugin(Hub.class), name, directory);
    }
}
