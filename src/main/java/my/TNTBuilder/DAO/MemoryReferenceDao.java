package my.TNTBuilder.DAO;

import my.TNTBuilder.DAO.*;
import my.TNTBuilder.DAO.File.*;
import my.TNTBuilder.Models.*;

import my.TNTBuilder.Exceptions.DaoException;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class MemoryReferenceDao implements ReferenceDao{
    //Variables to hold the various FileDAO
    private final ArmorDao armorDao = new FileArmorDao();
    private final EquipmentDao equipmentDao = new FileEquipmentDao();
    private final WeaponDao weaponDao = new FileWeaponDao();
    private final ItemTraitDao itemTraitDao = new FileItemTraitDao();
    private final SkillDao skillDao = new FileSkillDao();
    private final SkillsetDao skillsetDao = new FileSkillsetDao();
    private final FileUnitDao unitDao = new FileUnitDao(skillDao, skillsetDao);


    public MemoryReferenceDao() throws DaoException{
    }



    //Methods
    public List<String> getTeamOptions(){
        List<String> teamList = new ArrayList<>();
        Map<Integer, Unit> units = unitDao.getUnits();
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
        Map<Integer, Unit> units = unitDao.getUnits();
        List<Unit> unitOptions = new ArrayList<>();
        for (Map.Entry<Integer, Unit> entry : units.entrySet()){
            Unit unit = entry.getValue();
            if (unit.getFaction().equals(faction)){
                unitOptions.add(unit);
            }
        }
        return unitOptions;
    }



    //Getters


    public Map<Integer, Equipment> getEquipment() {
        return equipmentDao.getEquipment();
    }

    public Map<Integer, Armor> getArmors() {
        return armorDao.getArmors();
    }

    public Map<Integer, Weapon> getWeapons() {
        return weaponDao.getWeapons();
    }

    public Map<Integer, ItemTrait> getItemTraits() {
        return itemTraitDao.getItemTraits();
    }

    public Map<Integer, Skill> getSkills() {
        return skillDao.getSkills();
    }

    public Map<Integer, Unit> getUnits() {
        return unitDao.getUnits();
    }

    public Map<Integer, Skillset> getSkillsets() {
        return skillsetDao.getSkillsets();
    }
}
