package com.thoughtworks.tdd;

public class SuperSmartParkingBoy extends ParkingBoy{
    private ParkingLot parkingLotFirst;
    private ParkingLot parkingLotSecond;
    private String errorMessage;

    public void setParkingLot(ParkingLot parkingLotFirst, ParkingLot parkingLotSecond) {
        this.parkingLotFirst = parkingLotFirst;
        this.parkingLotSecond = parkingLotSecond;
    }

    public Ticket park(Car car, Ticket ticket) {
        if(car == null || (parkingLotFirst.isCarFull() && parkingLotSecond.isCarFull()) || (parkingLotFirst.hasParkedCar(car) || parkingLotSecond.hasParkedCar(car))) {
            this.errorMessage = "Not enough position.";
            return null;
        }else {
            if (parkingLotFirst.isCarFull() || parkingLotFirst.getRestCapacity() * 1.0 / parkingLotFirst.getTotalCapacity() < parkingLotSecond.getRestCapacity() * 1.0 / parkingLotSecond.getTotalCapacity()) {
                parkingLotSecond.parkCar(car, ticket);
            } else {
                parkingLotFirst.parkCar(car, ticket);
            }
            return ticket;
        }
    }

    public Car fetch(Ticket ticket) {
        if(!(parkingLotFirst.isFakeOrUsedTicket(ticket) || parkingLotSecond.isFakeOrUsedTicket(ticket))) {
            this.errorMessage = "Unrecognized parking ticket.";
        }
        if(ticket == null) {
            this.errorMessage = "Please provide your parking ticket.";
        }
        Car fetchCar = parkingLotFirst.getCar(ticket);
        return fetchCar != null? fetchCar: parkingLotSecond.getCar(ticket);
    }

    public String getErrorMessage() {
        return errorMessage;
    }

    public String checkTwoLotRestCapacity() {
        return "first: "+ parkingLotFirst.getRestCapacity() +", second: " + parkingLotSecond.getRestCapacity();
    }

    public String checkTwoLotPositionRate() {
        return "first: "+ parkingLotFirst.getRestCapacity() * 1.0 / parkingLotFirst.getTotalCapacity() +", second: " + parkingLotSecond.getRestCapacity() * 1.0 / parkingLotSecond.getTotalCapacity();
    }
}
