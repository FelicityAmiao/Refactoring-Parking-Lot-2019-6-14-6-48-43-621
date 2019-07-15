package com.thoughtworks.tdd;

import java.util.HashMap;
import java.util.Map;

public class ParkingLot {
    private Map<Ticket, Car> cars;
    private int TOTAL_CAPACITY;

    public ParkingLot(int totalCapacity) {
        this.TOTAL_CAPACITY = totalCapacity;
        cars = new HashMap<>(TOTAL_CAPACITY);
    }

    public void parkCar(Car car, Ticket ticket) {
        cars.put(ticket, car);
    }

    public Car getCar(Ticket ticket) {
        return cars.remove(ticket);
    }

    public boolean isCarFull() {
        return cars.size() == TOTAL_CAPACITY;
    }

    public boolean hasParkedCar(Car car) {
        return cars.containsValue(car);
    }

    public boolean isFakeOrUsedTicket(Ticket ticket) {
        return cars.containsKey(ticket);
    }

    public int getRestCapacity() {
        return TOTAL_CAPACITY - cars.size();
    }

    public float getTotalCapacity() {
        return TOTAL_CAPACITY;
    }
}
