package my.TNTBuilder.DAO;

import my.TNTBuilder.Models.*;

import java.util.*;

import my.TNTBuilder.Exceptions.DaoException;

public class FileRulebookDao implements ArmorDao, EquipmentDao, ItemTraitDao, SkillDao, UnitDao, WeaponDao, RulebookDao{
    //Maps to hold the different data types
    private Map<Integer, Referenceable> equipment = new TreeMap<>();
    private Map<Integer, Referenceable> armors = new TreeMap<>();
    private Map<Integer, Referenceable> weapons = new TreeMap<>();
    private Map<Integer, Referenceable> itemTraits = new TreeMap<>();
    private Map<Integer, Referenceable> skills = new TreeMap<>();
    private Map<Integer, Referenceable> units = new TreeMap<>();
    private Map<Integer, Referenceable> skillsets = new TreeMap<>();


    //Constructors automatically creates the populated rulebook
    public FileRulebookDao() throws DaoException{
        ruleBookMaker();
    }

    //Make FileReader read each file and convert them
    private void ruleBookMaker() throws DaoException {
        CSVConverter reader = new CSVConverter();
        itemTraits = reader.getItemTraits();
        equipment = reader.getEquipment();
        armors = reader.getArmors();
        weapons = reader.getWeapons();
        skills = reader.getSkills();
        units = reader.getUnits();
        skillsets = reader.getSkillsets();
    }


    //Methods
    public List<String> getTeamOptions(){
        List<String> teamList = new ArrayList<>();
        for(Map.Entry<Integer, Referenceable> entry : units.entrySet()){
            Unit unit = (Unit)entry.getValue();
            String faction = unit.getFaction();
            if (!teamList.contains(faction)){
                teamList.add(faction);
            }
        }

        return teamList;
    }

    public List<Unit> getUnitOptions(String faction){
        List<Unit> unitOptions = new ArrayList<>();
        for (Map.Entry<Integer, Referenceable> entry : units.entrySet()){
            Unit unit = (Unit)entry.getValue();
            if (unit.getFaction().equals(faction)){
                unitOptions.add(unit);
            }
        }
        return unitOptions;
    }






    //Getters


    public Map<Integer, Referenceable> getEquipment() {
        return equipment;
    }

    public Map<Integer, Referenceable> getArmors() {
        return armors;
    }

    public Map<Integer, Referenceable> getWeapons() {
        return weapons;
    }

    public Map<Integer, Referenceable> getItemTraits() {
        return itemTraits;
    }

    public Map<Integer, Referenceable> getSkills() {
        return skills;
    }

    public Map<Integer, Referenceable> getUnits() {
        return units;
    }
}
