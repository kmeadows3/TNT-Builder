package my.TNTBuilder;

import my.TNTBuilder.DAO.*;
import my.TNTBuilder.DAO.File.*;
import my.TNTBuilder.Models.*;

import my.TNTBuilder.Exceptions.DaoException;

import java.util.List;
import java.util.Map;

public class Reference {
    //Variables to hold the various DAO
    private final ArmorDao armorDao = new FileArmorDao();
    private final EquipmentDao equipmentDao = new FileEquipmentDao();
    private final WeaponDao weaponDao = new FileWeaponDao();
    private final ItemTraitDao itemTraitDao = new FileItemTraitDao();
    private final SkillDao skillDao = new FileSkillDao();
    private final SkillsetDao skillsetDao = new FileSkillsetDao();
    private final FileUnitDao unitDao = new FileUnitDao(skillDao, skillsetDao);


    public Reference() throws DaoException{
    }



    //Methods
    public List<String> getTeamOptions(){
        return unitDao.getTeamOptions();
    }

    public List<Unit> getUnitOptions(String faction){
        return unitDao.getUnitOptions(faction);
    }

    public List<Skill> stringListToSkillList (List<String> stringList){
        return skillDao.stringListToSkillList(stringList);
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
