package my.TNTBuilder.DataClasses;

public class ItemTrait implements Referenceable{
    private String name;
    private String effect;

    public ItemTrait(String name, String effect){
        this.name = name;
        this.effect = effect;
    }

    public ItemTrait() {
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
