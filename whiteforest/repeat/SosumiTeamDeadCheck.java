package com.songro.whiteforest.repeat;

import com.songro.whiteforest.Whiteforest;
import net.kyori.adventure.text.Component;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Location;

import java.io.IOException;

public class SosumiTeamDeadCheck {

    public void onDead() throws IOException {
        Location pailonLoc = Whiteforest.plugin.getData().getLocation("teams.sosumi.location");

        if(pailonLoc == null && Whiteforest.plugin.getDeadPlayerData().getBoolean("two6ean.isDead")) {
            Bukkit.broadcast(Component.text(ChatColor.RED + "고소미 팀이 붕괴했습니다!"));
            Whiteforest.plugin.getData().set("two6ean.isLeader", false);
            Whiteforest.plugin.getData().save(Whiteforest.plugin.playerDataFile);
        }
    }

}
