package travelPackage.menuSystem;

import travelPackage.interfaces.SystemManager;
import travelPackage.sealine.Cruise;
import travelPackage.sealine.Sealine;
import travelPackage.sealine.Seaport;
import travelPackage.sealine.cabins.CabinSection;
import travelPackage.sealine.cabins.CabinType;
import travelPackage.sealine.factories.SeaTripFactory;
import travelPackage.sealine.factories.SealineFactory;
import travelPackage.sealine.factories.SeaportFactory;
import travelPackage.utility.TravelSystemDate;
import travelPackage.utility.TravelSystemInterval;
import travelPackage.utility.UserUtil;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by moth on 6/1/16.
 */
public class SeaSystemManager implements SystemManager {
    private static final String[] MENU = {"Create a Seaport",
            "Create an Sealine",
            "Create a Trip",
            "Create a Cruise Section",
            "Find Available Trips",
            "Book Cabin",
            "Go back"};

    private SeaportFactory seaportFactory = new SeaportFactory();
    private SealineFactory sealineFactory = new SealineFactory();
    private SeaTripFactory seaTripFactory = new SeaTripFactory();


    public void createSeaport(String name){
        try {
            seaportFactory.createPort(name);
        }catch (Exception e){
            System.out.println(e.getMessage());
        }

    }

    public void createSealine(String name){
        try{
            sealineFactory.createCompany(name);
        }catch (Exception e){
            System.out.println(e.getMessage());
        }
    }

    public void createCruise(String sealine, String orig, String dest, TravelSystemDate dateStart, TravelSystemDate dateFinish, String id){
        ArrayList<Seaport> seaports = seaportFactory.getPorts();

        if (!sealineFactory.getCompanies().contains(new Sealine(sealine))){
            System.out.println("Trip not created: Sealine " + sealine + " does not exist!");

            //TODO : Handle non-existing airports properly? If this is even possible

        }else if (!(orig.length() == 3 && dest.length() == 3)){
            System.out.println("Seaport(s): " + (seaports.contains(new Seaport(orig)) ? "" : orig + " ") + (seaports.contains(new Seaport(dest)) ? "" : dest + ""));
        }else{
            try{
                seaTripFactory.createTrip(sealine, orig, dest, new TravelSystemInterval( dateStart, dateFinish), id);
            }catch (Exception e){
                System.out.println(e.getMessage());
            }
        }
    }

    public void createSection(String sealine, String flightID, int amount, double price, CabinType s){
        if (!sealineFactory.getCompanies().contains(new Sealine(sealine))){
            System.out.println("FlightSection not created: Sealine " + sealine + " not found!");
            return;
        }
        for (Cruise cruise : seaTripFactory.getTrips()){
            if (cruise.getID().compareTo(flightID) == 0 && cruise.checkSeats(new CabinSection(s, 1, 0)) == null){
                cruise.addSection(new CabinSection(s,amount, price ));
            }
        }
    }

    public ArrayList<Cruise> findAvailableCruises(String orig, String dest){
        ArrayList<Cruise> available = new ArrayList<>();
        for(Cruise cruise : seaTripFactory.getTrips()){
            if (validCruise(cruise, orig, dest))
                available.add(cruise);
        }
        System.out.println(available.size() + " cruises found.");
        return available;
    }

    private boolean validCruise(Cruise cruise, String orig, String dest) {
        return cruise.getOrigin().compareTo(orig) == 0 && cruise.getDestination().compareTo(dest) == 0 && cruise.seatCount() > 0;
    }

    public void bookSeat(String cruiseID, CabinType s){
        CabinSection fake = new CabinSection(s, 1, 0);
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
                case 1: createSeaport(UserUtil.getUserString("Airport Name"));
                    return this;
                case 2: createSealine(UserUtil.getUserString("Airline Name"));
                    return this;
                case 3: createCruise(UserUtil.getUserString("Airline Name"),
                        UserUtil.getUserString("Origin Port"),
                        UserUtil.getUserString("Destination Port"),
                        TravelSystemDate.createDateFromInput(),
                        TravelSystemDate.createDateFromInput(),
                        UserUtil.getUserString("Flight ID"));
                    return this;
                case 4: createSection(UserUtil.getUserString("Airline Name"),
                        UserUtil.getUserString("Flight ID"),
                        UserUtil.getUserPositiveInt("Row"),
                        UserUtil.getUserPositiveInt("Column"),
                        CabinType.valueOf(UserUtil.getUserString("Seat Class (E,B,F)")));
                    return this;
                case 5: findAvailableCruises(UserUtil.getUserString("Origin"), UserUtil.getUserString("Destination"));
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
