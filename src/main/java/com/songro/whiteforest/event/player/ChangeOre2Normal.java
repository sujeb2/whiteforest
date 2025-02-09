package com.songro.whiteforest.event.player;

import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.inventory.ItemStack;

public class ChangeOre2Normal implements Listener {

    @EventHandler
    public void changeOre(BlockBreakEvent e) {
        Player p = e.getPlayer();
        ItemStack pickaxe = p.getInventory().getItemInMainHand();
        if(pickaxe.containsEnchantment(Enchantment.SILK_TOUCH)) {
            switch (e.getBlock().getType()) {
                case COAL_ORE, DEEPSLATE_COAL_ORE:
                    e.setDropItems(false);
                    p.getInventory().addItem(new ItemStack(Material.COAL));
                    break;
                case DIAMOND_ORE, DEEPSLATE_DIAMOND_ORE:
                    e.setDropItems(false);
                    p.getInventory().addItem(new ItemStack(Material.DIAMOND));
                    break;
                case EMERALD_ORE, DEEPSLATE_EMERALD_ORE:
                    e.setDropItems(false);
                    p.getInventory().addItem(new ItemStack(Material.EMERALD));
                    break;
                case COPPER_ORE, DEEPSLATE_COPPER_ORE:
                    e.setDropItems(false);
                    p.getInventory().addItem(new ItemStack(Material.RAW_COPPER));
                    break;
                case LAPIS_ORE, DEEPSLATE_LAPIS_ORE:
                    e.setDropItems(false);
                    p.getInventory().addItem(new ItemStack(Material.LAPIS_LAZULI));
                    break;
                case IRON_ORE, DEEPSLATE_IRON_ORE:
                    e.setDropItems(false);
                    p.getInventory().addItem(new ItemStack(Material.RAW_IRON));
                    break;
                case GOLD_ORE, DEEPSLATE_GOLD_ORE:
                    e.setDropItems(false);
                    p.getInventory().addItem(new ItemStack(Material.RAW_GOLD));
                    break;
                case REDSTONE_ORE, DEEPSLATE_REDSTONE_ORE:
                    e.setDropItems(false);
                    p.getInventory().addItem(new ItemStack(Material.REDSTONE));
                    break;
            }
        }
    }

}
