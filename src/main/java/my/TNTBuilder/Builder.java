package my.TNTBuilder;

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

    public Unit newUnit(String name, Unit unit) throws TNTException{
        try {
            currentTeam.spendMoney(unit.getBaseCost());
            currentUnit = unit.clone();
        } catch (CloneNotSupportedException e ){
            throw new TNTException("Cannot make a new unit", e);
        }
        currentUnit.setUnitNickname(name);
        currentTeam.addUnit(currentUnit);
        //TODO make user fill out starting skills
        return currentUnit;
    };









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

    public Reference getRulebook() {
        return reference;
    }
}
