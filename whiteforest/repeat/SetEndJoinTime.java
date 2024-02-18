package com.songro.whiteforest.repeat;

import com.songro.whiteforest.Whiteforest;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class SetEndJoinTime {

    public void onTryJoinEnd() {
        LocalDateTime now = LocalDateTime.now();
        String formated = now.format(DateTimeFormatter.ofPattern("HH:mm:ss"));

        if (formated.equals("12:00:00")) {
            Whiteforest.plugin.getData().set("ableToJoinEnd", true);
        } else {
            Whiteforest.plugin.getData().set("ableToJoinEnd", false);
        }
    }

}
