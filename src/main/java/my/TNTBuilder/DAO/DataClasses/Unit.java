package my.TNTBuilder.DAO.DataClasses;

import my.TNTBuilder.DAO.DataClasses.Referenceable;
import my.TNTBuilder.Inventory.UnitInventory;

import java.util.List;

public class Unit implements Referenceable {
    private String unitNickname; //not called name so it's not confusing with getName
    private String title;
    private String faction;
    private String rank;
    private String type;
    private int baseCost;
    private String newPurchaseNote;
    private int additionalStartingSkills;
    private int wounds;
    private int defense;
    private int mettle;
    private int move;
    private int ranged;
    private int melee;
    private int strength;
    private int spentExperience;
    private int unspentExperience;
    private int[] availableSkillsets;
    private List<String> skillList;
    private UnitInventory inventory;


    //constructor

    public Unit(String faction, String title, String rank, String type, int baseCost, String newPurchaseNote,
                int wounds, int defense, int mettle, int move, int ranged, int melee, int strength,
                int[] availableSkillsets, List<String> skillList, int additionalStartingSkills, int spentExperience) {
        this.faction = faction;
        this.title = title;
        this.rank = rank;
        this.type = type;
        this.baseCost = baseCost;
        this.newPurchaseNote = newPurchaseNote;
        this.wounds = wounds;
        this.defense = defense;
        this.mettle = mettle;
        this.move = move;
        this.ranged = ranged;
        this.melee = melee;
        this.strength = strength;
        this.availableSkillsets = availableSkillsets;
        this.skillList = skillList;
        this.additionalStartingSkills = additionalStartingSkills;
        this.spentExperience = spentExperience;
    }







    //Getters
    @Override
    public String getName() {
        return title;
    }

    public String getUnitNickname() {
        return unitNickname;
    }

    public String getTitle() {
        return title;
    }

    public String getFaction() {
        return faction;
    }

    public String getRank() {
        return rank;
    }

    public String getType() {
        return type;
    }

    public int getBaseCost() {
        return baseCost;
    }

    public String getNewPurchaseNote() {
        return newPurchaseNote;
    }

    public int getWounds() {
        return wounds;
    }

    public int getDefense() {
        return defense;
    }

    public int getMettle() {
        return mettle;
    }

    public int getMove() {
        return move;
    }

    public int getRanged() {
        return ranged;
    }

    public int getMelee() {
        return melee;
    }

    public int getStrength() { return strength; }

    public int getSpentExperience() {
        return spentExperience;
    }

    public int getUnspentExperience() {
        return unspentExperience;
    }

    public int[] getAvailableSkillsets() {
        return availableSkillsets;
    }

    public List<String> getSkillList() {
        return skillList;
    }

    public UnitInventory getInventory() {
        return inventory;
    }

    public int getAdditionalStartingSkills() {
        return additionalStartingSkills;
    }
}
