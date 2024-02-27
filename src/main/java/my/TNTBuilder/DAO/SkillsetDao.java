package my.TNTBuilder.DAO;

import my.TNTBuilder.Models.Referenceable;

import java.util.Map;

public interface SkillsetDao {

    public Map<Integer, Referenceable> getSkillsets();
}
