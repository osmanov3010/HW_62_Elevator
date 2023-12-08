package ait.elevator.task;

import ait.elevator.model.Elevator;

public class Truck implements Runnable {
    private static Object[] monitor = {new Object(), new Object(), new Object(), new Object()};
    // идея такая, что для каждого элеватора сделать свой объект
    // костыль, но я предположу, что если бы можно было править класс Elevator,
    // то можно было бы задать equals и hashCode Elevator и в траках сделать MAP для всех элеваторов.
    // В конструкторе трака добавлять элеватор в эту мапу и дальше использовать как объект для синхронизации.
    // Не знаю как бы это влияло на производительность :)
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
            for (int j = 0; j < elevators.length; j++) {
                synchronized (monitor[j]) {
                    elevators[j].add(capacityForEachElevator);
                }
            }
        }
    }
}
