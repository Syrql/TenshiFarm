package fr.syrql.farming.data.target;

import org.bukkit.Material;

import java.util.List;

public class TargetData {
    private Material type;
    private Material target;
    private String name;
    private List<String> lore;
    private int slot;
    private double points;
    private TargetAction targetAction;

    public TargetData(Material type, Material target, String name, List<String> lore, int slot, double points, TargetAction targetAction) {
        this.type = type;
        this.target = target;
        this.name = name;
        this.lore = lore;
        this.slot = slot;
        this.points = points;
        this.targetAction = targetAction;
    }

    public Material getType() {
        return type;
    }

    public void setType(Material type) {
        this.type = type;
    }

    public Material getTarget() {
        return target;
    }

    public void setTarget(Material target) {
        this.target = target;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<String> getLore() {
        return lore;
    }

    public void setLore(List<String> lore) {
        this.lore = lore;
    }

    public int getSlot() {
        return slot;
    }

    public void setSlot(int slot) {
        this.slot = slot;
    }

    public double getPoints() {
        return points;
    }

    public void setPoints(double points) {
        this.points = points;
    }

    public TargetAction getTargetAction() {
        return targetAction;
    }

    public void setTargetAction(TargetAction targetAction) {
        this.targetAction = targetAction;
    }
}
