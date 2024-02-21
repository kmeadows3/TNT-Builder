package my.TNTBuilder.DAO;

import my.TNTBuilder.DAO.DataClasses.Referenceable;

import java.util.Map;

public interface EquipmentDao {

    public Map<String, Referenceable> getEquipment();
}
