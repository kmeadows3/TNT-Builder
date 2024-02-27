package my.TNTBuilder.DAO;

import my.TNTBuilder.Models.Referenceable;
import my.TNTBuilder.Models.Unit;

import java.util.List;
import java.util.Map;

public interface RulebookDao {

    public Map<Integer, Referenceable> getArmors();
    public Map<Integer, Referenceable> getItemTraits();
    public Map<Integer, Referenceable> getEquipment();
    public Map<Integer, Referenceable> getSkills();
    public Map<Integer, Referenceable> getUnits();
    public Map<Integer, Referenceable> getWeapons();
    public List<String> getTeamOptions();

    public List<Unit> getUnitOptions(String faction);
}
