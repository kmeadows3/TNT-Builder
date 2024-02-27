package my.TNTBuilder.DAO;

import my.TNTBuilder.Models.Referenceable;

import java.util.Map;

public interface WeaponDao {

    public Map<Integer, Referenceable> getWeapons();
}
