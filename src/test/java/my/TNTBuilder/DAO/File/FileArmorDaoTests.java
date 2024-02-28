package my.TNTBuilder.DAO.File;
import my.TNTBuilder.Exceptions.DaoException;
import my.TNTBuilder.Models.Armor;
import org.junit.*;

import java.io.File;
import java.util.ArrayList;
import java.util.Map;

public class FileArmorDaoTests {
    File testFile = new File("src/test/resources/testArmor.csv");
    FileArmorDao dao = null;

    @Before
    public void setDao() throws DaoException {
        dao = new FileArmorDao(testFile);
    }

    @Test
    public void get_armors_returns_correct_map(){
        Map<Integer, Armor> testMap = dao.getArmors();
        Armor expectedArmor = new Armor(1, "Ballistic Shield", 8, "", new ArrayList<>(),
                "N/A", false, 1, 1, true, 10,12);
        expectedArmor.getItemTraits().add("Shield");
        Assert.assertEquals(6, testMap.size());
        Assert.assertEquals(expectedArmor, testMap.get(1));
        Assert.assertTrue(expectedArmor.getItemTraits().containsAll(testMap.get(1).getItemTraits()));
    }
}
