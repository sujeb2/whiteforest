package com.songro.whiteforest.inventory;

import com.songro.whiteforest.Whiteforest;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.inventory.meta.SkullMeta;

import java.util.ArrayList;

public class PickTeammate {

    public Inventory mergeGUI(Player p) {
        Inventory inv = Bukkit.createInventory(null, 9, ChatColor.BOLD + "팀원 퇴출"); // 0  1  2  3  4  5  6  7  8
        inv.setItem(1, team1(p));
        inv.setItem(3, team2(p));
        inv.setItem(5, team3(p));
        inv.setItem(7, team4(p));
        return inv;
    }

    private ItemStack team1(Player p) {
        ItemStack relive = new ItemStack(Material.PLAYER_HEAD, 1);
        SkullMeta skullMeta = (SkullMeta) relive.getItemMeta();
        skullMeta.setOwner(Whiteforest.plugin.getData().getStringList("teams." + Whiteforest.plugin.getData().getString(p.getName() + ".team") + ".list").get(1));
        ItemMeta reliveMeta = relive.getItemMeta();
        reliveMeta.setDisplayName(Whiteforest.plugin.getData().getStringList("teams." + Whiteforest.plugin.getData().getString(p.getName() + ".team") + ".list").get(1));
        ArrayList<String> item4Lore = new ArrayList<>();
        item4Lore.add(ChatColor.GRAY + "[ 네더라이트 " + Whiteforest.plugin.getData().getInt("teams." + Whiteforest.plugin.getData().getString(p.getName() + ".team") + ".next") + " 개 필요 ]");
        reliveMeta.setLore(item4Lore);
        relive.setItemMeta(reliveMeta);

        return relive;
    }

    private ItemStack team2(Player p) {
        ItemStack relive = new ItemStack(Material.PLAYER_HEAD, 1);
        SkullMeta skullMeta = (SkullMeta) relive.getItemMeta();
        skullMeta.setOwner(Whiteforest.plugin.getData().getStringList("teams." + Whiteforest.plugin.getData().getString(p.getName() + ".team") + ".list").get(2));
        ItemMeta reliveMeta = relive.getItemMeta();
        reliveMeta.setDisplayName(Whiteforest.plugin.getData().getStringList("teams." + Whiteforest.plugin.getData().getString(p.getName() + ".team") + ".list").get(1));
        ArrayList<String> item4Lore = new ArrayList<>();
        item4Lore.add(ChatColor.GRAY + "[ 네더라이트 " + Whiteforest.plugin.getData().getInt("teams." + Whiteforest.plugin.getData().getString(p.getName() + ".team") + ".next") + " 개 필요 ]");
        reliveMeta.setLore(item4Lore);
        relive.setItemMeta(reliveMeta);

        return relive;
    }

    private ItemStack team3(Player p) {
        ItemStack relive = new ItemStack(Material.PLAYER_HEAD, 1);
        SkullMeta skullMeta = (SkullMeta) relive.getItemMeta();
        skullMeta.setOwner(Whiteforest.plugin.getData().getStringList("teams." + Whiteforest.plugin.getData().getString(p.getName() + ".team") + ".list").get(3));
        ItemMeta reliveMeta = relive.getItemMeta();
        reliveMeta.setDisplayName(Whiteforest.plugin.getData().getStringList("teams." + Whiteforest.plugin.getData().getString(p.getName() + ".team") + ".list").get(1));
        ArrayList<String> item4Lore = new ArrayList<>();
        item4Lore.add(ChatColor.GRAY + "[ 네더라이트 " + Whiteforest.plugin.getData().getInt("teams." + Whiteforest.plugin.getData().getString(p.getName() + ".team") + ".next") + " 개 필요 ]");
        reliveMeta.setLore(item4Lore);
        relive.setItemMeta(reliveMeta);

        return relive;
    }

    private ItemStack team4(Player p) {
        ItemStack relive = new ItemStack(Material.PLAYER_HEAD, 1);
        SkullMeta skullMeta = (SkullMeta) relive.getItemMeta();
        skullMeta.setOwner(Whiteforest.plugin.getData().getStringList("teams." + Whiteforest.plugin.getData().getString(p.getName() + ".team") + ".list").get(4));
        ItemMeta reliveMeta = relive.getItemMeta();
        reliveMeta.setDisplayName(Whiteforest.plugin.getData().getStringList("teams." + Whiteforest.plugin.getData().getString(p.getName() + ".team") + ".list").get(1));
        ArrayList<String> item4Lore = new ArrayList<>();
        item4Lore.add(ChatColor.GRAY + "[ 네더라이트 " + Whiteforest.plugin.getData().getInt("teams." + Whiteforest.plugin.getData().getString(p.getName() + ".team") + ".next") + " 개 필요 ]");
        reliveMeta.setLore(item4Lore);
        relive.setItemMeta(reliveMeta);

        return relive;
    }

}
