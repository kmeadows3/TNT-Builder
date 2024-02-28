package my.TNTBuilder.Models;

import java.util.List;

public class Equipment extends InventoryItem{
    public Equipment(int id, String type, int cost, String specialRules, List<String> itemTraits, String rarity, boolean isRelic) {
        super(id, type, cost, specialRules, itemTraits, rarity, isRelic);
    }

    public Equipment() {
    }


}
