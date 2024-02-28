package my.TNTBuilder.Models;

import java.util.Objects;

public class Skill extends UnitTrait{
    //Instance variables
    private int skillset;

    public Skill(int id, String name, String description, int skillset){
        super(id, name,description);
        this.skillset = skillset;
    }

    public Skill() {
    }

    public int getSkillset() { return skillset; }


    public void setSkillset(int skillset) {
        this.skillset = skillset;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        Skill skill = (Skill) o;
        return skillset == skill.skillset;
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), skillset);
    }
}
