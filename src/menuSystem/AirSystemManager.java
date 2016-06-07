package menuSystem;

import airline.Airline;
import airline.Airport;
import airline.Flight;
import airline.factories.*;
import airline.seats.SeatClass;
import airline.seats.Section;
import interfaces.*;
import utility.TravelSystemDate;
import utility.UserUtil;

import java.util.ArrayList;
import java.util.HashMap;

public class AirSystemManager implements SystemManager {
    private static final String[] MENU = {"Create an Airport ",
                                          "Create an Airline ",
                                          "Create a Flight",
                                          "Create a Section",
                                          "Find Available Flights",
                                          "Book seat",
                                          "Go back"};

    private CompanyFactory<Airline> airlineFactory = new AirlineFactory();
    private PortFactory<Airport> airportFactory = new AirportFactory();
    private TripFactory<Flight> flightFactory = new FlightFactory();

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
        if (!airlineFactory.getCompanies().contains(airline)){
            System.out.println("Flight not created: Airline " + airline + " does not exist!");

            //TODO : Handle non-existing airports properly? If this is even possible

        }else if (!(orig.length() == 3 && dest.length() == 3)){
            System.out.println("Airport(s): " + (airports.contains(orig) ? "" : orig + " ") + (airports.contains(dest) ? "" : dest + ""));
        }else{
            try{
                flightFactory.createTrip(airline, orig, dest, date, id);
            }catch (Exception e){
                System.out.println(e.getMessage());
            }
        }
    }

    public void createSection(String airline, String flightID, int rows, int cols, SeatClass s){
        if (!airlineFactory.getCompanies().contains(airline)){
            System.out.println("Section not created: Airline " + airline + " not found!");
            return;
        }
        for (Flight flight : flightFactory.getTrips()){
            if (flight.getID().compareTo(flightID) == 0 && flight.checkSeats(s) == null){
                flight.addSection(new Section(null, null, null, null));
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
        for(Flight flight : flightFactory.getTrips()){
            if(flightID.compareTo(flight.getID()) == 0){
                if (flight.checkSeats(s) != null && flight.checkSeats(s).check(row, column) && !flight.checkSeats(s).isTaken(row, column)) {
                    flight.checkSeats(s).takeSeat(row, column);
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
                case 7: return systemMap.get("Default");
            }
        }while(option > 0);
        return null;
    }
}
