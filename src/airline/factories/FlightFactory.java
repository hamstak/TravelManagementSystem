package airline.factories;

import airline.Flight;
import interfaces.TripFactory;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

public class FlightFactory implements TripFactory<Flight> {
    private ArrayList<Flight> flights = new ArrayList<>();

    public void createTrip(String airline, String orig, String dest, int year, int month, int day, int hour, int minute, String id) throws IllegalArgumentException{
        Flight toAdd = new Flight(airline, orig, dest, year, month, day, hour, minute, id);
        if (!valiDate(year, month, day)){
            throw new IllegalArgumentException("Flight not created: Invalid date!");
        }else if (flights.contains(toAdd)){
            throw new IllegalArgumentException("Flight not created: Flight " + id + " already exists!");
        }
        flights.add(toAdd);
    }

    public Flight findFlight(String id){
        for(Flight f : flights){
            if(f.getID().equals(id))
                return f;
        }
        return null;
    }

    private boolean valiDate(int year, int month, int day) {
        Date date = new Date();
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);

        if (year < calendar.get(Calendar.YEAR)) return false;

        if (month < calendar.get(Calendar.MONTH) && year <= calendar.get(Calendar.YEAR)) return false;

        if (day < calendar.get(Calendar.DAY_OF_MONTH) && month <= calendar.get(Calendar.MONTH)) return false;

        return true;
    }

    public ArrayList<Flight> getTrips() {
        return flights;
    }
}
