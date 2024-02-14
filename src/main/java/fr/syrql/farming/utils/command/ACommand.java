package fr.syrql.farming.utils.command;

import fr.syrql.farming.FarmingEvent;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.List;
import java.util.Objects;

public abstract class ACommand implements CommandExecutor {

    private final String commandName;
    private final String permission;
    private final boolean consoleCanExecute;
    private final FarmingEvent farmingEvent;

    public ACommand(FarmingEvent farmingEvent, String commandName, String permission, boolean consoleCanExecute) {
        this.permission = permission;
        this.commandName = commandName;
        this.consoleCanExecute = consoleCanExecute;
        this.farmingEvent = farmingEvent;
        Objects.requireNonNull(farmingEvent.getCommand(commandName)).setExecutor(this);
    }

    public ACommand(FarmingEvent farmingEvent, String commandName, String permission, boolean consoleCanExecute, List<String> aliases) {
        this.permission = permission;
        this.commandName = commandName;
        this.consoleCanExecute = consoleCanExecute;
        this.farmingEvent = farmingEvent;
        Objects.requireNonNull(farmingEvent.getCommand(commandName)).setAliases(aliases);
        Objects.requireNonNull(farmingEvent.getCommand(commandName)).setExecutor(this);
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {

        if (!command.getLabel().equalsIgnoreCase(commandName))
            return true;

        if (!consoleCanExecute && !(sender instanceof Player)) {
            sender.sendMessage(farmingEvent.getConfigManager().getString("NOT_PLAYER"));
            return true;
        }

        if (!sender.hasPermission(permission)) {
            sender.sendMessage(farmingEvent.getConfigManager().getString("NO_PERMISSION"));
            return true;
        }

        return execute(sender, args);
    }

    public abstract boolean execute(CommandSender sender, String[] args);
}
