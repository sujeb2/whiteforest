package com.songro.whiteforest.event.gui;

import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryCloseEvent;
import org.bukkit.inventory.ItemStack;

public class EnchantCloseEvent implements Listener {

    @EventHandler
    public void onCloseInventory(InventoryCloseEvent e) {
        if(e.getView().getTitle().equalsIgnoreCase(ChatColor.BOLD + "마법부여")) {
            ItemStack itemFromEnchant = e.getInventory().getItem(11);
            Player p = (Player) e.getView().getPlayer();

            if(itemFromEnchant != null) {
                p.getInventory().addItem(itemFromEnchant);
            }
        }
    }

}
