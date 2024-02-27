package my.TNTBuilder.DAO;

import my.TNTBuilder.Models.Referenceable;
import my.TNTBuilder.Models.Unit;

import java.util.List;
import java.util.Map;

public interface UnitDao {

    public List<String> getTeamOptions();
    public Map<Integer, Referenceable> getUnits();
    public List<Unit> getUnitOptions(String faction);
}
