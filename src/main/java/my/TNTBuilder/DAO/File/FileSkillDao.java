package my.TNTBuilder.DAO.File;

import my.TNTBuilder.DAO.SkillDao;
import my.TNTBuilder.Exceptions.DaoException;
import my.TNTBuilder.Models.Skill;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

public class FileSkillDao implements SkillDao {

    private Map<Integer, Skill> skills;
    private File skillFile = new File ("Reference/testCSVs/testSkills.csv");;
    private final CSVReader reader = new CSVReader();



    public FileSkillDao() throws DaoException {
        this.skills = stringArrayToMap(getFileLines(skillFile));
    }

    public FileSkillDao(File armorFile) throws DaoException{
        this.skillFile = armorFile;
        this.skills = stringArrayToMap(getFileLines(armorFile));
    }



    //Methods

    private List<String> getFileLines(File file) throws DaoException{
        List<String> fileLines = null;
        try {
            fileLines = reader.readCsvFile(skillFile);
        } catch (FileNotFoundException e ) {
            throw new DaoException("File not found", e);
        }
        return fileLines;
    }

    private Map<Integer, Skill> stringArrayToMap(List<String> fileLines) {
        Map<Integer, Skill> skills = new TreeMap<>();
        for (int i = 1; i < fileLines.size(); i++){
            Skill newItem = stringToSkill(fileLines.get(i));
            skills.put(newItem.getId(), newItem);
        }
        return skills;
    }

    private Skill stringToSkill(String string){
        String[] unitTraitParts = string.split(",");
        int id = Integer.parseInt(unitTraitParts[0]);
        String name = unitTraitParts[2];
        String description = unitTraitParts[3];
        int skillset = Integer.parseInt(unitTraitParts[1]);
        return new Skill(id, name, description, skillset);
    }




    //GETTER

    public Map<Integer, Skill> getSkills() {
        return skills;
    }
}
