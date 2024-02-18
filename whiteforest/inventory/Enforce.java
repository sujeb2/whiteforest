package com.songro.whiteforest.inventory;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.ArrayList;

public class Enforce {

    public Inventory enforceGUI(Player p) {
        Inventory inv = Bukkit.createInventory(null, 27, ChatColor.BOLD + "강화");
        for(int i=0; i<=10; i++) {
            inv.setItem(i, nullItem());
        }

        inv.setItem(12, nullItem());
        inv.setItem(13, nullItem());
        inv.setItem(14, nullItem());
        inv.setItem(15, enchantItem());

        for(int i2=16; i2<=26; i2++) {
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
        ItemStack enchant = new ItemStack(Material.BOOK);
        ItemMeta enchantMeta = enchant.getItemMeta();
        enchantMeta.setDisplayName(ChatColor.GOLD + "[ 강화 ]");
        ArrayList<String> item2Lore = new ArrayList<>();
        item2Lore.add(ChatColor.GREEN + "성공 확률: ");
        item2Lore.add(ChatColor.RED + "실패 확률: ");
        item2Lore.add(ChatColor.DARK_RED + "파괴 확률: ");
        item2Lore.add("");
        item2Lore.add(ChatColor.GOLD + "사용 강화석: ");
        enchantMeta.setLore(item2Lore);

        enchant.setItemMeta(enchantMeta);
        return enchant;
    }

}
