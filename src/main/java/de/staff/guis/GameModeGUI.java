package de.staff.guis;

import de.staff.StaffService;
import de.staff.utils.ChestBorder;
import de.staff.utils.ItemSkulls;
import de.staff.utils.ItemStackBuilder;
import lombok.Getter;
import lombok.Setter;
import org.bukkit.Material;
import org.bukkit.SkullType;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.InventoryType;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.InventoryHolder;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.SkullMeta;

public class GameModeGUI extends GUI {

    private StaffService plugin;


    private Player currentPlayer;

    public GameModeGUI(String name, int slots, InventoryHolder owner, InventoryType type, StaffService plugin) {
        super(name, slots, owner, type);
        this.plugin = plugin;
    }

    @Override
    public void fill(Inventory inventory, Player player) {
        ChestBorder cb = new ChestBorder(inventory);
        cb.fillSidesWithItem(new ItemStackBuilder(Material.STAINED_GLASS_PANE).setSubId(7).setDisplayName(" ").build());

        ItemStack skull = new ItemStack(Material.SKULL_ITEM, 1, (short) SkullType.PLAYER.ordinal());
        SkullMeta meta = (SkullMeta) skull.getItemMeta();
        meta.setOwner(currentPlayer.getName());
        meta.setDisplayName("§a" + currentPlayer.getName());
        skull.setItemMeta(meta);
        inventory.setItem(4, skull);

        inventory.setItem(0, ItemSkulls.getSkullTextureItem("8652e2b936ca8026bd28651d7c9f2819d2e923697734d18dfdb13550f8fdad5f", "§cBack"));



        inventory.setItem(10, new ItemStackBuilder(Material.FEATHER).setAmount(1).setDisplayName("§7Adventure").build());
        inventory.setItem(12, new ItemStackBuilder(Material.DIAMOND_SWORD).setAmount(1).setDisplayName("§7Survival").build());
        inventory.setItem(14, new ItemStackBuilder(Material.GRASS).setAmount(1).setDisplayName("§7Creative").build());
        inventory.setItem(16, new ItemStackBuilder(Material.EYE_OF_ENDER).setAmount(1).setDisplayName("§7Spectator").build());

    }

    public Player getCurrentPlayer() {
        return currentPlayer;
    }

    public void setCurrentPlayer(Player currentPlayer) {
        this.currentPlayer = currentPlayer;
    }
}
