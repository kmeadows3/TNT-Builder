package my.TNTBuilder.DAO.File;

import my.TNTBuilder.DAO.WeaponDao;
import my.TNTBuilder.Exceptions.DaoException;
import my.TNTBuilder.Models.Weapon;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

public class FileWeaponDao implements WeaponDao {
    private Map<Integer, Weapon> weapons;
    private File weaponFile = new File ("Reference/testCSVs/testWeapons.csv");;
    private final CSVReader reader = new CSVReader();



    public FileWeaponDao() throws DaoException {
        this.weapons = stringArrayToMap(getFileLines(weaponFile));
    }

    public FileWeaponDao(File weaponFile) throws DaoException{
        this.weaponFile = weaponFile;
        this.weapons = stringArrayToMap(getFileLines(weaponFile));
    }






    //Methods

    private List<String> getFileLines(File file) throws DaoException{
        List<String> fileLines = null;
        try {
            fileLines = reader.readCsvFile(weaponFile);
        } catch (FileNotFoundException e ) {
            throw new DaoException("File not found", e);
        }
        return fileLines;
    }

    private Map<Integer, Weapon> stringArrayToMap(List<String> fileLines) {
        Map<Integer, Weapon> weapons = new TreeMap<>();
        for (int i = 1; i < fileLines.size(); i++){
            Weapon newItem = stringToWeapon(fileLines.get(i));
            weapons.put(newItem.getId(), newItem);
        }
        return weapons;
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

    public Map<Integer, Weapon> getWeapons() {
        return weapons;
    }

}
