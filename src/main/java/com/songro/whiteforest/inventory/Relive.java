package com.songro.whiteforest.inventory;

import com.songro.whiteforest.Whiteforest;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.OfflinePlayer;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.SkullMeta;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Relive {

    public Inventory reliveGUI(Player p) {
        Inventory inv = Bukkit.createInventory(p, 54, ChatColor.BOLD + "팀원 부활");

        for (OfflinePlayer plr : Bukkit.getServer().getOfflinePlayers()) {
            if((Whiteforest.plugin.getDeadPlayerData().getString(Objects.requireNonNull(plr.getName())) != null) && Whiteforest.plugin.getDeadPlayerData().getBoolean(plr.getName() + ".isDead") && Objects.equals(Whiteforest.plugin.getData().getString(p.getName() + ".team"), Whiteforest.plugin.getData().getString(plr.getName() + ".team"))) {
                ItemStack i = new ItemStack(Material.LEGACY_SKULL_ITEM, 1, (short) 3);
                SkullMeta im = (SkullMeta) i.getItemMeta();

                List<String> il = new ArrayList<>();
                il.add(ChatColor.GREEN + "[ 좌클릭 ] : 에메랄드 10개를 소모하여 부활");
                im.setOwner(plr.getName());
                im.setLore(il);
                im.setDisplayName(plr.getName());
                i.setItemMeta(im);

                inv.addItem(i);
            }
        }

        return inv;
    }

}
