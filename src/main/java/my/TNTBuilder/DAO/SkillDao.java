package my.TNTBuilder.DAO;

import my.TNTBuilder.Models.Referenceable;

import java.util.Map;

public interface SkillDao {

    public Map<Integer, Referenceable> getSkills();
}
