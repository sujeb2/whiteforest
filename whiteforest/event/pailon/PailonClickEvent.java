package com.songro.whiteforest.event.pailon;

import com.songro.whiteforest.inventory.Relive;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryType;

import java.util.Objects;

public class PailonClickEvent implements Listener {

    @EventHandler
    public void onClickItem(InventoryClickEvent e){
        Player p = e.getWhoClicked().getKiller();
        e.setCancelled(true);

        if(Objects.requireNonNull(e.getClickedInventory()).getType() != InventoryType.PLAYER) {
            if (e.getView().getTitle().equalsIgnoreCase(ChatColor.BOLD + "파일런")) {
                if (e.getCurrentItem() != null) {
                    switch (e.getCurrentItem().getType()) {
                        case TOTEM_OF_UNDYING:
                            assert p != null;
                            p.openInventory(new Relive().reliveGUI(p));
                            break;
                        default:
                            break;
                    }
                }
            }
        }
    }

}
