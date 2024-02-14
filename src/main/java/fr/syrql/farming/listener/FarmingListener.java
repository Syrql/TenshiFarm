package fr.syrql.farming.listener;

import fr.syrql.farming.FarmingEvent;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.Inventory;

public class FarmingListener implements Listener {

    private final FarmingEvent farmingEvent;
    private final String inventoryName;

    public FarmingListener(FarmingEvent farmingEvent) {
        this.farmingEvent = farmingEvent;
        this.inventoryName = this.farmingEvent.getConfigManager().getString("INVENTORY.NAME");
    }

    @EventHandler
    public void onClick(InventoryClickEvent event) {

        Player player = (Player) event.getWhoClicked();
        Inventory inventory = event.getInventory();

        if (!inventory.getName().equalsIgnoreCase(this.inventoryName)) return;

        event.setCancelled(true);
    }
}
