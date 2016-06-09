package menuSystem;

import interfaces.SystemManager;
import sealine.Cruise;
import sealine.Sealine;
import sealine.Seaport;
import sealine.cabins.CabinSection;
import sealine.cabins.CabinType;
import sealine.factories.SeaTripFactory;
import sealine.factories.SealineFactory;
import sealine.factories.SeaportFactory;
import utility.TravelSystemDate;
import utility.UserUtil;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by moth on 6/1/16.
 */
public class SeaSystemManager implements SystemManager {
    private static final String[] MENU = {"Create a Seaport ",
            "Create an Sealine ",
            "Create a Trip",
            "Create a FlightSection",
            "Find Available Trips",
            "Book Cabin",
            "Go back"};

    private SeaportFactory seaportFactory = new SeaportFactory();
    private SealineFactory sealineFactory = new SealineFactory();
    private SeaTripFactory seaTripFactory = new SeaTripFactory();


    public void createAirport(String name){
        try {
            seaportFactory.createPort(name);
        }catch (Exception e){
            System.out.println(e.getMessage());
        }

    }

    public void createAirline(String name){
        try{
            sealineFactory.createCompany(name);
        }catch (Exception e){
            System.out.println(e.getMessage());
        }
    }

    public void createFlight(String sealine, String orig, String dest, TravelSystemDate date, String id){
        ArrayList<Seaport> seaports = seaportFactory.getPorts();
        if (!sealineFactory.getCompanies().contains(sealine)){
            System.out.println("Trip not created: Sealine " + sealine + " does not exist!");

            //TODO : Handle non-existing airports properly? If this is even possible

        }else if (!(orig.length() == 3 && dest.length() == 3)){
            System.out.println("Seaport(s): " + (seaports.contains(orig) ? "" : orig + " ") + (seaports.contains(dest) ? "" : dest + ""));
        }else{
            try{
                seaTripFactory.createTrip(sealine, orig, dest, date, id);
            }catch (Exception e){
                System.out.println(e.getMessage());
            }
        }
    }

    public void createSection(String sealine, String flightID, int amount, double price, CabinType s){
        if (!sealineFactory.getCompanies().contains(sealine)){
            System.out.println("FlightSection not created: Sealine " + sealine + " not found!");
            return;
        }
        for (Cruise cruise : seaTripFactory.getTrips()){
            if (cruise.getID().compareTo(flightID) == 0 && cruise.checkSeats(new CabinSection(s, 0, 0)) == null){
                cruise.addSection(new CabinSection(s,amount, price ));
            }
        }
    }

    public ArrayList<Cruise> findAvailableFlights(String orig, String dest){
        ArrayList<Cruise> available = new ArrayList<>();
        for(Cruise cruise : seaTripFactory.getTrips()){
            if (validFlight(cruise, orig, dest))
                available.add(cruise);
        }
        System.out.println(available.size() + " cruises found.");
        return available;
    }

    private boolean validFlight(Cruise cruise, String orig, String dest) {
        return cruise.getOrigin().compareTo(orig) == 0 && cruise.getDestination().compareTo(dest) == 0 && cruise.seatCount() > 0;
    }

    public void bookSeat(String cruiseID, CabinType s){
        CabinSection fake = new CabinSection(s, 0, 0);
        for(Cruise cruise : seaTripFactory.getTrips()){
            if(cruiseID.compareTo(cruise.getID()) == 0){
                if (cruise.checkSeats(fake) != null && cruise.checkSeats(fake).check(0,0) && !cruise.checkSeats(fake).isTaken(0,0)) {
                    cruise.checkSeats(fake).takeSeat(0,0);
                    return;
                }else{
                    System.out.println("Cabin not booked: Cabin already taken!");
                }
            }
        }
        System.out.println("Cabin not booked: Cabin does not exist!");
    }

    public void displaySystemDetails(){
        System.out.println(seaportFactory.getPorts().size() + " Seaports:");
        for (Seaport a : seaportFactory.getPorts()){
            System.out.println(a);
        }
        System.out.println();

        System.out.println(sealineFactory.getCompanies().size() + " Sealines:");
        for (Sealine airline : sealineFactory.getCompanies()){
            System.out.println(airline);
        }
        System.out.println();

        System.out.println(seaTripFactory.getTrips().size() + " Cruises:");
        for(Cruise flight : seaTripFactory.getTrips()){
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
                        CabinType.valueOf(UserUtil.getUserString("Seat Class (E,B,F)")));
                    return this;
                case 5: findAvailableFlights(UserUtil.getUserString("Origin"), UserUtil.getUserString("Destination"));
                    return this;
                case 6: bookSeat(UserUtil.getUserString("Flight ID"),
                        CabinType.valueOf(UserUtil.getUserString("Seat Class (E,B,F)")));
                    return this;
                case 7: return systemMap.get("Default");
            }
        }while(option > 0);
        return null;
    }
}
