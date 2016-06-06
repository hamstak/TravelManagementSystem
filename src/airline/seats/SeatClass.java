package airline.seats;

/**
 * Created by moth on 5/23/16.
 */
public enum SeatClass implements Comparable<SeatClass>{
    economy("Economy Class"), first("First Class"), business("Business Class");

    private String description;
     SeatClass(String description){
         this.description = description;
     }

}
