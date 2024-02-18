package my.TNTBuilder.Input;

import my.TNTBuilder.DataClasses.*;
import my.TNTBuilder.Exceptions.InvalidInventoryFile;
import my.TNTBuilder.Unit;

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

    public Map<String, Referenceable> stringConverter(List<String> fileLines, String type) throws InvalidInventoryFile{

        Map<String, Referenceable> ruleItem = new TreeMap<>();
        for (int i = 1; i < fileLines.size(); i++){
            Referenceable newItem = null;
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
                case "ItemTrait":
                    newItem = stringToItemTrait(fileLines.get(i));
                    break;
                case "UnitTrait":
                    newItem = stringToUnitTrait(fileLines.get(i));
                    break;
                case "Unit":
                    newItem = stringToUnit(fileLines.get(i));
                    break;
                default:
                    throw new InvalidInventoryFile("No CSV found of that type");
            }
            ruleItem.put(newItem.getName(), newItem);
        }
        return ruleItem;
    }

    private Equipment stringToEquipment(String string){
        String[] equipmentParts = string.split(",");
        String type = equipmentParts[1];
        int cost = Integer.parseInt(equipmentParts[2]);
        String specialRules = equipmentParts[3];
        List<String> traits = traitListStringSplitter(equipmentParts[4]);
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
        List<String> traits = traitListStringSplitter(armorParts[5]);
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
        List<String> traits = traitListStringSplitter(weaponParts[10]);
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

    private ItemTrait stringToItemTrait(String string){
        String[] itemTraitParts = string.split(",");
        String name = itemTraitParts[1];
        String effect = itemTraitParts[2];
        return new ItemTrait(name, effect);
    }

    private UnitTrait stringToUnitTrait(String string){
        String[] unitTraitParts = string.split(",");
        String name = unitTraitParts[2];
        String description = unitTraitParts[3];
        int skillset = Integer.parseInt(unitTraitParts[1]);
        return new UnitTrait(name, description, skillset);
    }


    private Unit stringToUnit(String string){
        String[] unitParts = string.split(",");
        String faction = unitParts[1];
        String title = unitParts[2];
        String rank = unitParts[3];
        String type = unitParts[4];
        int baseCost = Integer.parseInt(unitParts[5]);
        int wounds = Integer.parseInt(unitParts[6]);
        int defense = Integer.parseInt(unitParts[7]);
        int mettle = Integer.parseInt(unitParts[8]);
        int move = Integer.parseInt(unitParts[9]);
        int melee = Integer.parseInt(unitParts[10]);
        int ranged = Integer.parseInt(unitParts[11]);
        int strength = Integer.parseInt(unitParts[12]);
        String purchaseNotes = unitParts[13];
        List<String> skills = traitListStringSplitter(unitParts[14]);
        int startingSkills = Integer.parseInt(unitParts[15]);
        int[] availableSkillsets = skillsetSplitter(unitParts[16]);
        int startingExp = convertStartingExp(rank);

        return new Unit(faction, title, rank, type, baseCost, purchaseNotes, wounds, defense, mettle, move, ranged,
                melee, strength, availableSkillsets, skills, startingSkills, startingExp);
    }


    private List<String> traitListStringSplitter(String traits){
        if (traits.isEmpty() || traits.equals("N/A")){
            return new ArrayList<String>();
        } else {
            traits = traits.substring(1,traits.length()-1);
            String[] traitList = traits.split("\\|");
            return new ArrayList<>(Arrays.asList(traitList));
        }
    }

    private int[] skillsetSplitter(String skillsetString){
        if (skillsetString.isEmpty() || skillsetString.equals("N/A")){
            return new int[0];
        } else {
            skillsetString = skillsetString.substring(1,skillsetString.length()-1);
            String[] skillsetArray = skillsetString.split("\\|");
            int[] skillsets = new int[skillsetArray.length];
            for (int i = 0; i < skillsets.length; i++){
                skillsets[i] = Integer.parseInt(skillsetArray[i]);
            }
            return skillsets;
        }
    }

    private int convertStartingExp(String rank){
        if (rank.equalsIgnoreCase("Rank and File")){
            return 0;
        } else if (rank.equalsIgnoreCase("Specialist")) {
            return 21;
        } else if (rank.equalsIgnoreCase("Elite")) {
            return 46;
        } else {
            return 75;
        }
    }

}
