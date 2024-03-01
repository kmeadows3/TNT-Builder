package my.TNTBuilder.DAO;

import my.TNTBuilder.Models.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public interface ReferenceDao {

    public List<String> getTeamOptions();

    public List<Unit> getUnitOptions(String faction);



    //Getters


    public Map<Integer, Equipment> getEquipment();

    public Map<Integer, Armor> getArmors();

    public Map<Integer, Weapon> getWeapons();

    public Map<Integer, ItemTrait> getItemTraits();

    public Map<Integer, Skill> getSkills();

    public Map<Integer, Unit> getUnits();

    public Map<Integer, Skillset> getSkillsets();
}
