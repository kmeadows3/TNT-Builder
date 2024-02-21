package my.TNTBuilder;

import my.TNTBuilder.DAO.RulebookMaker;
import my.TNTBuilder.DAO.DataClasses.*;

import java.io.FileNotFoundException;
import java.util.Map;
import java.util.TreeMap;

import my.TNTBuilder.Exceptions.TNTException;
import my.TNTBuilder.DAO.CSVConverter;

public class Rulebook {
    //Maps to hold the different data types
    private Map<String, Referenceable> equipment = new TreeMap<>();
    private Map<String, Referenceable> armors = new TreeMap<>();
    private Map<String, Referenceable> weapons = new TreeMap<>();
    private Map<String, Referenceable> itemTraits = new TreeMap<>();
    private Map<String, Referenceable> skills = new TreeMap<>();
    private Map<String, Referenceable> units = new TreeMap<>();


    //Constructors automatically creates the populated rulebook
    public Rulebook() throws FileNotFoundException, TNTException { //FIX THROWS W/O CATCHES{
        ruleBookMaker();
    }

    //Make FileReader read each file and convert them
    private void ruleBookMaker() throws FileNotFoundException, TNTException {
        RulebookMaker reader = new CSVConverter();
        itemTraits = reader.getItemTraits();
        equipment = reader.getEquipment();
        armors = reader.getArmors();
        weapons = reader.getWeapons();
        skills = reader.getSkills();
        units = reader.getUnits();
    }



    //Getters


    public Map<String, Referenceable> getEquipment() {
        return equipment;
    }

    public Map<String, Referenceable> getArmors() {
        return armors;
    }

    public Map<String, Referenceable> getWeapons() {
        return weapons;
    }

    public Map<String, Referenceable> getItemTraits() {
        return itemTraits;
    }

    public Map<String, Referenceable> getSkills() {
        return skills;
    }

    public Map<String, Referenceable> getUnits() {
        return units;
    }
}
