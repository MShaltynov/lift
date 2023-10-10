package org.example;

import java.util.*;
import java.util.stream.Collectors;

public class Floor {


    private Set<Passenger> passengersGoUp;
    private Set<Passenger> passengersGoDown;


    public int getCurrentFloor() {
        return currentFloor;
    }

    private final int currentFloor;
    private final int maxFloor;

    public Floor(int floorNumber, int maxFloor) {
        this.currentFloor = floorNumber;
        this.maxFloor = maxFloor;
        Set<Passenger> passengers = generatePassengers();
        passengersGoDown = generatePassengerGoDown(passengers);
        passengersGoUp = generatePassengerGoUP(passengers);
    }

    public Set<Passenger> getPassengersGoUp() {
        return passengersGoUp;
    }


    public Set<Passenger> getPassengersGoDown() {
        return passengersGoDown;
    }



    private Set<Passenger> generatePassengerGoUP(Set<Passenger> passengers) {

        return passengers.stream()
                .filter(passenger -> passenger.getRequiredFloor() > passenger.getCurrentFloor())
                .collect(Collectors.toSet());
    }

    private Set<Passenger> generatePassengerGoDown(Set<Passenger> passengers) {
        return passengers.stream()
                .filter(passenger -> passenger.getRequiredFloor() < passenger.getCurrentFloor())
                .collect(Collectors.toSet());
    }

    private Set<Passenger> generatePassengers() {
        Set<Passenger> passengers = new HashSet<>();
        Random random = new Random();
        int maxPassenger = random.nextInt(10);
        for (int i = 0; i < maxPassenger; i++) {
            passengers.add(new Passenger(currentFloor, maxFloor));
        }
        return passengers;
    }

    @Override
    public String toString() {
        return Integer.toString(currentFloor);
    }
}
