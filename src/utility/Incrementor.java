package utility;

import airline.Flight;

import java.util.Iterator;

/**
 * Created by moth on 5/23/16.
 */
public abstract class Incrementor implements Iterator<Integer>{
    private int current = 0;
    private static Incrementor instance;
    private Incrementor(){

    }

    public static Incrementor getInstance(){
        if (instance == null) instance = new Incrementor();
        return instance;
    }


    @Override
    public boolean hasNext() {
        if (current != Integer.MAX_VALUE) return true;
        return false;
    }

    @Override
    public Integer next() {
        if (hasNext()){
            return ++current;
        }
        current = 0;
        return current;
    }

}
