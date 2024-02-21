package my.TNTBuilder.DAO;

import my.TNTBuilder.DAO.DataClasses.Referenceable;

import java.util.Map;

public interface ItemTraitDao {

    public Map<String, Referenceable> getItemTraits();
}
