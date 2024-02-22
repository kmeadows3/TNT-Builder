package my.TNTBuilder;

import my.TNTBuilder.DataClasses.Unit;
import my.TNTBuilder.Inventory.Inventory;
import my.TNTBuilder.Inventory.TeamInventory;

import java.util.LinkedHashMap;
import java.util.Map;

public class Team {
    private String name;
    private String faction;
    private int money;
    private Map <String, Unit> unitMap = new LinkedHashMap<>();
    private Inventory inventory = new TeamInventory();

    //Constructors
    public Team (){
    }

    public Team (String name, String faction, int money, Map<String, Unit> unitMap, TeamInventory inventory){
        this.name = name;
        this.faction = faction;
        this.money = money;
        this.unitMap = unitMap;
        this.inventory = inventory;
    }





    //Derived instance variables
    public int getBSCost(){
        return 0;    //NEEDS METHOD
    }

    public int relicCount(){
        return 0; // NEEDS METHOD
    }

    public int getUpkeep(){
        return 0; // NEEDS METHOD
    }

    //Getters and Setters
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getFaction() {
        return faction;
    }

    public void setFaction(String faction) {
        this.faction = faction;
    }

    public int getMoney() {
        return money;
    }

    public void setMoney(int money) {
        this.money = money;
    }

    public Map<String, Unit> getUnitMap() {
        return unitMap;
    }

    public void setUnitMap(Map<String, Unit> unitMap) {
        this.unitMap = unitMap;
    }

    public Inventory getInventory() {
        return inventory;
    }

    public void setInventory(Inventory inventory) {
        this.inventory = inventory;
    }
}
