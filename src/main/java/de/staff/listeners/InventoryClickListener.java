package de.staff.listeners;

import de.staff.StaffService;
import net.wesjd.anvilgui.AnvilGUI;
import org.bukkit.*;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;

import java.util.UUID;

public class InventoryClickListener implements Listener {

    private StaffService plugin;

    public InventoryClickListener(StaffService plugin) {
        this.plugin = plugin;
    }

    @EventHandler
    public void handle(final InventoryClickEvent event) {
        if (event.getClickedInventory() == null) {
            return;
        }

        if (event.getClickedInventory().getName().startsWith(this.plugin.getPrefix())) {
            event.setCancelled(true);
        }

        Player player = (Player) event.getWhoClicked();

        if (event.getClickedInventory() != null) {
            if (event.getInventory().getName().equals(this.plugin.getGUIName("maingui"))) {
                if (!event.getCurrentItem().hasItemMeta() || event.getCurrentItem() == null) {
                    return;
                } else if (event.getCurrentItem().getItemMeta().getDisplayName().equals(this.plugin.getItemName("maingui", 1))) {
                    if (player.hasPermission(this.plugin.getItemPerm("maingui", 1))) {
                        this.plugin.getGameModeGUI().setCurrentPlayer(player);
                        this.plugin.getGameModeGUI().open(player);
                    } else {
                        player.sendMessage(this.plugin.getMessage("noperms"));
                    }
                } else if (event.getCurrentItem().getItemMeta().getDisplayName().equals(this.plugin.getItemName("maingui", 2))) {
                    if (player.hasPermission(this.plugin.getItemPerm("maingui", 2))) {
                        this.plugin.getMangePlayerGUI().open(player);
                    } else {
                        player.sendMessage(this.plugin.getMessage("noperms"));
                    }
                } else if (event.getCurrentItem().getItemMeta().getDisplayName().equals(this.plugin.getItemName("maingui", 3))) {
                    this.plugin.getAnvilGUI().text("Player name");
                    this.plugin.getAnvilGUI().onComplete(((Oplayer, txt) -> {
                        Player p = Bukkit.getPlayer(txt);
                        if (Bukkit.getOnlinePlayers().contains(p)) {
                            if (!Oplayer.getName().equals(p.getName())) {
                                Player bp = Bukkit.getPlayer(txt);
                                if (bp != null) {
                                    Oplayer.teleport(bp);
                                    player.playSound(player.getLocation(), Sound.LEVEL_UP, 1, 1);
                                } else {
                                    Oplayer.sendMessage(this.plugin.getPrefix() + this.plugin.getMessage("isoffline"));
                                }

                                return AnvilGUI.Response.close();
                            } else {
                                Oplayer.sendMessage(this.plugin.getPrefix() + this.plugin.getMessage("cannoteffect"));
                                return AnvilGUI.Response.close();
                            }


                        } else {
                            return AnvilGUI.Response.text(this.plugin.getMessage("playernotfoud"));
                        }
                    }));

                    this.plugin.getAnvilGUI().open(player);
                }
            }



            else if (event.getInventory().getName().equals(this.plugin.getGUIName("gamemodegui"))) {
                Player target = Bukkit.getPlayer(ChatColor.stripColor(event.getClickedInventory().getItem(4).getItemMeta().getDisplayName()));
                if(target != null){

                    if (!event.getCurrentItem().hasItemMeta() || event.getCurrentItem() == null) {
                        return;
                    } else if (event.getCurrentItem().getType() == Material.FEATHER) {
                        if (player.hasPermission("minecraft.command.gamemode")) {
                            target.setGameMode(GameMode.ADVENTURE);
                            player.closeInventory();
                            player.playSound(player.getLocation(), Sound.LEVEL_UP, 1, 1);
                            target.sendMessage(this.plugin.getMessage("changedgamemodein2"));
                        }
                    } else if (event.getCurrentItem().getType() == Material.DIAMOND_SWORD) {
                        if (player.hasPermission("minecraft.command.gamemode")) {
                            target.setGameMode(GameMode.SURVIVAL);
                            player.closeInventory();
                            player.playSound(player.getLocation(), Sound.LEVEL_UP, 1, 1);
                            target.sendMessage(this.plugin.getMessage("changedgamemodein0"));
                        }
                    } else if (event.getCurrentItem().getType() == Material.GRASS) {
                        if (player.hasPermission("minecraft.command.gamemode")) {
                            target.setGameMode(GameMode.CREATIVE);
                            player.closeInventory();
                            player.playSound(player.getLocation(), Sound.LEVEL_UP, 1, 1);
                            target.sendMessage(this.plugin.getMessage("changedgamemodein1"));
                        }
                    } else if (event.getCurrentItem().getType() == Material.EYE_OF_ENDER) {
                        if (player.hasPermission("minecraft.command.gamemode")) {
                            target.setGameMode(GameMode.SPECTATOR);
                            player.closeInventory();
                            player.playSound(player.getLocation(), Sound.LEVEL_UP, 1, 1);
                            target.sendMessage(this.plugin.getMessage("changedgamemodein3"));
                        }
                    } else if (event.getCurrentItem().getType() == Material.SKULL_ITEM) {
                        if (ChatColor.stripColor(event.getCurrentItem().getItemMeta().getDisplayName()).equals("Back")) {
                            this.plugin.getMainGUI().open(player);
                            player.playSound(player.getLocation(), Sound.LAVA_POP, 1, 1);
                        }
                    } else if (event.getCurrentItem().getType() == Material.SKULL_ITEM) {
                        if (ChatColor.stripColor(event.getCurrentItem().getItemMeta().getDisplayName()).equals("Back")) {
                            this.plugin.getMainGUI().open(player);
                            player.playSound(player.getLocation(), Sound.LAVA_POP, 1, 1);
                        }
                    }
                } else {
                    player.sendMessage(this.plugin.getMessage("isoffline"));
                }



            }else  if (event.getInventory().getName().equals(this.plugin.getGUIName("bankickgui"))){
                if(event.getCurrentItem().getType() == Material.GRASS) {
                    Player target = Bukkit.getPlayer(ChatColor.stripColor(event.getClickedInventory().getItem(4).getItemMeta().getDisplayName()));
                    if (target != null) {
                        this.plugin.getGameModeGUI().setCurrentPlayer(target);
                        this.plugin.getGameModeGUI().open(player);
                    } else {
                        player.sendMessage(this.plugin.getMessage("isoffline"));
                    }
                }else if(event.getCurrentItem().getType() == Material.REDSTONE_TORCH_ON) {
                    Player target = Bukkit.getPlayer(ChatColor.stripColor(event.getClickedInventory().getItem(4).getItemMeta().getDisplayName()));
                    this.plugin.getAnvilGUI().text("Reason");
                    this.plugin.getAnvilGUI().onComplete(((Oplayer, txt) -> {
                        if (target != null) {
                            player.chat(this.plugin.getGUICommand("bankickgui", "ban").replace("%PLAYER%", target.getName()).replace("%REASON%", txt));
                        } else {
                            Oplayer.sendMessage(this.plugin.getPrefix() + this.plugin.getMessage("isoffline"));
                        }

                        return AnvilGUI.Response.close();
                    }));
                    this.plugin.getAnvilGUI().open(player);
                }else if(event.getCurrentItem().getType() == Material.STONE_SWORD) {
                    Player target = Bukkit.getPlayer(ChatColor.stripColor(event.getClickedInventory().getItem(4).getItemMeta().getDisplayName()));
                    this.plugin.getAnvilGUI().text("Reason");
                    this.plugin.getAnvilGUI().onComplete(((Oplayer, txt) -> {
                        if (target != null) {
                            player.chat(this.plugin.getGUICommand("bankickgui", "kick").replace("%PLAYER%", target.getName()).replace("%REASON%", txt));
                        } else {
                            Oplayer.sendMessage(this.plugin.getPrefix() + this.plugin.getMessage("isoffline"));
                        }

                        return AnvilGUI.Response.close();
                    }));
                    this.plugin.getAnvilGUI().open(player);
                }
            }



            else if (event.getInventory().getName().equals(this.plugin.getGUIName("manageplayergui"))) {
                if (event.getCurrentItem().getType() == Material.SKULL_ITEM &&
                        ChatColor.stripColor(event.getCurrentItem().getItemMeta().getDisplayName()).equals("Back")) {
                    this.plugin.getMainGUI().open(player);
                    player.playSound(player.getLocation(), Sound.LAVA_POP, 1, 1);
                } else if (event.getCurrentItem().getType() == Material.SKULL_ITEM &&
                        !(ChatColor.stripColor(event.getCurrentItem().getItemMeta().getDisplayName()).equals("Back"))) {
                    Player gplayer = Bukkit.getPlayer(ChatColor.stripColor(event.getCurrentItem().getItemMeta().getDisplayName()));
                    if (gplayer != null) {
                        openPlayerMenu(player, gplayer);
                    } else {
                        player.sendMessage(this.plugin.getMessage("isoffline"));
                    }

                }


                else if (event.getCurrentItem().getType() == Material.NAME_TAG) {
                    this.plugin.getAnvilGUI().text("Player name");
                    this.plugin.getAnvilGUI().onComplete(((Oplayer, txt) -> {
                        Player p = Bukkit.getPlayer(txt);
                        if (Bukkit.getOnlinePlayers().contains(p)) {
                            if (!Oplayer.getName().equals(p.getName())) {
                                Player bp = Bukkit.getPlayer(txt);
                                if (bp != null) {
                                    openPlayerMenu(Oplayer, bp);
                                    Oplayer.sendMessage(txt);
                                } else {
                                    Oplayer.sendMessage(this.plugin.getPrefix() + this.plugin.getMessage("isoffline"));
                                }

                                return AnvilGUI.Response.close();
                            } else {
                                Oplayer.sendMessage(this.plugin.getPrefix() + this.plugin.getMessage("cannoteffect"));
                                return AnvilGUI.Response.close();
                            }


                        } else {
                            return AnvilGUI.Response.text(this.plugin.getMessage("playernotfoud"));
                        }
                    }));
                    this.plugin.getAnvilGUI().open(player);
                }

            }
        }
    }


    public void openPlayerMenu(Player player, Player Oplayer) {
        this.plugin.getBkGUI().setOpenedPlayer(Oplayer);
        this.plugin.getBkGUI().open(player);
    }


}

