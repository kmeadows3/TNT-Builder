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

    public Team newTeam(String name, String faction, int money){
        currentTeam = new Team();
        currentTeam.setName(name);
        currentTeam.setFaction(faction);
        currentTeam.setMoney(money);

        return currentTeam;
    }

    public void newUnit(String name, String unitType) throws TNTException{
        try {
            Unit unitToClone = (Unit)rulebook.getUnits().get(unitType);
            currentUnit = (Unit)unitToClone.clone();
        } catch (CloneNotSupportedException e ){
            throw new TNTException("Cannot make a new unit", e);
        }
        currentUnit.setUnitNickname(name);
        //TODO make user fill out starting skills
        currentTeam.addUnit(currentUnit);
    };

    public boolean validateUnitTitle (String inputTitle){
        //TODO this does nothing - CURRENTLY WORKING HERE!
        return false;
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
