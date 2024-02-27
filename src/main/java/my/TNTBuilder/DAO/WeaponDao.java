package my.TNTBuilder.DAO;

import my.TNTBuilder.Models.InventoryItem;
import my.TNTBuilder.Models.Referenceable;
import my.TNTBuilder.Models.Weapon;

import java.util.Map;

public interface WeaponDao {

    public Map<Integer, Weapon> getWeapons();
}
