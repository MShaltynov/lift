package org.example;

import java.util.Random;

public class Passenger {
    private final int currentFloor;
    private int requiredFloor;
    private int maxFloor;

    public Passenger(int currentFloor, int maxFloor) {
        this.currentFloor = currentFloor;
        this.maxFloor=maxFloor;
        setRequiredFloor(maxFloor);
    }


    public int getCurrentFloor() {
        return currentFloor;
    }


    public int getRequiredFloor() {
        return this.requiredFloor;
    }

    private void setRequiredFloor(int maxFloor) {
        Random random = new Random();
        int willingFloor = random.nextInt(maxFloor);
        while (willingFloor == currentFloor) {
            willingFloor = random.nextInt(maxFloor);
        }
        this.requiredFloor = willingFloor;
    }

    @Override
    public String toString() {
        return  " " + currentFloor +
                "|" + requiredFloor;
    }
}
