package my.TNTBuilder;

import my.TNTBuilder.Exceptions.FailedPurchaseException;
import my.TNTBuilder.Models.Unit;
import my.TNTBuilder.Exceptions.TNTException;
import my.TNTBuilder.Inventory.Inventory;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class Team {
    private int id;
    private String name;
    private String faction;
    private int money;
    private List<Unit> unitList = new ArrayList<>();
    private Inventory inventory = new Inventory();

    //Constructors
    public Team (){
    }

    public Team (int id, String name, String faction, int money, List<Unit> unitList, Inventory inventory){
        this.id = id;
        this.name = name;
        this.faction = faction;
        this.money = money;
        this.unitList = unitList;
        this.inventory = inventory;
    }

    public void addUnit(Unit unit){
        unitList.add(unit);
    }

    public void spendMoney(int amountToSpend) throws FailedPurchaseException{
        if (money >= amountToSpend){
            money -= amountToSpend;
        } else {
            throw new FailedPurchaseException("You do not have enough money for that");
        }
    }

    //Derived instance variables

    public int getBSCost(){
        int bsCost = 0;
        for (Unit unit : unitList){
            bsCost += unit.getBSCost();
        }
        //TODO: add inventory BS cost later
        return bsCost;
    }

    public int relicCount(){
        return 0; // TODO: ADD METHOD
    }

    public int getUpkeep(){
        return 0; // TODO: ADD METHOD
    }













    //Getters and Setters
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

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

    public List<Unit> getUnitList() {
        return unitList;
    }

    public void setUnitMap(List<Unit> unitList) {
        this.unitList = unitList;
    }

    public Inventory getInventory() {
        return inventory;
    }

    public void setInventory(Inventory inventory) {
        this.inventory = inventory;
    }
}
