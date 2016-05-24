package airline.seats;

public class Section {
    private SeatClass seatClass;
    private boolean[][] isTaken;
    private int seatCount;

    public Section(int i, int j, SeatClass s){
        seatClass = s;
        isTaken = new boolean[i][j];
        seatCount = i*j;
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

    public SeatClass getSeatClass(){
        return seatClass;
    }

    public boolean check(int row, int column) {
        return row < isTaken.length && column < isTaken[0].length;
    }
}
