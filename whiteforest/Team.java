package com.songro.whiteforest.util;

import com.songro.whiteforest.Whiteforest;
import org.bukkit.entity.Player;

import java.util.Objects;

/**
 *  Team Manager for Whiteforest
 * */
public class Team {

    public Team() {
    }

    /**
     * @param p Player
     * @return Player team
     */
    public static String getTeamFromPlayer(Player p) {
        String team = "";

        switch (Objects.requireNonNull(Whiteforest.plugin.getData().getString(p.getName() + ".team"))) {
            case "sodabean":
                team = "sodabean";
                break;
            case "sosumi":
                team = "sosumi";
                break;
            case "peace":
                team = "peace";
                break;
            case "hitullni":
                team = "hitullni";
                break;
        }

        return team;
    }
}
