package my.TNTBuilder.DAO.File;


import my.TNTBuilder.DAO.ArmorDao;
import my.TNTBuilder.Exceptions.DaoException;
import my.TNTBuilder.Models.Armor;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

public class FileArmorDao implements ArmorDao {

    private Map<Integer, Armor> armors;
    private File armorFile = new File ("Reference/testCSVs/testArmor.csv");;
    private final CSVReader reader = new CSVReader();



    public FileArmorDao() throws DaoException{
        this.armors = stringArrayToMap(getFileLines(armorFile));
    }

    public FileArmorDao(File armorFile) throws DaoException{
        this.armorFile = armorFile;
        this.armors = stringArrayToMap(getFileLines(armorFile));
    }






    //Methods

    private List<String> getFileLines(File file) throws DaoException{
        List<String> fileLines = null;
        try {
            fileLines = reader.readCsvFile(armorFile);
        } catch (FileNotFoundException e ) {
            throw new DaoException("File not found", e);
        }
        return fileLines;
    }

    private Map<Integer, Armor> stringArrayToMap(List<String> fileLines) {
        Map<Integer, Armor> armors = new TreeMap<>();
        for (int i = 1; i < fileLines.size(); i++){
            Armor newItem = stringToArmor(fileLines.get(i));
            armors.put(newItem.getId(), newItem);
        }
        return armors;
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

    private List<String> traitListStringSplitter(String traits){
        if (traits.isEmpty() || traits.equals("N/A")){
            return new ArrayList<String>();
        } else {
            traits = traits.substring(1,traits.length()-1);
            String[] traitList = traits.split("\\|");
            return new ArrayList<>(Arrays.asList(traitList));
        }
    }



    //GETTER

    public Map<Integer, Armor> getArmors() {
        return armors;
    }



}
