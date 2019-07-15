package com.thoughtworks.tdd;

public class ParkingBoy {
    private ParkingLot parkingLotFirst;
    private ParkingLot parkingLotSecond;
    private String errorMessage;

    public void setParkingLot(ParkingLot parkingLotFirst, ParkingLot parkingLotSecond) {
        this.parkingLotFirst = parkingLotFirst;
        this.parkingLotSecond = parkingLotSecond;
    }

    public Ticket park(Car car, Ticket ticket) {
        if(isParkingLotsFull() || isCarValid(car)) {
            this.errorMessage = "Not enough position.";
            return null;
        }
        chooseParkingLotToPark(car, ticket);
        return ticket;
    }

    private void chooseParkingLotToPark(Car car, Ticket ticket) {
        if (parkingLotFirst.isCarFull()) {
            parkingLotSecond.parkCar(car, ticket);
        } else {
            parkingLotFirst.parkCar(car, ticket);
        }
    }

    private boolean isCarValid(Car car) {
        return car == null || isCarParked(car);
    }

    private boolean isCarParked(Car car) {
        return parkingLotFirst.hasParkedCar(car) || parkingLotSecond.hasParkedCar(car);
    }

    private boolean isParkingLotsFull() {
        return parkingLotFirst.isCarFull() && parkingLotSecond.isCarFull();
    }

    public Car fetch(Ticket ticket) {
        checkTicketSetErrorMessage(ticket);
        return getCarByParkingLots(ticket);
    }

    private Car getCarByParkingLots(Ticket ticket) {
        Car fetchCar = parkingLotFirst.getCar(ticket);
        return fetchCar != null? fetchCar: parkingLotSecond.getCar(ticket);
    }

    private void checkTicketSetErrorMessage(Ticket ticket) {
        if(!isRecognizedTicket(ticket)) {
            this.errorMessage = "Unrecognized parking ticket.";
        }
        if(ticket == null) {
            this.errorMessage = "Please provide your parking ticket.";
        }
    }

    private boolean isRecognizedTicket(Ticket ticket) {
        return parkingLotFirst.isFakeOrUsedTicket(ticket) || parkingLotSecond.isFakeOrUsedTicket(ticket);
    }

    public String getErrorMessage() {
        return errorMessage;
    }

}
