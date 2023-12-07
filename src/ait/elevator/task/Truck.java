package ait.elevator.task;

import ait.elevator.model.Elevator;

public class Truck implements Runnable {
    private static Object monitor = new Object();
    private int nRaces;
    private int capacity;
    private Elevator[] elevators;

    public Truck(int nRaces, int capacity, Elevator[] elevator) {
        this.nRaces = nRaces;
        this.capacity = capacity;
        this.elevators = elevator;
    }

    @Override
    public void run() {
        for (int i = 0; i < nRaces; i++) {
            int capacityForEachElevator = capacity / elevators.length; // It will work only for even numbers of capacity and elevator count
            for (Elevator el : elevators) {
                synchronized (monitor) {
                    el.add(capacityForEachElevator);
                }
            }

        }
    }
}
