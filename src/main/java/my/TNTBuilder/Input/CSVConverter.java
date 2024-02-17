package my.TNTBuilder.Input;

import my.TNTBuilder.DataClasses.*;
import my.TNTBuilder.Exceptions.InvalidInventoryFile;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

public class CSVConverter {

    public List<String> readCsvFile(File itemCSV) throws FileNotFoundException {
        List<String> inputLines = new ArrayList<>();
        try(Scanner reader = new Scanner(itemCSV)){
            while (reader.hasNextLine()){
                inputLines.add(reader.nextLine());
            }
        }
        return inputLines;
    }

    public Map<String, InventoryItem> itemConverter(List<String> fileLines, String type) throws InvalidInventoryFile{

        Map<String, InventoryItem> items = new TreeMap<>();
        for (int i = 1; i < fileLines.size(); i++){
            InventoryItem newItem = null;
            switch (type) {
                case "Equipment":
                    newItem = stringToEquipment(fileLines.get(i));
                    break;
                case "Armor":
                    newItem = stringToArmor(fileLines.get(i));
                    break;
                case "Weapon":
                    newItem = stringToWeapon(fileLines.get(i));
                    break;
                default:
                    throw new InvalidInventoryFile("No CSV found of that type");
            }

            items.put(newItem.getType(), newItem);
        }
        return items;
    }

    private Equipment stringToEquipment(String string){
        String[] equipmentParts = string.split(",");
        String type = equipmentParts[1];
        int cost = Integer.parseInt(equipmentParts[2]);
        String specialRules = equipmentParts[3];
        List<ItemTrait> traits = new ArrayList<>();  //Will need to fix to return List of Item Traits if traits exist
        String rarity = equipmentParts[5];
        Boolean isRelic = false;
        if (!rarity.equals("N/A")){
            isRelic = true;
        }
        Equipment currentItem = new Equipment(type, cost, specialRules, traits, rarity, isRelic);
        return currentItem;
    }


    private Armor stringToArmor(String string){
        String[] armorParts = string.split(",");
        String type = armorParts[1];
        int cost = Integer.parseInt(armorParts[6]);
        String specialRules = armorParts[4];
        List<ItemTrait> traits = new ArrayList<>();  //Will need to fix to return List of Item Traits if traits exist
        String rarity = armorParts[10];
        boolean isRelic = !rarity.equals("N/A");
        int meleeDefenseBonus = Integer.parseInt(armorParts[2]);
        int rangedDefenseBonus = Integer.parseInt(armorParts[3]);
        int cost2Wounds = Integer.parseInt(armorParts[7]);
        int cost3Wounds = Integer.parseInt(armorParts[8]);
        boolean isShield = armorParts[9].equals("TRUE");

        return new Armor(type, cost, specialRules, traits, rarity, isRelic, meleeDefenseBonus,
                rangedDefenseBonus, isShield, cost2Wounds, cost3Wounds);
    }

    private Weapon stringToWeapon(String string){
        String[] weaponParts = string.split(",");
        String type = weaponParts[1];
        int cost = Integer.parseInt(weaponParts[2]);
        String specialRules = weaponParts[9];
        List<ItemTrait> traits = new ArrayList<>();  //Will need to fix to return List of Item Traits if traits exist
        String rarity = weaponParts[11];
        boolean isRelic = !rarity.equals("N/A");
        int meleeRange = Integer.parseInt(weaponParts[3]);
        int rangedRange = Integer.parseInt(weaponParts[4]);
        int strength = Integer.parseInt(weaponParts[5]);
        int reliability = Integer.parseInt(weaponParts[6]);
        int handsRequired = Integer.parseInt(weaponParts[7]);
        String category = weaponParts[8];

        return new Weapon(type, cost, specialRules, traits, rarity, isRelic, meleeRange, rangedRange, strength,
        reliability, handsRequired, category);
    }

}
