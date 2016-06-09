package sealine;

import interfaces.Line;

/**
 * Created by moth on 6/7/16.
 */
public class Sealine implements Line, Comparable<Sealine> {
    private String name;

    public Sealine(String n){
        name = n;
    }
    @Override
    public String getName() {
        return name;
    }

    @Override
    public int compareTo(Sealine o) {
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
        Sealine airline = (Sealine) obj;
        return name.equals(airline.name);
    }

    public String toString(){
        return "Sealine : " + name;
    }
}
