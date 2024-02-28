package my.TNTBuilder.DAO.File;

import my.TNTBuilder.Exceptions.DaoException;
import my.TNTBuilder.Models.Armor;
import my.TNTBuilder.Models.ItemTrait;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.io.File;
import java.util.ArrayList;
import java.util.Map;

public class FileItemTraitDaoTests {
    File testFile = new File("src/test/resources/testItemTraits.csv");
    FileItemTraitDao dao = null;

    @Before
    public void setDao() throws DaoException {
        dao = new FileItemTraitDao(testFile);
    }

    @Test
    public void get_item_traits_returns_correct_map(){
        Map<Integer, ItemTrait> testMap = dao.getItemTraits();
        ItemTrait expectedItemTrait = new ItemTrait(1,"Burst", "Allow the shooter to fire twice in an " +
                "activation. If all AP is used to shoot gain 1 extra AP that must be used to fire a final time.");
        Assert.assertEquals(23, testMap.size());
        Assert.assertEquals(expectedItemTrait, testMap.get(1));
    }
}
