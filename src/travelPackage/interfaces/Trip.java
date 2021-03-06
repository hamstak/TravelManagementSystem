package travelPackage.interfaces;


import travelPackage.airline.seats.FlightSection;

public interface Trip<T> {
    int compareTo(T t);
    void addSection(FlightSection s);
    int seatCount();
    Section checkSeats(Section s);
    String getID();
    String getOrigin();
    String getDestination();
    boolean equals(Object obj);
    String toString();


}
