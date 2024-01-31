package com.songro.whiteforest;

import com.songro.whiteforest.event.player.LeadPlayerBeacon;
import com.songro.whiteforest.event.player.PlayerProtectionLayer;
import org.bukkit.Bukkit;
import org.bukkit.configuration.InvalidConfigurationException;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.File;
import java.io.IOException;
import java.util.logging.Logger;

public final class Whiteforest extends JavaPlugin {

    Logger log = Bukkit.getLogger();
    public static Whiteforest plugin;
    public File playerDataFile;
    private FileConfiguration playerData;

    @Override
    public void onEnable() {
        plugin = this;
        log.info("[WHITEFOREST] Started.");
        createPlayerDataYml();

        try {
            getServer().getPluginManager().registerEvents(new PlayerProtectionLayer(), this);
            getServer().getPluginManager().registerEvents(new LeadPlayerBeacon(), this);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onDisable() {
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

    public FileConfiguration getData() {
        return this.playerData;
    }
}
