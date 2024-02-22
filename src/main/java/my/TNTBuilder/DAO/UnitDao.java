package my.TNTBuilder.DAO;

import my.TNTBuilder.DataClasses.Referenceable;

import java.util.Map;

public interface UnitDao {

    public Map<String, Referenceable> getUnits();
}
