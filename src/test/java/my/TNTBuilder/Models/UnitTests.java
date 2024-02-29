package my.TNTBuilder.Models;
import org.junit.*;

import java.util.ArrayList;

public class UnitTests {

    private Unit unit;
    Skill testSkill;
    Skillset testSkillset;

    @Before
    public void setUnit(){
        unit = new Unit(1, "Caravanners", "Title", "Rank", "Type", 50, "Note", 1, 1, 1, 1, 1, 1, 1, new ArrayList<>(),
                new ArrayList<>(), 1, 0);
        testSkill = new Skill(1, "Scavenger", "When taking a weapon with limited ammo roll" +
                " 2d3 when determining ammo quantity and take the higher of the two. Upkeep does not need to be paid for" +
                " this unit. May not be taken by Freelancers.", 5);
        unit.getSkillList().add(testSkill);
        testSkillset = new Skillset();
        testSkillset.setId(1);
        testSkillset.setCategory("Test");
        testSkillset.setName("TestName");
        unit.getAvailableSkillsets().add(testSkillset);
    }

    @Test
    public void constructor_properly_instantiates_unit(){
        Unit unit1 = new Unit(1, "Caravanners", "Title", "Rank", "Type", 50, "Note", 1, 2, 3, 4, 5, 6, 7, new ArrayList<>(),
                new ArrayList<>(), 1, 0);
        unit1.getAvailableSkillsets().add(testSkillset);

        Assert.assertEquals(1, unit1.getId());
        Assert.assertEquals("Caravanners", unit1.getFaction());
        Assert.assertEquals("Rank", unit1.getRank());
        Assert.assertEquals("Type", unit1.getType());
        Assert.assertEquals(50,unit1.getBaseCost());
        Assert.assertEquals("Note", unit1.getNewPurchaseNote());
        Assert.assertEquals(1, unit1.getWounds());
        Assert.assertEquals(2, unit1.getDefense());
        Assert.assertEquals(3, unit1.getMettle());
        Assert.assertEquals(4, unit1.getMove());
        Assert.assertEquals(5, unit1.getRanged());
        Assert.assertEquals(6, unit1.getMelee());
        Assert.assertEquals(7, unit1.getStrength());
        Assert.assertEquals(testSkillset, unit1.getAvailableSkillsets().get(0));
        Assert.assertTrue(unit1.getSkillList().isEmpty());
        Assert.assertEquals(1, unit1.getAdditionalStartingSkills());
        Assert.assertEquals(0, unit1.getSpentExperience());
    }

    @Test
    public void clone_unit_clones_unit_with_empty_inventory_and_copied_skillList() throws CloneNotSupportedException{
        Unit unitClone = unit.clone();

        Assert.assertEquals(unit, unitClone);
        Assert.assertFalse(unit.getInventory() == unitClone.getInventory());
        Assert.assertFalse(unit.getSkillList() == unitClone.getSkillList());
        Assert.assertTrue(unitClone.getSkillList().containsAll(unitClone.getSkillList()));
        //TODO modify after inventory implemented
    }

    @Test
    public void getBsCost_returns_correct_bs_cost_no_inventory(){
        Assert.assertEquals(50, unit.getBSCost());
    }

    //TODO implement tests for BS with inventory when inventory implemented

    @Test
    public void getUpkeep_correctly_calculates_upkeep_leader(){
        unit.getSkillList().clear();
        unit.setRank("Leader");
        Assert.assertEquals(3, unit.getUnitUpkeep());
        //TODO modify after inventory implemented
    }

    @Test
    public void getUpkeep_correctly_calculates_upkeep_elite(){
        unit.getSkillList().clear();
        unit.setRank("Elite");
        Assert.assertEquals(2, unit.getUnitUpkeep());
        //TODO modify after inventory implemented
    }

    @Test
    public void getUpkeep_correctly_calculates_upkeep_rank_and_file(){
        unit.getSkillList().clear();
        unit.setRank("Rank and File");
        Assert.assertEquals(1, unit.getUnitUpkeep());
        //TODO modify after inventory implemented
    }

    @Test
    public void getUpkeep_correctly_calculates_upkeep_specialist(){
        unit.getSkillList().clear();
        unit.setRank("Rank and File");
        Assert.assertEquals(1, unit.getUnitUpkeep());
        //TODO modify after inventory implemented
    }

    @Test
    public void getUpkeep_correctly_calculates_upkeep_scavenger(){
        Assert.assertEquals(0, unit.getUnitUpkeep());
        //TODO modify after inventory implemented
    }

    @Test
    public void getCostToAdvance_correct_20_exp(){
        unit.setSpentExperience(10);
        unit.setUnspentExperience(10);
        Assert.assertEquals(5, unit.costToAdvance());
    }

    @Test
    public void getCostToAdvance_correct_45_exp(){
        unit.setSpentExperience(30);
        unit.setUnspentExperience(10);
        Assert.assertEquals(6, unit.costToAdvance());
    }

    @Test
    public void getCostToAdvance_correct_74_exp(){
        unit.setSpentExperience(64);
        unit.setUnspentExperience(10);
        Assert.assertEquals(7, unit.costToAdvance());
    }

    @Test
    public void getCostToAdvance_correct_75_exp(){
        unit.setSpentExperience(65);
        unit.setUnspentExperience(10);
        Assert.assertEquals(8, unit.costToAdvance());
    }


}
