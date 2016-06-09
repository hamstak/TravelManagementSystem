package travelPackage.interfaces;

/**
 * Created by moth on 6/8/16.
 */
public interface Section<T> {
    T getType();

    boolean check(int row, int column);

    boolean takeSeat(int row, int column);

    boolean isTaken(int row, int column);
}
