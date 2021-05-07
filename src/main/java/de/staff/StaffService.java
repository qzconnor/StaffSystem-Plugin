package de.staff;

import de.staff.commands.StaffCommand;
import de.staff.commands.tab.StaffTab;
import de.staff.guis.*;
import de.staff.listeners.InventoryClickListener;
import de.staff.listeners.PlayerConnectionListener;
import de.staff.provider.VisibiltyProvider;
import de.staff.utils.ItemStackBuilder;
import lombok.Getter;
import lombok.Setter;
import net.wesjd.anvilgui.AnvilGUI;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

import javax.xml.ws.FaultAction;
import java.util.*;

public class StaffService extends JavaPlugin {

    private StaffService plugin;

    private @Getter ArrayList<Player> staffs;

    private @Getter MainGUI mainGUI;
    private @Getter GameModeGUI gameModeGUI;
    private @Getter ManagePlayerGUI mangePlayerGUI;
    private @Getter AnvilGUI.Builder anvilGUI;
    private @Getter BanKickGUI bkGUI;

    private @Getter VisibiltyProvider visibiltyProvider;

    @Override
    public void onEnable() {
        plugin = this;

        staffs = new ArrayList<>();

        anvilGUI = new AnvilGUI.Builder();

        bkGUI = new BanKickGUI(getGUIName("bankickgui"), 9 * 3, null, null, this);

        anvilGUI.title("§aSearch Player").plugin(this);


        visibiltyProvider = new VisibiltyProvider(this);

        mainGUI = new MainGUI(getGUIName("maingui"), 45, null, null, this);
        gameModeGUI = new GameModeGUI(getGUIName("gamemodegui"), 9 * 3, null, null, this);
        mangePlayerGUI = new ManagePlayerGUI(getGUIName("manageplayergui"), 45, null, null, this);


        PluginManager pm = Bukkit.getPluginManager();
        pm.registerEvents(new PlayerConnectionListener(this), this);
        pm.registerEvents(new InventoryClickListener(this), this);

        getCommand("staff").setExecutor(new StaffCommand(this));
        getCommand("staff").setTabCompleter(new StaffTab(this));
        Bukkit.getConsoleSender().sendMessage("§aStaff started.");
        super.onEnable();

    }

    @Override
    public void onDisable() {
        super.onDisable();
    }

    @Override
    public void onLoad() {
        saveDefaultConfig();
        getConfig().options().copyDefaults(true);
        super.onLoad();
    }

    public StaffService getPlugin() {
        return plugin;
    }

    public String getPrefix() {
        return getConfig().contains("prefix") ? getConfig().getString("prefix").replace("&", "§") : "§c§lStaff §7× ";
    }

    public String getPerm(String perm) {
        return getConfig().getString("permissions." + perm.toLowerCase() + ".perm");
    }

    public String getMessage(String msg) {
        return getPrefix() + ChatColor.translateAlternateColorCodes('&', getConfig().getString("messages." + msg.toLowerCase()));
    }

    public String getGUIName(String gui) {
        return getPrefix() + ChatColor.translateAlternateColorCodes('&', getConfig().getString("guis." + gui.toLowerCase() + ".name"));
    }

    public String getGUICommand(String gui, String which) {
        return ChatColor.translateAlternateColorCodes('&', getConfig().getString("guis." + gui.toLowerCase() + ".commands." + which));
    }

    public String getItemName(String gui, int index) {
        return ChatColor.translateAlternateColorCodes('&', getConfig().getString("guis." + gui.toLowerCase() + ".items." + index));
    }

    public String getItemPerm(String gui, int path) {
        return getConfig().getString("guis." + gui + ".perms." + path);
    }

    public String getPermGUI(String perm) {
        return getConfig().getString("guis." + perm.toLowerCase() + ".perm");
    }


}