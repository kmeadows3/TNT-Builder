package my.TNTBuilder.DAO.DataClasses;

public class Skill extends UnitTrait{
    //Instance variables
    private String name;
    private String description;
    private int skillset;

    public Skill(String name, String description, int skillset){
        super(name,description);
        this.skillset = skillset;
    }

    public int getSkillset() { return skillset; }


}
