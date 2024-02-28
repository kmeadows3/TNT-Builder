package my.TNTBuilder.DAO.File;

import my.TNTBuilder.Exceptions.DaoException;
import my.TNTBuilder.Models.Weapon;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.io.File;
import java.util.ArrayList;
import java.util.Map;

public class FileWeaponDaoTests {


    File testFile = new File("src/test/resources/testWeapons.csv");
    FileWeaponDao dao = null;

    @Before
    public void setDao() throws DaoException {
        dao = new FileWeaponDao(testFile);
    }

    @Test
    public void get_weapons_returns_correct_map(){
        Map<Integer, Weapon> testMap = dao.getWeapons();
        Weapon expectedWeapon = new Weapon(1, "Bayonet", 4, "Doesn't count against carry capacity; add unit Strength " +
                "to weapon Strength during melee attack", new ArrayList<>(),"N/A", false, 0,
                -99, 1, -99, -99, "Melee");
        Assert.assertEquals(16, testMap.size());
        Assert.assertEquals(expectedWeapon, testMap.get(1));
        Assert.assertTrue(expectedWeapon.getItemTraits().containsAll(testMap.get(1).getItemTraits()));
    }
}
