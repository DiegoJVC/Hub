package com.cobelpvp.hub;

import lombok.Getter;
import com.cobelpvp.engine.Engine;

@Getter
public class Settings {

    private static Hub instance = Hub.getInstance();

    private Settings() {
        throw new UnsupportedOperationException("This is a utility class and cannot be instantiated");
    }

    public static String mongoUsername;
    public static boolean mongoOk;
    public static String mongoPassword;

    protected  static void load() {
        Engine.getInstance().saveDefaultConfig();

        mongoPassword = instance.getConfig().getString("Mongo.Password");
        mongoUsername = instance.getConfig().getString("Mongo.Username");
        mongoOk = instance.getConfig().getBoolean("Mongo.Auth");
    }

    public void save() {
        instance.getConfig().set("Mongo.Password", mongoPassword);
        instance.getConfig().set("Mongo.Username", mongoUsername);
        instance.getConfig().set("Mongo.Auth", mongoOk);

        instance.saveConfig();
    }
}
