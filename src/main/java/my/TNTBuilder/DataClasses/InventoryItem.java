package my.TNTBuilder.DataClasses;

import java.util.ArrayList;
import java.util.List;

public abstract class InventoryItem implements Referenceable{
    private String name;
    private int cost;
    private String specialRules;
    private List<String> itemTraits;
    private String Rarity;
    private boolean isRelic;

    //Constructor
    public InventoryItem(String type, int cost, String specialRules, List<String> itemTraits, String rarity, boolean isRelic) {
        this.name = type;
        this.cost = cost;
        this.specialRules = specialRules;
        this.itemTraits = itemTraits;
        Rarity = rarity;
        this.isRelic = isRelic;
    }

    //Getters
    public String getName() {
        return name;
    }

    public int getCost() {
        return cost;
    }

    public String getSpecialRules() {
        return specialRules;
    }

    public List<String> getItemTraits() {
        return itemTraits;
    }

    public String getRarity() {
        return Rarity;
    }

    public boolean isRelic() {
        return isRelic;
    }
}
