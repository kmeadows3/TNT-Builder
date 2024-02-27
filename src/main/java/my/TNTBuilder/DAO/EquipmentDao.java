package my.TNTBuilder.DAO;

import my.TNTBuilder.Models.Referenceable;

import java.util.Map;

public interface EquipmentDao {

    public Map<Integer, Referenceable> getEquipment();
}
