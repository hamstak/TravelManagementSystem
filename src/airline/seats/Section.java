package airline.seats;

import java.util.HashMap;

public class Section {
    private SeatClass[] seatClass;
    private boolean[][] isTaken;
    private int seatCount;
    private SectionLayout[] seatLayout;
    private HashMap<SeatClass, Double> priceMap = new HashMap<>();

    public Section(SeatClass[] s, SectionLayout[] layout, int[] rows, double[] prices){
        seatClass = s;
        int totalRows = 0;
        seatCount = 0;
        for (int x = 0; x < layout.length; x++)
            totalRows += rows[x];
        seatLayout = new SectionLayout[totalRows];
        isTaken = new boolean[totalRows][];
        totalRows = 0;
        for (int x = 0; x < layout.length; x++){
            for (int y = 0; y < rows[x]; y++){
                seatLayout[y + totalRows] = layout[x];
            }
            totalRows += rows[x];
        }
        for (int x = 0; x < isTaken.length; x++)
            isTaken[x] = new boolean[seatLayout[x].getSeats().length];

        for (int x = 0; x < isTaken.length; x++){
            seatCount+= isTaken[x].length;
        }
        for (int x = 0; x < seatClass.length; x++){
            priceMap.put(seatClass[x], prices[x]);
        }
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

    public SeatClass getSeatClass(int i){
        return seatClass[i];
    }

    public boolean check(int row, int column) {
        return row < isTaken.length && column < isTaken[0].length;
    }

    public double getPrice(SeatClass s){
        return priceMap.get(s);
    }
}
