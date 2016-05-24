package interfaces;


import airline.seats.SeatClass;
import airline.seats.Section;

public interface Trip<T> {
    int compareTo(T t);
    void addSection(Section s);
    int seatCount();
    Section checkSeats(SeatClass s);
    String getID();
    String getOrigin();
    String getDestination();
    boolean equals(Object obj);
    String toString();


}
