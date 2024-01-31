package com.songro.whiteforest.event.gui;

import org.bukkit.ChatColor;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;

public class PailonClickEvent implements Listener {

    @EventHandler
    public void onClickItem(InventoryClickEvent e){
        e.setCancelled(true);

        if(e.getView().getTitle().equalsIgnoreCase(ChatColor.BOLD + "파일런")) {
            if(e.getCurrentItem() != null) {
                switch (e.getCurrentItem().getType()) {
                    case TOTEM_OF_UNDYING:
                        break;

                    default:
                        break;
                }
            }
        }
    }

}
