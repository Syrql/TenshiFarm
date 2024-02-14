package fr.syrql.farming;

import fr.syrql.farming.config.ConfigManager;
import fr.syrql.farming.listener.FarmingListener;
import fr.syrql.farming.manager.FarmingManager;
import fr.syrql.farming.placeholder.FarmingPlaceholder;
import org.bukkit.plugin.java.JavaPlugin;

public class FarmingEvent extends JavaPlugin {

    private ConfigManager configManager;
    private FarmingManager farmingManager;
    private FarmingPlaceholder farmingPlaceholder;

    @Override
    public void onEnable() {
        this.saveDefaultConfig();

        this.registerManagers();
        this.registerCommands();
        this.registerListeners();

        if (this.getServer().getPluginManager().isPluginEnabled("PlaceholderAPI")) {
            this.farmingPlaceholder = new FarmingPlaceholder(this);
            this.farmingPlaceholder.register();
        }
    }

    @Override
    public void onDisable() {
        this.farmingPlaceholder.unregister();
    }

    private void registerManagers() {
        this.configManager = new ConfigManager(this);
        this.farmingManager = new FarmingManager(this);
    }

    private void registerCommands() {

    }

    private void registerListeners() {
        this.getServer().getPluginManager().registerEvents(new FarmingListener(this), this);
    }

    public ConfigManager getConfigManager() {
        return configManager;
    }

    public FarmingManager getFarmingManager() {
        return farmingManager;
    }
}
