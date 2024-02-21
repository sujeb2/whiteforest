package com.songro.whiteforest;

import com.songro.whiteforest.cmd.reload;
import com.songro.whiteforest.discord.buttonEvent;
import com.songro.whiteforest.discord.initImpl;
import com.songro.whiteforest.event.enchant.EnchantmentSystem;
import com.songro.whiteforest.event.enforce.EnforcementSystem;
import com.songro.whiteforest.event.gui.*;
import com.songro.whiteforest.event.pailon.PailonBreakEvent;
import com.songro.whiteforest.event.pailon.PailonClickEvent;
import com.songro.whiteforest.event.pailon.PailonSystem;
import com.songro.whiteforest.event.player.*;
import com.songro.whiteforest.repeat.*;
import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.JDABuilder;
import net.dv8tion.jda.api.Permission;
import net.dv8tion.jda.api.entities.Activity;
import net.dv8tion.jda.api.interactions.commands.DefaultMemberPermissions;
import net.dv8tion.jda.api.interactions.commands.OptionType;
import net.dv8tion.jda.api.interactions.commands.build.Commands;
import net.kyori.adventure.text.Component;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.configuration.InvalidConfigurationException;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scheduler.BukkitRunnable;

import java.io.File;
import java.io.IOException;
import java.util.Objects;
import java.util.logging.Logger;

public final class Whiteforest extends JavaPlugin {

    Logger log = Bukkit.getLogger();
    public static Whiteforest plugin;
    public File playerDataFile;
    public File deadPlayerDataFile;
    private FileConfiguration playerData;
    private FileConfiguration deadPlayerData;
    public JDA jda;

    @Override
    public void onEnable() {
        plugin = this;
        log.info("[WHITEFOREST] Started.");
        createPlayerDataYml();
        createDeadPlayerDatayml();
        jda = JDABuilder.createDefault("")
                .setActivity(Activity.playing("Collecting Star dust."))
                .addEventListeners(new initImpl())
                .addEventListeners(new buttonEvent())
                .build();

        try {
            jda.updateCommands().addCommands(
                    Commands.slash("ping", "Calculate ping of the bot"),
                    Commands.slash("register", "프로필 등록")
                            .addOption(OptionType.STRING, "name", "profile name", true),
                    Commands.slash("runcmd", "명령어 실행 (관리자 전용)")
                            .addOption(OptionType.STRING, "cmd", "cmd", true),
                    Commands.slash("pickteam", "팀원을 뽑습니다")
            ).queue();
        } catch (Exception e) {
            log.severe("디스코드에 슬래시 명령어를 등록하지 못하였습니다.");
            e.printStackTrace();
        }

        try {
            getServer().getPluginManager().registerEvents(new PailonSystem(), this);
            getServer().getPluginManager().registerEvents(new LeadPlayerBeacon(), this);
            getServer().getPluginManager().registerEvents(new PlayerJoinSystem(), this);
            getServer().getPluginManager().registerEvents(new isDead(), this);
            getServer().getPluginManager().registerEvents(new PailonBreakEvent(), this);
            getServer().getPluginManager().registerEvents(new CreationOfBannedItem(), this);
            getServer().getPluginManager().registerEvents(new CheckIfPlayerIsConfig(), this);
            getServer().getPluginManager().registerEvents(new ReliveClickEvent(), this);
            getServer().getPluginManager().registerEvents(new PailonClickEvent(), this);
            getServer().getPluginManager().registerEvents(new ExpStoreEvent(), this);
            getServer().getPluginManager().registerEvents(new TeamChat(), this);
            getServer().getPluginManager().registerEvents(new EnchantmentSystem(), this);
            getServer().getPluginManager().registerEvents(new EnchantGUIEvent(), this);
            getServer().getPluginManager().registerEvents(new EnchantCloseEvent(), this);
            getServer().getPluginManager().registerEvents(new ChangeOre2Normal(), this);
            getServer().getPluginManager().registerEvents(new TryJoinEnd(), this);
            getServer().getPluginManager().registerEvents(new PickTeammateGUIClickEvent(), this);
            getServer().getPluginManager().registerEvents(new GenFakePlayerOnQuit(), this);
            Objects.requireNonNull(getCommand("reload")).setExecutor(new reload());

            new BukkitRunnable() {
                @Override
                public void run() {
                    new IfPlayerNearBeaconSosumi().checkPlayer();
                }
            }.runTaskTimer(this, 0, 20);

            new BukkitRunnable() {
                @Override
                public void run() {
                    try {
                        new SetEndJoinTime().onTryJoinEnd();
                    } catch (IOException e) {
                        throw new RuntimeException(e);
                    }
                }
            }.runTaskTimer(this, 0, 20);

            new BukkitRunnable() {
                @Override
                public void run() {
                    new SpawnEnderDragon().spawnEnderDragon();
                }
            }.runTaskTimer(this, 0, 20);

            new BukkitRunnable() {
                @Override
                public void run() {
                    new IfPlayerNearBeaconHitullni().checkPlayer();
                }
            }.runTaskTimer(this, 0, 20);

            new BukkitRunnable() {
                @Override
                public void run() {
                    new IfPlayerNearBeaconPeace().checkPlayer();
                }
            }.runTaskTimer(this, 0, 20);

            new BukkitRunnable() {
                @Override
                public void run() {
                    new IfPlayerNearBeaconSodabean().checkPlayer();
                }
            }.runTaskTimer(this, 0, 20);

            new BukkitRunnable() {
                @Override
                public void run() {
                    Location pailonLoc = Whiteforest.plugin.getData().getLocation("teams.hitullni.location");

                    if(pailonLoc == null && Whiteforest.plugin.getDeadPlayerData().getBoolean("Jun09743.isDead")) {
                        Bukkit.broadcast(Component.text(ChatColor.RED + "3팀이 붕괴했습니다!"));
                        Whiteforest.plugin.getData().set("Jun09743.isLeader", false);
                        try {
                            Whiteforest.plugin.getData().save(Whiteforest.plugin.playerDataFile);
                        } catch (IOException e) {
                            throw new RuntimeException(e);
                        }
                        if(!isCancelled()) {
                            cancel();
                        }
                    }
                }
            }.runTaskTimer(this, 0, 20);

            new BukkitRunnable() {
                @Override
                public void run() {
                    Location pailonLoc = Whiteforest.plugin.getData().getLocation("teams.sosumi.location");

                    if(pailonLoc == null && Whiteforest.plugin.getDeadPlayerData().getBoolean("song_tam.isDead")) {
                        Bukkit.broadcast(Component.text(ChatColor.RED + "4팀이 붕괴했습니다!"));
                        Whiteforest.plugin.getData().set("song_tam.isLeader", false);
                        try {
                            Whiteforest.plugin.getData().save(Whiteforest.plugin.playerDataFile);
                        } catch (IOException e) {
                            throw new RuntimeException(e);
                        }
                        if(!isCancelled()) {
                            cancel();
                        }
                    }
                }
            }.runTaskTimer(this, 0, 20);

            new BukkitRunnable() {
                @Override
                public void run() {
                    Location pailonLoc = Whiteforest.plugin.getData().getLocation("teams.peace.location");

                    if(pailonLoc == null && Whiteforest.plugin.getDeadPlayerData().getBoolean("_Devil_Stars_.isDead")) {
                        Bukkit.broadcast(Component.text(ChatColor.RED + "2팀이 붕괴했습니다!"));
                        Whiteforest.plugin.getData().set("_Devil_Stars_.isLeader", false);
                        try {
                            Whiteforest.plugin.getData().save(Whiteforest.plugin.playerDataFile);
                        } catch (IOException e) {
                            throw new RuntimeException(e);
                        }
                        if(!isCancelled()) {
                            cancel();
                        }
                    }
                }
            }.runTaskTimer(this, 0, 20);

            new BukkitRunnable() {
                @Override
                public void run() {
                    Location pailonLoc = Whiteforest.plugin.getData().getLocation("teams.sodabean.location");

                    if(pailonLoc == null && Whiteforest.plugin.getDeadPlayerData().getBoolean("notSongro_.isDead")) {
                        Bukkit.broadcast(Component.text(ChatColor.RED + "1팀이 붕괴했습니다!"));
                        Whiteforest.plugin.getData().set("notSongro_.isLeader", false);
                        try {
                            Whiteforest.plugin.getData().save(Whiteforest.plugin.playerDataFile);
                        } catch (IOException e) {
                            throw new RuntimeException(e);
                        }
                        if(!isCancelled()) {
                            cancel();
                        }
                    }
                }
            }.runTaskTimer(this, 0, 20);

            new BukkitRunnable() {
                @Override
                public void run() {
                    for (Player p : Bukkit.getOnlinePlayers()) {
                        if(p.getWorld().getEnvironment() == World.Environment.NETHER) {
                            p.setFireTicks(20 * 2);
                        }

                        if(!p.isBanned()) {
                            Whiteforest.plugin.getDeadPlayerData().set(p.getName() + ".isDead", false);
                            try {
                                Whiteforest.plugin.deadPlayerData.save(deadPlayerDataFile);
                            } catch (IOException e) {
                                throw new RuntimeException(e);
                            }
                        }
                    }
                }
            }.runTaskTimer(this, 0, 20);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onDisable() {
        log.info("[WHITEFOREST] Saving");
        log.info("[WHITEFOREST] End");
    }

    public void createPlayerDataYml() {
        playerDataFile = new File(getDataFolder(), "playerData.yml");
        if (!playerDataFile.exists()) {
            log.warning("파일 만드는중.");
            playerDataFile.getParentFile().mkdirs();
            saveResource("playerData.yml", false);
            log.info("파일 만들어짐.");
        } else {
            log.info("파일 확인됨.");
        }

        playerData = new YamlConfiguration();
        try {
            playerData.load(playerDataFile);
        } catch (IOException | InvalidConfigurationException e) {
            log.severe("[WHITEFOREST] 플레이어 데이터을 불러오는중에 오류가 발생했습니다, 파일이 유효한가요?");
            e.printStackTrace();
            plugin.setEnabled(false);
        }
    }

    public void createDeadPlayerDatayml() {
        deadPlayerDataFile = new File(getDataFolder(), "deadPlayerData.yml");
        if (!deadPlayerDataFile.exists()) {
            log.warning("파일 만드는중.");
            deadPlayerDataFile.getParentFile().mkdirs();
            saveResource("deadPlayerData.yml", false);
            log.info("파일 만들어짐.");
        } else {
            log.info("파일 확인됨.");
        }

        deadPlayerData = new YamlConfiguration();
        try {
            deadPlayerData.load(deadPlayerDataFile);
        } catch (IOException | InvalidConfigurationException e) {
            log.severe("[WHITEFOREST] 플레이어 데이터을 불러오는중에 오류가 발생했습니다, 파일이 유효한가요?");
            e.printStackTrace();
            plugin.setEnabled(false);
        }
    }

    public FileConfiguration getData() {
        return this.playerData;
    }

    public FileConfiguration getDeadPlayerData() { return this.deadPlayerData; }
}
