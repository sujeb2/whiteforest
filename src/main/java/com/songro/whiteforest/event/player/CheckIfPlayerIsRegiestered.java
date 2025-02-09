package com.songro.whiteforest.event.player;

import com.songro.whiteforest.Whiteforest;
import net.kyori.adventure.text.Component;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

import java.util.List;

public class CheckIfPlayerIsRegiestered implements Listener {

    @EventHandler
    public void onJoin(PlayerJoinEvent e) {
        Player p = e.getPlayer();
        List<String> mcList = Whiteforest.plugin.getData().getStringList("teams.list");

        if(!mcList.contains(p.getName())) {
            p.kick(Component.text(p.getName() + "님은 등록되지 않은 플레이어입니다.\n디스코드에서 '/register' 명령어를 사용하여 등록후 들어와주세요."));
        }
    }

}
