package my.TNTBuilder.DataClasses;

public class UnitTrait implements Referenceable{
    //Instance variables
    private String name;
    private String description;

    public UnitTrait(String name, String description){
        this.name = name;
        this.description = description;
    }

    public UnitTrait() {
    }

    @Override
    public String getName() { return name; }

    public String getDescription() { return description; }

    public void setName(String name) {
        this.name = name;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
