package com.cobelpvp.hub.spawn.menus;

import com.cobelpvp.atheneum.menu.Button;
import com.cobelpvp.atheneum.menu.Menu;
import com.cobelpvp.atheneum.util.ColorText;
import com.cobelpvp.queue.shared.queue.Queue;
import com.cobelpvp.redstone.server.Server;
import com.cobelpvp.redstone.server.ServerHandler;
import com.cobelpvp.redstone.server.ServerState;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.ClickType;
import org.bukkit.inventory.ItemStack;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ServerSelector extends Menu {

    @Override
    public String getTitle(Player player) {
        return "Server Selector";
    }

    @Override
    public boolean isAutoUpdate() {
        return true;
    }

    @Override
    public Map<Integer, Button> getButtons(Player player) {
        Map<Integer, Button> buttons = new HashMap<>();

        buttons.put(0, new Button() {
            final Queue queue = Queue.getByName("HCFactions");
            final Server server = ServerHandler.getServer(queue.getName());

            @Override
            public String getName(Player player) {
                return ChatColor.YELLOW + "HCFactions";
            }

            @Override
            public List<String> getDescription(Player player) {
                List<String> lore = new ArrayList<>();
                try {
                    if (queue == null || server == null || server.getData() == null || server.getData().getState() == null) {
                        lore.add(ChatColor.RED + "Server is offline.");
                    } else if (server.getData().getState() != ServerState.OFFLINE) {
                        lore.add(ChatColor.BLUE + "Players: " + queue.getServerData().getOnlinePlayers() + "/" + queue.getServerData().getMaximumPlayers());
                    }
                } catch (Exception e) {
                    lore.add(ChatColor.RED + "Server is offline.");
                }
                return lore;
            }

            @Override
            public Material getMaterial(Player player) {
                return Material.DIAMOND_CHESTPLATE;
            }

            @Override
            public void clicked(Player player, int slot, ClickType clickType) {
                if (clickType.isLeftClick()) {
                    if (queue == null || server == null) {
                        return;
                    }

                    if (server.getData().getState() != ServerState.OFFLINE) {
                        player.chat("/joinqueue " + queue.getName());
                        player.closeInventory();
                    }
                }

                if (clickType.isRightClick()) {
                    player.chat("/leavequeue " + queue.getName());
                }
            }
        });



        buttons.put(1, new Button() {
            final Queue queue = Queue.getByName("KitMap");
            final Server server = ServerHandler.getServer(queue.getName());

            @Override
            public String getName(Player player) {
                return ChatColor.YELLOW + "KitMap";
            }

            @Override
            public List<String> getDescription(Player player) {
                List<String> lore = new ArrayList<>();
                try {
                    if (queue == null || server == null || server.getData() == null || server.getData().getState() == null) {
                        lore.add(ChatColor.RED + "Server is offline.");
                    } else if (server.getData().getState() != ServerState.OFFLINE) {
                        lore.add(ChatColor.BLUE + "Players: " + queue.getServerData().getOnlinePlayers() + "/" + queue.getServerData().getMaximumPlayers());
                    }
                } catch (Exception e) {
                    lore.add(ChatColor.RED + "Server is offline.");
                }
                return lore;
            }

            @Override
            public Material getMaterial(Player player) {
                return Material.ENDER_PEARL;
            }

            @Override
            public void clicked(Player player, int slot, ClickType clickType) {
                if (clickType.isLeftClick()) {
                    if (queue == null || server == null) {
                        return;
                    }

                    if (server.getData().getState() != ServerState.OFFLINE) {
                        player.chat("/joinqueue " + queue.getName());
                        player.closeInventory();
                    }
                }

                if (clickType.isRightClick()) {
                    player.chat("/leavequeue " + queue.getName());
                }
            }
        });



        buttons.put(2, new Button() {
            final Queue queue = Queue.getByName("Practice");
            final Server server = ServerHandler.getServer(queue.getName());

            @Override
            public String getName(Player player) {
                return ChatColor.YELLOW + "Practice";
            }

            @Override
            public List<String> getDescription(Player player) {
                List<String> lore = new ArrayList<>();
                try {
                    if (queue == null || server == null || server.getData() == null || server.getData().getState() == null) {
                        lore.add(ChatColor.RED + "Server is offline.");
                    } else if (server.getData().getState() != ServerState.OFFLINE) {
                        lore.add(ChatColor.BLUE + "Players: " + queue.getServerData().getOnlinePlayers() + "/" + queue.getServerData().getMaximumPlayers());
                    }
                } catch (Exception e) {
                    lore.add(ChatColor.RED + "Server is offline.");
                }
                return lore;
            }

            @Override
            public Material getMaterial(Player player) {
                return Material.BOW;
            }

            @Override
            public void clicked(Player player, int slot, ClickType clickType) {
                if (clickType.isLeftClick()) {
                    if (queue == null || server == null) {
                        return;
                    }

                    if (server.getData().getState() != ServerState.OFFLINE) {
                        player.chat("/joinqueue " + queue.getName());
                        player.closeInventory();
                    }
                }

                if (clickType.isRightClick()) {
                    player.chat("/leavequeue " + queue.getName());
                }
            }
        });

        buttons.put(3, new Button() {
            final Queue queue = Queue.getByName("KitPvP");
            final Server server = ServerHandler.getServer(queue.getName());

            @Override
            public String getName(Player player) {
                return ChatColor.YELLOW + "KitPvP";
            }

            @Override
            public List<String> getDescription(Player player) {
                List<String> lore = new ArrayList<>();
                try {
                    if (queue == null || server == null || server.getData() == null || server.getData().getState() == null) {
                        lore.add(ChatColor.RED + "Server is offline.");
                    } else if (server.getData().getState() != ServerState.OFFLINE) {
                        lore.add(ChatColor.BLUE + "Players: " + queue.getServerData().getOnlinePlayers() + "/" + queue.getServerData().getMaximumPlayers());
                    }
                } catch (Exception e) {
                    lore.add(ChatColor.RED + "Server is offline.");
                }
                return lore;
            }

            @Override
            public Material getMaterial(Player player) {
                return Material.GOLDEN_APPLE;
            }

            @Override
            public void clicked(Player player, int slot, ClickType clickType) {
                if (clickType.isLeftClick()) {
                    if (queue == null || server == null) {
                        return;
                    }

                    if (server.getData().getState() != ServerState.OFFLINE) {
                        player.chat("/joinqueue " + queue.getName());
                        player.closeInventory();
                    }
                }

                if (clickType.isRightClick()) {
                    player.chat("/leavequeue " + queue.getName());
                }
            }
        });

        buttons.put(4, new Button() {
            final Queue queue = Queue.getByName("PrisonOP");
            final Server server = ServerHandler.getServer(queue.getName());

            @Override
            public String getName(Player player) {
                return ChatColor.YELLOW + "PrisonOP";
            }

            @Override
            public List<String> getDescription(Player player) {
                List<String> lore = new ArrayList<>();
                try {
                    if (queue == null || server == null || server.getData() == null || server.getData().getState() == null) {
                        lore.add(ChatColor.RED + "Server is offline.");
                    } else if (server.getData().getState() != ServerState.OFFLINE) {
                        lore.add(ChatColor.BLUE + "Players: " + queue.getServerData().getOnlinePlayers() + "/" + queue.getServerData().getMaximumPlayers());
                    }
                } catch (Exception e) {
                    lore.add(ChatColor.RED + "Server is offline.");
                }
                return lore;
            }

            @Override
            public Material getMaterial(Player player) {
                return Material.DIAMOND_PICKAXE;
            }

            @Override
            public void clicked(Player player, int slot, ClickType clickType) {
                if (clickType.isLeftClick()) {
                    if (queue == null || server == null) {
                        return;
                    }

                    if (server.getData().getState() != ServerState.OFFLINE) {
                        player.chat("/joinqueue " + queue.getName());
                        player.closeInventory();
                    }
                }

                if (clickType.isRightClick()) {
                    player.chat("/leavequeue " + queue.getName());
                }
            }
        });

        buttons.put(3, new Button() {
            final Queue queue = Queue.getByName("SkyWars");
            final Server server = ServerHandler.getServer(queue.getName());

            @Override
            public String getName(Player player) {
                return ChatColor.YELLOW + "SkyWars";
            }

            @Override
            public List<String> getDescription(Player player) {
                List<String> lore = new ArrayList<>();
                try {
                    if (queue == null || server == null || server.getData() == null || server.getData().getState() == null) {
                        lore.add(ChatColor.RED + "Server is offline.");
                    } else if (server.getData().getState() != ServerState.OFFLINE) {
                        lore.add(ChatColor.BLUE + "Players: " + queue.getServerData().getOnlinePlayers() + "/" + queue.getServerData().getMaximumPlayers());
                    }
                } catch (Exception e) {
                    lore.add(ChatColor.RED + "Server is offline.");
                }
                return lore;
            }

            @Override
            public Material getMaterial(Player player) {
                return Material.BOW;
            }

            @Override
            public void clicked(Player player, int slot, ClickType clickType) {
                if (clickType.isLeftClick()) {
                    if (queue == null || server == null) {
                        return;
                    }

                    if (server.getData().getState() != ServerState.OFFLINE) {
                        player.chat("/joinqueue " + queue.getName());
                        player.closeInventory();
                    }
                }

                if (clickType.isRightClick()) {
                    player.chat("/leavequeue " + queue.getName());
                }
            }
        });
        return buttons;
    }
}
