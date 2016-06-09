package travelPackage.utility;

import travelPackage.interfaces.DateWrapper;

/**
 * Created by moth on 6/8/16.
 */
public class TravelSystemInterval implements DateWrapper {

    private TravelSystemDate startDate;
    private TravelSystemDate finishDate;

    public TravelSystemInterval(TravelSystemDate s, TravelSystemDate f){
        startDate = s;
        finishDate = f;
    }
    @Override
    public TravelSystemDate getStartDate() {
        return startDate;
    }

    @Override
    public TravelSystemDate getFinishDate() {
        return finishDate;
    }
}
