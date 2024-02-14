package fr.syrql.farming.config;

import fr.syrql.farming.FarmingEvent;
import org.bukkit.ChatColor;
import org.bukkit.configuration.file.FileConfiguration;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class ConfigManager {

    private FarmingEvent farmingEvent;
    private FileConfiguration config;
    private String prefix;

    public ConfigManager(FarmingEvent farmingEvent) {
        this.farmingEvent = farmingEvent;
        this.config = farmingEvent.getConfig();
        this.prefix = this.config.getString("PREFIX");
    }

    public String getString(String path) {
        return ChatColor.translateAlternateColorCodes('&', Objects.requireNonNull(this.config.getString(path)
                .replace("%prefix%", prefix)));
    }

    public List<String> getStringList(String path) {

        List<String> stringList = farmingEvent.getConfig().getStringList(path);
        ArrayList<String> toReturn = new ArrayList<>();

        stringList.forEach(line -> toReturn.add(ChatColor.translateAlternateColorCodes('&', line.replace("%prefix%", prefix))));

        return toReturn;
    }

    public List<Integer> getIntegerList(String path) {
        return farmingEvent.getConfig().getIntegerList(path);
    }

    public void setDouble(String path, double value) { config.set(path, value); }

    public int getInt(String path) {
        return config.getInt(path);
    }

    public boolean getBoolean(String path) {
        return config.getBoolean(path);
    }

    private double getDouble(String path) {
        return config.getDouble(path);
    }

    public double getFloat(String path) {
        return config.getDouble(path);
    }
    public long getLong(String path){
        return config.getLong(path);
    }

    public FileConfiguration getConfig() { return config; }

    public void updateConfig() {
        farmingEvent.saveConfig();
        farmingEvent.reloadConfig();
    }
}
