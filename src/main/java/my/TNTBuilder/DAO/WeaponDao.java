package my.TNTBuilder.DAO;

import my.TNTBuilder.DataClasses.Referenceable;

import java.util.Map;

public interface WeaponDao {

    public Map<String, Referenceable> getWeapons();
}
