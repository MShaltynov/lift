package org.example;

import java.util.*;

public class Building {
    private List<Floor> floors;
    private int maxFloor;
    private LiftCabin liftCabin;
    public static int totalFloors;

    public List<Floor> getFloors() {
        return floors;
    }

    public Building(int maxFloor) {

        this.maxFloor = maxFloor;
        this.floors = initializeFloors();

        this.liftCabin = new LiftCabin(floors);
    }

    public int getMaxFloor() {
        return maxFloor;
    }

    private List<Floor> initializeFloors() {
        List<Floor> floorList = new ArrayList<>();
        for (int i = 0; i < maxFloor; i++) {
            floorList.add(new Floor(i,maxFloor));
        }
        return floorList;
    }

    public LiftCabin getLiftCabin() {
        return liftCabin;
    }

    public void setLiftCabin(LiftCabin liftCabin) {
        this.liftCabin = liftCabin;
    }


}
