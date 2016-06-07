package airline;

import airline.seats.SeatClass;
import airline.seats.Section;
import interfaces.Trip;
import utility.TravelSystemDate;
import utility.Incrementor;

import java.util.ArrayList;

public class Flight implements Comparable<Flight>, Trip<Flight> {
    private String origin;
    private String destination;
    private String airline;
    private TravelSystemDate date;
    private String flightID;
    private int flightNumber = Incrementor.getInstance().next();
    private ArrayList<Section> sections = new ArrayList<>();

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
            for(int i = 0; i < s.getSeatClasses().length; i++)
            if (s.getSeatClass(i).compareTo(seatClass) == 0){
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

    public TravelSystemDate getDate(){
        return date;
    }

    @Override
    public boolean equals(Object obj){
        if (!(this.getClass().getSimpleName().equals(obj.getClass().getSimpleName()))) return false;
        Flight flight = (Flight) obj;
        return compareTo(flight) == 0;
    }

    public String toString(){
        String toReturn = "Flight " + flightID + " with " + sections.size() + " sections flies from " +
                origin + " to " + destination + " on " + date.getMonth() + "/" +
                date.getDay() + "/" + date.getYear() +", " +date.getHour()+ ":" + date.getMinute() + " ;\n";
        for (Section section : sections){
            toReturn += "\t" + section.getSeatCount() + "\n";
        }
        return toReturn;
    }
}
