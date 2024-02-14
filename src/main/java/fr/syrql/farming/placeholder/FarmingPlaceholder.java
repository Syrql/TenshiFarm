package fr.syrql.farming.placeholder;

import fr.syrql.farming.FarmingEvent;
import me.clip.placeholderapi.expansion.PlaceholderExpansion;
import org.apache.commons.lang.time.DurationFormatUtils;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

public class FarmingPlaceholder extends PlaceholderExpansion {

    private final FarmingEvent farmingEvent;
    private String notActive;

    public FarmingPlaceholder(FarmingEvent farmingEvent) {
        this.farmingEvent = farmingEvent;
        this.notActive = this.farmingEvent.getConfigManager().getString("NOT_FOUND_FORMAT_PLACEHOLDER");

    }

    @Override
    public @NotNull String getIdentifier() {
        return "farmingevent";
    }

    @Override
    public @NotNull String getAuthor() {
        return "SYRQL";
    }

    @Override
    public @NotNull String getVersion() {
        return "0.0.1";
    }

    public String onPlaceholderRequest(Player player, @NotNull String identifier) {

        if (identifier.equalsIgnoreCase("is_Active")) {
            if (this.farmingEvent.getFarmingManager().getFarmingData().isActive()) {
                return "true";
            } else {
                return "false";
            }
        }

        if (identifier.equalsIgnoreCase("timeleft")) {
            if (!this.farmingEvent.getFarmingManager().getFarmingData().isActive())
                return this.notActive;
            else {
                return DurationFormatUtils.formatDuration(this.farmingEvent.getFarmingManager().getFarmingData().getTimeleft() * 1000L, "mm:ss");
            }
        }

        if (identifier.equalsIgnoreCase("rotate")) {
            if (!this.farmingEvent.getFarmingManager().getFarmingData().isActive())
                return this.notActive;
            else {
                return DurationFormatUtils.formatDuration(this.farmingEvent.getFarmingManager().getFarmingData().getCurrentRotateTime() * 1000L, "mm:ss");
            }
        }

        if (identifier.equalsIgnoreCase("ranking_me")) {
            if (!this.farmingEvent.getFarmingManager().getFarmingData().isActive())
                return this.notActive;

            if (this.farmingEvent.getFarmingManager().getProfileByName(player.getName()) == null) {
                return this.notActive;
            }

            return String.valueOf(this.farmingEvent.getFarmingManager().getProfileByName(player.getName()).getPoints());
        }

        if (identifier.equalsIgnoreCase("ranking_position_me")) {

            if (!this.farmingEvent.getFarmingManager().getFarmingData().isActive())
                return this.notActive;

            if (this.farmingEvent.getFarmingManager().getProfileByName(player.getName()) == null) {
                return this.notActive;
            }

            return String.valueOf(this.farmingEvent.getFarmingManager().getPositionByProfile(player.getName()));
        }

        if (identifier.contains("ranking_name")) {
            if (!this.farmingEvent.getFarmingManager().getFarmingData().isActive())
                return this.notActive;

            String[] args = identifier.split(":");

            try {
                int number = Integer.parseInt(args[0]);

                if (this.farmingEvent.getFarmingManager().getProfileDataByPosition(number) == null)
                    return this.notActive;

                return this.farmingEvent.getFarmingManager().getProfileDataByPosition(number).getPlayerName();

            } catch (NumberFormatException e) {
                System.out.println("Impossible de trouver le nombre renseigné dans le placeholder " + identifier);
            }
        }

        if (identifier.contains("ranking_points")) {
            if (!this.farmingEvent.getFarmingManager().getFarmingData().isActive())
                return this.notActive;

            String[] args = identifier.split(":");

            try {
                int number = Integer.parseInt(args[0]);

                if (this.farmingEvent.getFarmingManager().getProfileDataByPosition(number) == null)
                    return this.notActive;

                return String.valueOf(this.farmingEvent.getFarmingManager().getProfileDataByPosition(number).getPoints());

            } catch (NumberFormatException e) {
                System.out.println("Impossible de trouver le nombre renseigné dans le placeholder " + identifier);
            }
        }

        return null;
    }

}

