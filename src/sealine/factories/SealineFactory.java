package sealine.factories;

import interfaces.CompanyFactory;
import sealine.Sealine;

import java.util.ArrayList;

/**
 * Created by moth on 6/7/16.
 */
public class SealineFactory implements CompanyFactory<Sealine>{
    private ArrayList<Sealine> sealines = new ArrayList<>();
    @Override
    public void createCompany(String name) {
        if (name.length() > 6 || name.length() == 0)
            throw new IllegalArgumentException("Airline not created: Invalid name! [" + name + "]");
        Sealine toAdd = new Sealine(name);
        if (sealines.contains(toAdd))
            throw new IllegalArgumentException("Airline not created: Airline "+ name + " already exists!");
        sealines.add(toAdd);
    }

    @Override
    public ArrayList<Sealine> getCompanies() {
        return sealines;
    }
}
