package com.songro.whiteforest.inventory;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.ArrayList;

public class Enchant {

    public Inventory enchantGUI(Player p) {
        Inventory inv = Bukkit.createInventory(null, 27, ChatColor.BOLD + "마법부여");
        for(int i=0; i<=10; i++) {
            inv.setItem(i, nullItem());
        }

        inv.setItem(12, nullItem());
        inv.setItem(13, nullItem());
        inv.setItem(14, nullItem());
        inv.setItem(15, enchantItem());

        for(int i3=16; i3<=19; i3++) {
            inv.setItem(i3, nullItem());
        }

        inv.setItem(20, removeEnchant());

        for(int i2=21; i2<=26; i2++) {
            inv.setItem(i2, nullItem());
        }

        return inv;
    }

    private ItemStack nullItem() {
        ItemStack nullItemFromGUI = new ItemStack(Material.GRAY_STAINED_GLASS_PANE);
        ItemMeta nullItemFromGUIMeta = nullItemFromGUI.getItemMeta();
        nullItemFromGUIMeta.setDisplayName(ChatColor.BLACK + "[]");
        nullItemFromGUI.setItemMeta(nullItemFromGUIMeta);

        return nullItemFromGUI;
    }

    private ItemStack enchantItem() {
        ItemStack enchant = new ItemStack(Material.ENCHANTED_BOOK);
        ItemMeta enchantMeta = enchant.getItemMeta();
        enchantMeta.setDisplayName(ChatColor.LIGHT_PURPLE + "[ 인첸트 ]");
        ArrayList<String> item2Lore = new ArrayList<>();
        item2Lore.add(ChatColor.GRAY + "[ 인첸트 시 : 청금석 1개 감소 ]");
        enchantMeta.setLore(item2Lore);

        enchant.setItemMeta(enchantMeta);
        return enchant;
    }

    private ItemStack removeEnchant() {
        ItemStack removeEnchantItem = new ItemStack(Material.BARRIER);
        ItemMeta nullItemFromGUIMeta = removeEnchantItem.getItemMeta();
        nullItemFromGUIMeta.setDisplayName(ChatColor.RED + "[ 인첸트 제거 ]");
        removeEnchantItem.setItemMeta(nullItemFromGUIMeta);

        return removeEnchantItem;
    }

}
