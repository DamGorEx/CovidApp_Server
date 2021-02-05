package mcs.Model;

public enum ShipMethod {
    UPC("UPC"), PP("poczta polska"), OW("odbiór własny");

    String name;
    ShipMethod (String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}
