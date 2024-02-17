package my.TNTBuilder.DataClasses;

import java.util.List;

public class Weapon extends InventoryItem{
    private int meleeRange;
    private int rangedRange;
    private int strength;
    private int reliability;
    private int handsRequired;
    private String Category;

    //Constructor
    public Weapon(String type, int cost, String specialRules, List<ItemTrait> itemTraits, String rarity, boolean isRelic,
                  int meleeRange, int rangedRange, int strength, int reliability, int handsRequired, String category) {
        super(type, cost, specialRules, itemTraits, rarity, isRelic);
        this.meleeRange = meleeRange;
        this.rangedRange = rangedRange;
        this.strength = strength;
        this.reliability = reliability;
        this.handsRequired = handsRequired;
        Category = category;
    }


    //Getters

    public int getMeleeRange() {
        return meleeRange;
    }

    public int getRangedRange() {
        return rangedRange;
    }

    public int getStrength() {
        return strength;
    }

    public int getReliability() {
        return reliability;
    }

    public int getHandsRequired() {
        return handsRequired;
    }

    public String getCategory() {
        return Category;
    }
}
