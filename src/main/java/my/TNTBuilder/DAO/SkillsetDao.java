package my.TNTBuilder.DAO;

import my.TNTBuilder.Models.Referenceable;
import my.TNTBuilder.Models.Skillset;

import java.util.Map;

public interface SkillsetDao {

    public Map<Integer, Skillset> getSkillsets();
}
