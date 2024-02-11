package com.songro.whiteforest.repeat;

import com.songro.whiteforest.Whiteforest;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

import java.util.Objects;

public class IfPlayerNearBeaconSodabean {

    public void checkPlayer() {
        for(Player p : Bukkit.getOnlinePlayers()) {
            Location team1BeaconLoc = Whiteforest.plugin.getData().getLocation("teams.sodabean.location");

            if (team1BeaconLoc != null && team1BeaconLoc.getNearbyPlayers(10).contains(p)) {
                if (!Objects.equals(Whiteforest.plugin.getData().getString(p.getName() + ".team"), "sodabean")) {
                    Bukkit.broadcast(ChatColor.RED + p.getName() + "님이 팀 기지에 침입했습니다!", "teams.sodabean");
                    p.addPotionEffect(new PotionEffect(PotionEffectType.WEAKNESS, 20 * 3, 1), true);
                } else {
                    p.addPotionEffect(new PotionEffect(PotionEffectType.HEAL, 20 * 3, 0), true);
                    p.addPotionEffect(new PotionEffect(PotionEffectType.FIRE_RESISTANCE, 20 * 3, 0), true);
                }
            } else {
                return;
            }
        }
    }

}
