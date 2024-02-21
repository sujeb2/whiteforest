package com.songro.whiteforest.event.player;

import com.songro.whiteforest.Whiteforest;
import net.kyori.adventure.text.Component;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

import java.util.List;

public class CheckIfPlayerIsConfig implements Listener {

    @EventHandler
    public void onJoinPlayer(PlayerJoinEvent e) {
        List<String> mcList = Whiteforest.plugin.getData().getStringList("teams.list");
        if(!mcList.contains(e.getPlayer().getName())) {
            Player p = e.getPlayer();
            p.kick(Component.text(ChatColor.GRAY + "등록되지 않은 플레이어 입니다."));
        } else if(Whiteforest.plugin.getData().getString(e.getPlayer().getName() + ".team").equalsIgnoreCase("none")) {
            Player p = e.getPlayer();
            p.kick(Component.text(ChatColor.GRAY + "팀이 아직 지정되지 않았습니다."));
        }
    }

}
