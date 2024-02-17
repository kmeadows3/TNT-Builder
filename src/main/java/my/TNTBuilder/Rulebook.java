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

    //Test reference files
    private final File equipmentFile = new File("Reference/testCSVs/testEquipment.csv");
    private final File armorFile = new File ("Reference/testCSVs/testArmor.csv");
    private final File weaponFile = new File ("Reference/testCSVs/testWeapons.csv");
    private final File itemTraitFile = new File ("Reference/testCSVs/testWeapons.csv");
    private final File unitFile = new File ("Reference/testCSVs/testArmor.csv");
    private final File unitTraitFile = new File("Reference/testCSVs/testEquipment.csv");

    //Maps to hold the different data types
    private Map<String, InventoryItem> equipment = new TreeMap<>();
    private Map<String, InventoryItem> armors = new TreeMap<>();
    private Map<String, InventoryItem> weapons = new TreeMap<>();
    private Map<String, InventoryItem> itemTraits = new TreeMap<>();

    //FileReader Object to read files
    private CSVConverter reader = new CSVConverter();


    //Make FileReader read each file and convert them

    private void ruleBookMaker() throws FileNotFoundException, TNTException { //throws w/o catches
        //Make the Equipment map
        List<String> inputFileLines = reader.readCsvFile(equipmentFile);
        equipment = reader.itemConverter(inputFileLines, "Equipment");
        //Make the Armor map
        inputFileLines = reader.readCsvFile(armorFile);
        armors = reader.itemConverter(inputFileLines, "Armor");
        //Make the Weapon map
        inputFileLines = reader.readCsvFile(weaponFile);
        weapons = reader.itemConverter(inputFileLines, "Weapon");

    }


    public static void main(String[] args) throws FileNotFoundException, TNTException{
        Rulebook rulebook = new Rulebook();
        rulebook.run();
    }


    private void run () throws FileNotFoundException, TNTException{  //FIX UNCAUGHT THROW
        ruleBookMaker();
        System.out.println();
    }







}
