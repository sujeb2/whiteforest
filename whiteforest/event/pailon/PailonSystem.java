package com.songro.whiteforest.event.pailon;

import com.songro.whiteforest.Whiteforest;
import net.kyori.adventure.text.Component;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
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
                Whiteforest.plugin.getData().set("team." + Whiteforest.plugin.getData().getString(p.getName() + ".team") + ".x", e.getBlockPlaced().getX());
                Whiteforest.plugin.getData().set("team." + Whiteforest.plugin.getData().getString(p.getName() + ".team") +".y", e.getBlockPlaced().getY());
                Whiteforest.plugin.getData().set("team." + Whiteforest.plugin.getData().getString(p.getName() + ".team") +".z", e.getBlockPlaced().getZ());
                Whiteforest.plugin.getData().set("team." + Whiteforest.plugin.getData().getString(p.getName() + ".team") + ".location", e.getBlockPlaced().getLocation());
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

    @EventHandler
    public void onBreakBeacon(BlockBreakEvent e) {
        if(e.getBlock().getType() == Material.BEACON) {
            int breakX = e.getBlock().getX();
            int breakY = e.getBlock().getY();
            int breakZ = e.getBlock().getZ();

            Whiteforest.plugin.getData().set(e.getPlayer().getName() + ".beaconLeft", Whiteforest.plugin.getData().getInt(e.getPlayer().getName() + ".beaconLeft") - 1);
            if(Whiteforest.plugin.getData().getInt("teams.peace.x") == breakX && Whiteforest.plugin.getData().getInt("teams.peace.y") == breakY && Whiteforest.plugin.getData().getInt("teams.peace.z") == breakZ) {
                Bukkit.broadcast(Component.text(ChatColor.RED + "평화팀이 무너졌습니다!"));
            } else if(Whiteforest.plugin.getData().getInt("teams.sosumi.x") == breakX && Whiteforest.plugin.getData().getInt("teams.sosumi.y") == breakY && Whiteforest.plugin.getData().getInt("teams.sosumi.z") == breakZ) {
                Bukkit.broadcast(Component.text(ChatColor.RED + "고소미팀이 무너졌습니다!"));
            } else if(Whiteforest.plugin.getData().getInt("teams.sodabean.x") == breakX && Whiteforest.plugin.getData().getInt("teams.sodabean.y") == breakY && Whiteforest.plugin.getData().getInt("teams.sodabean.z") == breakZ) {
                Bukkit.broadcast(Component.text(ChatColor.RED + "소다맛 완두콩팀이 무너졌습니다!"));
            } else if(Whiteforest.plugin.getData().getInt("teams.hitullni.x") == breakX && Whiteforest.plugin.getData().getInt("teams.hitullni.y") == breakY && Whiteforest.plugin.getData().getInt("teams.hitullni.z") == breakZ) {
                Bukkit.broadcast(Component.text(ChatColor.RED + "히틀니팀이 무너졌습니다!"));
            } else if(Whiteforest.plugin.getData().getInt("teams.dummy.x") == breakX && Whiteforest.plugin.getData().getInt("teams.dummy.y") == breakY && Whiteforest.plugin.getData().getInt("teams.dummy.z") == breakZ) {
                Bukkit.broadcast(Component.text(ChatColor.RED + "teamdummy가 무너졌습니다!"));
            }
            try {
                Whiteforest.plugin.getData().save(Whiteforest.plugin.playerDataFile);
            } catch (IOException ex) {
                ex.printStackTrace();
            }
            e.setDropItems(false);
        }
    }

}