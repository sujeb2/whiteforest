package com.songro.whiteforest.event.gui;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

public class ExpStoreEvent implements Listener {

    @EventHandler
    public void onClickItem(InventoryClickEvent e){
        Player p = (Player) e.getView().getPlayer();

        if (e.getView().getTitle().equalsIgnoreCase(ChatColor.BOLD + "레벨 상점")) {
                if (e.getCurrentItem() != null && !e.getCurrentItem().getType().equals(Material.AIR)) {
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
                        if(p.getLevel() >= 100) {
                            p.setLevel(p.getLevel() - 100);
                            p.getInventory().addItem(new ItemStack(Material.BEACON));
                        } else {
                            p.sendMessage(ChatColor.YELLOW + "충분한 레벨이 아닙니다!");
                        }
                    }

                    if(e.getCurrentItem().getType() == Material.NETHER_STAR && e.getCurrentItem().getItemMeta().hasDisplayName()) {
                        e.setCancelled(true);
                        if(p.getLevel() >= 7) {
                            p.setLevel(p.getLevel() - 7);
                            ItemStack item = new ItemStack(Material.NETHER_STAR);
                            ItemMeta itemMeta = item.getItemMeta();
                            itemMeta.setDisplayName(ChatColor.GOLD + "[강화석]");
                            item.setItemMeta(itemMeta);

                            for(int i = 0; i<=5; i++) {
                                p.getInventory().addItem(item);
                            }
                        } else {
                            p.sendMessage(ChatColor.YELLOW + "충분한 레벨이 아닙니다!");
                        }
                    }
                }
        }
    }

}