package travelPackage.sealine.cabins;

import travelPackage.interfaces.Section;

import java.util.ArrayList;

/**
 * Created by moth on 6/8/16.
 */
public class CabinSection implements Section<CabinType>{
    private ArrayList<Cabin> cabins = new ArrayList<>();
    private double price;

    public CabinSection(CabinType c, int amount, double price){
        for (int i = 0; i < amount; i++){
            cabins.add(new Cabin(c));
        }
        this.price = price;
    }

    public CabinType getType() {
        Cabin check = cabins.get(0);
        return cabins.get(0).getType();
    }

    @Override
    public boolean check(int row, int column) {
        for (Cabin cabin : cabins){
            if (!cabin.isTaken()) return true;
        }
        return false;
    }

    @Override
    public boolean takeSeat(int row, int column) {
        for (Cabin cabin: cabins){
            if (!cabin.isTaken()){
                cabin.take();
                return true;
            }
        }
        return false;
    }

    @Override
    public boolean isTaken(int row, int column) {
        return false;
    }

    public int getSeatCount() {
        return cabins.size();
    }
}
