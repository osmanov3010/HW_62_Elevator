package ait.elevator;

import ait.elevator.model.Elevator;
import ait.elevator.task.Truck;

public class ElevatorAppl {
    private static final int N_TRUCK = 1000;
    private static final int N_RACES = 10;
    private static final int CAPACITY = 20;

    public static void main(String[] args) throws InterruptedException {
        Elevator[] elevators = {
                new Elevator("V. I. Lenin"),
                new Elevator("I. V. Stalin"),
                //new Elevator("N. S. Khrushchev"),
                //new Elevator("M. S. Gorbachev"),
        };

        Truck[] trucks = new Truck[N_TRUCK];
        for (int i = 0; i < trucks.length; i++) {
            trucks[i] = new Truck(N_RACES, CAPACITY, elevators);
        }
        Thread[] threads = new Thread[N_TRUCK];
        for (int i = 0; i < threads.length; i++) {
            threads[i] = new Thread(trucks[i]);
            threads[i].start();
        }
        for (Thread t : threads) {
            t.join();
        }

        for (Elevator el : elevators) {
            System.out.println("Elevator " + el.getName() + " has " + el.getCurrentVolume());
        }

    }
}
