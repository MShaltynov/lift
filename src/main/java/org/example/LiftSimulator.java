package org.example;

import java.util.*;
import java.util.stream.Collectors;

public class LiftSimulator {
    Building building;

    GamePrinter gamePrinter;
    TreeSet<Integer> floorsUpToGetPassengers = new TreeSet<>();

    TreeSet<Integer> floorsDownToGetPassengers = new TreeSet<>();

    public void initialize() {
        Random random = new Random();
        int maxFloorNumber = random.nextInt(15, 20);
        building = new Building(maxFloorNumber);
        System.out.println("Building created");
        gamePrinter = new GamePrinter(building);
        start();


    }


    public void start() {
        // 1 get  all passengers to up and down
        int step = 1;
        gamePrinter.print();
        while (building.getLiftCabin().getDirection() != 0) {


            System.out.println("Step " + step + " ---------------------------------------");
            System.out.println("CurrentFloor " + building.getLiftCabin().getCurrentFloor());
            exitOrEnter();
            gamePrinter.print();
            checkingLiftStops();

            System.out.println("floorsUpToGetPassengers " + floorsUpToGetPassengers);
            System.out.println("floorsDownToGetPassengers " + floorsDownToGetPassengers);

            building.getLiftCabin().calculateDirection(floorsUpToGetPassengers, floorsDownToGetPassengers);
            building.getLiftCabin().move(floorsUpToGetPassengers, floorsDownToGetPassengers);

            step++;
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
        gamePrinter.print();
    }

    private void checkingLiftStops() {
//required floors+current floors
        Set<Integer> liftPassengersNecessaryFloors = building.getLiftCabin().passengers.stream()
                .map(passenger -> passenger.getRequiredFloor()).collect(Collectors.toSet());

        Set<Integer> currentUpFloors = building.getFloors().stream()
                .flatMap(floor -> floor.getPassengersGoUp().stream())
                .map(passenger -> passenger.getCurrentFloor())
                .collect(Collectors.toCollection(() -> new TreeSet<>()));
        floorsUpToGetPassengers.clear();
        floorsUpToGetPassengers.addAll(currentUpFloors);
        floorsUpToGetPassengers.addAll(liftPassengersNecessaryFloors);


        Set<Integer> currentDownFloors = building.getFloors().stream()
                .flatMap(floor -> floor.getPassengersGoDown().stream())
                .map(passenger -> passenger.getCurrentFloor())
                .collect(Collectors.toCollection(() -> new TreeSet<>()));
        floorsDownToGetPassengers.clear();
        floorsDownToGetPassengers.addAll(currentDownFloors);
        floorsDownToGetPassengers.addAll(liftPassengersNecessaryFloors);
    }

    private void exitOrEnter() {
        List<Passenger> passengersOnTheLift = new ArrayList<>();
        //passengersOnTheLift.sort(passengerComparator);
        passengersOnTheLift.addAll(building.getLiftCabin().passengers);
        int exitCounter = 0;
        //check to exit
        for (Passenger passenger : passengersOnTheLift) {
            if (passenger.getRequiredFloor() == building.getLiftCabin().getCurrentFloor()) {
                building.getLiftCabin().passengers.remove(passenger);
                try {
                    floorsUpToGetPassengers.remove(passenger);
                } catch (Exception e) {

                }
                try {
                    floorsDownToGetPassengers.remove(passenger);
                } catch (Exception e) {

                }
                exitCounter++;
            }
        }
        System.out.println(exitCounter + " passengers left the lift ");
        //check to enter
        if ((building.getLiftCabin().getDirection() > 0 || building.getLiftCabin().getCurrentFloor() == 0)
                && building.getLiftCabin().getCurrentFloor() != building.getMaxFloor() - 1) {
            addPassengersToLift(building.getFloors().get(building.getLiftCabin().getCurrentFloor()).getPassengersGoUp());
        } else if ((building.getLiftCabin().getDirection() < 0 || building.getLiftCabin().getCurrentFloor() == building.getMaxFloor() - 1)
                && (building.getLiftCabin().getCurrentFloor() != 0)) {
            addPassengersToLift(building.getFloors().get(building.getLiftCabin().getCurrentFloor()).getPassengersGoDown());
        }

    }

    private void addPassengersToLift(Set<Passenger> passengersGo) {
        int enteredCount = 0;
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(" passengers entered to the lift [");

        while (building.getLiftCabin().passengers.size() < building.getLiftCabin().getMaxCapacity() && passengersGo.size() != 0) {
            Passenger movingPassenger = passengersGo.iterator().next();
            stringBuilder.append(movingPassenger).append(" ");

            building.getLiftCabin().passengers.add(movingPassenger);
            passengersGo.remove(movingPassenger);
            enteredCount++;
        }
        stringBuilder.append("]");
        System.out.println(enteredCount +stringBuilder.toString());

    }

}
