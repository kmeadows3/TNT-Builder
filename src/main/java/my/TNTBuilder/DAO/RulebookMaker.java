package my.TNTBuilder.DAO;

import my.TNTBuilder.DAO.DataClasses.Referenceable;

import java.util.Map;

public interface RulebookMaker {

    public Map<String, Referenceable> getEquipment();

    public Map<String, Referenceable> getArmors();

    public Map<String, Referenceable> getWeapons();

    public Map<String, Referenceable> getItemTraits();

    public Map<String, Referenceable> getSkills();

    public Map<String, Referenceable> getUnits();


}
