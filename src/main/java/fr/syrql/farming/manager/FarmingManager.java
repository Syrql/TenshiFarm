package fr.syrql.farming.manager;

import fr.syrql.farming.FarmingEvent;
import fr.syrql.farming.comparator.ProfileComparator;
import fr.syrql.farming.data.event.FarmingData;
import fr.syrql.farming.data.profile.ProfileData;
import fr.syrql.farming.data.target.TargetAction;
import fr.syrql.farming.data.target.TargetData;
import me.clip.placeholderapi.PlaceholderAPI;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;
import java.util.stream.Collectors;

public class FarmingManager {

    private final FarmingEvent farmingEvent;
    private final List<ProfileData> profiles;
    private final List<TargetData> targetData;
    private FarmingData farmingData;
    private int defaultRotateTime;

    public FarmingManager(FarmingEvent farmingEvent) {
        this.farmingEvent = farmingEvent;
        this.profiles = new ArrayList<>();
        this.targetData = new ArrayList<>();
        this.setup();
    }

    private void setup() {

        for (String key : this.farmingEvent.getConfig().getConfigurationSection("TARGETS").getKeys(false)) {
            String path = "TARGETS." + key + ".";
            Material type = Material.getMaterial(this.farmingEvent.getConfigManager().getString(path + "TYPE"));
            Material icon = Material.getMaterial(this.farmingEvent.getConfigManager().getString(path + "ICON"));
            String name = this.farmingEvent.getConfigManager().getString(path + "NAME");
            List<String> lore = this.farmingEvent.getConfigManager().getStringList(path + "LORE");
            int slot = this.farmingEvent.getConfigManager().getInt(path + "SLOT");
            int points = this.farmingEvent.getConfigManager().getInt(path + "POINTS");
            TargetAction action = TargetAction.valueOf(this.farmingEvent.getConfigManager().getString(path + "ACTION"));

            TargetData data = new TargetData(type, icon, name, lore, slot, points, action);
            this.targetData.add(data);
        }

        this.defaultRotateTime = this.farmingEvent.getConfigManager().getInt("ROTATE_TIME");

        int maxTargets = this.farmingEvent.getConfigManager().getInt("MAX_TARGETS");

        this.farmingData = new FarmingData(maxTargets, this.defaultRotateTime);
    }

    public void start(int time) {
        this.profiles.clear();
        this.farmingData.setTimeleft(time);
        this.farmingData.setCurrentRotateTime(this.defaultRotateTime);
        this.farmingData.getTargets().clear();
        this.farmingData.setActive(true);

        for (int i = 0; i < this.farmingData.getMaxTargets(); i++) {
            TargetData data = this.getRandomTargetData();

            if (!this.containsTarget(this.farmingData.getTargets(), data)) {
                this.farmingData.getTargets().add(data);
            }
        }

        this.farmingEvent.getConfigManager().getStringList("START_MESSAGE").forEach(Bukkit::broadcastMessage);
    }

    public ProfileData getProfileDataByPosition(int position) {
        LinkedList<ProfileData> profileDataLinkedList = new LinkedList<>(this.profiles);

        List<ProfileData> datas = profileDataLinkedList.stream().sorted(new ProfileComparator()).collect(Collectors.toList());

        if (datas.size() < position)
            return null;

        return datas.get(position - 1);
    }

    public int getPositionByProfile(String playerName) {
        LinkedList<ProfileData> profileDataLinkedList = new LinkedList<>(this.profiles);

        List<ProfileData> datas = profileDataLinkedList.stream().sorted(new ProfileComparator()).collect(Collectors.toList());

        if (!profileDataLinkedList.contains(this.getProfileByName(playerName))) return -1;

        return datas.indexOf(this.getProfileByName(playerName)) + 1;

    }

    public void stop() {

        for (Player player : Bukkit.getOnlinePlayers()) {
            this.farmingEvent.getConfigManager().getStringList("STOP_MESSAGE")
                    .forEach(line -> player.sendMessage(PlaceholderAPI.setPlaceholders(player, line)));
        }

        this.profiles.clear();
        this.farmingData.setCurrentRotateTime(this.defaultRotateTime);
        this.farmingData.getTargets().clear();
        this.farmingData.setActive(false);
    }

    public void createProfile(Player player) {

        ProfileData profileData = new ProfileData(player.getName(), 0);
        this.profiles.add(profileData);
    }

    public void addPointsToProfile(Player player, double points) {

        if (this.getProfileByName(player.getName()) == null)
            this.createProfile(player);

        ProfileData profileData = this.getProfileByName(player.getName());
        profileData.setPoints(profileData.getPoints() + points);
    }

    public ProfileData getProfileByName(String name) {
        return this.profiles.stream().filter(profile -> profile.getPlayerName().equalsIgnoreCase(name)).findFirst().orElse(null);
    }


    public TargetData getRandomTargetData() {
        return this.getTargetData().get(ThreadLocalRandom.current().nextInt(this.getTargetData().size()));
    }

    public FarmingData getFarmingData() {
        return farmingData;
    }

    public TargetData getTargetDataByTypeAndAction(Material type, TargetAction targetAction) {
        return this.getTargetData().stream().filter(data -> data.getType() == type && data.getTargetAction() == targetAction).findFirst().orElse(null);
    }

    public boolean containsTarget(List<TargetData> targetData, TargetData data) {

        for (TargetData targets : targetData) {
            if (targets.getSlot() == data.getSlot()
                    && targets.getType() == data.getType()
                    && targets.getTarget() == data.getTarget())
                return true;
        }
        return false;
    }

    public List<TargetData> getTargetData() {
        return targetData;
    }
}
