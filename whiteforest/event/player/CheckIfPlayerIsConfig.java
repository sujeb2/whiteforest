package com.songro.whiteforest.event.player;

import com.songro.whiteforest.Whiteforest;
import net.kyori.adventure.text.Component;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

public class CheckIfPlayerIsConfig implements Listener {

    @EventHandler
    public void onJoinPlayer(PlayerJoinEvent e) {
        if(!Whiteforest.plugin.getData().contains(e.getPlayer().getName())) {
            Player p = e.getPlayer();
            p.kick(Component.text(ChatColor.ITALIC + "등록되지 않은 플레이어 입니다.\n문의: 서버 문의"));
        }
    }

}
