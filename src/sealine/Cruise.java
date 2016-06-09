package sealine;

import airline.Flight;
import airline.seats.FlightSection;
import airline.seats.SeatClass;
import interfaces.Section;
import interfaces.Trip;
import sealine.cabins.CabinSection;
import sealine.cabins.CabinType;
import utility.TravelSystemDate;

import java.util.ArrayList;

/**
 * Created by moth on 6/7/16.
 */
public class Cruise implements Trip<Cruise>, Comparable<Cruise>{
    private String origin;
    private String destination;
    private String sealine;
    private TravelSystemDate date;
    private String cruiseID;
    private ArrayList<CabinSection> sections = new ArrayList<>();


    public Cruise(String sl, String orig, String dest, TravelSystemDate date, String cr){
        sealine = sl;
        origin = orig;
        destination = dest;
        this.date = date;
        cruiseID = cr;
    }



    public void addSection(CabinSection s){sections.add(s);}

    @Override
    public int compareTo(Cruise o) {
        if(sealine.compareTo(o.sealine) != 0) return sealine.compareTo(o.sealine);

        if (origin.compareTo(o.origin) != 0) return origin.compareTo(o.sealine);

        if (destination.compareTo(o.destination) != 0) return destination.compareTo(o.destination);
        int compare = Integer.compare(date.getYear() + date.getMonth() + date.getDay(), o.date.getYear() + o.date.getMonth() + o.date.getDay());
        if ( compare != 0) return compare;

        return cruiseID.compareTo(o.cruiseID);
    }

    @Override
    public void addSection(FlightSection s) {

    }

    public int seatCount(){
        int toReturn = 0;
        for (CabinSection s : sections){
            toReturn += s.getSeatCount();
        }
        return toReturn;
    }

    public Section checkSeats(Section s){
        for (CabinSection c : sections){
            if (c.getType() == s.getType())
                return s;
        }
        return null;
    }

    public String getID(){
        return cruiseID;
    }

    public String getOrigin() {
        return origin;
    }

    public String getDestination() {
        return destination;
    }

    public TravelSystemDate getDate(){
        return date;
    }

    @Override
    public boolean equals(Object obj){
        if (!(this.getClass().getSimpleName().equals(obj.getClass().getSimpleName()))) return false;
        Cruise cruise = (Cruise) obj;
        return compareTo(cruise) == 0;
    }

    public String toString(){
        String toReturn = "Flight " + cruiseID + " with " + sections.size() + " sections flies from " +
                origin + " to " + destination + " on " + date.getMonth() + "/" +
                date.getDay() + "/" + date.getYear() +", " +date.getHour()+ ":" + date.getMinute() + " ;\n";
        for (CabinSection section : sections){
            toReturn += "\t" + section.getSeatCount() + "\n";
        }
        return toReturn;
    }
}
