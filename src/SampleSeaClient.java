import menuSystem.SeaSystemManager;
import sealine.cabins.CabinType;
import utility.TravelSystemDate;

/**
 * Created by moth on 6/8/16.
 */
public class SampleSeaClient {
    public static void main(String[] args) {

        SeaSystemManager res = new SeaSystemManager();
        res.createSeaport("DEN");
        res.createSeaport("DFW");
        res.createSeaport("LON");
        res.createSeaport("DEN");
        res.createSeaport("DENW");

        res.createSealine("DELTA");
        res.createSealine("AMER");
        res.createSealine("FRONT");
        res.createSealine("FRONTIER"); //invalid
        res.createSealine("FRONT"); //invalid

        TravelSystemDate dateStart1 = new TravelSystemDate(2016, 5, 20, 6, 50);
        TravelSystemDate dateFinish1 = new TravelSystemDate(2016, 5, 30, 6, 50);
        TravelSystemDate dateStart2 = new TravelSystemDate(2016, 3, 20, 6, 50);
        TravelSystemDate dateFinish2 = new TravelSystemDate(2016, 4, 20, 6, 40);
        TravelSystemDate dateStart3 = new TravelSystemDate(2016, 5, 20, 6, 50);
        TravelSystemDate dateFinish3 = new TravelSystemDate(26, 5, 6, 6, 50);

        res.createCruise("DELTA", "DEN", "LON", dateStart1, dateFinish1,  "123");
        res.createCruise("DELTA", "DEN", "DEH", dateStart2, dateFinish2, "567abc");
        res.createCruise("DEL", "DEN", "LON", dateStart3, dateFinish3, "567"); //invalid airline

        res.createSection("DELTA","123", 30, 2, CabinType.C);
        res.createSection("DELTA","123", 50, 3, CabinType.F);
        res.createSection("DELTA","123", 100, 3, CabinType.DF);//Invalid
        res.createSection("SWSERTT","123", 200, 5, CabinType.DC);//Invalid airline
        res.bookSeat("123", CabinType.F);
        res.bookSeat("123", CabinType.C);
        res.bookSeat("123", CabinType.DC);
        res.bookSeat("123", CabinType.DF);//Invalid airline
        res.bookSeat("123haha7", CabinType.F);// Invalid flightId
        res.bookSeat("123", CabinType.F);//already booked
        res.displaySystemDetails();
        res.findAvailableCruises("DEN", "LON");
    }
}
