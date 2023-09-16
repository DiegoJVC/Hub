package com.cobelpvp.hub.managers;

import com.cobelpvp.engine.util.ItemBuilder;
import com.cobelpvp.hub.cosmetics.ArmorTypes;
import org.bukkit.ChatColor;
import org.bukkit.Color;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.LeatherArmorMeta;

public class ArmorManager {

    public static void handleArmor(Player player, ArmorTypes armorTypes) {
        String highPermission = "hub.highstaff"; String mediumPermission = "hub.mediumstaff"; String lowPermission = "hub.lowstaff";
        String cobelPermission = "hub.cobel"; String inmortalPermission = "hub.inmortal";
        String legendPermission = "hub.legend"; String ancientPermission = "hub.ancient"; String archonPermission = "hub.archon";

        if (!(player.hasPermission(highPermission)
                || player.hasPermission(mediumPermission)
                || player.hasPermission(lowPermission)
                || player.hasPermission(cobelPermission)
                || player.hasPermission(inmortalPermission)
                || player.hasPermission(ancientPermission)
                || player.hasPermission(archonPermission)
                || player.hasPermission(legendPermission))) {
            player.sendMessage(ChatColor.DARK_RED + "You don't have permissions to use this armor.");
            return;
        }

        if (armorTypes == ArmorTypes.High_Staff) {
            ItemStack highChestplate = new ItemBuilder(Material.LEATHER_CHESTPLATE).enchantment(Enchantment.DURABILITY, 1).build();
            ItemStack highLeggings = new ItemBuilder(Material.LEATHER_LEGGINGS).enchantment(Enchantment.DURABILITY, 1).build();
            ItemStack highBoots = new ItemBuilder(Material.LEATHER_BOOTS).enchantment(Enchantment.DURABILITY, 1).build();
            LeatherArmorMeta highChestplateMeta = (LeatherArmorMeta) highChestplate.getItemMeta();
            LeatherArmorMeta highLeggingsMeta = (LeatherArmorMeta) highLeggings.getItemMeta();
            LeatherArmorMeta highBootsMeta = (LeatherArmorMeta) highBoots.getItemMeta();
            highChestplateMeta.setColor(Color.RED);
            highLeggingsMeta.setColor(Color.RED);
            highBootsMeta.setColor(Color.RED);
            highChestplate.setItemMeta(highChestplateMeta);
            highLeggings.setItemMeta(highLeggingsMeta);
            highBoots.setItemMeta(highBootsMeta);
            if (player.hasPermission(highPermission)) {
                player.getInventory().setChestplate(highChestplate);
                player.getInventory().setLeggings(highLeggings);
                player.getInventory().setBoots(highBoots);
            }
        }

        /**
         * Medium Staff Armor
         */
        if (armorTypes == ArmorTypes.Medium_Staff) {
            ItemStack mediumChestplate = new ItemBuilder(Material.LEATHER_CHESTPLATE).enchantment(Enchantment.DURABILITY, 1).build();
            ItemStack mediumLeggings = new ItemBuilder(Material.LEATHER_LEGGINGS).enchantment(Enchantment.DURABILITY, 1).build();
            ItemStack mediumBoots = new ItemBuilder(Material.LEATHER_BOOTS).enchantment(Enchantment.DURABILITY, 1).build();
            LeatherArmorMeta mediumChestplateMeta = (LeatherArmorMeta) mediumChestplate.getItemMeta();
            LeatherArmorMeta mediumLeggingsMeta = (LeatherArmorMeta) mediumLeggings.getItemMeta();
            LeatherArmorMeta mediumBootsMeta = (LeatherArmorMeta) mediumBoots.getItemMeta();
            mediumChestplateMeta.setColor(Color.BLACK);
            mediumLeggingsMeta.setColor(Color.BLACK);
            mediumBootsMeta.setColor(Color.BLACK);
            mediumChestplate.setItemMeta(mediumChestplateMeta);
            mediumLeggings.setItemMeta(mediumLeggingsMeta);
            mediumBoots.setItemMeta(mediumBootsMeta);
            if (player.hasPermission(mediumPermission)) {
                player.getInventory().setChestplate(mediumChestplate);
                player.getInventory().setLeggings(mediumLeggings);
                player.getInventory().setBoots(mediumBoots);
            }
        }

        /**
         * Low Staff Armor
         */
        if (armorTypes == ArmorTypes.Low_Staff) {
            ItemStack lowChestplate = new ItemBuilder(Material.LEATHER_CHESTPLATE).enchantment(Enchantment.DURABILITY, 1).build();
            ItemStack lowLeggings = new ItemBuilder(Material.LEATHER_LEGGINGS).enchantment(Enchantment.DURABILITY, 1).build();
            ItemStack lowBoots = new ItemBuilder(Material.LEATHER_BOOTS).enchantment(Enchantment.DURABILITY, 1).build();
            LeatherArmorMeta lowChestplateMeta = (LeatherArmorMeta) lowChestplate.getItemMeta();
            LeatherArmorMeta lowLeggingsMeta = (LeatherArmorMeta) lowLeggings.getItemMeta();
            LeatherArmorMeta lowBootsMeta = (LeatherArmorMeta) lowBoots.getItemMeta();
            lowChestplateMeta.setColor(Color.GRAY);
            lowLeggingsMeta.setColor(Color.GRAY);
            lowBootsMeta.setColor(Color.GRAY);
            lowChestplate.setItemMeta(lowChestplateMeta);
            lowLeggings.setItemMeta(lowLeggingsMeta);
            lowBoots.setItemMeta(lowBootsMeta);
            if (player.hasPermission(lowPermission)) {
                player.getInventory().setChestplate(lowChestplate);
                player.getInventory().setLeggings(lowLeggings);
                player.getInventory().setBoots(lowBoots);
            }
        }

        /**
         * Cobel Armor
         */
        if (armorTypes == ArmorTypes.Cobel) {
            ItemStack cobelChestplate = new ItemBuilder(Material.DIAMOND_CHESTPLATE).enchantment(Enchantment.DURABILITY, 1).build();
            ItemStack cobelLeggings = new ItemBuilder(Material.DIAMOND_LEGGINGS).enchantment(Enchantment.DURABILITY, 1).build();
            ItemStack cobelBoots = new ItemBuilder(Material.DIAMOND_BOOTS).enchantment(Enchantment.DURABILITY, 1).build();
            if (player.hasPermission(cobelPermission)) {
                player.getInventory().setChestplate(cobelChestplate);
                player.getInventory().setLeggings(cobelLeggings);
                player.getInventory().setBoots(cobelBoots);
            }
        }

        /**
         * Inmortal Armor
         */
        if (armorTypes == ArmorTypes.Inmortal) {
            ItemStack inmortalChestplate = new ItemBuilder(Material.GOLD_CHESTPLATE).enchantment(Enchantment.DURABILITY, 1).build();
            ItemStack inmortalLeggings = new ItemBuilder(Material.GOLD_LEGGINGS).enchantment(Enchantment.DURABILITY, 1).build();
            ItemStack inmortalBoots = new ItemBuilder(Material.GOLD_BOOTS).enchantment(Enchantment.DURABILITY, 1).build();
            if (player.hasPermission(inmortalPermission)) {
                player.getInventory().setChestplate(inmortalChestplate);
                player.getInventory().setLeggings(inmortalLeggings);
                player.getInventory().setBoots(inmortalBoots);
            }
        }

        /**
         * Legend Armor
         */
        if (armorTypes == ArmorTypes.Legend) {
            ItemStack legendChestplate = new ItemBuilder(Material.IRON_CHESTPLATE).enchantment(Enchantment.DURABILITY, 1).build();
            ItemStack legendLeggings = new ItemBuilder(Material.IRON_LEGGINGS).enchantment(Enchantment.DURABILITY, 1).build();
            ItemStack legendBoots = new ItemBuilder(Material.IRON_BOOTS).enchantment(Enchantment.DURABILITY, 1).build();
            if (player.hasPermission(legendPermission)) {
                player.getInventory().setChestplate(legendChestplate);
                player.getInventory().setLeggings(legendLeggings);
                player.getInventory().setBoots(legendBoots);
            }
        }

        /**
         * Ancient Armor
         */
        if (armorTypes == ArmorTypes.Ancient) {
            ItemStack ancientChestplate = new ItemBuilder(Material.CHAINMAIL_CHESTPLATE).enchantment(Enchantment.DURABILITY, 1).build();
            ItemStack ancientLeggings = new ItemBuilder(Material.CHAINMAIL_LEGGINGS).enchantment(Enchantment.DURABILITY, 1).build();
            ItemStack ancientBoots = new ItemBuilder(Material.CHAINMAIL_BOOTS).enchantment(Enchantment.DURABILITY, 1).build();
            if (player.hasPermission(ancientPermission)) {
                player.getInventory().setChestplate(ancientChestplate);
                player.getInventory().setLeggings(ancientLeggings);
                player.getInventory().setBoots(ancientBoots);
            }
        }

        /**
         * Archon Armor
         */
        if (armorTypes == ArmorTypes.Archon) {
            ItemStack archonChestplate = new ItemBuilder(Material.LEATHER_CHESTPLATE).enchantment(Enchantment.DURABILITY, 1).build();
            ItemStack archonLeggings = new ItemBuilder(Material.LEATHER_LEGGINGS).enchantment(Enchantment.DURABILITY, 1).build();
            ItemStack archonBoots = new ItemBuilder(Material.LEATHER_BOOTS).enchantment(Enchantment.DURABILITY, 1).build();
            if (player.hasPermission(archonPermission)) {
                player.getInventory().setChestplate(archonChestplate);
                player.getInventory().setLeggings(archonLeggings);
                player.getInventory().setBoots(archonBoots);
            }
        }
    }
}
