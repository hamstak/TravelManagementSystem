package airline.factories;

import airline.Airport;
import interfaces.PortFactory;

import java.util.ArrayList;
import java.util.InputMismatchException;


public class AirportFactory implements PortFactory<Airport> {
    private ArrayList<Airport> airports = new ArrayList<>();

    public void createPort(String name){
        if (name.length()!= 3) throw new InputMismatchException("Airport not created: Invalid name! [" + name + "]");
        Airport toAdd = new Airport(name);
        if(airports.contains(toAdd)) throw new InputMismatchException("Airport not created: Airport " + name + " already exists!");
        airports.add(toAdd);
    }

    public ArrayList<Airport> getPorts(){
        return airports;
    }


}
