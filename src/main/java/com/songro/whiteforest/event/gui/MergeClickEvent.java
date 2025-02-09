package com.songro.whiteforest.event.gui;

import com.songro.whiteforest.util.Team;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.block.Skull;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.inventory.meta.SkullMeta;

public class MergeClickEvent implements Listener {

    @EventHandler
    public void onClickItem(InventoryClickEvent e){
        Player p = (Player) e.getView().getPlayer();

        if (e.getView().getTitle().equalsIgnoreCase(ChatColor.BOLD + "팀 병합")) {
                if (e.getCurrentItem() != null && !e.getCurrentItem().getType().equals(Material.AIR)) {
                    if(e.getCurrentItem().getType() == Material.LEGACY_SKULL_ITEM && e.getCurrentItem().getItemMeta().hasDisplayName()) {
                        e.setCancelled(true);
                        SkullMeta skullOwner = (SkullMeta) e.getCurrentItem().getItemMeta();

                        String getteam = Team.getTeamFromPlayer(skullOwner.getOwningPlayer().getPlayer());
                        String leaderteam = Team.getTeamFromPlayer(p);
                        if(getteam != null) {
                            switch(getteam) {
                                case "peace":

                                default:
                                    p.sendMessage(ChatColor.RED + "팀을 찾을수 없습니다.");
                            }
                        } else {
                            p.sendMessage(ChatColor.RED + "알수없는 팀 입니다.");
                        }
                    }
                }
        }
    }

}