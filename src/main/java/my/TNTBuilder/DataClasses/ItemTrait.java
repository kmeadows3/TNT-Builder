package my.TNTBuilder.DataClasses;

public class ItemTrait implements Referenceable{
    private int id;
    private String name;
    private String effect;

    public ItemTrait(int id, String name, String effect){
        this.id = id;
        this.name = name;
        this.effect = effect;
    }

    public ItemTrait() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public String getEffect() {
        return effect;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setEffect(String effect) {
        this.effect = effect;
    }
}
