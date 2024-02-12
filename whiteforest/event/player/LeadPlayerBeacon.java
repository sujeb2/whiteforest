package com.songro.whiteforest.event.player;

import com.songro.whiteforest.Whiteforest;
import com.songro.whiteforest.inventory.Pailon;
import net.kyori.adventure.text.Component;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;

public class LeadPlayerBeacon implements Listener {

    @EventHandler
    public void onPressBeacon(PlayerInteractEvent e){
        Player p = e.getPlayer();
        try {
            Material beacon = e.getClickedBlock().getType();

            if(e.getAction() != Action.LEFT_CLICK_BLOCK) {
                if (beacon == Material.BEACON) {
                    if (Whiteforest.plugin.getData().getBoolean(p.getName() + ".isLeader")) {
                        if (e.getClickedBlock().getX() == Whiteforest.plugin.getData().getInt("teams." + Whiteforest.plugin.getData().getString(p.getName() + ".team") + ".x") && e.getClickedBlock().getY() == Whiteforest.plugin.getData().getInt("teams." + Whiteforest.plugin.getData().getString(p.getName() + ".team") + ".y") && e.getClickedBlock().getZ() == Whiteforest.plugin.getData().getInt("teams." + Whiteforest.plugin.getData().getString(p.getName() + ".team") + ".z")) {
                            e.setCancelled(true);
                            p.openInventory(new Pailon().pailonGUI());
                        } else {
                            p.sendActionBar(ChatColor.RED + "자신의 파일런이 아닙니다!");
                            p.closeInventory();
                        }
                    } else {
                        p.sendActionBar(ChatColor.RED + "리더가 아닙니다!");
                        e.setCancelled(true);
                    }
                }
            }
        } catch (NullPointerException npe) {
            return;
        }
    }

}