package travelPackage.sealine.factories;

import travelPackage.interfaces.PortFactory;
import travelPackage.sealine.Seaport;

import java.util.ArrayList;
import java.util.InputMismatchException;

/**
 * Created by moth on 6/7/16.
 */
public class SeaportFactory implements PortFactory<Seaport>{
    private ArrayList<Seaport> seaports = new ArrayList<>();
    @Override
    public void createPort(String name) {
        if (name.length()!= 3) throw new InputMismatchException("Airport not created: Invalid name! [" + name + "]");
        Seaport toAdd = new Seaport(name);
        if(seaports.contains(toAdd)) throw new InputMismatchException("Airport not created: Airport " + name + " already exists!");
        seaports.add(toAdd);
    }

    @Override
    public ArrayList<Seaport> getPorts() {
        return seaports;
    }
}
