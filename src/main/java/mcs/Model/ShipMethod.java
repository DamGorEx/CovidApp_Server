package mcs.Model;

public enum ShipMethod {
    UPC("UPC"), PP("poczta polska"), OW("odbiórk włsany");

    String name;
    ShipMethod (String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}
