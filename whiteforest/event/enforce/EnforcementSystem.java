package com.songro.whiteforest.event.enforce;

import com.songro.whiteforest.inventory.Enforce;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;

public class EnforcementSystem implements Listener {

    @EventHandler
    public void onClickEnforceBlock(PlayerInteractEvent e) {
        Player p = e.getPlayer();

        if(e.getAction() != Action.LEFT_CLICK_BLOCK && e.getClickedBlock() != null) {
            Material beacon = e.getClickedBlock().getType();

            if (beacon == Material.GRINDSTONE) {
                e.setCancelled(true);
                p.openInventory(new Enforce().enforceGUI(p));
            }
        }
    }

}
