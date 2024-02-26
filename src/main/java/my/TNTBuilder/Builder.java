package my.TNTBuilder;

import my.TNTBuilder.DataClasses.Unit;
import my.TNTBuilder.Exceptions.DaoException;
import my.TNTBuilder.Exceptions.TNTException;

public class Builder {
    private Team currentTeam;
    private Unit currentUnit;
    private Rulebook rulebook;

    public Builder() throws DaoException{
        rulebook = new Rulebook();
    }

    public Team newTeam(String name, String faction, int money) throws TNTException{
        if (money <= 0){
            throw new TNTException("You must start with a positive amount of money");
        }
        //TODO validate factions
        currentTeam = new Team();
        currentTeam.setName(name);
        currentTeam.setFaction(faction);
        currentTeam.setMoney(money);

        return currentTeam;
    }

    public void newUnit(String name, String unitType) throws TNTException{
        try {
            if (!validateNewUnit(unitType)){
                throw new TNTException("You cannot buy that unit");
            }
            Unit unitToClone = (Unit)rulebook.getUnits().get(unitType);
            currentTeam.spendMoney(unitToClone.getBaseCost());
            currentUnit = (Unit)unitToClone.clone();
        } catch (CloneNotSupportedException e ){
            throw new TNTException("Cannot make a new unit", e);
        }
        currentUnit.setUnitNickname(name);
        currentTeam.addUnit(currentUnit);
        //TODO make user fill out starting skills

    };

    public boolean validateNewUnit(String inputTitle) throws TNTException{
        if (!rulebook.getUnits().containsKey(inputTitle)){
            return false;
        }

        Unit unit = (Unit)rulebook.getUnits().get(inputTitle);

        return unit.getFaction().equals(currentTeam.getFaction());
    }








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

    public Rulebook getRulebook() {
        return rulebook;
    }
}
