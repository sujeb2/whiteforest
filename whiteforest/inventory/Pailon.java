package com.songro.whiteforest.inventory;

import com.songro.whiteforest.Whiteforest;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

public class Pailon {

    public Inventory pailonGUI(Player p) {
        Inventory inv = Bukkit.createInventory(null, 9, ChatColor.BOLD + "파일런");
        inv.setItem(0, spawnTeam());
        inv.setItem(2, reliveTeam());
        inv.setItem(4, expShop());
        inv.setItem(6, tpAllTeam());
        inv.setItem(8, spawnPailon(p));

        return inv;
    }

    private ItemStack spawnTeam() {
        ItemStack relive = new ItemStack(Material.DIAMOND, 1);
        ItemMeta reliveMeta = relive.getItemMeta();
        reliveMeta.setDisplayName(ChatColor.GREEN + "팀원 소환");
        relive.setItemMeta(reliveMeta);

        return relive;
    }

    private ItemStack reliveTeam() {
        ItemStack relive = new ItemStack(Material.TOTEM_OF_UNDYING, 1);
        ItemMeta reliveMeta = relive.getItemMeta();
        reliveMeta.setDisplayName(ChatColor.DARK_GREEN + "팀원 부활");
        relive.setItemMeta(reliveMeta);

        return relive;
    }

    private ItemStack expShop() {
        ItemStack expStore = new ItemStack(Material.EXPERIENCE_BOTTLE, 1);
        ItemMeta expStoreItemMeta = expStore.getItemMeta();
        expStoreItemMeta.setDisplayName(ChatColor.YELLOW + "레벨 상점");
        expStore.setItemMeta(expStoreItemMeta);

        return expStore;
    }

    private ItemStack tpAllTeam() {
        ItemStack tpAllteam1 = new ItemStack(Material.COMPASS, 1);
        ItemMeta tpAllMeta = tpAllteam1.getItemMeta();
        tpAllMeta.setDisplayName(ChatColor.LIGHT_PURPLE + "팀원 불러오기");
        tpAllteam1.setItemMeta(tpAllMeta);

        return tpAllteam1;
    }

    private ItemStack spawnPailon(Player p) {
        ItemStack spawnPailonItem = new ItemStack(Material.PURPLE_GLAZED_TERRACOTTA, 1);
        ItemMeta tpAllMeta = spawnPailonItem.getItemMeta();
        if(Whiteforest.plugin.getData().getBoolean(p.getName() + ".ableToSpawnPailon")) {
            tpAllMeta.setDisplayName(ChatColor.GREEN + "[ 파일런에서 스폰 : 켜짐 ]");
        } else {
            tpAllMeta.setDisplayName(ChatColor.RED + "[ 파일런에서 스폰 : 꺼짐 ]");
        }
        spawnPailonItem.setItemMeta(tpAllMeta);

        return spawnPailonItem;
    }

}
