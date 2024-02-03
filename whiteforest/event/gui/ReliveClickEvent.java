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

        if(e.getClickedInventory().getType() != InventoryType.PLAYER) {
            if (e.getView().getTitle().equals(ChatColor.BOLD + "팀원 부활")) {
                OfflinePlayer t = Bukkit.getOfflinePlayer(e.getCurrentItem().getItemMeta().getDisplayName());

                if (t != null) {
                    Inventory pInv = p.getInventory();
                    if (pInv.containsAtLeast(new ItemStack(Material.EMERALD), 10)) {
                        Whiteforest.plugin.getDeadPlayerData().set(t.getName() + ".isDead", false);
                        Bukkit.getBanList(BanList.Type.NAME).pardon(Objects.requireNonNull(t.getName()));
                        try {
                            Whiteforest.plugin.getDeadPlayerData().save(Whiteforest.plugin.deadPlayerDataFile);
                        } catch (IOException ex) {
                            throw new RuntimeException(ex);
                        }
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
