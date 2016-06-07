package interfaces;

import java.util.ArrayList;

/**
 * Created by moth on 5/24/16.
 */
public interface TripFactory<T> {
    void createTrip(String company, String orig, String dest, int year, int month, int day, int hour, int minute, String id);
    ArrayList<T> getTrips();
}
