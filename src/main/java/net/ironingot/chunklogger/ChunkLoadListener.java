package net.ironingot.chunklogger;

import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.world.ChunkLoadEvent;
import org.bukkit.entity.Entity;
import org.bukkit.Chunk;
import org.bukkit.World;
import org.bukkit.Location;

public class ChunkLoadListener implements Listener {
    public ChunkLogger plugin;

    public ChunkLoadListener(ChunkLogger plugin) {
        this.plugin = plugin;
        plugin.getServer().getPluginManager().registerEvents(this, plugin);
    }

    @EventHandler
    public void onChunkLoadEvent(ChunkLoadEvent event) {
        if (!plugin.getPluginConfig().isEnable())
            return;

        if (!plugin.getPluginConfig().isTargetWorld(event.getWorld().getName()))
            return;

        logChunkLoadEvent(event);
    }

    public void logChunkLoadEvent(ChunkLoadEvent event) {
        Chunk chunk = event.getChunk();
        World world = event.getWorld();
        RecordStore record = plugin.getRecordStore();

        String logString =
            "[" + event.getEventName() + "]" +
            " World: [" + world.getName() + "]" +
            " Chunk: (x: " + chunk.getX() +
            ", z: " + chunk.getZ() + ")" +
            " " + (event.isNewChunk() ? " (isNewChunk)" : "");

        plugin.output(logString);
    }
}
