package net.ironingot.chunklogger;

import java.io.File;
import java.io.IOException;
import java.util.List;

public class ChunkLoggerConfig extends Config {

    public  ChunkLoggerConfig(File file) {
        super(file);
    }

    public Boolean isEnable() {
        return getAsBoolean("enable", Boolean.FALSE);
    }

    public void enable() {
        setAsBoolean("enable", Boolean.TRUE);
    }

    public void disable() {
        setAsBoolean("enable", Boolean.FALSE);
    }

    public Boolean isBroadcastEnable() {
        return getAsBoolean("broadcast", Boolean.FALSE);
    }

    public void enableBroadcast() {
        setAsBoolean("broadcast", Boolean.TRUE);
    }

    public void disableBroadcast() {
        setAsBoolean("broadcast", Boolean.FALSE);
    }

    public boolean isTargetWorld(String worldName) {
        List<String> list = getAsStringList("world");
        return list.contains(worldName);
    }
}
