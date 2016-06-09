package sealine.factories;

import interfaces.DateWrapper;
import interfaces.TripFactory;
import sealine.Cruise;
import utility.TravelSystemDate;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

/**
 * Created by moth on 6/7/16.
 */
public class SeaTripFactory implements TripFactory<Cruise>{
    ArrayList<Cruise> cruises = new ArrayList<>();
    
    public void createTrip(String sealine, String orig, String dest, DateWrapper dateWrapper, String id) throws IllegalArgumentException{
        TravelSystemDate dateStart = dateWrapper.getStartDate();
        TravelSystemDate dateFinish = dateWrapper.getFinishDate();

        Cruise toAdd = new Cruise(sealine, orig, dest, dateStart, dateFinish, id);

        if (!valiDate(dateStart) || !valiDate(dateFinish)){
            throw new IllegalArgumentException("Cruise not created: Invalid date!");
        }else if (cruises.contains(toAdd)){
            throw new IllegalArgumentException("Cruise not created: Cruise " + id + " already exists!");
        }

        if(!validInterval(dateWrapper))
            throw new IllegalArgumentException("Cruise not created: Cruise does not cover a valid interval!");
        cruises.add(toAdd);
    }

    private boolean validInterval(DateWrapper dateWrapper) {
        Date startDate = new Date(dateWrapper.getStartDate().getYear() - 1900, dateWrapper.getStartDate().getMonth(), dateWrapper.getStartDate().getDay());
        Date endDate = new Date(dateWrapper.getFinishDate().getYear() - 1900, dateWrapper.getFinishDate().getMonth(), dateWrapper.getFinishDate().getDay());

        if (endDate.getTime() - startDate.getTime() <= 0)
            return false;

        if ((endDate.getTime() - startDate.getTime())/(8.64*Math.pow(10,7)) <= 21)
            return true;
        return false;
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
