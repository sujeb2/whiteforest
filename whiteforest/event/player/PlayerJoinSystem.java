package com.songro.whiteforest.event.player;

import com.songro.whiteforest.Whiteforest;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;
import org.bukkit.inventory.ItemStack;
import com.songro.whiteforest.util.Team;

import java.io.IOException;
import java.util.Objects;

public class PlayerJoinSystem implements Listener {
    @EventHandler
    public void onJoin(PlayerJoinEvent e) throws IOException {
        e.setJoinMessage(null);

        Player p = e.getPlayer();
        String getteam = Team.getTeamFromPlayer(p);

        switch (getteam) {
            case "hitullni":
                p.addAttachment(Whiteforest.plugin, "teams.hitullni", true);
                p.setDisplayName(ChatColor.LIGHT_PURPLE + p.getName());
                if(Whiteforest.plugin.getData().getLocation("teams.hitullni.location") != null && Whiteforest.plugin.getData().getBoolean(p.getName() + ".ableToSpawnPailon")) {
                    p.teleport(Objects.requireNonNull(Whiteforest.plugin.getData().getLocation("teams.hitullni.location")));
                }
                break;
            case "sosumi":
                p.addAttachment(Whiteforest.plugin, "teams.sosumi", true);
                p.setDisplayName(ChatColor.GOLD + p.getName());
                if(Whiteforest.plugin.getData().getLocation("teams.sosumi.location") != null && Whiteforest.plugin.getData().getBoolean(p.getName() + ".ableToSpawnPailon")) {
                    p.teleport(Objects.requireNonNull(Whiteforest.plugin.getData().getLocation("teams.sosumi.location")));
                }
                break;
            case "sodabean":
                p.addAttachment(Whiteforest.plugin, "teams.sodabean", true);
                p.setDisplayName(ChatColor.GREEN + p.getName());
                if(Whiteforest.plugin.getData().getLocation("teams.sodabean.location") != null && Whiteforest.plugin.getData().getBoolean(p.getName() + ".ableToSpawnPailon")) {
                    p.teleport(Objects.requireNonNull(Whiteforest.plugin.getData().getLocation("teams.sodabean.location")));
                }
                break;
            case "peace":
                p.addAttachment(Whiteforest.plugin, "teams.peace", true);
                p.setDisplayName(ChatColor.YELLOW + p.getName());
                if(Whiteforest.plugin.getData().getLocation("teams.peace.location") != null && Whiteforest.plugin.getData().getBoolean(p.getName() + ".ableToSpawnPailon")) {
                    p.teleport(Objects.requireNonNull(Whiteforest.plugin.getData().getLocation("teams.peace.location")));
                }
                break;
        }

        if(Whiteforest.plugin.getData().getBoolean(p.getName() + ".isLeader") && !Whiteforest.plugin.getData().getBoolean(p.getName() + ".gotPailon")) {
            p.getInventory().addItem(new ItemStack(Material.BEACON));
            Whiteforest.plugin.getData().set(p.getName() + ".gotPailon", true);
            Whiteforest.plugin.getData().save(Whiteforest.plugin.playerDataFile);
        }
    }

    @EventHandler
    public void onQuit(PlayerQuitEvent e){
        e.setQuitMessage(null);
    }

}