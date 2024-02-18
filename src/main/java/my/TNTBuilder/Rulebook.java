package my.TNTBuilder;

import my.TNTBuilder.DataClasses.*;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import my.TNTBuilder.Exceptions.TNTException;
import my.TNTBuilder.Input.CSVConverter;

public class Rulebook {
    //Maps to hold the different data types
    private Map<String, Referenceable> equipment = new TreeMap<>();
    private Map<String, Referenceable> armors = new TreeMap<>();
    private Map<String, Referenceable> weapons = new TreeMap<>();
    private Map<String, Referenceable> itemTraits = new TreeMap<>();
    private Map<String, Referenceable> unitTraits = new TreeMap<>();
    private Map<String, Referenceable> units = new TreeMap<>();


    //Constructors automatically creates the populated rulebook
    public Rulebook() throws FileNotFoundException, TNTException { //FIX THROWS W/O CATCHES{
        ruleBookMaker();
    }

    //Make FileReader read each file and convert them
    private void ruleBookMaker() throws FileNotFoundException, TNTException {
        //Test reference files, csv converter to work with them
        CSVConverter reader = new CSVConverter();
        File equipmentFile = new File("Reference/testCSVs/testEquipment.csv");
        File armorFile = new File ("Reference/testCSVs/testArmor.csv");
        File weaponFile = new File ("Reference/testCSVs/testWeapons.csv");
        File itemTraitFile = new File ("Reference/testCSVs/testItemTraits.csv");
        File unitFile = new File ("Reference/testCSVs/testUnits.csv");
        File unitTraitFile = new File("Reference/testCSVs/testUnitTraits.csv");
        //generates the maps of rules
        itemTraits = reader.stringConverter(reader.readCsvFile(itemTraitFile), "ItemTrait");
        equipment = reader.stringConverter(reader.readCsvFile(equipmentFile), "Equipment");
        armors = reader.stringConverter(reader.readCsvFile(armorFile), "Armor");
        weapons = reader.stringConverter(reader.readCsvFile(weaponFile), "Weapon");
        unitTraits = reader.stringConverter(reader.readCsvFile(unitTraitFile), "UnitTrait");
        units = reader.stringConverter(reader.readCsvFile(unitFile), "Unit");
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

    public Map<String, Referenceable> getUnitTraits() {
        return unitTraits;
    }

    public Map<String, Referenceable> getUnits() {
        return units;
    }
}
