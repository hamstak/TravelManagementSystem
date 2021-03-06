package travelPackage.airline.factories;

import travelPackage.airline.Flight;
import travelPackage.interfaces.DateWrapper;
import travelPackage.interfaces.TripFactory;
import travelPackage.utility.TravelSystemDate;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

public class FlightFactory implements TripFactory<Flight> {
    private ArrayList<Flight> flights = new ArrayList<>();

    public FlightFactory(ArrayList<Flight> fligts){
        this.flights = fligts;
    }

    public FlightFactory(){

    }
    public void createTrip(String airline, String orig, String dest, DateWrapper dateWrapper, String id) throws IllegalArgumentException{
        TravelSystemDate date = dateWrapper.getStartDate();
        Flight toAdd = new Flight(airline, orig, dest, date, id);

        if (!valiDate(date)){
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

    private boolean valiDate(TravelSystemDate date) {
        Date checkDate = new Date();
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(checkDate);

        if (date.getYear() < calendar.get(Calendar.YEAR)) return false;

        if ( date.getMonth()< calendar.get(Calendar.MONTH) && date.getYear() <= calendar.get(Calendar.YEAR)) return false;

        if (date.getDay() < calendar.get(Calendar.DAY_OF_MONTH) && date.getMonth() <= calendar.get(Calendar.MONTH)) return false;

        return true;
    }

    public ArrayList<Flight> getTrips() {
        return flights;
    }
}
