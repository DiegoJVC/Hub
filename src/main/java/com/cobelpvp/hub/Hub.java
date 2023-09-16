package com.cobelpvp.hub;

import com.cobelpvp.hub.configs.ConfigurationHandler;
import com.cobelpvp.hub.cosmetics.profile.Profile;
import com.cobelpvp.hub.listeners.BungeecordListeners;
import com.cobelpvp.hub.managers.ItemManager;
import com.cobelpvp.hub.nametag.HubNametagProvider;
import com.cobelpvp.hub.scoreboard.HubScoreboardConfiguration;
import com.cobelpvp.hub.threads.PlayerCountThread;
import com.mongodb.MongoClient;
import com.mongodb.MongoClientOptions;
import com.mongodb.MongoCredential;
import com.mongodb.ServerAddress;
import com.mongodb.client.MongoDatabase;
import lombok.Getter;
import lombok.Setter;
import com.cobelpvp.atheneum.command.TeamsCommandHandler;
import com.cobelpvp.atheneum.nametag.TeamsNametagHandler;
import com.cobelpvp.atheneum.scoreboard.TeamsScoreboardHandler;
import com.cobelpvp.engine.util.ListenerHandler;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.util.Vector;

@Getter
@Setter
public class Hub extends JavaPlugin {

    @Getter
    private static Hub instance;

    @Getter
    private static Hub plugin;

    private PlayerCountThread playerCountThread;
    private ItemManager itemManager;
    private MongoDatabase mongoDatabase;

    @Override
    public void onEnable() {
        instance = this;
        plugin = this;

        /**
         * Load the Mongo Database
         */
        if (Settings.mongoOk) {
            mongoDatabase = new MongoClient(new ServerAddress("127.0.0.1", 27017), MongoCredential.createCredential(Settings.mongoUsername, "Hub", Settings.mongoPassword.toCharArray()), MongoClientOptions.builder().build()).getDatabase("Hub");
        } else {
            mongoDatabase = new MongoClient("127.0.0.1", 27017).getDatabase("Hub");
        }

        /**
         * Load the configuration
         */
        new ConfigurationHandler();

        /**
         * Register everything
         */
        registerCommands();
        registerListeners();
        registerManagers();
        registerScoreboard();
        registerNametag();
        registerBungeeListeners();

        /**
         * Start the PlayerCountThread
         */
        playerCountThread.start();
    }

    @Override
    public void onDisable() {
        playerCountThread.stop();

        Bukkit.getOnlinePlayers().forEach(player -> {
            Profile profile = Profile.getByPlayerName(player.getName());

            assert profile != null;
            profile.save();
        });
    }

    /**
     * Register Commands
     */
    public void registerCommands() {
        TeamsCommandHandler.registerAll(this);
    }

    /**
     * Register Listeners
     */
    public void registerListeners() {
        ListenerHandler.loadListenersFromPackage(this, "com.cobelpvp.hub.listeners");
        ListenerHandler.loadListenersFromPackage(this, "com.cobelpvp.hub.cosmetics.profile");
    }

    /**
     * Register Managers
     */
    public void registerManagers() {
        playerCountThread = new PlayerCountThread();
        itemManager = new ItemManager();
    }

    public Location getSpawnLocation() {
        return (Hub.getInstance().getServer().getWorld("world").getSpawnLocation().add(new Vector(0.5, 1, 0.5)));
    }

    /**
     * Register Scoreboard
     */
    public void registerScoreboard() {
        TeamsScoreboardHandler.setConfiguration(new HubScoreboardConfiguration().create());
    }

    /**
     * Register Nametag Provider
     */
    public void registerNametag() {
        TeamsNametagHandler.registerProvider(new HubNametagProvider());
    }

    /**
     * Register BungeeCord Channels
     */
    private void registerBungeeListeners(){
        getServer().getMessenger().registerOutgoingPluginChannel(this, "BungeeCord");
        getServer().getMessenger().registerIncomingPluginChannel(this, "BungeeCord", new BungeecordListeners());
    }
}
