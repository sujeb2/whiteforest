package com.songro.whiteforest.event.player;

import org.bukkit.World;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerChangedWorldEvent;

public class PlayerWorldTypeNether implements Listener {

    @EventHandler
    public void onJoinWorld(PlayerChangedWorldEvent e) {
        Player p = e.getPlayer();
        World pWorld = p.getWorld();

        if(e.getFrom().getEnvironment().equals(World.Environment.NETHER)) {
            p.setFireTicks(20);
        }
    }

}
