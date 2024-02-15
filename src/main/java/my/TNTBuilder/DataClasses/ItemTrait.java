package my.TNTBuilder.DataClasses;

public class ItemTrait {
    private String name;
    private String effect;

    public ItemTrait(String name, String effect){
        this.name = name;
        this.effect = effect;
    }

    public String getName() {
        return name;
    }

    public String getEffect() {
        return effect;
    }
}
