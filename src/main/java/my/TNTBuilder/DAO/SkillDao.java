package my.TNTBuilder.DAO;

import my.TNTBuilder.DataClasses.Referenceable;

import java.util.Map;

public interface SkillDao {

    public Map<String, Referenceable> getSkills();
}
