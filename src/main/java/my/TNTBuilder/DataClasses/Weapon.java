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


    public Weapon(int id, String type, int cost, String specialRules, List<String> itemTraits, String rarity, boolean isRelic,
                  int meleeRange, int rangedRange, int strength, int reliability, int handsRequired, String category) {
        super(id, type, cost, specialRules, itemTraits, rarity, isRelic);
        this.meleeRange = meleeRange;
        this.rangedRange = rangedRange;
        this.strength = strength;
        this.reliability = reliability;
        this.handsRequired = handsRequired;
        Category = category;
    }

    public Weapon() {
    }

    //Getters

    public void setMeleeRange(int meleeRange) {
        this.meleeRange = meleeRange;
    }

    public void setRangedRange(int rangedRange) {
        this.rangedRange = rangedRange;
    }

    public void setStrength(int strength) {
        this.strength = strength;
    }

    public void setReliability(int reliability) {
        this.reliability = reliability;
    }

    public void setHandsRequired(int handsRequired) {
        this.handsRequired = handsRequired;
    }

    public void setCategory(String category) {
        Category = category;
    }

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
