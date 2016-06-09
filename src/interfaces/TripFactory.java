package interfaces;

import utility.TravelSystemDate;

import java.util.ArrayList;

/**
 * Created by moth on 5/24/16.
 */
public interface TripFactory<T> {
    void createTrip(String company, String orig, String dest, DateWrapper date, String id);
    ArrayList<T> getTrips();
}
