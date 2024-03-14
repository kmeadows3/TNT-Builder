package my.TNTBuilder;

import my.TNTBuilder.DAO.MemoryReferenceDao;
import my.TNTBuilder.DAO.ReferenceDao;
import my.TNTBuilder.Exceptions.FailedMoneyTransaction;
import my.TNTBuilder.Exceptions.InvalidUnitPurchaseException;
import my.TNTBuilder.Models.Unit;
import my.TNTBuilder.Exceptions.DaoException;
import my.TNTBuilder.Exceptions.TNTException;
import my.TNTBuilder.Models.UnitTrait;

import java.util.List;

public class Builder {
    private Team currentTeam;
    private Unit currentUnit;
    private ReferenceDao reference;

    public Builder() throws DaoException{
        reference = new MemoryReferenceDao();
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

    public int[] gainMoney(int gainedMoney) throws FailedMoneyTransaction{
        int[] startingEndingFunds = new int[]{currentTeam.getMoney(), 0};

        if (gainedMoney <= 0) {
            throw new FailedMoneyTransaction("Money gained must be positive");
        }
        currentTeam.setMoney(currentTeam.getMoney() + gainedMoney);
        startingEndingFunds[1] = currentTeam.getMoney();
        return startingEndingFunds;
    }

    public int[] spendMoney(int amountToSpend) throws FailedMoneyTransaction{
        int[] startingEndingFunds = new int[]{currentTeam.getMoney(), 0};
        if (amountToSpend <= 0 ){
            throw new FailedMoneyTransaction("Money lost must be positive.");
        } else if (currentTeam.getMoney() >= amountToSpend){
            currentTeam.setMoney(currentTeam.getMoney() - amountToSpend);
        } else {
            throw new FailedMoneyTransaction("You do not have enough money for that");
        }
        startingEndingFunds[1] = currentTeam.getMoney();
        return startingEndingFunds;
    }

    public int gainExp(int addedExp) throws TNTException{
        if (addedExp <= 0 ){
            throw new TNTException("You must gain a positive amount of experience");
        }
        int newExpTotal = currentUnit.getUnspentExperience() + addedExp;
        currentUnit.setUnspentExperience(newExpTotal);
        return currentUnit.getUnspentExperience();
    }

    public int spendExp(int expSpent) throws TNTException{
        int newExpTotal = currentUnit.getUnspentExperience() - expSpent;

        if (expSpent <=0){
            throw new TNTException("You must spend a positive amount of experience");
        } else if (newExpTotal < 0) {
            throw new TNTException("You do not have enough experience to spend.");
        }
        currentUnit.setUnspentExperience(newExpTotal);
        return currentUnit.getUnspentExperience();
    }

//    public List<UnitTrait> getAvailableTraits() {
//        reference.getSkillsets();
//    }






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

    public ReferenceDao getReference() {
        return reference;
    }
}
