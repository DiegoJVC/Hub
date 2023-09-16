package com.cobelpvp.hub.cosmetics.profile;

import com.mongodb.client.MongoCollection;
import com.mongodb.client.model.Filters;
import com.mongodb.client.model.ReplaceOptions;
import lombok.Getter;
import lombok.Setter;
import com.cobelpvp.atheneum.uuid.TeamsUUIDCache;
import com.cobelpvp.hub.cosmetics.ArmorTypes;
import com.cobelpvp.hub.Hub;
import org.bson.Document;
import org.bukkit.Bukkit;
import org.bukkit.OfflinePlayer;
import org.bukkit.entity.Player;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

@Getter
@Setter
public class Profile {

    @Getter private static Map<UUID, Profile> profileMap = new HashMap<>();
    private static MongoCollection<Document> collection = Hub.getInstance().getMongoDatabase().getCollection("profiles");

    private UUID uuid;
    private String name;
    private ArmorTypes armors;
    private boolean loaded;

    public Profile(String name, UUID uuid) {
        this.uuid = uuid;
        this.name = name;
        this.armors = ArmorTypes.None;
        load();
    }

    public Player getPlayer() {
        return Bukkit.getPlayer(uuid);
    }

    public void load() {
        Document document = collection.find(Filters.eq("uuid", uuid.toString())).first();

        if (document != null) {
            if (name == null) {
                name = document.getString("name");
            }

            armors = ArmorTypes.valueOf(document.getString("armors"));
        }

        loaded = true;
    }

    public void save() {
        Document document = new Document();
        document.put("name", name);
        document.put("uuid", uuid.toString());
        document.put("armors", armors.name());
        collection.replaceOne(Filters.eq("uuid", uuid.toString()), document, new ReplaceOptions().upsert(true));
    }

    public static Profile getByPlayerUUID(UUID uuid) {
        if (profileMap.containsKey(uuid)) {
            return profileMap.get(uuid);
        }

        return new Profile(null, uuid);
    }

    public static Profile getByPlayerName(String name) {
        Player player = Bukkit.getPlayer(name);

        if (player != null) {
            return profileMap.get(player.getUniqueId());
        }

        OfflinePlayer offlinePlayer = Bukkit.getOfflinePlayer(name);

        if (offlinePlayer.hasPlayedBefore()) {
            if (profileMap.containsKey(offlinePlayer.getUniqueId())) {
                return profileMap.get(offlinePlayer.getUniqueId());
            }

            return new Profile(offlinePlayer.getName(), offlinePlayer.getUniqueId());
        }

        UUID uuid = TeamsUUIDCache.uuid(name);

        if (uuid != null) {
            if (profileMap.containsKey(uuid)) {
                return profileMap.get(uuid);
            }

            return new Profile(name, uuid);
        }

        return null;
    }

    public UUID getUuid() {
        return uuid;
    }

    public String getName() {
        return name;
    }

    public boolean isLoaded() {
        return loaded;
    }

    public ArmorTypes getArmors() {
        return armors;
    }

    public void setArmors(ArmorTypes armors) {
        this.armors = armors;
    }

    public void setName(String name) {
        this.name = name;
    }

    public static Map<UUID, Profile> getProfileMap() {
        return profileMap;
    }
}
