package my.TNTBuilder.DAO.File;

import my.TNTBuilder.DAO.SkillDao;
import my.TNTBuilder.DAO.SkillsetDao;
import my.TNTBuilder.Exceptions.DaoException;
import my.TNTBuilder.Models.Armor;
import my.TNTBuilder.Models.Skill;
import my.TNTBuilder.Models.Skillset;
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
        SkillDao skillDao = new FileSkillDao();
        SkillsetDao skillsetDao = new FileSkillsetDao();
        dao = new FileUnitDao(testFile, skillDao, skillsetDao);
    }

    @Test
    public void get_units_returns_correct_map(){
        Map<Integer, Unit> testMap = dao.getUnits();
        Unit expected = new Unit(4, "Caravanners", "Defender", "Rank and File", "Human",
                23, "", 1, 6, 5, 5, 4,
                4, 5, new ArrayList<>(), new ArrayList<>(), 0, 0);
        Skill brave = new Skill(5, "Brave", "+2 bonus when making Will tests.", 7);
        expected.getSkillList().add(brave);

        Skillset testSkillset = new Skillset();
        testSkillset.setId(1);
        testSkillset.setCategory("Skill");
        testSkillset.setName("Melee");
        expected.getAvailableSkillsets().add(testSkillset);
        testSkillset = new Skillset();
        testSkillset.setId(2);
        testSkillset.setCategory("Skill");
        testSkillset.setName("Marksmanship");
        expected.getAvailableSkillsets().add(testSkillset);
        testSkillset = new Skillset();
        testSkillset.setId(3);
        testSkillset.setCategory("Skill");
        testSkillset.setName("Survival");
        expected.getAvailableSkillsets().add(testSkillset);


        Assert.assertEquals(8, testMap.size());
        Assert.assertEquals(expected, testMap.get(4));
        Assert.assertTrue(expected.getSkillList().containsAll(testMap.get(4).getSkillList()));
        Assert.assertTrue(expected.getAvailableSkillsets().containsAll(testMap.get(4).getAvailableSkillsets()));
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
