package my.TNTBuilder.DAO;

import my.TNTBuilder.DAO.DataClasses.Referenceable;

import java.util.Map;

public interface UnitDao {

    public Map<String, Referenceable> getUnits();
}
