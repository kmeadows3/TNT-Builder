package my.TNTBuilder.DAO;

import my.TNTBuilder.DAO.MemoryReferenceDao;
import my.TNTBuilder.Exceptions.DaoException;
import my.TNTBuilder.Models.Unit;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

public class MemoryReferenceDaoTests {
    MemoryReferenceDao dao;

    @Before
    public void setDao() throws DaoException{
        dao = new MemoryReferenceDao();
    }


    @Test
    public void get_team_options_returns_team_list(){
        List<String> expected = new ArrayList<>();
        expected.add("Caravanners");
        expected.add("Raiders");
        Assert.assertTrue(expected.containsAll(dao.getTeamOptions()));
    }

    @Test
    public void get_unit_options_returns_unit_list() throws DaoException {
        List<Unit> units = new ArrayList<>();
        units.add(dao.getUnits().get(1));
        units.add(dao.getUnits().get(2));
        units.add(dao.getUnits().get(3));
        units.add(dao.getUnits().get(4));

        Assert.assertTrue(units.containsAll(dao.getUnitOptions("Caravanners")));
    }

}
