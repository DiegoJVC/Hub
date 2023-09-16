package com.cobelpvp.hub.cosmetics.menu;

import com.cobelpvp.hub.cosmetics.ArmorTypes;
import com.cobelpvp.hub.cosmetics.profile.Profile;
import lombok.AllArgsConstructor;
import com.cobelpvp.atheneum.menu.Button;
import com.cobelpvp.atheneum.menu.Menu;
import com.cobelpvp.engine.util.ItemBuilder;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.ClickType;
import org.bukkit.inventory.ItemStack;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@AllArgsConstructor
public class CosmeticMenu extends Menu {

    private final Profile profile;

    @Override
    public String getTitle(Player player) {
        return ChatColor.DARK_GRAY + "Cosmetics menu.";
    }

    @Override
    public Map<Integer, Button> getButtons(Player player) {
        Map<Integer, Button> buttons = new HashMap<>();

        buttons.put(0, new Button() {
            @Override
            public String getName(Player player) {
                return ChatColor.GREEN + "Armors";
            }

            @Override
            public List<String> getDescription(Player player) {
                List<String> lore = new ArrayList<>();
                lore.add(ChatColor.BLUE + "Selected: " + (profile.getArmors() == ArmorTypes.None ? "None" : profile.getArmors().name().toLowerCase().replace("_", " ")));
                return lore;
            }

            @Override
            public Material getMaterial(Player player) {
                return null;
            }

            @Override
            public ItemStack getButtonItem(Player player) {
                ItemStack stack = new ItemBuilder(Material.LEATHER_CHESTPLATE).enchantment(Enchantment.DURABILITY).name(getName(player)).lore(getDescription(player)).build();
                return stack;
            }

            @Override
            public void clicked(Player player, int slot, ClickType clickType) {
                new ArmorsMenu().openMenu(player);
            }
        });
        return buttons;
    }


}
