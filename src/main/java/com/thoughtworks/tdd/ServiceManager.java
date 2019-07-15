package com.thoughtworks.tdd;

import java.util.List;

public class ServiceManager extends ParkingBoy{
    private List<ParkingBoy> parkingBoys;
    private String errorMsgFromParkingBoy;

    public ServiceManager(List<ParkingBoy> parkingBoys) {
        this.parkingBoys = parkingBoys;
    }

    public Ticket specifyParkingBoyPark(ParkingBoy specifyParkingBoy, Car car) {
        Ticket ticket = specifyParkingBoy.park(car, new Ticket());
        this.errorMsgFromParkingBoy = specifyParkingBoy.getErrorMessage();
        return ticket;
    }

    public List<ParkingBoy> getManagerParkingBoys() {
        return this.parkingBoys;
    }

    public Car specifyParkingBoyFetch(ParkingBoy parkingBoy, Ticket ticket) {
        Car car = parkingBoy.fetch(ticket);
        this.errorMsgFromParkingBoy = parkingBoy.getErrorMessage();
        return car;
    }

    public String getErrorMsgFromParkingBoy() {
        return errorMsgFromParkingBoy;
    }
}
