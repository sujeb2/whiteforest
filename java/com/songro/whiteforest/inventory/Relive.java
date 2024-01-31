package com.songro.whiteforest.inventory;

import org.bukkit.Bukkit;
import org.bukkit.inventory.Inventory;

public class Relive {

    public Inventory reliveGUI() {
        Inventory inv = Bukkit.createInventory(null, 54, "팀원 부활");

        return inv;
    }

}
