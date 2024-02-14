package fr.syrql.farming.commands;

import fr.syrql.farming.FarmingEvent;
import fr.syrql.farming.utils.command.ACommand;
import org.bukkit.command.CommandSender;

public class FarmingCommand extends ACommand {

    private final FarmingEvent farmingEvent;

    public FarmingCommand(FarmingEvent farmingEvent) {
        super(farmingEvent, "farmingevent", "command.farmingevent", true)
        this.farmingEvent = farmingEvent;
    }

    @Override
    public boolean execute(CommandSender sender, String[] args) {

        if (args.length == 0) {
            return true;
        }
        return true;
    }
}
