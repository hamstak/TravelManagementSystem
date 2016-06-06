import airline.Airline;
import airline.Airport;
import airline.Flight;
import airline.factories.*;
import airline.seats.SeatClass;
import airline.seats.Section;
import interfaces.*;

import java.util.ArrayList;
import java.util.HashMap;

public class AirSystemManager implements SystemManager {
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

    public void createFlight(String airline, String orig, String dest, int year, int month, int day, String id){
        ArrayList<Airport> airports = airportFactory.getPorts();
        if (!airlineFactory.getCompanies().contains(airline)){
            System.out.println("Flight not created: Airline " + airline + " does not exist!");

            //TODO : Handle non-existing airports properly? If this is even possible

        }else if (!(orig.length() == 3 && dest.length() == 3)){
            System.out.println("Airport(s): " + (airports.contains(orig) ? "" : orig + " ") + (airports.contains(dest) ? "" : dest + ""));
        }else{
            try{
                flightFactory.createTrip(airline, orig, dest, year, month, day, id);
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
                flight.addSection(new Section(rows, cols, s));
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

    public void bookSeat(String air, String flightID, SeatClass s, int row, char col){
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
        return null;
    }
}
