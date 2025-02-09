package com.songro.whiteforest.event.player;

import com.songro.whiteforest.util.Team;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerChatEvent;

public class TeamChat implements Listener {

    @EventHandler
    public void onChat(PlayerChatEvent e) {
        Player p = e.getPlayer();
        String getteam = Team.getTeamFromPlayer(p);

        switch (getteam) {
            case "hitullni":
                Bukkit.broadcast(p.getName() + " > "  + e.getMessage(), "teams.hitullni");
                break;
            case "peace":
                Bukkit.broadcast(p.getName() + " > "  + e.getMessage(), "teams.peace");
                break;
            case "sosumi":
                Bukkit.broadcast(p.getName() + " > "  + e.getMessage(), "teams.sosumi");
                break;
            case "sodabean":
                Bukkit.broadcast(p.getName() + " > "  + e.getMessage(), "teams.sodabean");
                break;
        }
    }

}
