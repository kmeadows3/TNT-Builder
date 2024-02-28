package my.TNTBuilder.DAO.File;

import my.TNTBuilder.Exceptions.DaoException;
import my.TNTBuilder.Models.Armor;
import my.TNTBuilder.Models.Unit;
import my.TNTBuilder.Reference;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class FileUnitDaoTests {

    File testFile = new File("src/test/resources/testUnits.csv");
    FileUnitDao dao = null;

    @Before
    public void setDao() throws DaoException {
        dao = new FileUnitDao(testFile);
    }

    @Test
    public void get_units_returns_correct_map(){
        Map<Integer, Unit> testMap = dao.getUnits();
        Unit expected = new Unit(1, "Caravanners", "Trade Master", "Leader", "Human",
                80, "Starts with 2 empty skills", 2, 6, 7, 5, 6,
                6, 6, new int[]{1,2,3,4,5,6,7,8}, new ArrayList<>(), 2, 75);
        expected.getSkillList().add("Scavenger");
        Assert.assertEquals(8, testMap.size());
        Assert.assertEquals(expected, testMap.get(1));
        Assert.assertTrue(expected.getSkillList().containsAll(testMap.get(1).getSkillList()));
    }

    @Test
    public void get_team_options_returns_team_list(){
        List<String> expected = new ArrayList<>();
        expected.add("Caravanners");
        expected.add("Raiders");
        Assert.assertTrue(expected.containsAll(dao.getTeamOptions()));
    }

    @Test
    public void get_unit_options_returns_unit_list() throws DaoException{
        List<Unit> units = new ArrayList<>();
        units.add(dao.getUnits().get(1));
        units.add(dao.getUnits().get(2));
        units.add(dao.getUnits().get(3));
        units.add(dao.getUnits().get(4));

        Assert.assertTrue(units.containsAll(dao.getUnitOptions("Caravanners")));
    }
}
