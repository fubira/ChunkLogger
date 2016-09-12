package net.ironingot.chunklogger;

import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.world.ChunkPopulateEvent;
import org.bukkit.entity.Entity;
import org.bukkit.Chunk;
import org.bukkit.World;
import org.bukkit.Location;

public class ChunkPopulateListener implements Listener {
    public ChunkLogger plugin;

    public ChunkPopulateListener(ChunkLogger plugin) {
        this.plugin = plugin;
        plugin.getServer().getPluginManager().registerEvents(this, plugin);
    }

    @EventHandler
    public void onChunkPopulateEvent(ChunkPopulateEvent event) {
        if (!plugin.getPluginConfig().isEnable())
            return;

        logChunkPopulateEvent(event);
    }

    public void logChunkPopulateEvent(ChunkPopulateEvent event) {
        Chunk chunk = event.getChunk();
        World world = event.getWorld();
        RecordStore record = plugin.getRecordStore();

        String logString =
            "[" + event.getEventName() + "]" +
            " World: [" + world.getName() + "]" +
            " Chunk: (x: " + chunk.getX() +
            ", z: " + chunk.getZ() + ")" +
            " " + (chunk.isLoaded() ? " (IsLoaded)" : "");

        plugin.output(logString);
    }
}
