package sealine;

import interfaces.Port;

/**
 * Created by moth on 6/7/16.
 */
public class Seaport implements Port , Comparable<Seaport>{
    private String name;

    public Seaport(String n){
        name = n;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public int compareTo(Seaport o) {
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
        Seaport other = (Seaport) obj;
        return name.equals(other.name);
    }

    public String toString(){
        return "Seaport: " + name;
    }


}
