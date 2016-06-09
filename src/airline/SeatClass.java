package airline;

/**
 * Created by moth on 5/23/16.
 */
public enum SeatClass implements Comparable<SeatClass>{
    E("Economy Class"), F("First Class"), B("Business Class");

    public char encode(){
        return description.charAt(0);
    }

    private String description;
     SeatClass(String description){
         this.description = description;
     }

}
