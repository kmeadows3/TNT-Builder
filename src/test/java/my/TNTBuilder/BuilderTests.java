package my.TNTBuilder;

import my.TNTBuilder.DataClasses.Unit;
import my.TNTBuilder.Exceptions.TNTException;
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
        Assert.assertEquals(0, testTeam.getUnitMap().size());
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
        String title = "Trade Master";
        Team team = builder.newTeam("Team Name", "Caravanners", 500);
        builder.newUnit(name, title);
        Assert.assertEquals(builder.getRulebook().getUnits().get(title), builder.getCurrentUnit());
    }

}
