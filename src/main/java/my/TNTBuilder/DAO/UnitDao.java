package my.TNTBuilder.DAO;

import my.TNTBuilder.Models.Referenceable;
import my.TNTBuilder.Models.Unit;

import java.util.List;
import java.util.Map;

public interface UnitDao {
    public Map<Integer, Unit> getUnits();

    public List<String> getTeamOptions();

    public List<Unit> getUnitOptions(String faction);
}
