package de.staff.guis;

import de.staff.listeners.InventoryClickListener;
import lombok.Getter;
import lombok.Setter;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryType;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.InventoryHolder;

import javax.annotation.Nullable;

public abstract class GUI {
    private @Getter @Setter String name;
    private @Getter @Setter int slots;
    private @Getter @Setter InventoryHolder owner;
    private @Getter @Setter InventoryType type;

    private @Getter Inventory inventory;
    public GUI(String name, int slots, @Nullable InventoryHolder owner,@Nullable InventoryType type) {
        this.name = name;
        this.slots = slots;
        this.owner = owner;
        this.type = type;

        inventory = createInv();

    }

    private Inventory createInv(){
        if(type != null && type == InventoryType.ENDER_CHEST || type == InventoryType.CHEST){
            return Bukkit.createInventory(owner, type, name);
        }else {
            return Bukkit.createInventory(owner, slots, name);
        }


    }

    public abstract void fill(Inventory inventory, Player player);


    public void open(Player player){
        player.openInventory(inventory);
        fill(inventory, player);
    }

}
