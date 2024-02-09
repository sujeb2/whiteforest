package com.songro.whiteforest.event.pailon;

import com.songro.whiteforest.Whiteforest;
import net.kyori.adventure.text.Component;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.entity.ArmorStand;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.block.BlockPlaceEvent;

import java.io.IOException;

public class PailonSystem implements Listener {

    @EventHandler
    public void onPlaceBeacon(BlockPlaceEvent e) {
        Player p = e.getPlayer();

        if(Whiteforest.plugin.getData().getBoolean(p.getName() + ".isLeader")) {
            if(e.getBlockPlaced().getType() == Material.BEACON) {
                p.sendMessage(ChatColor.GREEN + "파일런이 설치되었습니다!");
                Whiteforest.plugin.getData().set("teams." + Whiteforest.plugin.getData().getString(p.getName() + ".team") + ".x", e.getBlockPlaced().getX());
                Whiteforest.plugin.getData().set("teams." + Whiteforest.plugin.getData().getString(p.getName() + ".team") +".y", e.getBlockPlaced().getY());
                Whiteforest.plugin.getData().set("teams." + Whiteforest.plugin.getData().getString(p.getName() + ".team") +".z", e.getBlockPlaced().getZ());
                Whiteforest.plugin.getData().set("teams." + Whiteforest.plugin.getData().getString(p.getName() + ".team") + ".location", e.getBlockPlaced().getLocation());
                try {
                    Whiteforest.plugin.getData().save(Whiteforest.plugin.playerDataFile);
                } catch (IOException ex) {
                    p.sendMessage(ChatColor.RED + "파일런 설치에 실패하였습니다.");
                }
            }
        } else {
            e.setCancelled(true);
        }
    }

}