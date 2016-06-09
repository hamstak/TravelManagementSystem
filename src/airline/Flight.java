package airline;

import airline.seats.FlightSection;
import interfaces.Section;
import interfaces.Trip;
import utility.TravelSystemDate;

import java.util.ArrayList;

public class Flight implements Comparable<Flight>, Trip<Flight> {
    private String origin;
    private String destination;
    private String airline;
    private TravelSystemDate date;
    private String flightID;
    private ArrayList<FlightSection> flightSections = new ArrayList<>();

    public Flight(String al, String og, String dest, TravelSystemDate date, String id){
        airline = al;
        origin = og;
        destination = dest;
        this.date = date;
        flightID = id;
    }

    @Override
    public int compareTo(Flight o) {
        if(airline.compareTo(o.airline) != 0) return airline.compareTo(o.airline);

        if (origin.compareTo(o.origin) != 0) return origin.compareTo(o.airline);

        if (destination.compareTo(o.destination) != 0) return destination.compareTo(o.destination);
        int compare = Integer.compare(date.getYear() + date.getMonth() + date.getDay(), o.date.getYear() + o.date.getMonth() + o.date.getDay());
        if ( compare != 0) return compare;

        return flightID.compareTo(o.flightID);

    }

    public void addSection(FlightSection s){
        flightSections.add(s);}

    public int seatCount(){
        int toReturn = 0;
        for (FlightSection s : flightSections){
            toReturn += s.getSeatCount();
        }
        return toReturn;
    }

    @Override
    public Section checkSeats(Section s) {
        for (FlightSection f : flightSections){
            if(f.getType() == s.getType())
                return f;
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

    public TravelSystemDate getDate(){
        return date;
    }

    public ArrayList<FlightSection> getSections(){ return flightSections; }

    @Override
    public boolean equals(Object obj){
        if (!(this.getClass().getSimpleName().equals(obj.getClass().getSimpleName()))) return false;
        Flight flight = (Flight) obj;
        return compareTo(flight) == 0;
    }

    public String toString(){
        String toReturn = "Flight " + flightID + " with " + flightSections.size() + " flightSections flies from " +
                origin + " to " + destination + " on " + date.getMonth() + "/" +
                date.getDay() + "/" + date.getYear() +", " +date.getHour()+ ":" + date.getMinute() + " ;\n";
        for (FlightSection flightSection : flightSections){
            toReturn += "\t" + flightSection.getSeatCount() + "\n";
        }
        return toReturn;
    }
}
