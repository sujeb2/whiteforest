package com.songro.whiteforest.event.player;

import com.songro.whiteforest.Whiteforest;
import org.bukkit.ChatColor;
import org.bukkit.World;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerPortalEvent;

public class TryJoinEnd implements Listener {

    @EventHandler
    public void onEnterEndPortal(PlayerPortalEvent e) {
        if(e.getTo().getWorld().getEnvironment() == World.Environment.THE_END && !Whiteforest.plugin.getData().getBoolean("ableToJoinEnd")) {
            e.setCancelled(true);
            e.getPlayer().sendActionBar(ChatColor.RED + "현재 시간대에는 엔드에 들어갈수 없습니다.");
        }
    }

}
