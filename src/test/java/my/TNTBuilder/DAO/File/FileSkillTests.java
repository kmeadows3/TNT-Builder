package my.TNTBuilder.DAO.File;

import my.TNTBuilder.Exceptions.DaoException;
import my.TNTBuilder.Models.Skill;
import org.junit.*;

import java.io.File;
import java.util.Map;

public class FileSkillTests {
    File testFile = new File("src/test/resources/testSkills.csv");
    FileSkillDao dao = null;

    @Before
    public void setDao() throws DaoException {
        dao = new FileSkillDao(testFile);
    }

    @Test
    public void get_skills_returns_correct_map(){
        Map<Integer, Skill> testMap = dao.getSkills();
        Skill expectedSkill = new Skill(1, "Scavenger", "When taking a weapon with limited ammo roll" +
                " 2d3 when determining ammo quantity and take the higher of the two. Upkeep does not need to be paid for" +
                " this unit. May not be taken by Freelancers.", 5);
        Assert.assertEquals(8, testMap.size());
        Assert.assertEquals(expectedSkill, testMap.get(1));
    }
}
