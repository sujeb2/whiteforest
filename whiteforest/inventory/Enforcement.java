package com.songro.whiteforest.inventory;

import com.songro.whiteforest.Whiteforest;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.OfflinePlayer;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.SkullMeta;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Enforcement {

    public Inventory enforceGUI(Player p) {
        Inventory inv = Bukkit.createInventory(p, 27, ChatColor.BOLD + "강화");

        return inv;
    }

}
