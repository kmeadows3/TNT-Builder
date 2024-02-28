package my.TNTBuilder;

import my.TNTBuilder.Exceptions.TNTException;
import my.TNTBuilder.Inventory.Inventory;
import my.TNTBuilder.Models.Unit;
import org.junit.*;

public class BuilderTests {
    Builder builder = null;

    Team testTeam = null;

    @Before
    public void setBuilder() throws TNTException {
        builder = new Builder();
    }

    @Test
    public void newTeam_makes_new_team_empty_team() throws TNTException{
        String name = "Test Name";
        String faction = "Caravanners";
        int money = 500;
        Team testTeam = builder.newTeam(name, faction, money);
        Assert.assertEquals(name, testTeam.getName());
        Assert.assertEquals(faction, testTeam.getFaction());
        Assert.assertEquals(money, testTeam.getMoney());
        Assert.assertEquals(0, testTeam.getUnitList().size());
        //TODO deal with inventory when that's implemented
    }

    @Test
    public void newTeam_will_fail_with_negative_starting_money(){
        String name = "Test Name";
        String faction = "Caravanners";
        int money = -100;
        try {
            Team testTeam = builder.newTeam(name, faction, money);
            Assert.fail();
        } catch (TNTException e){        }
    }

    @Test
    public void newUnit_clones_unit_successfully() throws TNTException{
        String name = "Unit Name";
        Unit unit = builder.getRulebook().getUnits().get(1);
        Team team = builder.newTeam("Team Name", "Caravanners", 500);
        builder.newUnit(name, unit);
        Assert.assertEquals(builder.getRulebook().getUnits().get(1), builder.getCurrentUnit());
        Assert.assertFalse(unit.getInventory() == builder.getCurrentUnit().getInventory());
        Assert.assertFalse(unit.getSkillList() == builder.getCurrentUnit().getSkillList());
    }

    @Test
    public void newUnit_fails_if_not_enough_money() throws TNTException{
        String name = "Unit Name";
        Unit unit = (Unit) builder.getRulebook().getUnits().get(1);
        Team team = builder.newTeam("Team Name", "Caravanners", 30);

        try {
            builder.newUnit(name, unit);
            Assert.fail();
        } catch (TNTException e){
            
        }

    }

}
