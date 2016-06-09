package travelPackage.airline.seats;

/**
 * Created by moth on 6/6/16.
 */
public enum SectionLayout{
    S(new Seat[]{new AisleWindow(), new AisleWindow()}, 'S'),
    M(new Seat[]{new Window(), new Aisle(), new Aisle(), new Window()}, 'M'),
    W(new Seat[]{new Window(), new Default(), new Aisle(), new Aisle(), new Default(), new Window()}, 'W');


    private char id;
    private Seat[] seats;

    SectionLayout(Seat[] seats, char id){
        this.seats = seats;
        this.id = id;
    }

    public Seat[] getSeats() {
        return seats;
    }

    public char getID() {return id;}

    private static class AisleWindow implements Seat{

        @Override
        public boolean isAisle() {
            return true;
        }

        @Override
        public boolean isWindow() {
            return true;
        }
    }

    private static class Default implements Seat{

        @Override
        public boolean isAisle() {
            return false;
        }

        @Override
        public boolean isWindow() {
            return false;
        }
    }

    private static class Window implements Seat{

        @Override
        public boolean isAisle() {
            return false;
        }

        @Override
        public boolean isWindow() {
            return true;
        }
    }

    private static class Aisle implements Seat{

        @Override
        public boolean isAisle() {
            return true;
        }

        @Override
        public boolean isWindow() {
            return false;
        }
    }
}
