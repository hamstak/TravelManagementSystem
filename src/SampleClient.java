import airline.SeatClass;
import menuSystem.AirSystemManager;

public class SampleClient {
    /**   * @param args   */
    public static void main(String[] args) {
        // TODO Auto-generated method stub

        AirSystemManager res = new AirSystemManager();
        res.createAirport("DEN");
        res.createAirport("DFW");
        res.createAirport("LON");
        res.createAirport("DEN");
        res.createAirport("DENW");

        res.createAirline("DELTA");
        res.createAirline("AMER");
        res.createAirline("FRONT");
        res.createAirline("FRONTIER"); //invalid
        res.createAirline("FRONT"); //invalid

//        res.createFlight("DELTA", "DEN", "LON", 2016, 10, 10, "123");
//        res.createFlight("DELTA", "DEN", "DEH", 2016, 8, 8, "567abc");
//        res.createFlight("DEL", "DEN", "LON", 2016, 9, 8, "567"); //invalid airline
//        res.createFlight("DELTA", "LON33", "DEN33", 2017, 5, 7, "123");//invalid airports
//        res.createFlight("AMER", "DEN", "LON", 2010, 40, 100, "123abc");// invalid date

        res.createSection("DELTA","123", 2, 2, SeatClass.E);
        res.createSection("DELTA","123", 2, 3, SeatClass.F);
        res.createSection("DELTA","123", 2, 3, SeatClass.F);//Invalid
        res.createSection("SWSERTT","123", 5, 5, SeatClass.E);//Invalid airline
//        res.bookSeat("DELTA", "123", SeatClass.F, 1, 'A');
//        res.bookSeat("DELTA", "123", SeatClass.E, 1, 'A');
//        res.bookSeat("DELTA", "123", SeatClass.E, 1, 'B');
//        res.bookSeat("DELTA888", "123", SeatClass.B, 1, 'A');//Invalid airline
//        res.bookSeat("DELTA", "123haha7", SeatClass.B, 1, 'A');// Invalid flightId
//        res.bookSeat("DELTA", "123", SeatClass.E, 1, 'A');//already booked
        res.displaySystemDetails();
        res.findAvailableFlights("DEN", "LON");
    }
}
