package com.songro.whiteforest.repeat;

import com.songro.whiteforest.Whiteforest;

import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class SetEndJoinTime {

    public void onTryJoinEnd() throws IOException {
        LocalDateTime now = LocalDateTime.now();
        String formated = now.format(DateTimeFormatter.ofPattern("HH:mm:ss"));

        if (formated.equalsIgnoreCase("17:00:00")) {
            Whiteforest.plugin.getData().set("ableToJoinEnd", true);
        } else if(formated.equalsIgnoreCase("00:00:00")) {
            Whiteforest.plugin.getData().set("ableToJoinEnd", false);
        }

        Whiteforest.plugin.getData().save(Whiteforest.plugin.playerDataFile);
    }

}
