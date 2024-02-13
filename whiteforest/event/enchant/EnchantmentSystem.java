package com.songro.whiteforest.event.enchant;

import com.songro.whiteforest.inventory.Enchant;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;

public class EnchantmentSystem implements Listener {

    @EventHandler
    public void onClickEnforceBlock(PlayerInteractEvent e) {
        Player p = e.getPlayer();

        if(e.getAction() != Action.LEFT_CLICK_BLOCK && e.getClickedBlock() != null) {
            Material beacon = e.getClickedBlock().getType();

            if (beacon == Material.ENCHANTING_TABLE) {
                e.setCancelled(true);
                p.openInventory(new Enchant().enchantGUI(p));
            }
        }
    }

}
