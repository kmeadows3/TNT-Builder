package my.TNTBuilder.DAO.File;

import my.TNTBuilder.DAO.SkillsetDao;
import my.TNTBuilder.Exceptions.DaoException;
import my.TNTBuilder.Models.Skillset;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

public class FileSkillsetDao implements SkillsetDao {

    private Map<Integer, Skillset> skillsets;
    private File skillsetFile = new File ("Reference/testCSVs/testSkillsets.csv");;
    private final CSVReader reader = new CSVReader();



    public FileSkillsetDao() throws DaoException {
        this.skillsets = stringArrayToMap(getFileLines(skillsetFile));
    }

    public FileSkillsetDao(File skillsetFile) throws DaoException{
        this.skillsetFile = skillsetFile;
        this.skillsets = stringArrayToMap(getFileLines(skillsetFile));
    }






    //Methods

    private List<String> getFileLines(File file) throws DaoException{
        List<String> fileLines = null;
        try {
            fileLines = reader.readCsvFile(skillsetFile);
        } catch (FileNotFoundException e ) {
            throw new DaoException("File not found", e);
        }
        return fileLines;
    }

    private Map<Integer, Skillset> stringArrayToMap(List<String> fileLines) {
        Map<Integer, Skillset> skillset = new TreeMap<>();
        for (int i = 1; i < fileLines.size(); i++){
            Skillset newItem = stringToSkillset(fileLines.get(i));
            skillset.put(newItem.getId(), newItem);
        }
        return skillset;
    }

    private Skillset stringToSkillset(String string) {
        String[] skillsetParts = string.split(",");
        int id = Integer.parseInt(skillsetParts[0]);
        String name = skillsetParts[1];
        String category = skillsetParts[2];
        Skillset currentItem = new Skillset();
        currentItem.setName(name);
        currentItem.setCategory(category);
        currentItem.setId(id);
        return currentItem;
    }



    //GETTER

    public Map<Integer, Skillset> getSkillsets() {
        return skillsets;
    }

}
