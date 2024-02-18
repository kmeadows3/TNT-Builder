package my.TNTBuilder.DataClasses;

import my.TNTBuilder.Unit;

public class UnitTrait implements Referenceable{
    //Instance variables
    private String name;
    private String description;
    private int skillset;

    public UnitTrait(String name, String description, int skillset){
        this.name = name;
        this.description = description;
        this.skillset = skillset;
    }


    @Override
    public String getName() { return name; }

    public String getDescription() { return description; }

    public int getSkillset() { return skillset; }
}
