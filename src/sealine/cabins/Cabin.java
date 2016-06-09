package sealine.cabins;

/**
 * Created by moth on 6/8/16.
 */
public class Cabin {
    private boolean isTaken = false;
    private CabinType type;

    public Cabin(CabinType c){
        type = c;
    }

    public boolean isTaken(){return isTaken;}

    public boolean take(){
        if (!isTaken){
            isTaken = true;
            return true;
        }
        return false;
    }

    public CabinType getType() {
        return type;
    }
}
