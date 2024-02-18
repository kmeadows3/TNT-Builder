package my.TNTBuilder.DataClasses;

import my.TNTBuilder.Unit;

public class UnitTrait implements Referenceable{
    //Instance variables
    private String name;
    private String description;

    public UnitTrait(String name, String description){
        this.name = name;
        this.description = description;
    }


    @Override
    public String getName() { return name; }

    public String getDescription() { return description; }

}
