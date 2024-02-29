package my.TNTBuilder;
import my.TNTBuilder.Exceptions.FailedMoneyTransaction;
import my.TNTBuilder.Exceptions.InvalidUnitPurchaseException;
import my.TNTBuilder.Inventory.Inventory;
import my.TNTBuilder.Models.Skill;
import my.TNTBuilder.Models.Unit;
import org.junit.*;

import java.util.ArrayList;

public class TeamTests {

    private Team team;

    @Before
    public void setTeam() {
        team = new Team(1, "Name", "Caravanners", 200, new ArrayList<>(), new Inventory());
    }

    @Test
    public void constructor_properly_instantiates_team(){
        Team testTeam = new Team(1, "Name", "Caravanners", 200, new ArrayList<>(), new Inventory());
        Assert.assertEquals(1, testTeam.getId());
        Assert.assertEquals("Name", testTeam.getName());
        Assert.assertEquals("Caravanners", testTeam.getFaction());
        Assert.assertEquals(200, testTeam.getMoney());
        Assert.assertTrue(testTeam.getUnitList().isEmpty());
        //TODO test inventory once inventory is implemented
    }



    @Test
    public void get_BS_cost_totals_correctly() throws InvalidUnitPurchaseException{
        Unit unit1 = new Unit(1, "Caravanners", "Title", "Rank", "Type", 50, "Note", 1, 1, 1, 1, 1, 1, 1, new ArrayList<>(),
                new ArrayList<>(), 1, 0);
        Unit unit2 = new Unit(2, "Caravanners", "Title", "Rank", "Type", 100, "Note", 1, 1, 1, 1, 1, 1, 1, new ArrayList<>(),
                new ArrayList<>(), 1, 0);
        team.getUnitList().add(unit1);
        team.getUnitList().add(unit2);
        //TODO when inventory is implemented, add inventory to test
        Assert.assertEquals(150, team.getBSCost());
    }

    //TODO add relicCount tests when implemented

    @Test
    public void get_upkeep_totals_correctly_one_unit() throws InvalidUnitPurchaseException{
        Unit unit1 = new Unit(1, "Caravanners", "Title", "Elite", "Type", 50, "Note", 1, 1, 1, 1, 1, 1, 1, new ArrayList<>(),
                new ArrayList<>(), 1, 0);
        team.getUnitList().add(unit1);
        Assert.assertEquals(2, team.getUpkeep());
    }

    @Test
    public void get_upkeep_totals_correctly_multiple_units() throws InvalidUnitPurchaseException{
        Unit unit1 = new Unit(1, "Caravanners", "Title", "Elite", "Type", 50, "Note", 1, 1, 1, 1, 1, 1, 1, new ArrayList<>(),
                new ArrayList<>(), 1, 0);
        Unit unit2 = new Unit(2, "Caravanners", "Title", "Leader", "Type", 50, "Note", 1, 1, 1, 1, 1, 1, 1, new ArrayList<>(),
                new ArrayList<>(), 1, 0);
        Unit unit3 = new Unit(3, "Caravanners", "Title", "Rank and File", "Type", 50, "Note", 1, 1, 1, 1, 1, 1, 1, new ArrayList<>(),
                new ArrayList<>(), 1, 0);
        team.getUnitList().add(unit1);
        team.getUnitList().add(unit2);
        team.getUnitList().add(unit3);
        Assert.assertEquals(6, team.getUpkeep());
    }

    @Test
    public void get_upkeep_ignores_units_with_scavenger() throws InvalidUnitPurchaseException{
        Unit unit1 = new Unit(1, "Caravanners", "Title", "Elite", "Type", 50, "Note", 1, 1, 1, 1, 1, 1, 1, new ArrayList<>(),
                new ArrayList<>(), 1, 0);
        Unit unit2 = new Unit(2, "Caravanners", "Title", "Leader", "Type", 50, "Note", 1, 1, 1, 1, 1, 1, 1, new ArrayList<>(),
                new ArrayList<>(), 1, 0);
        Skill scavenger = new Skill(1, "Scavenger", "When taking a weapon with limited ammo roll" +
                " 2d3 when determining ammo quantity and take the higher of the two. Upkeep does not need to be paid for" +
                " this unit. May not be taken by Freelancers.", 5);
        unit2.getSkillList().add(scavenger);

        team.getUnitList().add(unit1);
        team.getUnitList().add(unit2);
        Assert.assertEquals(2, team.getUpkeep());
    }



}
