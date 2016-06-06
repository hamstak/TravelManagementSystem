import airline.Airline;
import airline.Airport;
import utility.FileImport;

import java.util.ArrayList;

public class FileImportTester {
    public static void main(String[] args){
        String[] stops ;
        ArrayList<ArrayList<String>> trips = new ArrayList<ArrayList<String>>();
        utility.FileImport fi = new FileImport();

        stops = fi.readFile(trips);
        ArrayList<Airport> ports = fi.buildPorts(stops);
        System.out.println(ports);
        ArrayList<Airline> lines = fi.buildLines(trips);
        System.out.println(lines);
    }
}
