package my.TNTBuilder.DAO.File;

import my.TNTBuilder.DAO.SkillDao;
import my.TNTBuilder.DAO.SkillsetDao;
import my.TNTBuilder.DAO.UnitDao;
import my.TNTBuilder.Exceptions.DaoException;
import my.TNTBuilder.Models.Skill;
import my.TNTBuilder.Models.Skillset;
import my.TNTBuilder.Models.Unit;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

public class FileUnitDao implements UnitDao {


    private File unitFile = new File ("Reference/testCSVs/testUnits.csv");
    private Map<Integer, Unit> units;
    private final CSVReader reader = new CSVReader();
    private SkillDao skillDao = null;
    private SkillsetDao skillsetDao = null;




    //Methods
    public List<String> getTeamOptions(){
        List<String> teamList = new ArrayList<>();
        for(Map.Entry<Integer, Unit> entry : units.entrySet()){
            Unit unit = entry.getValue();
            String faction = unit.getFaction();
            if (!teamList.contains(faction)){
                teamList.add(faction);
            }
        }
        return teamList;
    }

    public List<Unit> getUnitOptions(String faction){
        List<Unit> unitOptions = new ArrayList<>();
        for (Map.Entry<Integer, Unit> entry : units.entrySet()){
            Unit unit = entry.getValue();
            if (unit.getFaction().equals(faction)){
                unitOptions.add(unit);
            }
        }
        return unitOptions;
    }





    //Constructors
    public FileUnitDao(SkillDao skillDao, SkillsetDao skillsetDao) throws DaoException{
        this.skillDao = skillDao;
        this.skillsetDao = skillsetDao;
        this.units = stringArrayToMap(getFileLines(unitFile));

    }

    public FileUnitDao(File unitFile, SkillDao skillDao, SkillsetDao skillsetDao) throws DaoException{
        this.unitFile = unitFile;
        this.skillDao = skillDao;
        this.skillsetDao = skillsetDao;
        this.units = stringArrayToMap(getFileLines(unitFile));


    }


    //Getters
    public Map<Integer, Unit> getUnits() {
        return units;
    }


    //Methods to populate the unit map
    private List<String> getFileLines(File file) throws DaoException{
        List<String> fileLines = null;
        try {
            fileLines = reader.readCsvFile(unitFile);
        } catch (FileNotFoundException e ) {
            throw new DaoException("File not found", e);
        }
        return fileLines;
    }


    private Map<Integer, Unit> stringArrayToMap(List<String> fileLines){
        Map<Integer, Unit> units = new TreeMap<>();
        for (int i = 1; i < fileLines.size(); i++){
            Unit newItem = null;
            newItem = stringToUnit(fileLines.get(i));
            units.put(newItem.getId(), newItem);
        }
        return units;
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
        List<String> skillsAsStrings = traitListStringSplitter(unitParts[14]);
        List<Skill> skillList = skillDao.stringListToSkillList(skillsAsStrings);
        int startingSkills = Integer.parseInt(unitParts[15]);
        int[] availableSkillsets = skillsetSplitter(unitParts[16]);
        List<Skillset> skillsetList = skillsetDao.skillsetsFromSkillsetIdArray(availableSkillsets);
        int startingExp = convertStartingExp(rank);

        return new Unit(id, faction, title, rank, type, baseCost, purchaseNotes, wounds, defense, mettle, move, ranged,
                melee, strength, skillsetList, skillList, startingSkills, startingExp);
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
