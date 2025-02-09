package com.songro.whiteforest.event.gui;

import com.songro.whiteforest.Whiteforest;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;

public class EnforcementGUIEvent implements Listener {

    @EventHandler
    public void onEnchantItem(InventoryClickEvent e) {
        Player p = (Player) e.getView().getPlayer();

        if(e.getView().getTitle().equalsIgnoreCase(ChatColor.BOLD + "강화")) {
                try {
                    if (e.getCurrentItem() != null && !e.getCurrentItem().getType().equals(Material.AIR)) {
                        Material clickedItem = e.getCurrentItem().getType();

                        if (clickedItem == Material.BOOK && e.getCurrentItem().getItemMeta().hasDisplayName()) {
                            if (p.getInventory().contains(Material.NETHER_STAR)) {
                                e.setCancelled(true);
                                if (p.getOpenInventory().getInventory(11) != null) {
                                    ItemStack item12 = e.getClickedInventory().getItem(11);

                                    if (item12 != null) {
                                        if(!item12.getItemMeta().hasLore()) {

                                        }

                                        p.playSound(p, Sound.BLOCK_CHAIN_PLACE, 8, 10);
                                    } else {
                                        e.setCancelled(true);
                                        p.sendMessage(ChatColor.YELLOW + "아이템이 없습니다.");
                                        p.playSound(p, Sound.BLOCK_ANVIL_PLACE, 8, 10);
                                    }
                                } else {
                                    e.setCancelled(true);
                                    p.sendMessage(ChatColor.YELLOW + "아이템이 없습니다.");
                                    p.playSound(p, Sound.BLOCK_ANVIL_PLACE, 8, 10);
                                }
                            } else {
                                e.setCancelled(true);
                                p.sendMessage(ChatColor.YELLOW + "강화에 필요한 아이템이 없습니다.");
                                p.playSound(p, Sound.BLOCK_ANVIL_PLACE, 8, 10);
                            }
                        } else if(clickedItem == Material.GRAY_STAINED_GLASS_PANE) {
                            e.setCancelled(true);
                        }
                    }
                } catch (Exception npe) {
                    Whiteforest.plugin.getLogger().info("none");
                }
        }
    }


    public void removeEnchantments(ItemStack item){
        for(Map.Entry<Enchantment, Integer> e : item.getEnchantments().entrySet()){
            item.removeEnchantment(e.getKey());
        }
    }

    public static void removeItem(Inventory inventory, Material type, int amount) {
        if (amount <= 0) return;
        int size = inventory.getSize();
        for (int slot = 0; slot < size; slot++) {
            ItemStack is = inventory.getItem(slot);
            if (is == null) continue;
            if (type == is.getType()) {
                int newAmount = is.getAmount() - amount;
                if (newAmount > 0) {
                    is.setAmount(newAmount);
                    break;
                } else {
                    inventory.clear(slot);
                    amount = -newAmount;
                    if (amount == 0) break;
                }
            }
        }
    }
}
