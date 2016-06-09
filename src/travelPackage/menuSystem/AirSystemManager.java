package travelPackage.menuSystem;

import travelPackage.airline.Airline;
import travelPackage.airline.Airport;
import travelPackage.airline.Flight;
import travelPackage.airline.factories.*;
import travelPackage.airline.SeatClass;
import travelPackage.airline.seats.FlightSection;
import travelPackage.interfaces.*;
import travelPackage.utility.FileExport;
import travelPackage.utility.FileImport;
import travelPackage.utility.TravelSystemDate;
import travelPackage.utility.UserUtil;

import java.util.ArrayList;
import java.util.HashMap;

public class AirSystemManager implements SystemManager {
    private static final String[] MENU = {"Create an Airport ",
                                          "Create an Airline ",
                                          "Create a Flight",
                                          "Create a FlightSection",
                                          "Find Available Flights",
                                          "Book seat",
                                          "Read air system from file",
                                          "Write air system to file",
                                          "Go back"};

    private CompanyFactory<Airline> airlineFactory = new AirlineFactory();
    private PortFactory<Airport> airportFactory = new AirportFactory();
    private TripFactory<Flight> flightFactory = new FlightFactory();
    private HashMap<String, SystemManager> systemMap;

    public AirSystemManager(){}

    public AirSystemManager(ArrayList<Airport> ports, ArrayList<Airline> lines, ArrayList<Flight> flights){
        airportFactory = new AirportFactory(ports);

        airlineFactory = new AirlineFactory(lines);

        flightFactory = new FlightFactory(flights);

    }

    public void createAirport(String name){
        try {
            airportFactory.createPort(name);
        }catch (Exception e){
            System.out.println(e.getMessage());
        }

    }

    public void createAirline(String name){
        try{
            airlineFactory.createCompany(name);
        }catch (Exception e){
            System.out.println(e.getMessage());
        }
    }

    public void createFlight(String airline, String orig, String dest, TravelSystemDate date, String id){
        ArrayList<Airport> airports = airportFactory.getPorts();
        if (!airlineFactory.getCompanies().contains(new Airline(airline))){
            System.out.println("Flight not created: Airline " + airline + " does not exist!");

            //TODO : Handle non-existing airports properly? If this is even possible

        }else if (!(orig.length() == 3 && dest.length() == 3)){
            System.out.println("Airport(s): " + (airports.contains(new Airport(orig)) ? "" : orig + " ") + (airports.contains(new Airport(dest)) ? "" : dest + ""));
        }else{
            try{
                flightFactory.createTrip(airline, orig, dest, date, id);
            }catch (Exception e){
                System.out.println(e.getMessage());
            }
        }
    }

    public void createSection(String airline, String flightID, int rows, int cols, SeatClass s){
        if (!airlineFactory.getCompanies().contains(new Airline(airline))){
            System.out.println("FlightSection not created: Airline " + airline + " not found!");
            return;
        }
        for (Flight flight : flightFactory.getTrips()){
            if (flight.getID().compareTo(flightID) == 0 && flight.checkSeats(new FlightSection(s, null, 0, 0)) == null){
                flight.addSection(new FlightSection(null, null, 0, 0));
            }
        }
    }

    public ArrayList<Flight> findAvailableFlights(String orig, String dest){
        ArrayList<Flight> available = new ArrayList<>();
        for(Flight flight : flightFactory.getTrips()){
            if (validFlight(flight, orig, dest))
                available.add(flight);
        }
        System.out.println(available.size() + " flights found.");
        return available;
    }

    private boolean validFlight(Flight flight, String orig, String dest) {
        return flight.getOrigin().compareTo(orig) == 0 && flight.getDestination().compareTo(dest) == 0 && flight.seatCount() > 0;
    }

    public void bookSeat(String flightID, SeatClass s, int row, char col){
        int column = Character.isLowerCase(col) ? col - 39 : col - 65;
        FlightSection fake = new FlightSection(s, null, 0, 0);
        for(Flight flight : flightFactory.getTrips()){
            if(flightID.compareTo(flight.getID()) == 0){
                if (flight.checkSeats(fake) != null && flight.checkSeats(fake).check(row, column) && !flight.checkSeats(fake).isTaken(row, column)) {
                    flight.checkSeats(fake).takeSeat(row, column);
                    return;
                }else{
                    System.out.println("Seat not booked: Seat already taken!");
                }
            }
        }
        System.out.println("Seat not booked: Seat does not exist!");
    }

    public void displaySystemDetails(){
        System.out.println(airportFactory.getPorts().size() + " Airports:");
        for (Airport a : airportFactory.getPorts()){
            System.out.println(a);
        }
        System.out.println();

        System.out.println(airlineFactory.getCompanies().size() + " Airlines:");
        for (Airline airline : airlineFactory.getCompanies()){
            System.out.println(airline);
        }
        System.out.println();

        System.out.println(flightFactory.getTrips().size() + " Flights:");
        for(Flight flight : flightFactory.getTrips()){
            System.out.println(flight);
        }
        System.out.println();

    }


    @Override
    public SystemManager runMenu(HashMap<String, SystemManager> systemMap) {
        this.systemMap = systemMap;
        System.out.println("Air Travel Simulator 2016");
        int option;
        do {
            option = UserUtil.dynamicMenu(MENU);
            switch (option){
                case 1: createAirport(UserUtil.getUserString("Airport Name"));
                    return this;
                case 2: createAirline(UserUtil.getUserString("Airline Name"));
                    return this;
                case 3: createFlight(UserUtil.getUserString("Airline Name"),
                        UserUtil.getUserString("Origin Port"),
                        UserUtil.getUserString("Destination Port"),
                        TravelSystemDate.createDateFromInput(),
                        UserUtil.getUserString("Flight ID"));
                    return this;
                case 4: createSection(UserUtil.getUserString("Airline Name"),
                                      UserUtil.getUserString("Flight ID"),
                                      UserUtil.getUserPositiveInt("Row"),
                                      UserUtil.getUserPositiveInt("Column"),
                                      SeatClass.valueOf(UserUtil.getUserString("Seat Class (E,B,F)")));
                    return this;
                case 5: findAvailableFlights(UserUtil.getUserString("Origin"), UserUtil.getUserString("Destination"));
                    return this;
                case 6: bookSeat(UserUtil.getUserString("Flight ID"),
                            SeatClass.valueOf(UserUtil.getUserString("Seat Class (E,B,F)")),
                            UserUtil.getUserPositiveInt("Row"),
                            UserUtil.getUserString("Column Letter").charAt(0));
                    return this;
                case 7: importFile();
                    return systemMap.get("Air");
                case 8: exportFile();
                    return systemMap.get("Air");
                case 9: return systemMap.get("Default");
            }
        }while(option > 0);
        return null;
    }

    private void importFile(){
        FileImport fi = new FileImport();
        ArrayList<ArrayList<String>> trips = new ArrayList<ArrayList<String>>();
        ArrayList<Airport> ports = new ArrayList<>();
        ArrayList<Airline> lines = new ArrayList<>();
        ArrayList<Flight> flights = new ArrayList<>();
        String[] stops = fi.readFile(trips);

        ports = fi.buildPorts(stops);
        lines = fi.buildLines(trips);
        flights = fi.buildFlights(trips);

        systemMap.put("Air", new AirSystemManager(ports, lines, flights));

        System.out.println("Air system imported!");
    }

    private void exportFile() {
        FileExport fe = new FileExport();
        String airsys = fe.convertSystem(airportFactory.getPorts(), airlineFactory.getCompanies(), flightFactory.getTrips());
        fe.writeFile(airsys);

        System.out.println("Air system exported!");
    }
}
