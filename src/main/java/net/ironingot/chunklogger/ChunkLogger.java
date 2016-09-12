package net.ironingot.chunklogger;

import java.io.File;
import java.util.HashMap;
import java.util.logging.Logger;

import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.ChatColor;

public class ChunkLogger extends JavaPlugin {
    public static final Logger logger = Logger.getLogger("ChunkLogger");
    public static ChunkLogger plugin = null;

    private ChunkLoggerConfig config;
    private RecordStore recordStore;

    @Override
    public void onEnable() {
        if (plugin != null) {
            return;
        }

        plugin = this;
        setupConfig();

        getCommand("chunklog").setExecutor(new ChunkLoggerCommand(this));
        recordStore = new RecordStore();
        new ChunkPopulateListener(this);

        logger.info(getStateString());
    }

    @Override
    public void onDisable() {
    }

    private void setupConfig() {
        File configFile = new File(getDataFolder(), "config.yml");

        if (!configFile.exists()) {
            saveDefaultConfig();
        }

        config = new ChunkLoggerConfig(configFile);
    }

    public ChunkLoggerConfig getPluginConfig() {
        return config;
    }

    public String getPermissionName(String tag) {
        return "chunklogger." + tag;
    }

    public RecordStore getRecordStore()
    {
        return recordStore;
    }

    public void clearRecordStore()
    {
        recordStore.clear();
    }

    public String getStateString() {
        return getDescription().getName() + " is currently " +
               (config.isEnable() ? "enabled" : "disabled") + " " +
               (config.isBroadcastEnable() ? "(broadcast mode)" : "(server log mode)");
    }

    public void output(String logString) {
        if (config.isBroadcastEnable())
        {
            getServer().broadcastMessage(ChatColor.DARK_GREEN + logString);
        } else {
            logger.info(logString);
        }
    }
}
