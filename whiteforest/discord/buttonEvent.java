package com.songro.whiteforest.discord;

import com.songro.whiteforest.Whiteforest;
import com.songro.whiteforest.util.Team;
import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.events.GenericEvent;
import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent;
import net.dv8tion.jda.api.events.interaction.component.ButtonInteractionEvent;
import net.dv8tion.jda.api.hooks.EventListener;
import org.bukkit.Bukkit;
import org.bukkit.Color;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.jetbrains.annotations.NotNull;

import java.util.Collections;
import java.util.List;
import java.util.Random;

import static com.songro.whiteforest.event.gui.EnchantGUIEvent.removeItem;

public class buttonEvent implements EventListener {
    @Override
    public void onEvent(@NotNull GenericEvent e) {
        if(e instanceof ButtonInteractionEvent) {
            if(((ButtonInteractionEvent) e).getComponentId().equals("1") || ((ButtonInteractionEvent) e).getComponentId().equals("2") || ((ButtonInteractionEvent) e).getComponentId().equals("3")) {
                int rand = new Random().nextInt(Whiteforest.plugin.getData().getStringList("teams.list").size());
                List<String> pList = Whiteforest.plugin.getData().getStringList("teams.list");
                String p = pList.get(rand);
                String mName = ((ButtonInteractionEvent) e).getUser().getGlobalName();

                if(mName.equals("songro") || mName.equals("Jun") || mName.equals("✰밤 별✰")) {
                    Player l = Bukkit.getPlayer(mName);
                    String team = Team.getTeamFromPlayer(Bukkit.getPlayer(mName));
                    if(Whiteforest.plugin.getData().getStringList("teams.sosumi.list").contains(p) || Whiteforest.plugin.getData().getStringList("teams.sodabean.list").contains(p) || Whiteforest.plugin.getData().getStringList("teams.hitullni.list").contains(p) || Whiteforest.plugin.getData().getStringList("teams.sosumi.list").contains(p)) {
                        try {
                            if (team != null && Whiteforest.plugin.getData().getBoolean((l != null ? l.getName() : null) + ".isLeader") && Whiteforest.plugin.getData().getStringList("teams." + team + ".list").size() < 5) {
                                Inventory inv = l.getInventory();
                                if (inv.containsAtLeast(new ItemStack(Material.DIAMOND), 5)) {
                                    List<String> teamList = Whiteforest.plugin.getData().getStringList("teams." + team + ".list");
                                    EmbedBuilder eb = new EmbedBuilder();
                                    eb.setTitle("결과");
                                    eb.setDescription("");
                                    eb.setFooter("id : " + ((ButtonInteractionEvent) e).getComponentId());

                                    removeItem(inv, Material.DIAMOND, 5);
                                    teamList.add(p);
                                    Whiteforest.plugin.getData().set(p + ".team", team);
                                    Whiteforest.plugin.getData().set("teams." + team + ".list", teamList);
                                } else {
                                    ((ButtonInteractionEvent) e).reply("> :bangbang: 다이아몬드가 부족합니다.").queue();
                                }
                            } else {
                                ((ButtonInteractionEvent) e).reply("> :bangbang: 다이아몬드가 부족합니다.").queue();
                            }
                        } catch (Exception e1) {
                            ((ButtonInteractionEvent) e).reply("> :bangbang: 서버에 들어와있지 않습니다.").queue();
                        }
                    } else {
                        Collections.shuffle(pList);
                        ((ButtonInteractionEvent) e).reply("> :bangbang: 뽑기 실패..");
                    }
                } else {
                    ((ButtonInteractionEvent) e).reply("> :bangbang: 리더가 아닙니다.").queue();
                }
            }
        }
    }
}
