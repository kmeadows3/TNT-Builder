package my.TNTBuilder.DAO;

import my.TNTBuilder.Models.Referenceable;

import java.util.Map;

public interface ArmorDao {

    public Map<Integer, Referenceable> getArmors();
}
