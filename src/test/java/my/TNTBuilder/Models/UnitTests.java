package my.TNTBuilder.Models;
import org.junit.*;

import java.util.ArrayList;

public class UnitTests {

    private Unit unit;

    @Before
    public void setUnit(){
        unit = new Unit(1, "Caravanners", "Title", "Rank", "Type", 50, "Note", 1, 1, 1, 1, 1, 1, 1, new int[]{1, 2, 3},
                new ArrayList<>(), 1, 0);
        unit.getSkillList().add("Test Skill");
    }

    @Test
    public void constructor_properly_instantiates_unit(){
        Unit unit1 = new Unit(1, "Caravanners", "Title", "Rank", "Type", 50, "Note", 1, 2, 3, 4, 5, 6, 7, new int[]{1, 2, 3},
                new ArrayList<>(), 1, 0);

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
        Assert.assertArrayEquals(new int[]{1, 2, 3}, unit1.getAvailableSkillsets());
        Assert.assertTrue(unit1.getSkillList().isEmpty());
        Assert.assertEquals(1, unit1.getAdditionalStartingSkills());
        Assert.assertEquals(0, unit1.getSpentExperience());
    }

    @Test
    public void clone_unit_clones_unit_with_empty_inventory_and_copied_skillList() throws CloneNotSupportedException{
        unit.getSkillList().add("Skill");
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


}
