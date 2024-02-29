package my.TNTBuilder;
import my.TNTBuilder.Exceptions.DaoException;
import my.TNTBuilder.Exceptions.FailedPurchaseException;
import my.TNTBuilder.Exceptions.InvalidUnitPurchaseException;
import my.TNTBuilder.Inventory.Inventory;
import my.TNTBuilder.Models.Skill;
import my.TNTBuilder.Models.Unit;
import org.junit.*;

import java.util.ArrayList;
import java.util.List;

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
    public void add_unit_adds_unit() throws InvalidUnitPurchaseException {
        Unit unit = new Unit(1, "Caravanners", "Title", "Rank", "Type", 50,
                "Note", 1, 1, 1, 1, 1, 1, 1, new ArrayList<>(),
                new ArrayList<>(), 1, 0);
        team.addUnit(unit);

        Assert.assertEquals(1, team.getUnitList().size());
        Assert.assertEquals(unit, team.getUnitList().get(0));
    }

    @Test
    public void add_unit_throws_exception_with_invalid_unit() {
        Unit unit = new Unit(1, "Raiders", "Title", "Rank", "Type", 50,
                "Note", 1, 1, 1, 1, 1, 1, 1, new ArrayList<>(),
                new ArrayList<>(), 1, 0);
        try {
            team.addUnit(unit);
            Assert.fail();
        } catch (InvalidUnitPurchaseException e) {

        }
    }

    @Test
    public void spend_money_reduces_team_money() throws FailedPurchaseException {
        team.spendMoney(150);
        Assert.assertEquals(50, team.getMoney());
    }

    @Test
    public void spend_money_fails_when_not_enough_money() {
        try{
            team.spendMoney(201);
            Assert.fail();
        } catch (FailedPurchaseException e){

        }
    }

    @Test
    public void spend_money_fails_with_negative_parameter() {
        try{
            team.spendMoney(-1);
            Assert.fail();
        } catch (FailedPurchaseException e){

        }
    }

    @Test
    public void get_BS_cost_totals_correctly() throws InvalidUnitPurchaseException{
        Unit unit1 = new Unit(1, "Caravanners", "Title", "Rank", "Type", 50, "Note", 1, 1, 1, 1, 1, 1, 1, new ArrayList<>(),
                new ArrayList<>(), 1, 0);
        Unit unit2 = new Unit(2, "Caravanners", "Title", "Rank", "Type", 100, "Note", 1, 1, 1, 1, 1, 1, 1, new ArrayList<>(),
                new ArrayList<>(), 1, 0);
        team.addUnit(unit1);
        team.addUnit(unit2);
        //TODO when inventory is implemented, add inventory to test
        Assert.assertEquals(150, team.getBSCost());
    }

    //TODO add relicCount tests when implemented

    @Test
    public void get_upkeep_totals_correctly_one_unit() throws InvalidUnitPurchaseException{
        Unit unit1 = new Unit(1, "Caravanners", "Title", "Elite", "Type", 50, "Note", 1, 1, 1, 1, 1, 1, 1, new ArrayList<>(),
                new ArrayList<>(), 1, 0);
        team.addUnit(unit1);
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
        team.addUnit(unit1);
        team.addUnit(unit2);
        team.addUnit(unit3);
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

        team.addUnit(unit1);
        team.addUnit(unit2);
        Assert.assertEquals(2, team.getUpkeep());
    }



}
