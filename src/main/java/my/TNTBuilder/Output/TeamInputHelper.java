package my.TNTBuilder.Output;

public class TeamInputHelper {
    String name;
    String faction;
    int money;


    public TeamInputHelper(String name, String faction, int money){
        this.name = name;
        this.faction = faction;
        this.money = money;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getFaction() {
        return faction;
    }

    public void setFaction(String faction) {
        this.faction = faction;
    }

    public int getMoney() {
        return money;
    }

    public void setMoney(int money) {
        this.money = money;
    }
}
