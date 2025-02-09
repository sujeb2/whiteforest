package com.songro.whiteforest.cmd;

import com.songro.whiteforest.Whiteforest;
import net.kyori.adventure.text.Component;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.InvalidConfigurationException;
import org.jetbrains.annotations.NotNull;

import java.io.IOException;

public class reload implements CommandExecutor {
    @Override
    public boolean onCommand(@NotNull CommandSender commandSender, @NotNull Command command, @NotNull String s, @NotNull String[] strings) {
        try {
            Bukkit.broadcast(Component.text(ChatColor.RED + "Reloading..."));
            Whiteforest.plugin.getData().load(Whiteforest.plugin.playerDataFile);
            Whiteforest.plugin.getDeadPlayerData().load(Whiteforest.plugin.deadPlayerDataFile);
            Bukkit.broadcast(Component.text(ChatColor.GREEN + "Reloaded!"));
        } catch (IOException | InvalidConfigurationException e) {
            throw new RuntimeException(e);
        }

        return true;
    }
}
