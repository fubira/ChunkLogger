package net.ironingot.chunklogger;

import java.io.File;
import java.io.IOException;
import java.util.List;

import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.configuration.file.YamlConfiguration;

public class Config {
    private File configFile;
    private YamlConfiguration configYaml;

    public Config(File configFile) {
        this.configFile = configFile;
        load();
    }

    public boolean load() {
        try {
            this.configYaml = YamlConfiguration.loadConfiguration(configFile);
        } catch (IllegalArgumentException e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }

    public boolean save() {
        try {
            configYaml.save(configFile);
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }

    public Boolean getAsBoolean(String key, Boolean defaultValue) {
        Boolean value = (Boolean) configYaml.get(key);

        if (value == null) {
            configYaml.set(key, defaultValue);
            save();
        }

        return value;
    }

    public void setAsBoolean(String key, Boolean value)
    {
        configYaml.set(key, value);
        save();
    }

    public Integer getAsInteger(String key, Integer defaultValue) {
        Integer value = (Integer) configYaml.get(key);

        if (value == null) {
            configYaml.set(key, defaultValue);
            save();
        }

        return value;
    }

    public void setAsInteger(String key, Integer value)
    {
        configYaml.set(key, value);
        save();
    }

    public Double getAsDouble(String key, Double defaultValue) {
        Double value = (Double) configYaml.get(key);

        if (value == null) {
            configYaml.set(key, defaultValue);
            save();
        }

        return value;
    }

    public void setAsDouble(String key, Double value)
    {
        configYaml.set(key, value);
        save();
    }

    public String getAsString(String key, String defaultValue) {
        String value = (String) configYaml.get(key);

        if (value == null) {
            configYaml.set(key, defaultValue);
            save();
        }

        return value;
    }

    public void setAsString(String key, String value)
    {
        configYaml.set(key, value);
        save();
    }

    public List<String> getAsStringList(String key)
    {
        return configYaml.getStringList(key);
    }

    public void setAsStringList(String key, List<String> list)
    {
        configYaml.set(key, list);
        save();
    }
}
