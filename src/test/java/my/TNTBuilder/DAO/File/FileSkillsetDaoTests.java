package my.TNTBuilder.DAO.File;

import my.TNTBuilder.Exceptions.DaoException;
import my.TNTBuilder.Models.Armor;
import my.TNTBuilder.Models.Equipment;
import my.TNTBuilder.Models.Skillset;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.io.File;
import java.util.ArrayList;
import java.util.Map;

public class FileSkillsetDaoTests {

    File testFile = new File("src/test/resources/testSkillsets.csv");
    FileSkillsetDao dao = null;

    @Before
    public void setDao() throws DaoException {
        dao = new FileSkillsetDao(testFile);
    }

    @Test
    public void get_armors_returns_correct_map(){
        Map<Integer, Skillset> testMap = dao.getSkillsets();
        Skillset expected = new Skillset();
        expected.setCategory("Skill");
        expected.setId(1);
        expected.setName("Melee");
        Assert.assertEquals(15, testMap.size());
        Assert.assertEquals(expected, testMap.get(1));
    }
}
