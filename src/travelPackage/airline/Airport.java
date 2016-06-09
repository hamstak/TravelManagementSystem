package travelPackage.airline;

import travelPackage.interfaces.Port;

/**
 * Created by moth on 5/22/16.
 */
public class Airport implements Comparable<Airport>, Port {
    private String name;

    public Airport(String name){
        this.name = name;
    }

    public String getName(){ return this.name; }

    @Override
    public int compareTo(Airport o) {
        return name.compareTo(o.name);
    }


    @Override
    public boolean equals(Object obj) {
        if (!(this.getClass().getSimpleName().compareTo(obj.getClass().getSimpleName()) == 0)){
            if (this.name.getClass().getSimpleName().compareTo(obj.getClass().getSimpleName()) == 0){
                String string = (String) obj;
                return this.name.equals(string);
            }
            return false;
        }
        Airport other = (Airport) obj;
        return name.equals(other.name);
    }

    public String toString(){
        return "Airport: " + name;
    }
}
