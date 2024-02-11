package com.songro.whiteforest.event.pailon;

import com.songro.whiteforest.Whiteforest;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;

import java.io.IOException;

public class PailonBreakEvent implements Listener {

    @EventHandler
    public void onBreakBeacon(BlockBreakEvent e) {
        if(e.getBlock().getType() == Material.BEACON) {
            if(e.getBlock().getX() == Whiteforest.plugin.getData().getInt("teams.hitullni.x") && e.getBlock().getY() == Whiteforest.plugin.getData().getInt("teams.hitullni.y") && e.getBlock().getZ() == Whiteforest.plugin.getData().getInt("teams.hitullni.z")) {
                Bukkit.broadcast(ChatColor.RED + "파일런이 파괴되었습니다!", "teams.hitullni");
                Whiteforest.plugin.getData().set("teams.hitullni.x", 0);
                Whiteforest.plugin.getData().set("teams.hitullni.y", 0);
                Whiteforest.plugin.getData().set("teams.hitullni.z", 0);
                Whiteforest.plugin.getData().set("teams.hitullni.location", null);
            } else if(e.getBlock().getX() == Whiteforest.plugin.getData().getInt("teams.sosumi.x") && e.getBlock().getY() == Whiteforest.plugin.getData().getInt("teams.sosumi.y") && e.getBlock().getZ() == Whiteforest.plugin.getData().getInt("teams.sosumi.z")) {
                Bukkit.broadcast(ChatColor.RED + "파일런이 파괴되었습니다!", "teams.sosumi");
                Whiteforest.plugin.getData().set("teams.sosumi.x", 0);
                Whiteforest.plugin.getData().set("teams.sosumi.y", 0);
                Whiteforest.plugin.getData().set("teams.sosumi.z", 0);
                Whiteforest.plugin.getData().set("teams.sosumi.location", null);
            } else if(e.getBlock().getX() == Whiteforest.plugin.getData().getInt("teams.sodabean.x") && e.getBlock().getY() == Whiteforest.plugin.getData().getInt("teams.sodabean.y") && e.getBlock().getZ() == Whiteforest.plugin.getData().getInt("teams.sodabean.z")) {
                Bukkit.broadcast(ChatColor.RED + "파일런이 파괴되었습니다!", "teams.sodabean");
                Whiteforest.plugin.getData().set("teams.sodabean.x", 0);
                Whiteforest.plugin.getData().set("teams.sodabean.y", 0);
                Whiteforest.plugin.getData().set("teams.sodabean.z", 0);
                Whiteforest.plugin.getData().set("teams.sodabean.location", null);
            } else if(e.getBlock().getX() == Whiteforest.plugin.getData().getInt("teams.peace.x") && e.getBlock().getY() == Whiteforest.plugin.getData().getInt("teams.peace.y") && e.getBlock().getZ() == Whiteforest.plugin.getData().getInt("teams.peace.z")) {
                Bukkit.broadcast(ChatColor.RED + "파일런이 파괴되었습니다!", "teams.peace");
                Whiteforest.plugin.getData().set("teams.peace.x", 0);
                Whiteforest.plugin.getData().set("teams.peace.y", 0);
                Whiteforest.plugin.getData().set("teams.peace.z", 0);
                Whiteforest.plugin.getData().set("teams.peace.location", null);
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
