package de.staff.commands;

import de.staff.StaffService;
import org.bukkit.Bukkit;
import org.bukkit.Sound;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class StaffCommand implements CommandExecutor {

    private StaffService plugin;

    public StaffCommand(StaffService plugin) {
        this.plugin = plugin;
    }

    @Override
    public boolean onCommand(CommandSender commandSender, Command command, String label, String[] args) {

        if (commandSender instanceof Player) {
            final Player player = (Player) commandSender;
            if (args.length == 0) {
                if (player.hasPermission(this.plugin.getPerm("command"))) {
                    if (!this.plugin.getStaffs().contains(player)) {
                        this.plugin.getStaffs().add(player);
                        this.plugin.getVisibiltyProvider().setInvisible(player);
                        this.plugin.getVisibiltyProvider().setOtherStaffsVisible(player);
                        player.sendMessage(this.plugin.getMessage("staffjoined"));
                        player.playSound(player.getLocation(), Sound.LEVEL_UP, 1, 1);
                    } else {
                        this.plugin.getStaffs().remove(player);
                        Bukkit.getOnlinePlayers().forEach(player1 -> {
                           player1.showPlayer(player);
                           player.showPlayer(player1);
                        });
                        player.sendMessage(this.plugin.getMessage("staffleaved"));
                        player.playSound(player.getLocation(), Sound.LEVEL_UP, 1, 1);
                    }
                } else {
                    player.sendMessage(this.plugin.getMessage("noperms"));
                }
            } else if (args.length == 1) {
                if (args[0].equalsIgnoreCase("gui")) {
                    if (player.hasPermission(this.plugin.getPerm("command"))) {
                        if (this.plugin.getStaffs().contains(player)) {
                            this.plugin.getMainGUI().open(player);
                            player.playSound(player.getLocation(), Sound.LEVEL_UP, 1, 1);
                        } else {
                            player.sendMessage(this.plugin.getMessage("notinstaffmode"));

                        }
                    } else {
                        player.sendMessage(this.plugin.getMessage("noperms"));
                    }
                } else if (args[0].equalsIgnoreCase("reload")) {
                    if (player.hasPermission(this.plugin.getPerm("admin"))) {
                        this.plugin.reloadConfig();
                        player.sendMessage(this.plugin.getPrefix() + "§7You reloaded the Config!");
                    }
                }
            }
        }
        return false;
    }
}
