package de.staff.utils;

import org.bukkit.Color;
import org.bukkit.DyeColor;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.inventory.meta.LeatherArmorMeta;

import java.util.Arrays;

public class ItemStackBuilder {

    private ItemStack itemStack;
    private ItemMeta itemMeta;

    public ItemStackBuilder(Material material) {
        itemStack = new ItemStack(material, 1, (short) 0);
        itemMeta = itemStack.getItemMeta();
    }
    public ItemStackBuilder setDisplayName(String displayName) {
        itemMeta.setDisplayName(displayName);
        return this;
    }
    public ItemStackBuilder setAmount(int amount) {
        itemStack.setAmount(amount);
        return this;
    }
    public ItemStackBuilder setLore(String[] lore) {
        itemMeta.setLore(Arrays.asList(lore));
        return this;
    }
    public ItemStackBuilder setEnchantment(Enchantment enchantment, int level) {
        itemMeta.addEnchant(enchantment, level, true);
        return this;
    }
    public ItemStackBuilder setSubId(int subId) {
        itemStack.setDurability((short)subId);
        return this;
    }
    public ItemStackBuilder setDyeColor(DyeColor dyeColor) {
        itemStack.setDurability(dyeColor.getData());
        return this;
    }
    public ItemStackBuilder setArmorColor(Color color) {
        ((LeatherArmorMeta) itemMeta).setColor(color);
        return this;
    }
    public ItemStackBuilder setItemFlags(ItemFlag itemFlags) {
        itemMeta.addItemFlags(itemFlags);
        return this;
    }
    public ItemStackBuilder setUnbreakable(boolean unbreakable) {
        itemMeta.spigot().setUnbreakable(unbreakable);
        return this;
    }
    public ItemStack build() {
        itemStack.setItemMeta(itemMeta);
        return itemStack;
    }
}
