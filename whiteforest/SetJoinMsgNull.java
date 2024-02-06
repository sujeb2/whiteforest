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

public class SetJoinMsgNull implements Listener {
    @EventHandler
    public void onJoin(PlayerJoinEvent e) {
        e.setJoinMessage(null);

        Player p = e.getPlayer();
        if(Whiteforest.plugin.getData().getBoolean(p.getName() + ".isLeader") && !Whiteforest.plugin.getData().getBoolean(p.getName() + ".gotPailon")) {
            p.getInventory().addItem(new ItemStack(Material.BEACON));
        }

        String getteam = Team.getTeamFromPlayer(p);

        switch (getteam) {
            case "hitullni":
                p.addAttachment(Whiteforest.plugin, "teams.hitullni", true);
                p.setDisplayName(ChatColor.LIGHT_PURPLE + p.getName());
                break;
            case "sosumi":
                p.addAttachment(Whiteforest.plugin, "teams.sosumi", true);
                p.setDisplayName(ChatColor.GOLD + p.getName());
                break;
            case "sodabean":
                p.addAttachment(Whiteforest.plugin, "teams.sodabean", true);
                p.setDisplayName(ChatColor.GREEN + p.getName());
                break;
            case "peace":
                p.addAttachment(Whiteforest.plugin, "teams.peace", true);
                p.setDisplayName(ChatColor.YELLOW + p.getName());
                break;
        }
    }

    @EventHandler
    public void onQuit(PlayerQuitEvent e){
        e.setQuitMessage(null);
    }

}
