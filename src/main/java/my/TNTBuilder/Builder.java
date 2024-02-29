package my.TNTBuilder;

import my.TNTBuilder.Exceptions.FailedMoneyTransaction;
import my.TNTBuilder.Exceptions.InvalidUnitPurchaseException;
import my.TNTBuilder.Models.Unit;
import my.TNTBuilder.Exceptions.DaoException;
import my.TNTBuilder.Exceptions.TNTException;

public class Builder {
    private Team currentTeam;
    private Unit currentUnit;
    private Reference reference;

    public Builder() throws DaoException{
        reference = new Reference();
    }

    public Team newTeam(String name, String faction, int money) throws TNTException{
        if (money <= 0){
            throw new TNTException("You must start with a positive amount of money");
        }
        currentTeam = new Team();
        currentTeam.setName(name);
        currentTeam.setFaction(faction);
        currentTeam.setMoney(money);

        return currentTeam;
    }

    public void newUnit(String name, Unit unit) throws TNTException{
        try {
            if (unit.getFaction().equals(currentTeam.getFaction())) {
                spendMoney(unit.getBaseCost());
                currentUnit = unit.clone();
            } else {
                throw new InvalidUnitPurchaseException("This unit cannot be added to this team.");
            }
        } catch (CloneNotSupportedException e ){
            throw new TNTException("Cannot make a new unit", e);
        }
        currentUnit.setUnitNickname(name);
        //TODO make user fill out starting skills
        currentTeam.getUnitList().add(currentUnit);
    }

    public int gainMoney(int gainedMoney) throws FailedMoneyTransaction{
        if (gainedMoney <= 0) {
            throw new FailedMoneyTransaction("Money gained must be positive");
        }
        currentTeam.setMoney(currentTeam.getMoney() + gainedMoney);
        return currentTeam.getMoney();
    }

    public int spendMoney(int amountToSpend) throws FailedMoneyTransaction{
        if (amountToSpend <= 0 ){
            throw new FailedMoneyTransaction("Money lost must be positive.");
        } else if (currentTeam.getMoney() >= amountToSpend){
            currentTeam.setMoney(currentTeam.getMoney() - amountToSpend);
        } else {
            throw new FailedMoneyTransaction("You do not have enough money for that");
        }
        return currentTeam.getMoney();
    }








    //getters and setters

    public Team getCurrentTeam() {
        return currentTeam;
    }

    public void setCurrentTeam(Team currentTeam) {
        this.currentTeam = currentTeam;
    }

    public Unit getCurrentUnit() {
        return currentUnit;
    }

    public void setCurrentUnit(Unit currentUnit) {
        this.currentUnit = currentUnit;
    }

    public Reference getReference() {
        return reference;
    }
}
