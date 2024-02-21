package my.TNTBuilder.DAO.DataClasses;

import java.util.List;

public class Equipment extends InventoryItem{
    public Equipment(String type, int cost, String specialRules, List<String> itemTraits, String rarity, boolean isRelic) {
        super(type, cost, specialRules, itemTraits, rarity, isRelic);
    }
}
