package travelPackage.interfaces;

import java.util.ArrayList;

/**
 * Created by moth on 5/24/16.
 */
public interface PortFactory<T> {
    void createPort(String name);
    ArrayList<T> getPorts();
}
