package com.songro.whiteforest.inventory;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

public class Pailon {

    public Inventory pailonGUI() {
        Inventory inv = Bukkit.createInventory(null, 9, ChatColor.BOLD + "파일런");
        inv.setItem(1, reliveTeam());
        inv.setItem(4, expShop());

        return inv;
    }

    private ItemStack reliveTeam() {
        ItemStack relive = new ItemStack(Material.TOTEM_OF_UNDYING, 1);
        ItemMeta reliveMeta = relive.getItemMeta();
        reliveMeta.setDisplayName(ChatColor.GREEN + "팀원 소환");
        relive.setItemMeta(reliveMeta);

        return relive;
    }

    private ItemStack expShop() {
        ItemStack expStore = new ItemStack(Material.EXPERIENCE_BOTTLE, 1);
        ItemMeta expStoreItemMeta = expStore.getItemMeta();
        expStoreItemMeta.setDisplayName(ChatColor.YELLOW + "경험치 상점");
        expStore.setItemMeta(expStoreItemMeta);

        return expStore;
    }

}
