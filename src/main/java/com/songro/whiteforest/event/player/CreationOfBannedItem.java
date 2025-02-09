package com.songro.whiteforest.event.player;

import org.bukkit.Material;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.CraftItemEvent;
import org.bukkit.event.inventory.InventoryType;
import org.bukkit.inventory.ItemStack;

import java.util.Objects;

public class CreationOfBannedItem implements Listener {

    @EventHandler
    public void onCreateBan(CraftItemEvent e){
        if(Objects.requireNonNull(e.getClickedInventory()).getType() != InventoryType.CREATIVE) {
            if (e.getRecipe().getResult().equals(new ItemStack(Material.BEACON)) || e.getRecipe().getResult().equals(new ItemStack(Material.END_CRYSTAL))) {
                e.setCancelled(true);
            }
        }
    }

}
