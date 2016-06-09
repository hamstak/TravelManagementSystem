import airline.Airline;
import airline.Airport;
import airline.Flight;
import utility.FileExport;
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
        ArrayList<Flight> flights = fi.buildFlights(trips);
        System.out.println(flights);

        FileExport fe = new FileExport();
        String sys = fe.convertSystem(ports, lines, flights);
        fe.writeFile(sys);
    }
}
