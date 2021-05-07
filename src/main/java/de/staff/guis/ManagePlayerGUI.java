package de.staff.guis;

import de.staff.StaffService;
import de.staff.listeners.InventoryClickListener;
import de.staff.utils.ChestBorder;
import de.staff.utils.ItemSkulls;
import de.staff.utils.ItemStackBuilder;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.SkullType;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.InventoryType;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.InventoryHolder;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.inventory.meta.SkullMeta;

import javax.annotation.Nullable;

public class ManagePlayerGUI extends GUI {

    private StaffService plugin;

    public ManagePlayerGUI(String name, int slots, @Nullable InventoryHolder owner, @Nullable InventoryType type, StaffService plugin) {
        super(name, slots, owner, type);
        this.plugin = plugin;
    }

    @Override
    public void fill(Inventory inventory, Player player) {
        inventory.clear();
        ChestBorder cb = new ChestBorder(inventory);
        cb.fillSidesWithItem(new ItemStackBuilder(Material.STAINED_GLASS_PANE).setSubId(7).setDisplayName(" ").build());

        inventory.setItem(0, ItemSkulls.getSkullTextureItem("8652e2b936ca8026bd28651d7c9f2819d2e923697734d18dfdb13550f8fdad5f", "§cBack"));

        int i = 0;
        for(Player op : Bukkit.getOnlinePlayers()){
            if(op != player){
                if(i <= cb.getNonSideSlots().size()){
                    setPlayerHead(cb.getNonSideSlots().get(i), op, inventory);
                    player.updateInventory();
                    i++;
                }
            }

        }


        inventory.setItem(36, new ItemStackBuilder(Material.NAME_TAG).setAmount(1).setDisplayName("Search").build());
        player.updateInventory();
    }

    public void setPlayerHead(int slot, Player player, Inventory inventory) {
        ItemStack skull = new ItemStack(Material.SKULL_ITEM, 1, (short) SkullType.PLAYER.ordinal());
        SkullMeta meta = (SkullMeta) skull.getItemMeta();
        meta.setOwner(player.getName());
        meta.setDisplayName("§a" + player.getName());
        skull.setItemMeta(meta);
        inventory.setItem(slot, skull);
    }

}
