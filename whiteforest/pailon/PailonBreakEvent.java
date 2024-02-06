package com.songro.whiteforest.event.pailon;

import com.songro.whiteforest.Whiteforest;
import net.kyori.adventure.text.Component;
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
            Whiteforest.plugin.getData().set(e.getPlayer().getName() + ".beaconLeft", Whiteforest.plugin.getData().getInt(e.getPlayer().getName() + ".beaconLeft") - 1);
            if(e.getBlock().getLocation() == Whiteforest.plugin.getData().getLocation("teams.peace.location")) {
                Bukkit.broadcast(Component.text(ChatColor.RED + "평화팀이 무너졌습니다!"));
                Whiteforest.plugin.getData().set("team.peace.location", 0);
            } else if(e.getBlock().getLocation() == Whiteforest.plugin.getData().getLocation("teams.sosumi.location")) {
                Bukkit.broadcast(Component.text(ChatColor.RED + "고소미팀이 무너졌습니다!"));
                Whiteforest.plugin.getData().set("team.sosumi.location", 0);
            } else if(e.getBlock().getLocation() == Whiteforest.plugin.getData().getLocation("teams.sodabean.location")) {
                Bukkit.broadcast(Component.text(ChatColor.RED + "소다맛 완두콩팀이 무너졌습니다!"));
                Whiteforest.plugin.getData().set("team.sodabean.location", 0);
            } else if(e.getBlock().getLocation() == Whiteforest.plugin.getData().getLocation("teams.hitnullni.location")) {
                Bukkit.broadcast(Component.text(ChatColor.RED + "히틀니팀이 무너졌습니다!"));
                Whiteforest.plugin.getData().set("team.hitnullni.location", 0);
            } else if(e.getBlock().getLocation() == Whiteforest.plugin.getData().getLocation("teams.hitnullni.location")) {
                Bukkit.broadcast(Component.text(ChatColor.RED + "teamdummy가 무너졌습니다!"));
                Whiteforest.plugin.getData().set("team.hitnullni.location", 0);
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
