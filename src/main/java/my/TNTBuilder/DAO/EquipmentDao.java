package my.TNTBuilder.DAO;

import my.TNTBuilder.Models.Equipment;
import my.TNTBuilder.Models.InventoryItem;
import my.TNTBuilder.Models.Referenceable;

import java.util.Map;

public interface EquipmentDao {

    public Map<Integer, Equipment> getEquipment();
}
