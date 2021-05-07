package de.staff.listeners;

import de.staff.StaffService;
import de.staff.guis.ManagePlayerGUI;
import org.bukkit.Bukkit;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerKickEvent;
import org.bukkit.event.player.PlayerQuitEvent;

public class PlayerConnectionListener implements Listener {

    private StaffService plugin;

    public PlayerConnectionListener(StaffService plugin) {
        this.plugin = plugin;
    }

    @EventHandler
    public void handle(final PlayerJoinEvent event) {
        if(this.plugin.getStaffs().size() != 0) {
            this.plugin.getStaffs().forEach(staffs -> {
                this.plugin.getVisibiltyProvider().setInvisibleCustom(staffs, event.getPlayer());
            });
        }
    }

    @EventHandler
    public void handle(final PlayerQuitEvent event) {
        if(this.plugin.getStaffs().contains(event.getPlayer())) {
            this.plugin.getStaffs().remove(event.getPlayer());
        }
    }
    @EventHandler
    public void handle(final PlayerKickEvent event) {
        if(this.plugin.getStaffs().contains(event.getPlayer())) {
            this.plugin.getStaffs().remove(event.getPlayer());
        }
    }
}
