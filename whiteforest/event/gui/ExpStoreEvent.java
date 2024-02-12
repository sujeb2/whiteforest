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
import org.bukkit.inventory.meta.ItemMeta;

import java.util.Objects;

public class ExpStoreEvent implements Listener {

    @EventHandler
    public void onClickItem(InventoryClickEvent e){
        Player p = (Player) e.getView().getPlayer();

        if (e.getView().getTitle().equalsIgnoreCase(ChatColor.BOLD + "레벨 상점")) {
            
            if (Objects.requireNonNull(e.getClickedInventory()).getType() != InventoryType.PLAYER) {
                if (e.getCurrentItem() != null && !e.getCurrentItem().equals(Material.AIR)) {
                    if(e.getCurrentItem().getType() == Material.EXPERIENCE_BOTTLE && e.getCurrentItem().getItemMeta().hasDisplayName()) {
                        e.setCancelled(true);
                        if(p.getLevel() >= 2) {
                            p.setLevel(p.getLevel() - 2);
                            p.getInventory().addItem(new ItemStack(Material.EXPERIENCE_BOTTLE));
                        } else {
                            p.sendMessage(ChatColor.YELLOW + "충분한 레벨이 아닙니다!");
                        }
                    }

                    if(e.getCurrentItem().getType() == Material.TOTEM_OF_UNDYING && e.getCurrentItem().getItemMeta().hasDisplayName()) {
                        e.setCancelled(true);
                        if(p.getLevel() >= 40) {
                            p.setLevel(p.getLevel() - 40);
                            p.getInventory().addItem(new ItemStack(Material.TOTEM_OF_UNDYING));
                        } else {
                            p.sendMessage(ChatColor.YELLOW + "충분한 레벨이 아닙니다!");
                        }
                    }

                    if(e.getCurrentItem().getType() == Material.BEACON && e.getCurrentItem().getItemMeta().hasDisplayName()) {
                        e.setCancelled(true);
                        if(p.getLevel() >= 300) {
                            p.setLevel(p.getLevel() - 300);
                            p.getInventory().addItem(new ItemStack(Material.BEACON));
                        } else {
                            p.sendMessage(ChatColor.YELLOW + "충분한 레벨이 아닙니다!");
                        }
                    }

                    if(e.getCurrentItem().getType() == Material.NETHER_STAR && e.getCurrentItem().getItemMeta().hasDisplayName()) {
                        e.setCancelled(true);
                        if(p.getLevel() >= 5) {
                            p.setLevel(p.getLevel() - 5);
                            ItemStack item = new ItemStack(Material.NETHER_STAR);
                            ItemMeta itemMeta = item.getItemMeta();
                            itemMeta.setDisplayName(ChatColor.GOLD + "[강화석]");
                            item.setItemMeta(itemMeta);

                            p.getInventory().addItem(item);
                        } else {
                            p.sendMessage(ChatColor.YELLOW + "충분한 레벨이 아닙니다!");
                        }
                    }
                } else {
                    return;
                }
            }
        }
    }

}