package com.songro.whiteforest.inventory;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

public class PickTeam {

    public Inventory mergeGUI() {
        Inventory inv = Bukkit.createInventory(null, 9, ChatColor.BOLD + "팀원 뽑기");
        inv.setItem(4, new ItemStack(Material.PLAYER_HEAD));
        return inv;
    }

}
