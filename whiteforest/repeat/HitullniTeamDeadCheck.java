package com.songro.whiteforest.repeat;

import com.songro.whiteforest.Whiteforest;
import net.kyori.adventure.text.Component;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Location;

import java.io.IOException;

public class HitullniTeamDeadCheck {

    public void onDead() {
        Location pailonLoc = Whiteforest.plugin.getData().getLocation("teams.hitullni.location");

        if(pailonLoc == null && Whiteforest.plugin.getDeadPlayerData().getBoolean("Jun09743.isDead")) {
            Bukkit.broadcast(Component.text(ChatColor.RED + "히틀니 팀이 붕괴했습니다!"));

            Whiteforest.plugin.getData().set("Jun09743.isLeader", false);
            try {
                Whiteforest.plugin.getData().save(Whiteforest.plugin.playerDataFile);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }

}
