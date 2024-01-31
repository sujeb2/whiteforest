package com.songro.whiteforest.event.player;

import com.songro.whiteforest.Whiteforest;
import net.kyori.adventure.text.Component;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.block.BlockPlaceEvent;

import java.io.IOException;

public class PlayerProtectionLayer implements Listener {

    @EventHandler
    public void onPlaceBeacon(BlockPlaceEvent e) {
        Player p = e.getPlayer();

        if(Whiteforest.plugin.getData().getBoolean(p.getName() + ".isLeader")) {
            if(e.getBlockPlaced().getType() == Material.BEACON) {
                p.sendMessage(ChatColor.GREEN + "파일런이 설치되었습니다!");
                Whiteforest.plugin.getData().set(p.getName() + ".beaconPos.x", e.getBlockPlaced().getX());
                Whiteforest.plugin.getData().set(p.getName() + ".beaconPos.y", e.getBlockPlaced().getY());
                Whiteforest.plugin.getData().set(p.getName() + ".beaconPos.z", e.getBlockPlaced().getZ());
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

            if(Whiteforest.plugin.getData().getInt("_Devil_Stars_.beaconPos.x") == breakX && Whiteforest.plugin.getData().getInt("_Devil_Stars_.beaconPos.y") == breakY && Whiteforest.plugin.getData().getInt("_Devil_Stars_.beaconPos.z") == breakZ) {
                Bukkit.broadcast(Component.text(ChatColor.RED + "평화팀 의 파일런이 파괴되었습니다!"));
            } else if(Whiteforest.plugin.getData().getInt("Star_Sara.beaconPos.x") == breakX && Whiteforest.plugin.getData().getInt("Star_Sara.beaconPos.y") == breakY && Whiteforest.plugin.getData().getInt("Star_Sara.beaconPos.z") == breakZ) {
                Bukkit.broadcast(Component.text(ChatColor.RED + "고소미팀 의 파일런이 파괴되었습니다!"));
            } else if(Whiteforest.plugin.getData().getInt("two6ean.beaconPos.x") == breakX && Whiteforest.plugin.getData().getInt("two6ean.beaconPos.y") == breakY && Whiteforest.plugin.getData().getInt("two6ean.beaconPos.z") == breakZ) {
                Bukkit.broadcast(Component.text(ChatColor.RED + "소다맛 완두콩팀 의 파일런이 파괴되었습니다!"));
            } else if(Whiteforest.plugin.getData().getInt("Jun09743.beaconPos.x") == breakX && Whiteforest.plugin.getData().getInt("Jun09743.beaconPos.y") == breakY && Whiteforest.plugin.getData().getInt("Jun09743.beaconPos.z") == breakZ) {
                Bukkit.broadcast(Component.text(ChatColor.RED + "히틀니팀 의 파일런이 파괴되었습니다!"));
            } else if(Whiteforest.plugin.getData().getInt("notSongro_.beaconPos.x") == breakX && Whiteforest.plugin.getData().getInt("notSongro_.beaconPos.y") == breakY && Whiteforest.plugin.getData().getInt("notSongro_.beaconPos.z") == breakZ) {
                Bukkit.broadcast(Component.text(ChatColor.RED + "teamdummy 의 파일런이 파괴되었습니다!"));
            }
        }
    }

}
