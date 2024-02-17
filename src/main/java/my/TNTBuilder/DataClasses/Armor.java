package my.TNTBuilder.DataClasses;

import java.util.List;

public class Armor extends InventoryItem{
    private int meleeDefenseBonus;
    private int rangedDefenseBonus;
    private boolean isShield;
    private int cost2Wounds;
    private int cost3Wounds;


    //Constructor

    public Armor(String type, int cost, String specialRules, List<ItemTrait> itemTraits, String rarity, boolean isRelic,
                 int meleeDefenseBonus, int rangedDefenseBonus, boolean isShield, int cost2Wounds, int cost3Wounds) {
        super(type, cost, specialRules, itemTraits, rarity, isRelic);
        this.meleeDefenseBonus = meleeDefenseBonus;
        this.rangedDefenseBonus = rangedDefenseBonus;
        this.isShield = isShield;
        this.cost2Wounds = cost2Wounds;
        this.cost3Wounds = cost3Wounds;
    }

    //Getters
    public int getMeleeDefenseBonus() {
        return meleeDefenseBonus;
    }

    public int getRangedDefenseBonus() {
        return rangedDefenseBonus;
    }

    public boolean isShield() {
        return isShield;
    }

    public int getCost2Wounds() {
        return cost2Wounds;
    }

    public int getCost3Wounds() {
        return cost3Wounds;
    }
}
