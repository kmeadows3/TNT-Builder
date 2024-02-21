package my.TNTBuilder.DAO;

import my.TNTBuilder.DAO.DataClasses.Referenceable;

import java.util.Map;

public interface WeaponDao {

    public Map<String, Referenceable> getWeapons();
}
