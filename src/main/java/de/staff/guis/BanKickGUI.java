package de.staff.guis;

import de.staff.StaffService;
import de.staff.utils.ChestBorder;
import de.staff.utils.ItemStackBuilder;
import org.bukkit.Material;
import org.bukkit.SkullType;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.InventoryType;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.InventoryHolder;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.SkullMeta;

import javax.annotation.Nullable;

public class BanKickGUI extends GUI{

    private StaffService plugin;

    public BanKickGUI(String name, int slots, @Nullable InventoryHolder owner, @Nullable InventoryType type, StaffService plugin) {
        super(name, slots, owner, type);
        this.plugin = plugin;
    }

    private Player openedPlayer;

    @Override
    public void fill(Inventory inventory, Player player) {
        inventory.clear();
        ChestBorder cb = new ChestBorder(inventory);
        cb.fillSidesWithItem(new ItemStackBuilder(Material.STAINED_GLASS_PANE).setSubId(7).setDisplayName(" ").build());

        ItemStack skull = new ItemStack(Material.SKULL_ITEM, 1, (short) SkullType.PLAYER.ordinal());
        SkullMeta meta = (SkullMeta) skull.getItemMeta();
        meta.setOwner(getOpenedPlayer().getName());
        meta.setDisplayName("§a" + getOpenedPlayer().getName());
        skull.setItemMeta(meta);
        inventory.setItem(4, skull);

        inventory.setItem(11, new ItemStackBuilder(Material.GRASS).setDisplayName("§7Set Gamemode").build());
        inventory.setItem(13, new ItemStackBuilder(Material.REDSTONE_TORCH_ON).setDisplayName("§7Ban Player").build());
        inventory.setItem(15, new ItemStackBuilder(Material.STONE_SWORD).setDisplayName("§7Kick Player").build());

    }
    public Player getOpenedPlayer() {
        return openedPlayer;
    }

    public void setOpenedPlayer(Player openedPlayer) {
        this.openedPlayer = openedPlayer;
    }
}
