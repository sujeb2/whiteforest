package com.songro.whiteforest.inventory;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.ArrayList;

public class ExpStore {

    public Inventory expStoreGUI() {
        Inventory inv = Bukkit.createInventory(null, 9, ChatColor.BOLD + "레벨 상점");
        inv.setItem(1, expShopItem1());
        inv.setItem(3, expShopItem2());
        inv.setItem(5, expShopItem3());
        inv.setItem(7, expShopItem4());

        return inv;
    }

    private ItemStack expShopItem1() {
        ItemStack item1 = new ItemStack(Material.EXPERIENCE_BOTTLE, 1);
        ItemMeta item1ItemMeta = item1.getItemMeta();
        item1ItemMeta.setDisplayName(ChatColor.YELLOW + "경험치 병");
        ArrayList<String> item1Lore = new ArrayList<>();
        item1Lore.add(ChatColor.GRAY + "[ 구매 시 : 레벨 2 감소 ]");
        item1ItemMeta.setLore(item1Lore);
        item1.setItemMeta(item1ItemMeta);

        return item1;
    }

    private ItemStack expShopItem2() {
        ItemStack item2 = new ItemStack(Material.TOTEM_OF_UNDYING, 1);
        ItemMeta item2ItemMeta = item2.getItemMeta();
        item2ItemMeta.setDisplayName(ChatColor.GOLD + "불사의 토템");
        ArrayList<String> item2Lore = new ArrayList<>();
        item2Lore.add(ChatColor.GRAY + "[ 구매 시 : 레벨 40 감소 ]");
        item2ItemMeta.setLore(item2Lore);
        item2.setItemMeta(item2ItemMeta);

        return item2;
    }

    private ItemStack expShopItem3() {
        ItemStack item4 = new ItemStack(Material.BEACON, 1);
        ItemMeta item4ItemMeta = item4.getItemMeta();
        item4ItemMeta.setDisplayName(ChatColor.AQUA + "파일런");
        ArrayList<String> item4Lore = new ArrayList<>();
        item4Lore.add(ChatColor.GRAY + "[ 구매 시 : 레벨 300 감소 ]");
        item4ItemMeta.setLore(item4Lore);
        item4.setItemMeta(item4ItemMeta);

        return item4;
    }

    private ItemStack expShopItem4() {
        ItemStack item3 = new ItemStack(Material.NETHER_STAR, 1);
        ItemMeta item3ItemMeta = item3.getItemMeta();
        item3ItemMeta.setDisplayName(ChatColor.BLUE + "강화석" + ChatColor.YELLOW + " (x5)");
        ArrayList<String> item3Lore = new ArrayList<>();
        item3Lore.add(ChatColor.GRAY + "[ 구매 시 : 레벨 7 감소 ]");
        item3ItemMeta.setLore(item3Lore);
        item3.setItemMeta(item3ItemMeta);

        return item3;
    }

}
