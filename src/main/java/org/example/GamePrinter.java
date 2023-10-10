package org.example;

import java.util.Iterator;
import java.util.TreeSet;

public class GamePrinter {
    private Building building;

    public GamePrinter(Building building) {
        this.building = building;
    }
    private char upArrow = '\u2191';  // Unicode for ↑
    private char downArrow = '\u2193';
    public void print() {


        Iterator<Floor> floorIterator = building.getFloors().iterator();
        for (int i = building.getFloors().size()-1; i >=0; i--) {
            Floor floor = building.getFloors().get(i);
            System.out.print(floor.getCurrentFloor() + "| " + upArrow + floor.getPassengersGoUp().size() + " " + downArrow + floor.getPassengersGoDown().size());
            if (building.getLiftCabin().getCurrentFloor() == floor.getCurrentFloor()) {
                System.out.println(" [" +getDirectionChar()+ building.getLiftCabin().passengers.size() + "]"+building.getLiftCabin().passengers);
            } else {
                System.out.println();
            }

        }
    }

    private char getDirectionChar() {
        if (building.getLiftCabin().getDirection()>0){
            return upArrow;
        } else if(building.getLiftCabin().getDirection()<0){
            return downArrow;
        }
        else return 0;
    }

}
