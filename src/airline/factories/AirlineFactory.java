package airline.factories;

import airline.Airline;

import java.util.ArrayList;

/**
 * Created by moth on 5/23/16.
 */

public class AirlineFactory implements CompanyFactory<Airline> {
    public ArrayList<Airline> airlines = new ArrayList<>();

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
