package com.songro.whiteforest.event.player;

import org.bukkit.Material;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerAttemptPickupItemEvent;

public class InvaildItem implements Listener {

    @EventHandler
    public void onGotItem(PlayerAttemptPickupItemEvent e) {
        if(e.getItem().getType().equals(Material.ELYTRA)) {
            e.getItem().remove();
        }
    }

}
