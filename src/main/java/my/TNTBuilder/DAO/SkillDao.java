package my.TNTBuilder.DAO;

import my.TNTBuilder.Models.Referenceable;
import my.TNTBuilder.Models.Skill;

import java.util.List;
import java.util.Map;

public interface SkillDao {

    public Map<Integer, Skill> getSkills();

    public List<Skill> stringListToSkillList (List<String> stringList);
}
