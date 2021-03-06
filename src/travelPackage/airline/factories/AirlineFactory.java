package travelPackage.airline.factories;

import travelPackage.airline.Airline;
import travelPackage.interfaces.CompanyFactory;

import java.util.ArrayList;

/**
 * Created by moth on 5/23/16.
 */

public class AirlineFactory implements CompanyFactory<Airline> {
    public ArrayList<Airline> airlines = new ArrayList<>();

    public AirlineFactory(ArrayList<Airline> lines){
        airlines = lines;
    }
    public AirlineFactory(){

    }
    public void createCompany(String name) throws IllegalArgumentException{
        if (name.length() > 6 || name.length() == 0)
            throw new IllegalArgumentException("Airline not created: Invalid name! [" + name + "]");
        Airline toAdd = new Airline(name);
        if (airlines.contains(toAdd))
            throw new IllegalArgumentException("Airline not created: Airline "+ name + " already exists!");
        airlines.add(toAdd);
    }

    public ArrayList<Airline> getCompanies(){ return airlines; }
}
