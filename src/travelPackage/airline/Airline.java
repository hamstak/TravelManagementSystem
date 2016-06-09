package travelPackage.airline;

import travelPackage.interfaces.Line;

public class Airline implements Comparable<Airline>, Line {
    private String name;

    public Airline(String name){
        this.name = name;
    }

    public String getName(){
        return name;
    }

    @Override
    public int compareTo(Airline o) {
        return name.compareTo(o.name);
    }

    @Override
    public boolean equals(Object obj) {
        if (!(this.getClass().getSimpleName().compareTo(obj.getClass().getSimpleName()) == 0)){
            if(name.getClass().getSimpleName().equals(obj.getClass().getSimpleName())){
                return name.equals(obj);
            }
            return false;
        }
        Airline airline = (Airline) obj;
        return name.equals(airline.name);
    }

    public String toString(){
        return "Airline : " + name;
    }
}
