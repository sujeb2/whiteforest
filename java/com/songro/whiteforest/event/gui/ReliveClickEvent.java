package com.songro.whiteforest.event.gui;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.OfflinePlayer;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;

public class ReliveClickEvent implements Listener {

    @EventHandler
    public void reliveInvClick(InventoryClickEvent e){
        Player p = e.getWhoClicked().getKiller();
        OfflinePlayer t = Bukkit.getOfflinePlayer(e.getCurrentItem().getItemMeta().getDisplayName());

        if(t != null) {

        } else {
            p.sendMessage(ChatColor.RED + "해당 플레이어는 존재하지 않습니다!");
        }
    }

}
