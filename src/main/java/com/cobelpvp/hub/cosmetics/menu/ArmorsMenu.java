package com.cobelpvp.hub.cosmetics.menu;

import com.cobelpvp.hub.cosmetics.ArmorTypes;
import com.cobelpvp.hub.cosmetics.profile.Profile;
import com.google.common.collect.Lists;
import com.cobelpvp.atheneum.menu.Button;
import com.cobelpvp.atheneum.menu.Menu;
import com.cobelpvp.atheneum.util.ColorText;
import com.cobelpvp.hub.managers.ArmorManager;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.ClickType;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ArmorsMenu extends Menu {

    @Override
    public String getTitle(Player player) {
        return "Armors";
    }

    @Override
    public Map<Integer, Button> getButtons(Player player) {
        Map<Integer, Button> buttons = new HashMap<>();
        for (ArmorTypes armorTypes : ArmorTypes.values()) {
            if (armorTypes == ArmorTypes.None) continue;

            buttons.put(buttons.size(), new Button() {
                @Override
                public String getName(Player player) {
                    return ChatColor.AQUA + armorTypes.name().replace("_", " ");
                }

                @Override
                public List<String> getDescription(Player player) {
                    return Lists.newArrayList();
                }

                @Override
                public Material getMaterial(Player player) {
                    return Material.BOOK;
                }

                @Override
                public void clicked(Player player, int slot, ClickType clickType) {
                    Profile profile = Profile.getByPlayerName(player.getName());

                    if (profile != null) {
                        if (clickType.isLeftClick()) {
                            player.sendMessage(ColorText.translate("&6Selected " + armorTypes.name().replace("_", " ")));
                            player.closeInventory();
                            Button.playNeutral(player);

                            ArmorManager.handleArmor(player, armorTypes);

                            /**
                             * Profile
                             */
                            profile.setArmors(armorTypes);
                            profile.save();
                        }

                        if (clickType.isRightClick()) {
                            player.closeInventory();

                            player.getInventory().setHelmet(null); player.getInventory().setChestplate(null); player.getInventory().setLeggings(null); player.getInventory().setBoots(null);

                            player.updateInventory();

                            profile.setArmors(ArmorTypes.None);
                            profile.save();

                            player.sendMessage(ChatColor.DARK_RED + "You have been cleared your current armor.");
                        }
                    }
                }
            });
        }
        return buttons;
    }
}
