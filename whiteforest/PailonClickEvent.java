package com.songro.whiteforest.event.pailon;

import com.songro.whiteforest.inventory.ExpStore;
import com.songro.whiteforest.inventory.Relive;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryType;

import java.util.Objects;

public class PailonClickEvent implements Listener {

    @EventHandler
    public void onClickItem(InventoryClickEvent e){
        Player p = (Player) e.getView().getPlayer();

        if (e.getView().getTitle().equalsIgnoreCase(ChatColor.BOLD + "파일런")) {
            if (Objects.requireNonNull(e.getClickedInventory()).getType() != InventoryType.PLAYER) {
                if (e.getCurrentItem() != null && !e.getCurrentItem().equals(Material.AIR)) {
                    if(e.getCurrentItem().getType() == Material.EXPERIENCE_BOTTLE && e.getCurrentItem().getItemMeta().hasDisplayName()) {
                        e.setCancelled(true);
                        p.closeInventory();
                        p.openInventory(new ExpStore().expStoreGUI());
                    }

                    if(e.getCurrentItem().getType() == Material.TOTEM_OF_UNDYING && e.getCurrentItem().getItemMeta().hasDisplayName()) {
                        e.setCancelled(true);
                        p.closeInventory();
                        p.openInventory(new Relive().reliveGUI(p));
                    }
                } else {
                    return;
                }
            }
        }
    }

}
