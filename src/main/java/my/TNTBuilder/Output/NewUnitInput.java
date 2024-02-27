package my.TNTBuilder.Output;

import my.TNTBuilder.Models.Unit;

public class NewUnitInput {
    String name;
    Unit unit;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Unit getUnit() {
        return unit;
    }

    public void setUnit(Unit unit) {
        this.unit = unit;
    }
}
