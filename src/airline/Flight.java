package airline;

import airline.seats.SeatClass;
import airline.seats.Section;
import interfaces.Trip;
import utility.Incrementor;

import java.util.ArrayList;

public class Flight implements Comparable<Flight>, Trip<Flight> {
    private String origin;
    private String destination;
    private String airline;
    private int year;
    private int month;
    private int day;
    private String flightID;
    private int flightNumber = Incrementor.getInstance().next();
    private ArrayList<Section> sections = new ArrayList<>();

    public Flight(String al, String og, String dest, int y, int m, int d, String id){
        airline = al;
        origin = og;
        destination = dest;
        year = y;
        month = m;
        day = d;
        flightID = id;
    }

    @Override
    public int compareTo(Flight o) {
        if(airline.compareTo(o.airline) != 0) return airline.compareTo(o.airline);

        if (origin.compareTo(o.origin) != 0) return origin.compareTo(o.airline);

        if (destination.compareTo(o.destination) != 0) return destination.compareTo(o.destination);

        if ( Integer.compare(year + month + day, year + month + day) != 0) return Integer.compare(year + month + day, year + month + day);

        return flightID.compareTo(o.flightID);

    }

    public void addSection(Section s){sections.add(s);}

    public int seatCount(){
        int toReturn = 0;
        for (Section s : sections){
            toReturn += s.getSeatCount();
        }
        return toReturn;
    }

    public Section checkSeats(SeatClass seatClass){
        int toReturn = 0;
        for (Section s : sections){
            if (s.getSeatClass().compareTo(seatClass) == 0){
                return s;
            }
        }
        return null;
    }

    public String getID(){
        return flightID;
    }

    public String getOrigin() {
        return origin;
    }

    public String getDestination() {
        return destination;
    }

    @Override
    public boolean equals(Object obj){
        if (!(this.getClass().getSimpleName().equals(obj.getClass().getSimpleName()))) return false;
        Flight flight = (Flight) obj;
        return compareTo(flight) == 0;
    }

    public String toString(){
        String toReturn = "Flight " + flightID + " with " + sections.size() + " sections flies from " +
                origin + " to " + destination + " on " + month + "/" + day + "/" + year + ":\n";
        for (Section section : sections){
            toReturn += "\t" + section.getSeatCount() + "\n";
        }
        return toReturn;
    }
}
