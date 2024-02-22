package my.TNTBuilder;

import my.TNTBuilder.DataClasses.Unit;
import my.TNTBuilder.Exceptions.DaoException;

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
}
