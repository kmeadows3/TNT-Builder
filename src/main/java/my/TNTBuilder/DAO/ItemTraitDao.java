package my.TNTBuilder.DAO;

import my.TNTBuilder.Models.Referenceable;

import java.util.Map;

public interface ItemTraitDao {

    public Map<Integer, Referenceable> getItemTraits();
}
