package com.songro.whiteforest.event.gui;

import com.songro.whiteforest.inventory.ExpStore;
import com.songro.whiteforest.inventory.Relive;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryType;
import org.bukkit.inventory.ItemStack;

import java.util.Objects;

public class ExpStoreEvent implements Listener {

    @EventHandler
    public void onClickItem(InventoryClickEvent e){
        Player p = (Player) e.getView().getPlayer();

        if (e.getView().getTitle().equalsIgnoreCase(ChatColor.BOLD + "경험치 상점")) {
            if (Objects.requireNonNull(e.getClickedInventory()).getType() != InventoryType.PLAYER) {
                if (e.getCurrentItem() != null && !e.getCurrentItem().equals(Material.AIR)) {
                    if(e.getCurrentItem().getType() == Material.EXPERIENCE_BOTTLE && e.getCurrentItem().getItemMeta().hasDisplayName()) {
                        e.setCancelled(true);
                        if(p.getExp() >= 10) {
                            p.setExp(p.getExp() - 10);
                            p.getInventory().addItem(new ItemStack(Material.EXPERIENCE_BOTTLE));
                        } else {
                            p.sendMessage(ChatColor.YELLOW + "충분한 경험치가 없습니다!");
                        }
                    }

                    if(e.getCurrentItem().getType() == Material.TOTEM_OF_UNDYING && e.getCurrentItem().getItemMeta().hasDisplayName()) {
                        e.setCancelled(true);
                        if(p.getExp() >= 100) {
                            p.setExp(p.getExp() - 100);
                            p.getInventory().addItem(new ItemStack(Material.TOTEM_OF_UNDYING));
                        } else {
                            p.sendMessage(ChatColor.YELLOW + "충분한 경험치가 없습니다!");
                        }
                    }

                    if(e.getCurrentItem().getType() == Material.BEACON && e.getCurrentItem().getItemMeta().hasDisplayName()) {
                        e.setCancelled(true);
                        if(p.getExp() >= 3000) {
                            p.setExp(p.getExp() - 3000);
                            p.getInventory().addItem(new ItemStack(Material.BEACON));
                        } else {
                            p.sendMessage(ChatColor.YELLOW + "충분한 경험치가 없습니다!");
                        }
                    }

                    if(e.getCurrentItem().getType() == Material.BARRIER && e.getCurrentItem().getItemMeta().hasDisplayName()) {
                        e.setCancelled(true);
                    }
                } else {
                    return;
                }
            }
        }
    }

}
