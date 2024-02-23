package my.TNTBuilder.DataClasses;

public class UnitTrait implements Referenceable{
    //Instance variables
    private String name;
    private String description;
    private int id;

    public UnitTrait(int id, String name, String description){
        this.id = id;
        this.name = name;
        this.description = description;
    }

    public UnitTrait() {
    }


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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
