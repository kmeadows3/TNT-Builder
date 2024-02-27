package my.TNTBuilder.DAO.File;

import my.TNTBuilder.DAO.ItemTraitDao;
import my.TNTBuilder.Exceptions.DaoException;
import my.TNTBuilder.Models.ItemTrait;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

public class FileItemTraitDao implements ItemTraitDao {
    private Map<Integer, ItemTrait> itemTraits;
    private File itemTraitFile = new File ("Reference/testCSVs/testItemTraits.csv");
    private final CSVReader reader = new CSVReader();



    public FileItemTraitDao() throws DaoException {
        this.itemTraits = stringArrayToMap(getFileLines(itemTraitFile));
    }

    public FileItemTraitDao(File armorFile) throws DaoException{
        this.itemTraitFile = armorFile;
        this.itemTraits = stringArrayToMap(getFileLines(armorFile));
    }






    //Methods

    private List<String> getFileLines(File file) throws DaoException{
        List<String> fileLines = null;
        try {
            fileLines = reader.readCsvFile(itemTraitFile);
        } catch (FileNotFoundException e ) {
            throw new DaoException("File not found", e);
        }
        return fileLines;
    }

    private Map<Integer, ItemTrait> stringArrayToMap(List<String> fileLines) {
        Map<Integer, ItemTrait> itemTraits = new TreeMap<>();
        for (int i = 1; i < fileLines.size(); i++){
            ItemTrait newItem = stringToItemTrait(fileLines.get(i));
            itemTraits.put(newItem.getId(), newItem);
        }
        return itemTraits;
    }

    private ItemTrait stringToItemTrait(String string){
        String[] itemTraitParts = string.split(",");
        int id = Integer.parseInt(itemTraitParts[0]);
        String name = itemTraitParts[1];
        String effect = itemTraitParts[2];
        return new ItemTrait(id, name, effect);
    }



    //GETTER

    public Map<Integer, ItemTrait> getItemTraits() {
        return itemTraits;
    }

}
