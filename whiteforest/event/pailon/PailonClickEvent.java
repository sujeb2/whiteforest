package com.songro.whiteforest.event.pailon;

import com.songro.whiteforest.Whiteforest;
import com.songro.whiteforest.inventory.ExpStore;
import com.songro.whiteforest.inventory.Relive;
import com.songro.whiteforest.util.Team;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Location;
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
                try {
                    if (e.getCurrentItem() != null && !e.getCurrentItem().getType().equals(Material.AIR)) {
                        if (e.getCurrentItem().getType() == Material.EXPERIENCE_BOTTLE && e.getCurrentItem().getItemMeta().hasDisplayName()) {
                            e.setCancelled(true);
                            p.closeInventory();
                            p.openInventory(new ExpStore().expStoreGUI());
                        }

                        if (e.getCurrentItem().getType() == Material.TOTEM_OF_UNDYING && e.getCurrentItem().getItemMeta().hasDisplayName()) {
                            e.setCancelled(true);
                            if(Whiteforest.plugin.getData().getBoolean(p.getName() + ".isLeader")) {
                                p.closeInventory();
                                p.openInventory(new Relive().reliveGUI(p));
                            } else {
                                p.sendMessage(ChatColor.RED + "리더가 아닙니다!");
                            }
                        }

                        if (e.getCurrentItem().getType() == Material.COMPASS && e.getCurrentItem().getItemMeta().hasDisplayName()) {
                            e.setCancelled(true);
                            if(Whiteforest.plugin.getData().getBoolean(p.getName() + ".isLeader")) {
                                Location warpLoc = e.getWhoClicked().getLocation();
                                String getteam = Team.getTeamFromPlayer(p);

                                for (Player p1 : Bukkit.getOnlinePlayers()) {
                                    if (p1.hasPermission("teams." + getteam)) {
                                        p1.teleport(warpLoc);
                                    }
                                }
                            } else {
                                p.sendMessage(ChatColor.RED + "리더가 아닙니다!");
                            }
                        }
                    }
                } catch (Exception npe) {
                    Whiteforest.plugin.getLogger().info("none");
                }
        }
    }

}
