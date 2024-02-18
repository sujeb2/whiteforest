package com.songro.whiteforest.event.player;

import com.songro.whiteforest.Whiteforest;
import com.songro.whiteforest.util.Team;
import net.kyori.adventure.text.Component;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.PlayerDeathEvent;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class isDead implements Listener {

    @EventHandler
    public void isPlayerDead(PlayerDeathEvent e) {
        Player p = e.getPlayer();

        Whiteforest.plugin.getLogger().info(p.getName() + " death cause: " + e.getDeathMessage());
        e.setDeathMessage(null);

        // 여기서부터 죽은후 시간
        Date rejoinTime = new Date();
        SimpleDateFormat sdFormat = new SimpleDateFormat("yyy-MM-dd HH:mm:ss");
        Calendar cal = Calendar.getInstance();
        String str_rejoinTime = sdFormat.format(cal.getTime());

        cal.setTime(rejoinTime);
        cal.add(Calendar.MINUTE, 25);
        rejoinTime = cal.getTime();
        String getteam = Team.getTeamFromPlayer(p);

            Whiteforest.plugin.getDeadPlayerData().set(p.getName() + ".isDead", true);
            p.banPlayer(ChatColor.GRAY + "자신과 영혼이 분리되었습니다.\n" + ChatColor.RED + "재연결 가능 시간: " + rejoinTime, rejoinTime);
            try {
                Whiteforest.plugin.getDeadPlayerData().save(Whiteforest.plugin.deadPlayerDataFile);
            } catch (IOException ioe){
                Whiteforest.plugin.getLogger().severe("Cannot save data for " + p.getName());
            }
    }

}
