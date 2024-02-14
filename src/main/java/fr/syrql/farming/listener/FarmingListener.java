package fr.syrql.farming.listener;

import fr.syrql.farming.FarmingEvent;
import fr.syrql.farming.data.target.TargetAction;
import fr.syrql.farming.data.target.TargetData;
import org.bukkit.block.Block;
import org.bukkit.entity.Item;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.player.PlayerFishEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

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

    @EventHandler
    public void onBreak(BlockBreakEvent event) {

        if (!this.farmingEvent.getFarmingManager().getFarmingData().isActive()) return;

        Player player = event.getPlayer();
        Block block = event.getBlock();

        if (player == null || block == null) return;

        TargetData targetData = this.farmingEvent.getFarmingManager().getTargetDataByTypeAndAction(block.getType(), TargetAction.BREAK);

        if (targetData == null) return;

        this.farmingEvent.getFarmingManager().addPointsToProfile(player, targetData.getPoints());
    }

    @EventHandler
    public void onInteract(PlayerInteractEvent event) {

        if (!this.farmingEvent.getFarmingManager().getFarmingData().isActive()) return;

        Player player = event.getPlayer();
        Block block = event.getClickedBlock();

        if (player == null || block == null) return;

        TargetData targetData = this.farmingEvent.getFarmingManager().getTargetDataByTypeAndAction(block.getType(), TargetAction.INTERACT);

        if (targetData == null) return;

        this.farmingEvent.getFarmingManager().addPointsToProfile(player, targetData.getPoints());
    }

    @EventHandler
    public void onFish(PlayerFishEvent event) {

        if (!this.farmingEvent.getFarmingManager().getFarmingData().isActive()) return;
        if (event.isCancelled()) return;

        if (event.getCaught() == null) return;
        if (!(event.getCaught() instanceof Item)) return;

        Player player = event.getPlayer();
        ItemStack fish = ((Item) event.getCaught()).getItemStack();

        TargetData targetData = this.farmingEvent.getFarmingManager().getTargetDataByTypeAndAction(fish.getType(), TargetAction.FISH);

        if (targetData == null) return;

        this.farmingEvent.getFarmingManager().addPointsToProfile(player, targetData.getPoints());
    }
}
