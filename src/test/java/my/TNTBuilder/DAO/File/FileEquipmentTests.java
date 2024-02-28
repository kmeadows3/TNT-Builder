package my.TNTBuilder.DAO.File;

import my.TNTBuilder.Exceptions.DaoException;
import my.TNTBuilder.Models.Armor;
import my.TNTBuilder.Models.Equipment;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.io.File;
import java.util.ArrayList;
import java.util.Map;

public class FileEquipmentTests {
    File testEquipmentFile = new File("src/test/resources/testEquipment.csv");
    FileEquipmentDao dao = null;

    @Before
    public void setDao() throws DaoException {
        dao = new FileEquipmentDao(testEquipmentFile);
    }

    @Test
    public void get_equipment_returns_correct_map(){
        Map<Integer, Equipment> testMap = dao.getEquipment();
        Equipment expectedEquipment = new Equipment(1, "Berserker Brew", 3, "When consumed before battle gain +1 to " +
                "move and melee but gain Frenzied", new ArrayList<>(), "N/A", false);
        Assert.assertEquals(7, testMap.size());
        Assert.assertEquals(expectedEquipment, testMap.get(1));
    }
}
