package my.TNTBuilder.DAO;

import my.TNTBuilder.DataClasses.*;
import my.TNTBuilder.Exceptions.DaoException;
import my.TNTBuilder.DataClasses.Unit;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

public class CSVConverter implements ArmorDao, WeaponDao, EquipmentDao, ItemTraitDao, SkillDao, UnitDao, RulebookMaker{

    private File equipmentFile = new File("Reference/testCSVs/testEquipment.csv");
    private File armorFile = new File ("Reference/testCSVs/testArmor.csv");
    private File weaponFile = new File ("Reference/testCSVs/testWeapons.csv");
    private File itemTraitFile = new File ("Reference/testCSVs/testItemTraits.csv");
    private File unitFile = new File ("Reference/testCSVs/testUnits.csv");
    private File skillFile = new File("Reference/testCSVs/testSkills.csv");
    private Map<String, Referenceable> equipment;
    private Map<String, Referenceable> armors;
    private Map<String, Referenceable> weapons;
    private Map<String, Referenceable> itemTraits;
    private Map<String, Referenceable> skills;
    private Map<String, Referenceable> units;


    public CSVConverter() throws DaoException{

        try {
            itemTraits = stringConverter(readCsvFile(itemTraitFile), "ItemTrait");
            equipment = stringConverter(readCsvFile(equipmentFile), "Equipment");
            armors = stringConverter(readCsvFile(armorFile), "Armor");
            weapons = stringConverter(readCsvFile(weaponFile), "Weapon");
            skills = stringConverter(readCsvFile(skillFile), "Skill");
            units = stringConverter(readCsvFile(unitFile), "Unit");
        } catch (FileNotFoundException e){
            throw new DaoException("Inventory File Not Found");
        }
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

    //Methods
    public List<String> readCsvFile(File itemCSV) throws FileNotFoundException {
        List<String> inputLines = new ArrayList<>();
        try(Scanner reader = new Scanner(itemCSV)){
            while (reader.hasNextLine()){
                inputLines.add(reader.nextLine());
            }
        }
        return inputLines;
    }

    private Map<String, Referenceable> stringConverter(List<String> fileLines, String type) {

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
                case "Skill":
                    newItem = stringToSkill(fileLines.get(i));
                    break;
                case "Unit":
                    newItem = stringToUnit(fileLines.get(i));
                    break;
                default:
                    break;
            }
            ruleItem.put(newItem.getName(), newItem);
        }
        return ruleItem;
    }

    private Equipment stringToEquipment(String string){
        String[] equipmentParts = string.split(",");
        int id = Integer.parseInt(equipmentParts[0]);
        String type = equipmentParts[1];
        int cost = Integer.parseInt(equipmentParts[2]);
        String specialRules = equipmentParts[3];
        List<String> traits = traitListStringSplitter(equipmentParts[4]);
        String rarity = equipmentParts[5];
        Boolean isRelic = false;
        if (!rarity.equals("N/A")){
            isRelic = true;
        }
        Equipment currentItem = new Equipment(id, type, cost, specialRules, traits, rarity, isRelic);
        return currentItem;
    }

    private Armor stringToArmor(String string){
        String[] armorParts = string.split(",");
        int id = Integer.parseInt(armorParts[0]);
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

        return new Armor(id, type, cost, specialRules, traits, rarity, isRelic, meleeDefenseBonus,
                rangedDefenseBonus, isShield, cost2Wounds, cost3Wounds);
    }

    private Weapon stringToWeapon(String string){
        String[] weaponParts = string.split(",");
        int id = Integer.parseInt(weaponParts[0]);
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

        return new Weapon(id, type, cost, specialRules, traits, rarity, isRelic, meleeRange, rangedRange, strength,
        reliability, handsRequired, category);
    }

    private ItemTrait stringToItemTrait(String string){
        String[] itemTraitParts = string.split(",");
        int id = Integer.parseInt(itemTraitParts[0]);
        String name = itemTraitParts[1];
        String effect = itemTraitParts[2];
        return new ItemTrait(id, name, effect);
    }

    private Skill stringToSkill(String string){
        String[] unitTraitParts = string.split(",");
        int id = Integer.parseInt(unitTraitParts[0]);
        String name = unitTraitParts[2];
        String description = unitTraitParts[3];
        int skillset = Integer.parseInt(unitTraitParts[1]);
        return new Skill(id, name, description, skillset);
    }


    private Unit stringToUnit(String string){
        String[] unitParts = string.split(",");
        int id = Integer.parseInt(unitParts[0]);
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

        return new Unit(id, faction, title, rank, type, baseCost, purchaseNotes, wounds, defense, mettle, move, ranged,
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
