package com.songro.whiteforest.finalenforce;

import net.kyori.adventure.text.Component;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.player.PlayerItemHeldEvent;

import java.util.Arrays;

enum Swordlist {
    IRON_SWORD,
    WOODEN_SWORD,
    STONE_SWORD,
    GOLDEN_SWORD,
    NETHERITE_SWORD,
    DIAMOND_SWORD
}

public class Sword implements Listener {

    @EventHandler
    public void onDamageEntity(PlayerItemHeldEvent e) {
        Player p = e.getPlayer();
        if(p.getInventory().getItemInMainHand().getItemMeta().lore().contains(Component.text("✯✯✯✯✯✯✯✯✯✯")) &&
                    Arrays.stream(Swordlist.values()).anyMatch(v -> v.name().equals(p.getInventory().getItemInMainHand().getType().name()))) {
            p.setNoDamageTicks(1);
        }
    }
}
