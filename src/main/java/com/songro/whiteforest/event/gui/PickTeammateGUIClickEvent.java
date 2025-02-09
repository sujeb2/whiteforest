package com.songro.whiteforest.event.gui;

import com.songro.whiteforest.Whiteforest;
import com.songro.whiteforest.util.Team;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.OfflinePlayer;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.inventory.meta.SkullMeta;

import java.io.IOException;
import java.util.Collections;
import java.util.List;

public class PickTeammateGUIClickEvent implements Listener {

    @EventHandler
    public void onClickItem(InventoryClickEvent e){
        Player p = (Player) e.getWhoClicked();

        if (e.getView().getTitle().equalsIgnoreCase(ChatColor.BOLD + "팀원 퇴출")) {
                if (e.getCurrentItem() != null && !e.getCurrentItem().getType().equals(Material.AIR)) {
                    if(e.getCurrentItem().getType() == Material.LEGACY_SKULL_ITEM && e.getCurrentItem().getItemMeta().hasDisplayName()) {
                        e.setCancelled(true);
                        ItemMeta skullOwner = e.getCurrentItem().getItemMeta();
                        OfflinePlayer t = Bukkit.getOfflinePlayer(skullOwner.getDisplayName());
                        List<String> teamlist = Whiteforest.plugin.getData().getStringList("teams.teamlist");
                        List<String> playerList = Whiteforest.plugin.getData().getStringList("teams." + Whiteforest.plugin.getData().getString(p.getName() + ".team") + ".list");
                        Collections.shuffle(teamlist);
                        Whiteforest.plugin.getData().set(t.getName() + ".team", teamlist);
                        playerList.remove(p.getName());
                        Whiteforest.plugin.getData().set("teams." + Whiteforest.plugin.getData().getString(p.getName() + ".team") + ".list", playerList);

                        try {
                            Whiteforest.plugin.getData().save(Whiteforest.plugin.playerDataFile);
                        } catch (IOException ex) {
                            throw new RuntimeException(ex);
                        }
                    }
                }
        }
    }

}