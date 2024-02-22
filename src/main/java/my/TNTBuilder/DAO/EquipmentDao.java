package my.TNTBuilder.DAO;

import my.TNTBuilder.DataClasses.Referenceable;

import java.util.Map;

public interface EquipmentDao {

    public Map<String, Referenceable> getEquipment();
}
