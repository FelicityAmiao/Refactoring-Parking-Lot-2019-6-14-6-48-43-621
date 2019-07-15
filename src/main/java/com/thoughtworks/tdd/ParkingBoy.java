package com.thoughtworks.tdd;

public class ParkingBoy {
    protected static final String UNRECOGNIEZD_PARKING_TICKET = "Unrecognized parking ticket.";
    protected static final String NOT_ENOUGH_POSITION = "Not enough position.";
    protected static final String NOT_PROVIDE_TICKET = "Please provide your parking ticket.";
    protected ParkingLot parkingLotFirst;
    protected ParkingLot parkingLotSecond;
    protected String errorMessage;

    public void setParkingLot(ParkingLot parkingLotFirst, ParkingLot parkingLotSecond) {
        this.parkingLotFirst = parkingLotFirst;
        this.parkingLotSecond = parkingLotSecond;
    }

    public Ticket park(Car car, Ticket ticket) {
        if(isParkingLotsFull() || isCarValid(car)) {
            this.errorMessage = NOT_ENOUGH_POSITION;
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

    protected boolean isCarValid(Car car) {
        return car == null || isCarParked(car);
    }

    private boolean isCarParked(Car car) {
        return parkingLotFirst.hasParkedCar(car) || parkingLotSecond.hasParkedCar(car);
    }

    protected boolean isParkingLotsFull() {
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
            this.errorMessage = UNRECOGNIEZD_PARKING_TICKET;
        }
        if(ticket == null) {
            this.errorMessage = NOT_PROVIDE_TICKET;
        }
    }

    private boolean isRecognizedTicket(Ticket ticket) {
        return parkingLotFirst.isFakeOrUsedTicket(ticket) || parkingLotSecond.isFakeOrUsedTicket(ticket);
    }

    public String getErrorMessage() {
        return errorMessage;
    }

}
