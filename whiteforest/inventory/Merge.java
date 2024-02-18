package com.songro.whiteforest.inventory;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.inventory.meta.SkullMeta;

import java.util.ArrayList;

public class Merge {

    public Inventory mergeGUI() {
        Inventory inv = Bukkit.createInventory(null, 9, ChatColor.BOLD + "팀 병합");
        inv.setItem(1, expShopItem1());
        inv.setItem(3, expShopItem2());
        inv.setItem(5, expShopItem3());
        inv.setItem(7, expShopItem4());

        return inv;
    }

    private ItemStack expShopItem1() {
        ItemStack item1 = new ItemStack(Material.LEGACY_SKULL_ITEM, 1, (short) 3);
        SkullMeta im = (SkullMeta) item1.getItemMeta();
        im.setOwner("_Devil_Stars_");
        im.setDisplayName(ChatColor.YELLOW + "평화팀");

        return item1;
    }

    private ItemStack expShopItem2() {
        ItemStack item2 = new ItemStack(Material.LEGACY_SKULL_ITEM, 1, (short) 3);
        SkullMeta im = (SkullMeta) item2.getItemMeta();
        im.setOwner("two6ean");
        im.setDisplayName(ChatColor.GREEN + "소다맛 완두콩팀");

        return item2;
    }

    private ItemStack expShopItem3() {
        ItemStack item4 = new ItemStack(Material.LEGACY_SKULL_ITEM, 1, (short) 3);
        SkullMeta im = (SkullMeta) item4.getItemMeta();
        im.setOwner("Jun09743");
        im.setDisplayName(ChatColor.LIGHT_PURPLE + "히틀니팀");

        return item4;
    }

    private ItemStack expShopItem4() {
        ItemStack item3 = new ItemStack(Material.LEGACY_SKULL_ITEM, 1, (short) 3);
        SkullMeta im = (SkullMeta) item3.getItemMeta();
        im.setOwner("Star_Sara");
        im.setDisplayName(ChatColor.GOLD + "고소미팀");

        return item3;
    }

}
