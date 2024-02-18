package com.songro.whiteforest.repeat;

import net.kyori.adventure.text.Component;
import org.bukkit.*;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class SpawnEnderDragon {

    public void spawnEnderDragon() {
        try {
                LocalDateTime now = LocalDateTime.now();
                String formated = now.format(DateTimeFormatter.ofPattern("HH:mm:ss"));

                if (formated.equals("12:00:00")) {
                    World svWorld = Bukkit.getWorlds().get(2);

                    if(svWorld != null) {
                        if(svWorld.getEnderDragonBattle().hasBeenPreviouslyKilled()) {
                            svWorld.getEnderDragonBattle().initiateRespawn();
                            Bukkit.broadcast(Component.text(ChatColor.RED + "엔더드래곤이 스폰되었습니다!"));
                        }
                    }
                }
        } catch (NullPointerException npe) {
            return;
        }
    }
}

