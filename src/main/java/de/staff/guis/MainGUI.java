package de.staff.guis;

import de.staff.StaffService;
import de.staff.utils.ChestBorder;
import de.staff.utils.ItemStackBuilder;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.InventoryType;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.InventoryHolder;

public class MainGUI extends GUI {

    private StaffService plugin;

    public MainGUI(String name, int slots, InventoryHolder owner, InventoryType type, StaffService plugin) {
        super(name, slots, owner, type);
        this.plugin = plugin;
    }

    @Override
    public void fill(Inventory inventory, Player player) {
        ChestBorder cb = new ChestBorder(inventory);

        cb.fillSidesWithItem(new ItemStackBuilder(Material.STAINED_GLASS_PANE).setSubId(7).setDisplayName(" ").build());

        if(player.hasPermission(this.plugin.getItemPerm("maingui", 1))){
            inventory.setItem(20 , new ItemStackBuilder(Material.GRASS).setDisplayName(this.plugin.getItemName("maingui", 1)).build());
        }else{
            inventory.setItem(20 , new ItemStackBuilder(Material.BARRIER).setDisplayName(this.plugin.getMessage("noperms")).build());
        }
        if(player.hasPermission(this.plugin.getItemPerm("maingui", 2))){
            inventory.setItem(22 , new ItemStackBuilder(Material.DIAMOND_SWORD).setDisplayName(this.plugin.getItemName("maingui", 2)).build());
        }else{
            inventory.setItem(22 , new ItemStackBuilder(Material.BARRIER).setDisplayName(this.plugin.getMessage("noperms")).build());
        }
        if(player.hasPermission(this.plugin.getItemPerm("maingui", 1))) {
            inventory.setItem(24, new ItemStackBuilder(Material.EYE_OF_ENDER).setDisplayName(this.plugin.getItemName("maingui", 3)).build());
        }else{
            inventory.setItem(24 , new ItemStackBuilder(Material.BARRIER).setDisplayName(this.plugin.getMessage("noperms")).build());
        }


    }
}
