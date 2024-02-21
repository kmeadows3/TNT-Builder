package my.TNTBuilder.DAO.DataClasses;

public class ItemTrait implements Referenceable{
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
