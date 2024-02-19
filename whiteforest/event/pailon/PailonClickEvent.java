package com.songro.whiteforest.event.pailon;

import com.songro.whiteforest.Whiteforest;
import com.songro.whiteforest.inventory.ExpStore;
import com.songro.whiteforest.inventory.Merge;
import com.songro.whiteforest.inventory.PickTeammate;
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
import org.bukkit.inventory.meta.ItemMeta;

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
                            p.closeInventory();
                            p.openInventory(new Relive().reliveGUI(p));
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
                                p.sendActionBar(ChatColor.GREEN + "팀원을 불러왔습니다");
                            } else {
                                p.sendMessage(ChatColor.RED + "리더가 아닙니다!");
                            }
                        }

                        if(e.getCurrentItem().getType() == Material.PURPLE_GLAZED_TERRACOTTA  && e.getCurrentItem().getItemMeta().hasDisplayName()) {
                            e.setCancelled(true);
                            ItemMeta cancelItem = e.getCurrentItem().getItemMeta();
                           if(!Whiteforest.plugin.getData().getBoolean(p.getName() + ".ableToSpawnPailon")) {
                               Whiteforest.plugin.getData().set(p.getName() + ".ableToSpawnPailon", true);
                               p.sendMessage(ChatColor.GREEN + "파일런 스폰을 허용했습니다.");
                               cancelItem.setDisplayName(ChatColor.GREEN + "[ 파일런에서 스폰 : 켜짐 ]");
                           } else {
                               Whiteforest.plugin.getData().set(p.getName() + ".ableToSpawnPailon", false);
                               p.sendMessage(ChatColor.RED + "파일런 스폰을 거부했습니다.");
                               cancelItem.setDisplayName(ChatColor.RED + "[ 파일런에서 스폰 : 꺼짐 ]");
                           }

                           Whiteforest.plugin.getData().save(Whiteforest.plugin.playerDataFile);

                            e.getCurrentItem().setItemMeta(cancelItem);
                        }

                        if(e.getCurrentItem().getType() == Material.EMERALD && e.getCurrentItem().getItemMeta().hasDisplayName()) {
                            e.setCancelled(true);
                            if(Whiteforest.plugin.getData().getBoolean(p.getName() + ".isLeader")) {
                                p.closeInventory();
                                p.openInventory(new Merge().mergeGUI());
                            } else {
                                p.sendMessage(ChatColor.RED + "리더가 아닙니다!");
                            }
                        }

                        if(e.getCurrentItem().getType() == Material.BARRIER && e.getCurrentItem().getItemMeta().hasDisplayName()) {
                            e.setCancelled(true);
                            if(Whiteforest.plugin.getData().getBoolean(p.getName() + ".isLeader")) {
                                p.closeInventory();
                                p.openInventory(new PickTeammate().mergeGUI(p));
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
