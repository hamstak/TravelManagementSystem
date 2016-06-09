package travelPackage.airline.seats;

import travelPackage.airline.SeatClass;
import travelPackage.interfaces.Section;

public class FlightSection  implements Section<SeatClass>{
    private SeatClass seatClass;
    private boolean[][] isTaken;
    private int seatCount;
    private SectionLayout seatLayout;
    private double price;

    public FlightSection(SeatClass s, SectionLayout layout, int rows, double prices){
        seatClass = s;
        isTaken = new boolean[rows][layout.getSeats().length];
        seatLayout = layout;
        price = prices;
        seatCount = rows + layout.getSeats().length;
    }

    public boolean takeSeat(int i, int j){
        if (isTaken[i][j]) return false;
        isTaken[i][j] = true;
        seatCount--;
        return true;
    }

    public boolean isTaken(int i, int j){
        return isTaken[i][j];
    }

    public int getSeatCount(){
        return seatCount;
    }

    public SeatClass getType(){
        return seatClass;
    }

    public boolean check(int row, int column) {
        return row < isTaken.length && column < isTaken[0].length;
    }

    public double getPrice(){
        return price;
    }

    public SectionLayout getSeatLayout(){return seatLayout;}

    public int getRows() {return isTaken.length;}

}
