package com.songro.whiteforest.repeat;

import net.kyori.adventure.text.Component;
import org.bukkit.*;
import org.bukkit.entity.Player;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Objects;

public class SpawnEnderDragon {

    public void spawnEnderDragon(Player p) {
        try {
            if (Objects.requireNonNull(p.getWorld().getEnderDragonBattle()).hasBeenPreviouslyKilled()) {
                SimpleDateFormat sdFormat = new SimpleDateFormat("HH:mm:ss");
                Calendar cal = Calendar.getInstance();
                String str_rejoinTime = sdFormat.format(cal.getTime());

                if (str_rejoinTime.equals("12:00:00")) {
                    if (p.getWorld().getEnvironment() == World.Environment.THE_END) {
                        p.getWorld().getEnderDragonBattle().initiateRespawn();
                        Bukkit.broadcast(Component.text(ChatColor.RED + "엔더드래곤이 다시 스폰되었습니다!"));
                    }
                }
            }
        } catch (NullPointerException npe) {
            return;
        }
    }
}

