package airline.seats;

/**
 * Created by moth on 6/6/16.
 */
public enum SectionLayout{
    S(new Seat[]{new AisleWindow(), new AisleWindow()}),
    M(new Seat[]{new Window(), new Aisle(), new Aisle(), new Window()}),
    W(new Seat[]{new Window(), new Default(), new Aisle(), new Aisle(), new Default(), new Window()});


    private Seat[] seats;
    SectionLayout(Seat[] seats){
        this.seats = seats;
    }

    public Seat[] getSeats() {
        return seats;
    }

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
