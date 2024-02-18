package com.songro.whiteforest.repeat;

import com.songro.whiteforest.Whiteforest;
import net.kyori.adventure.text.Component;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Location;

import java.io.IOException;

public class SodabeanTeamDeadCheck {

    public void onDead() {
        Location pailonLoc = Whiteforest.plugin.getData().getLocation("teams.sodabean.location");

        if(pailonLoc == null && Whiteforest.plugin.getDeadPlayerData().getBoolean("Star_Sara.isDead")) {
            Bukkit.broadcast(Component.text(ChatColor.RED + "소다맛완두콩 팀이 붕괴했습니다!"));
            Whiteforest.plugin.getData().set("two6ean.isLeader", false);
            try {
                Whiteforest.plugin.getData().save(Whiteforest.plugin.playerDataFile);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }

}
