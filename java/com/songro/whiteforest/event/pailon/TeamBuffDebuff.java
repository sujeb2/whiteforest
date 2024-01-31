package com.songro.whiteforest.event.pailon;

import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerMoveEvent;

public class TeamBuffDebuff implements Listener {

    @EventHandler
    public void isPlayerNearPailon(PlayerMoveEvent e){
        Player p = e.getPlayer();
        Location pLoc = p.getLocation();
        World world = pLoc.getWorld();
    }

}
