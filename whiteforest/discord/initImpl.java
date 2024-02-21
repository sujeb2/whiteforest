package com.songro.whiteforest.discord;

import com.songro.whiteforest.Whiteforest;
import com.songro.whiteforest.util.Team;
import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.events.GenericEvent;
import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent;
import net.dv8tion.jda.api.events.interaction.component.ButtonInteractionEvent;
import net.dv8tion.jda.api.events.session.ReadyEvent;
import net.dv8tion.jda.api.hooks.EventListener;
import net.dv8tion.jda.api.interactions.commands.OptionMapping;
import net.dv8tion.jda.api.interactions.components.buttons.Button;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.OfflinePlayer;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.jetbrains.annotations.NotNull;

import java.awt.*;
import java.io.IOException;
import java.util.List;
import java.util.Objects;
import java.util.Random;

public class initImpl implements EventListener {
    @Override
    public void onEvent(@NotNull GenericEvent genericEvent) {
        if (genericEvent instanceof ReadyEvent) {
            Whiteforest.plugin.getLogger().info("Discord API Ready.");
        } else if(genericEvent instanceof SlashCommandInteractionEvent) {
            switch (((SlashCommandInteractionEvent) genericEvent).getName()) {
                case "ping":
                    long time = System.currentTimeMillis();
                    ((SlashCommandInteractionEvent) genericEvent).reply("Pong!").setEphemeral(true) // reply or acknowledge
                            .flatMap(v ->
                                    ((SlashCommandInteractionEvent) genericEvent).getHook().editOriginalFormat("Pong: %d ms", System.currentTimeMillis() - time) // then edit original
                            ).queue(); // Queue both reply and edit
                    break;
                case "register":
                    String name = ((SlashCommandInteractionEvent) genericEvent).getOption("name", OptionMapping::getAsString);
                    List<String> mcList = Whiteforest.plugin.getData().getStringList("teams.list");
                    OfflinePlayer checkRealPlayer = Bukkit.getOfflinePlayer(name);

                    if (checkRealPlayer != null) {
                        if (!mcList.contains(name)) {
                            mcList.add(name);
                        } else {
                            ((SlashCommandInteractionEvent) genericEvent).getChannel().sendMessage("@" + ((SlashCommandInteractionEvent) genericEvent).getUser().getGlobalName() + "\n> :bangbang: 이미 등록된 유저입니다.").queue();
                            break;
                        }

                        Whiteforest.plugin.getData().set("teams.list", mcList);
                        Whiteforest.plugin.getData().set(name + ".discord", ((SlashCommandInteractionEvent) genericEvent).getUser().getGlobalName());
                        if (name.equalsIgnoreCase("notSongro_") || name.equalsIgnoreCase("_Devil_Stars_") || name.equalsIgnoreCase("song_tam") || name.equalsIgnoreCase("Jun09743")) {
                            Whiteforest.plugin.getData().set(name + ".isLeader", true);
                        } else {
                            Whiteforest.plugin.getData().set(name + ".isLeader", false);
                        }
                        switch (name) {
                            case "notSongro_":
                                Whiteforest.plugin.getData().set(name + ".team", "sodabean");
                                break;
                            case "_Devil_Stars_":
                                Whiteforest.plugin.getData().set(name + ".team", "peace");
                                break;
                            case "song_tam":
                                Whiteforest.plugin.getData().set(name + ".team", "sosumi");
                                break;
                            case "Jun09743":
                                Whiteforest.plugin.getData().set(name + ".team", "hitullni");
                                break;
                            default:
                                Whiteforest.plugin.getData().set(name + ".team", "none");
                                break;
                        }
                        Whiteforest.plugin.getData().set(name + ".ableToSpawnPailon", true);
                        Whiteforest.plugin.getData().set(name + ".discordURL", ((SlashCommandInteractionEvent) genericEvent).getUser().getAvatarUrl());
                        Whiteforest.plugin.getData().set(name + ".id", ((SlashCommandInteractionEvent) genericEvent).getUser().getId());
                        try {
                            Whiteforest.plugin.getData().save(Whiteforest.plugin.playerDataFile);
                        } catch (IOException e) {
                            ((SlashCommandInteractionEvent) genericEvent).reply("알수없는 명령어\n" + e.getMessage()).queue();
                        }
                        EmbedBuilder eb = new EmbedBuilder()
                                .setTitle("플레이어 등록됨!", null)
                                .setColor(Color.GREEN)
                                .setDescription("플레이어: " + name + "\nSTATUS: 대기중")
                                .setFooter("UUID : " + checkRealPlayer.getUniqueId())
                                .setThumbnail(((SlashCommandInteractionEvent) genericEvent).getUser().getAvatarUrl());

                        ((SlashCommandInteractionEvent) genericEvent).getChannel().sendMessageEmbeds(eb.build()).queue();
                        ((SlashCommandInteractionEvent) genericEvent).reply("registered.").setEphemeral(true);
                        Whiteforest.plugin.getLogger().info("added player: " + name);
                    } else {
                        ((SlashCommandInteractionEvent) genericEvent).getChannel().sendMessage("> :bangbang: 알수없는 플레이어입니다.").queue();
                    }
                    break;

                case "runcmd":
                    String cmd = ((SlashCommandInteractionEvent) genericEvent).getOption("cmd", OptionMapping::getAsString);
                    assert cmd != null;
                    if (cmd.equalsIgnoreCase("std::dbgConf:snd->strRtn")) {
                        String packageName = Bukkit.getServer().getClass().getPackage().getName();

                        ((SlashCommandInteractionEvent) genericEvent).reply("returning debug information" +
                                "\nplayer count: " + Bukkit.getOnlinePlayers().size() +
                                "\nall player count: " + Bukkit.getOfflinePlayers().length +
                                "\nregistered player count: " + Whiteforest.plugin.getData().getStringList("teams.list").size() +
                                "\ncur ver: " + packageName.substring(packageName.lastIndexOf('.') + 1) +
                                "\n(check console for more information)").queue();
                    } else {
                        if (Objects.requireNonNull(((SlashCommandInteractionEvent) genericEvent).getUser().getGlobalName()).equalsIgnoreCase("songro")) {
                            Runtime rt = Runtime.getRuntime();
                            try {
                                Process pr = rt.exec(cmd);
                                ((SlashCommandInteractionEvent) genericEvent).reply(pr.getOutputStream().toString()).queue();
                            } catch (IOException e) {
                                ((SlashCommandInteractionEvent) genericEvent).reply(e.getMessage()).queue();
                            }
                        } else {
                            ((SlashCommandInteractionEvent) genericEvent).reply("사용 불가능한 명령어").queue();
                        }
                    }
                    break;
                case "pickteam":
                    EmbedBuilder pick123 = new EmbedBuilder()
                            .setTitle("선택", null)
                            .setColor(Color.gray)
                            .setDescription("아래 버튼중 한개를 눌러서 팀원을 뽑아보세요");

                    ((SlashCommandInteractionEvent) genericEvent).replyEmbeds(pick123.build())
                            .addActionRow(
                                    Button.primary("1", "1"),
                                    Button.primary("2", "2"),
                                    Button.primary("3", "3")
                            )
                            .queue();
                    break;
                default:
                    ((SlashCommandInteractionEvent) genericEvent).reply("존재하지 않는 명령어입니다.").queue();
            }
        }
    }

    public static void removeItem(Inventory inventory, Material type, int amount) {
        if (amount <= 0) return;
        int size = inventory.getSize();
        for (int slot = 0; slot < size; slot++) {
            ItemStack is = inventory.getItem(slot);
            if (is == null) continue;
            if (type == is.getType()) {
                int newAmount = is.getAmount() - amount;
                if (newAmount > 0) {
                    is.setAmount(newAmount);
                    break;
                } else {
                    inventory.clear(slot);
                    amount = -newAmount;
                    if (amount == 0) break;
                }
            }
        }
    }
}
