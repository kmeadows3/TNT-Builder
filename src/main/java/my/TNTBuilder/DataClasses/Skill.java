package my.TNTBuilder.DataClasses;

public class Skill extends UnitTrait{
    //Instance variables
    private String name;
    private String description;
    private int skillset;

    public Skill(String name, String description, int skillset){
        super(name,description);
        this.skillset = skillset;
    }

    public Skill() {
    }

    public int getSkillset() { return skillset; }


    public void setName(String name) {
        this.name = name;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setSkillset(int skillset) {
        this.skillset = skillset;
    }
}
