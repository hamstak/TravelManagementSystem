package airline.seats;

/**
 * Created by moth on 5/23/16.
 */
public enum  SeatClass implements Comparable<SeatClass>{
    economy(75.00, "Economy Class"), first(10000.00, "First Class"),business(100.00, "Business Class");


    private double price;
    private String description;
     SeatClass(double price, String description){
         this.price = price;
         this.description = description;
     }

}
