package de.staff.provider;

import de.staff.StaffService;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

public class VisibiltyProvider {

    private StaffService plugin;

    public VisibiltyProvider(StaffService plugin) {
        this.plugin = plugin;
    }

    public void setInvisible(Player target) {
        Bukkit.getOnlinePlayers().forEach(players -> {
            players.hidePlayer(target);
            target.showPlayer(players);
        });
    }

    public void setInvisibleCustom(Player target, Player customGetter) {
        customGetter.hidePlayer(target);
        target.showPlayer(customGetter);

    }

    public void setVisible(Player target) {
        Bukkit.getOnlinePlayers().forEach(players -> {
            players.showPlayer(target);
            target.showPlayer(players);
        });
    }
    public void setOtherStaffsVisible(Player target) {
        if(this.plugin.getStaffs().size() <= 2) {
            this.plugin.getStaffs().forEach(staffs -> {
                staffs.showPlayer(target);
                target.showPlayer(staffs);
            });
        }
    }
}
