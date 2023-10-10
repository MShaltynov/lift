package org.example;

import java.util.*;

public class LiftCabin {

    Set<Passenger> passengers = new HashSet<>();
    List<Floor> floors;
    private int currentFloor;

    private int direction;


    public int getCurrentFloor() {
        return currentFloor;
    }

    private int maxCapacity = 10;

    private int maxFloor;

    public void setCurrentFloor(int currentFloor) {
        this.currentFloor = currentFloor;
    }

    public LiftCabin(List<Floor> floors) {
        this.maxFloor = floors.size();
        this.floors = floors;
        this.direction = 1;

        setCurrentFloor(0);
        Object o = new Object();

    }



    public int getMaxCapacity() {
        return maxCapacity;
    }


    public int calculateDirection(TreeSet<Integer> queueToUp, TreeSet<Integer> queueToDown) {
        if (queueToUp.isEmpty() && queueToDown.isEmpty()) {
            currentFloor=0;
            direction=0;
            return 0;
        }

        if ((currentFloor == 0) || (direction > 0) && (!queueToUp.isEmpty()) && (currentFloor < queueToUp.last())) {
            direction = 1;
        } else if ((currentFloor == maxFloor - 1) || ((!queueToDown.isEmpty()) && direction < 0 && currentFloor > queueToDown.first())) {
            direction = -1;
        }
        else {
            if(!queueToDown.isEmpty()){
                direction =-1;
                return direction;
            }

            direction = 0;
        }
        return direction;
    }

    public int getDirection() {
        return direction;
    }

    public int move(Set<Integer> floorsUp, TreeSet<Integer> floorsDown) {
        Iterator<Integer> floorIterator;
        int tempFloor;
        if (direction > 0) {
            floorIterator = floorsUp.iterator();
            while (floorIterator.hasNext()) {
                tempFloor = floorIterator.next();
                if (tempFloor > currentFloor) {
                    currentFloor = tempFloor;

                    break;
                }
            }
            if (floorsUp.isEmpty()&&!floorsDown.isEmpty()){
                currentFloor=floorsDown.last();
                direction=-1;
            }
        } else if (direction < 0) {
            floorIterator = floorsDown.descendingIterator();

            while (floorIterator.hasNext()) {
                tempFloor = floorIterator.next();
                if (tempFloor < currentFloor) {
                    currentFloor = tempFloor;
                    break;
                }
            }
        }
        return currentFloor;
    }
}
