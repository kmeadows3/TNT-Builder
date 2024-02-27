package my.TNTBuilder;

import my.TNTBuilder.DAO.*;
import my.TNTBuilder.DAO.File.*;
import my.TNTBuilder.Models.*;

import java.util.*;

import my.TNTBuilder.Exceptions.DaoException;

public class Rulebook {
    //Variables to hold the various DAO
    private final ArmorDao armorReader = new FileArmorDao();
    private final EquipmentDao equipmentReader = new FileEquipmentDao();
    private final WeaponDao weaponReader = new FileWeaponDao();
    private final ItemTraitDao itemTraitReader = new FileItemTraitDao();
    private final SkillDao skillReader = new FileSkillDao();
    private final SkillsetDao skillsetReader = new FileSkillsetDao();
    private final FileUnitDao unitReader = new FileUnitDao();

    //Maps to hold the different data types
    private Map<Integer, Equipment> equipment = new TreeMap<>();
    private Map<Integer, Armor> armors = new TreeMap<>();
    private Map<Integer, Weapon> weapons = new TreeMap<>();
    private Map<Integer, ItemTrait> itemTraits = new TreeMap<>();
    private Map<Integer, Skill> skills = new TreeMap<>();
    private Map<Integer, Unit> units = new TreeMap<>();
    private Map<Integer, Skillset> skillsets = new TreeMap<>();


    //Constructors automatically creates the populated rulebook
    public Rulebook() throws DaoException{
        ruleBookMaker();
    }

    //Make FileReader read each file and convert them
    private void ruleBookMaker() throws DaoException {
        itemTraits = itemTraitReader.getItemTraits();
        equipment = equipmentReader.getEquipment();
        armors = armorReader.getArmors();
        weapons = weaponReader.getWeapons();
        skills = skillReader.getSkills();
        units = unitReader.getUnits();
        skillsets = skillsetReader.getSkillsets();
    }


    //Methods
    public List<String> getTeamOptions(){
        return unitReader.getTeamOptions();
    }

    public List<Unit> getUnitOptions(String faction){
        return unitReader.getUnitOptions(faction);
    }






    //Getters


    public Map<Integer, Equipment> getEquipment() {
        return equipment;
    }

    public Map<Integer, Armor> getArmors() {
        return armors;
    }

    public Map<Integer, Weapon> getWeapons() {
        return weapons;
    }

    public Map<Integer, ItemTrait> getItemTraits() {
        return itemTraits;
    }

    public Map<Integer, Skill> getSkills() {
        return skills;
    }

    public Map<Integer, Unit> getUnits() {
        return units;
    }

    public Map<Integer, Skillset> getSkillsets() {
        return skillsets;
    }
}
