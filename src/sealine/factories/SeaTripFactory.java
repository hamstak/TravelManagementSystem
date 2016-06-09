package sealine.factories;

import interfaces.TripFactory;
import sealine.Cruise;
import utility.TravelSystemDate;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

/**
 * Created by moth on 6/7/16.
 */
public class SeaTripFactory implements TripFactory<Cruise>{
    ArrayList<Cruise> cruises = new ArrayList<>();
    
    public void createTrip(String sealine, String orig, String dest, TravelSystemDate date, String id) throws IllegalArgumentException{
        Cruise toAdd = new Cruise(sealine, orig, dest, date, id);

        if (!valiDate(date)){
            throw new IllegalArgumentException("Cruise not created: Invalid date!");
        }else if (cruises.contains(toAdd)){
            throw new IllegalArgumentException("Cruise not created: Cruise " + id + " already exists!");
        }
        cruises.add(toAdd);
    }

    public Cruise findCruise(String id){
        for(Cruise f : cruises){
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

    public ArrayList<Cruise> getTrips() {
        return cruises;
    }
}
