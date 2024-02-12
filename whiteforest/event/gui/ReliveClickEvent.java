package com.songro.whiteforest.event.gui;

import com.songro.whiteforest.Whiteforest;
import org.bukkit.*;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryType;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

import java.io.IOException;
import java.util.Objects;

public class ReliveClickEvent implements Listener {

    @EventHandler
    public void reliveInvClick(InventoryClickEvent e){
        Player p = (Player) e.getView().getPlayer();

        if(e.getView().getTitle().equalsIgnoreCase(ChatColor.BOLD + "팀원 부활")) {
            if (Objects.requireNonNull(e.getClickedInventory()).getType() != InventoryType.PLAYER) {
                if (e.getCurrentItem() != null && !e.getCurrentItem().equals(Material.AIR)) {
                    OfflinePlayer t = Bukkit.getOfflinePlayer(e.getCurrentItem().getItemMeta().getDisplayName());

                    if (t != null) {
                        e.setCancelled(true);
                        Inventory pInv = p.getInventory();
                        if (pInv.containsAtLeast(new ItemStack(Material.EMERALD), 10)) {
                            Whiteforest.plugin.getDeadPlayerData().set(t.getName() + ".isDead", false);
                            removeItems(pInv, Material.EMERALD, 10);
                            Bukkit.getBanList(BanList.Type.NAME).pardon(Objects.requireNonNull(t.getName()));
                            try {
                                Whiteforest.plugin.getDeadPlayerData().save(Whiteforest.plugin.deadPlayerDataFile);
                            } catch (IOException ex) {
                                throw new RuntimeException(ex);
                            }
                            p.sendMessage(t.getName() + "님이 부활하였습니다!");
                        } else {
                            p.sendMessage(ChatColor.RED + "부활에 필요한 최소한의 에메랄드가 없습니다!");
                        }
                    } else {
                        p.sendMessage(ChatColor.RED + "해당 플레이어는 존재하지 않습니다!");
                    }
                }
            }
        }
    }

    public static void removeItems(Inventory inventory, Material type, int amount) {
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
