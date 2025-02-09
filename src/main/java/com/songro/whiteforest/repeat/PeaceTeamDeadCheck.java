package com.songro.whiteforest.repeat;

import com.songro.whiteforest.Whiteforest;
import net.kyori.adventure.text.Component;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Location;

import java.io.IOException;

public class PeaceTeamDeadCheck {

    public void onDead() {
        Location pailonLoc = Whiteforest.plugin.getData().getLocation("teams.peace.location");

        if(pailonLoc == null && Whiteforest.plugin.getData().getBoolean("asdfasdf.isDead")) {
            Bukkit.broadcast(Component.text(ChatColor.RED + "2팀이 붕괴했습니다!"));
            Whiteforest.plugin.getData().set("asdfasdf.isLeader", false);
            try {
                Whiteforest.plugin.getData().save(Whiteforest.plugin.playerDataFile);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }

}
