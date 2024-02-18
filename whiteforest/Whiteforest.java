package com.songro.whiteforest;

import com.songro.whiteforest.event.enchant.EnchantmentSystem;
import com.songro.whiteforest.event.enforce.EnforcementSystem;
import com.songro.whiteforest.event.gui.*;
import com.songro.whiteforest.event.pailon.PailonBreakEvent;
import com.songro.whiteforest.event.pailon.PailonClickEvent;
import com.songro.whiteforest.event.pailon.PailonSystem;
import com.songro.whiteforest.event.player.*;
import com.songro.whiteforest.repeat.*;
import net.kyori.adventure.text.Component;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.configuration.InvalidConfigurationException;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scheduler.BukkitRunnable;

import java.io.File;
import java.io.IOException;
import java.util.logging.Logger;

public final class Whiteforest extends JavaPlugin {

    Logger log = Bukkit.getLogger();
    public static Whiteforest plugin;
    public File playerDataFile;
    public File deadPlayerDataFile;
    private FileConfiguration playerData;
    private FileConfiguration deadPlayerData;

    @Override
    public void onEnable() {
        plugin = this;
        log.info("[WHITEFOREST] Started.");
        createPlayerDataYml();
        createDeadPlayerDatayml();

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
            getServer().getPluginManager().registerEvents(new EnforcementGUIEvent(), this);
            getServer().getPluginManager().registerEvents(new EnforcementSystem(), this);
            getServer().getPluginManager().registerEvents(new TryJoinEnd(), this);

            new BukkitRunnable() {
                @Override
                public void run() {
                    new IfPlayerNearBeaconSosumi().checkPlayer();
                }
            }.runTaskTimer(this, 0, 20);

            new BukkitRunnable() {
                @Override
                public void run() {
                    new SetEndJoinTime().onTryJoinEnd();
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
                        Bukkit.broadcast(Component.text(ChatColor.RED + "히틀니 팀이 붕괴했습니다!"));
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
                        Bukkit.broadcast(Component.text(ChatColor.RED + "고소미 팀이 붕괴했습니다!"));
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
                        Bukkit.broadcast(Component.text(ChatColor.RED + "평화 팀이 붕괴했습니다!"));
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
                        Bukkit.broadcast(Component.text(ChatColor.RED + "소다맛완두콩 팀이 붕괴했습니다!"));
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
