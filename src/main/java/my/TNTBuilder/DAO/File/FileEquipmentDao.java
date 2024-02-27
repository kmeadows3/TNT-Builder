package my.TNTBuilder.DAO.File;

import my.TNTBuilder.DAO.EquipmentDao;
import my.TNTBuilder.Exceptions.DaoException;
;
import my.TNTBuilder.Models.Equipment;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

public class FileEquipmentDao implements EquipmentDao {

    private Map<Integer, Equipment> equipment;
    private File equipmentFile = new File ("Reference/testCSVs/testEquipment.csv");;
    private final CSVReader reader = new CSVReader();

    public FileEquipmentDao() throws DaoException {
        this.equipment = stringArrayToMap(getFileLines(equipmentFile));
    }

    public FileEquipmentDao(File equipmentFile) throws DaoException{
        this.equipmentFile = equipmentFile;
        this.equipment = stringArrayToMap(getFileLines(equipmentFile));
    }

    //Methods

    private List<String> getFileLines(File file) throws DaoException{
        List<String> fileLines = null;
        try {
            fileLines = reader.readCsvFile(equipmentFile);
        } catch (FileNotFoundException e ) {
            throw new DaoException("File not found", e);
        }
        return fileLines;
    }

    private Map<Integer, Equipment> stringArrayToMap(List<String> fileLines) {
        Map<Integer, Equipment> equipment = new TreeMap<>();
        for (int i = 1; i < fileLines.size(); i++){
            Equipment newItem = stringToEquipment(fileLines.get(i));
            equipment.put(newItem.getId(), newItem);
        }
        return equipment;
    }


    private Equipment stringToEquipment(String string){
        String[] equipmentParts = string.split(",");
        int id = Integer.parseInt(equipmentParts[0]);
        String type = equipmentParts[1];
        int cost = Integer.parseInt(equipmentParts[2]);
        String specialRules = equipmentParts[3];
        List<String> traits = traitListStringSplitter(equipmentParts[4]);
        String rarity = equipmentParts[5];
        boolean isRelic = false;
        if (!rarity.equals("N/A")){
            isRelic = true;
        }

        return new Equipment(id, type, cost, specialRules, traits, rarity, isRelic);
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


    public Map<Integer, Equipment> getEquipment() {
        return equipment;
    }
}
