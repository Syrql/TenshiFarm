package fr.syrql.farming.inventory;

import fr.syrql.farming.FarmingEvent;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;

public class FarmingInventory {

    private final FarmingEvent farmingEvent;
    private String inventoryName;
    private int inventorySize;

    public FarmingInventory(FarmingEvent farmingEvent) {
        this.farmingEvent = farmingEvent;
        this.inventoryName = this.farmingEvent.getConfigManager().getString("INVENTORY.NAME");
        this.inventorySize = this.farmingEvent.getConfigManager().getInt("INVENTORY.SIZE");
    }

    public void openInventory(Player player) {

        Inventory inventory = Bukkit.createInventory(null, this.inventorySize, this.inventoryName);

        Material border = Material.getMaterial(this.farmingEvent.getConfigManager().getString("INVENTORY.BORDER_TYPE"));
        String borderName = this.farmingEvent.getConfigManager().getString("INVENTORY.BORDER_NAME");
        byte borderData = (byte) this.farmingEvent.getConfigManager().getInt("INVENTORY.BORDER_DATA");

        for (int i : this.farmingEvent.getConfigManager().getIntegerList("INVENTORY.BORDER_SLOTS")) {

        }
    }
}
