package com.songro.whiteforest.repeat;

import com.songro.whiteforest.Whiteforest;
import org.bukkit.Bukkit;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.entity.Player;

import java.util.Objects;

public class IfPlayerNearBeacon {

    public void checkPlayer() {
        for(Player p : Bukkit.getOnlinePlayers()) {
            ConfigurationSection cs = Whiteforest.plugin.getData().getConfigurationSection("teams");

            assert cs != null;
            for(String key : cs.getKeys(false)) {
                if(p.getLocation().distance(Objects.requireNonNull(Whiteforest.plugin.getData().getLocation("teams." + key + ".location"))) > 20) {
                    // ?
                }
            }
        }
    }

}
