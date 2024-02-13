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
import org.bukkit.event.inventory.InventoryType;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

import java.util.*;

public class EnchantGUIEvent implements Listener {

    @EventHandler
    public void onEnchantItem(InventoryClickEvent e) {
        Player p = (Player) e.getView().getPlayer();

        if(e.getView().getTitle().equalsIgnoreCase(ChatColor.BOLD + "마법부여")) {
                try {
                    if (e.getCurrentItem() != null && !e.getCurrentItem().getType().equals(Material.AIR)) {
                        Material clickedItem = e.getCurrentItem().getType();

                        if (clickedItem == Material.ENCHANTED_BOOK && e.getCurrentItem().getItemMeta().hasDisplayName()) {
                            if (p.getInventory().contains(Material.LAPIS_LAZULI)) {
                                e.setCancelled(true);
                                if (p.getOpenInventory().getInventory(11) != null) {
                                    ItemStack item12 = e.getClickedInventory().getItem(11);

                                    if (item12 != null) {
                                        List<Enchantment> possible = new ArrayList<>();

                                        for (Enchantment ench : Enchantment.values()) {
                                            if (ench.canEnchantItem(item12)) {
                                                possible.add(ench);
                                            }
                                        }

                                        if (!possible.isEmpty()) {
                                            Collections.shuffle(possible);
                                            Enchantment chosen = possible.get(0);
                                            if(item12.getEnchantments().size() <= 3) {
                                                item12.addEnchantment(chosen, 1 + (int) (Math.random() * ((chosen.getMaxLevel() - 1) + 1)));
                                                removeItem(p.getInventory(), Material.LAPIS_LAZULI, 1);
                                            } else {
                                                p.sendMessage(ChatColor.YELLOW + "더 이상 인첸트가 불가능합니다.");
                                            }
                                        } else {
                                            p.sendMessage(ChatColor.YELLOW + "인첸트가 불가능한 아이템입니다.");
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
                                p.sendMessage(ChatColor.YELLOW + "마법부여에 필요한 아이템이 없습니다.");
                                p.playSound(p, Sound.BLOCK_ANVIL_PLACE, 8, 10);
                            }
                        } else if(clickedItem == Material.GRAY_STAINED_GLASS_PANE) {
                            e.setCancelled(true);
                        } else if(clickedItem == Material.BARRIER) {
                            if(p.getOpenInventory().getInventory(11) != null) {
                                e.setCancelled(true);
                                ItemStack item12 = e.getClickedInventory().getItem(11);
                                removeEnchantments(item12);
                            } else {
                                e.setCancelled(true);
                                p.sendMessage(ChatColor.YELLOW + "아이템이 없습니다.");
                                p.playSound(p, Sound.BLOCK_ANVIL_PLACE, 8, 10);
                            }
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
