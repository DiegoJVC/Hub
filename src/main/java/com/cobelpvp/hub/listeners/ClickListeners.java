package com.cobelpvp.hub.listeners;

import com.cobelpvp.hub.cosmetics.menu.CosmeticMenu;
import com.cobelpvp.hub.cosmetics.profile.Profile;
import com.cobelpvp.hub.spawn.menus.ServerSelector;
import com.cobelpvp.hub.util.RandomUtils;
import org.bukkit.*;
import org.bukkit.entity.*;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.ProjectileHitEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.player.PlayerQuitEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.FireworkMeta;
import org.spigotmc.event.entity.EntityDismountEvent;

import java.util.Random;

public class ClickListeners implements Listener {

    @EventHandler
    public void onClick(PlayerInteractEvent event) {
        Player player = event.getPlayer();

        Profile profile = Profile.getByPlayerName(player.getName());

        if (player.getItemInHand() == null || player.getItemInHand().getType() == Material.AIR) {
            return;
        }

        if (event.getAction().name().startsWith("RIGHT_")) {

            if (event.getItem().getType() == Material.ENDER_PEARL) {
                event.setCancelled(true);

                if (player.getVehicle() != null && player.getVehicle() instanceof EnderPearl) {
                    player.getVehicle().remove();
                }

                EnderPearl enderPearl = player.launchProjectile(EnderPearl.class);
                enderPearl.setPassenger(player);
                enderPearl.setVelocity(player.getLocation().getDirection().multiply(2));
                player.playSound(player.getLocation(), Sound.ENDERMAN_TELEPORT, 1.0F, 1.0F);
                player.updateInventory();
            }

            ItemStack item = event.getItem();

            if (item == null) {
                return;
            }

            switch (item.getType()) {
                case WATCH:
                    new ServerSelector().openMenu(player);
                    break;
                case CHEST:
                    new CosmeticMenu(profile).openMenu(player);
                    break;
            }

            if (item.getType() == Material.FIREWORK) {
                event.setCancelled(true);
                if (item.getItemMeta() != null && item.getItemMeta().getDisplayName() != null && item.getItemMeta().getDisplayName().equals(ChatColor.GOLD + "Rocket")) {
                    shootFirework(player, true);
                } else {
                    shootFirework(player, false);
                }
            }
        }
    }

    @EventHandler(priority = EventPriority.NORMAL)
    public void OnProjectileHit(ProjectileHitEvent event) {
        if (event.getEntity() instanceof EnderPearl) {
            if (event.getEntity().getShooter() instanceof Player) {
                ((Player) event.getEntity().getShooter()).getLocation().add(0.0D, 1.0D, 0.0D);
            }
        }
    }

    @EventHandler(priority = EventPriority.NORMAL)
    public void OnEntityDismount(EntityDismountEvent event) {
        if (event.getEntity() instanceof Player) {
            event.getDismounted().remove();
            event.getEntity().getLocation().add(0.0D, 1.0D, 0.0D);
        }
    }

    public static void shootFirework(Player player, boolean rocket) {
        Firework firework = (Firework) player.getWorld().spawnEntity(player.getLocation(), EntityType.FIREWORK);
        if (rocket) {
            firework.setPassenger(player);
        }
        FireworkMeta fireworkMeta = firework.getFireworkMeta();

        int random = RandomUtils.getRandomNumber(5);

        FireworkEffect.Type type = FireworkEffect.Type.BALL;
        switch (random) {
            case 2: {
                type = FireworkEffect.Type.BALL_LARGE;
                break;
            }
            case 3: {
                type = FireworkEffect.Type.BURST;
                break;
            }
            case 4: {
                type = FireworkEffect.Type.CREEPER;
                break;
            }
            case 5: {
                type = FireworkEffect.Type.STAR;
                break;
            }
        }

        Color firstColor = getRandomColor(), secondColor = getRandomColor();
        FireworkEffect effect = FireworkEffect.builder().withColor(firstColor).withFade(secondColor).with(type).trail(true).build();
        fireworkMeta.addEffect(effect);
        fireworkMeta.setPower(2);
        firework.setFireworkMeta(fireworkMeta);
        ItemStack itemInHand = player.getItemInHand();
        if (itemInHand.getAmount() <= 1) {
            player.setItemInHand(null);
        } else {
            itemInHand.setAmount(itemInHand.getAmount() - 1);
        }
    }

    @EventHandler(priority = EventPriority.NORMAL)
    public void OnPlayerQuit(PlayerQuitEvent event) {
        if (event.getPlayer().isInsideVehicle()) {
            event.getPlayer().getVehicle().remove();
        }
    }

    public static Color getRandomColor() {
        return Color.fromBGR(new Random().nextInt(255), new Random().nextInt(255), new Random().nextInt(255));
    }
}
