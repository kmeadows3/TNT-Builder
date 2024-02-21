package my.TNTBuilder.DAO;

import my.TNTBuilder.DAO.DataClasses.Referenceable;

import java.util.Map;

public interface SkillDao {

    public Map<String, Referenceable> getSkills();
}
