package travelPackage.sealine.cabins;

/**
 * Created by moth on 6/8/16.
 */
public enum CabinType {
    F("Family", 4), DF("Deluxe Family", 6), C("Couple", 2), DC("Deluxe Couple", 2);

    private final String name;
    private final int capacity;

    CabinType(String name, int cap){
        this.name = name;
        this.capacity = cap;
    }
}
