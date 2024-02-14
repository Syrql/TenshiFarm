package fr.syrql.farming.data.event;

import fr.syrql.farming.data.target.TargetData;

import java.util.List;

public class FarmingData {

    private int maxTargets;
    private int defaultRotateTime;
    private int currentRotateTime;
    private int timeleft;
    private List<TargetData> targets;
    private boolean isActive;

    public FarmingData(int maxTargets, int defaultRotateTime) {
        this.maxTargets = maxTargets;
        this.defaultRotateTime = defaultRotateTime;
    }

    public int getMaxTargets() {
        return maxTargets;
    }

    public void setMaxTargets(int maxTargets) {
        this.maxTargets = maxTargets;
    }

    public int getDefaultRotateTime() {
        return defaultRotateTime;
    }

    public void setDefaultRotateTime(int defaultRotateTime) {
        this.defaultRotateTime = defaultRotateTime;
    }

    public int getCurrentRotateTime() {
        return currentRotateTime;
    }

    public void setCurrentRotateTime(int currentRotateTime) {
        this.currentRotateTime = currentRotateTime;
    }

    public int getTimeleft() {
        return timeleft;
    }

    public void setTimeleft(int timeleft) {
        this.timeleft = timeleft;
    }

    public List<TargetData> getTargets() {
        return targets;
    }

    public void setTargets(List<TargetData> targets) {
        this.targets = targets;
    }

    public boolean isActive() {
        return isActive;
    }

    public void setActive(boolean active) {
        isActive = active;
    }
}
