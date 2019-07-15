package com.thoughtworks.tdd;

public class SmartParkingBoy extends ParkingBoy{
    public Ticket park(Car car, Ticket ticket) {
        if(isCarValid(car) || isParkingLotsFull()) {
            this.errorMessage = NOT_ENOUGH_POSITION;
            return null;
        }
        smartChooseParkingLotToPark(car, ticket);
        return ticket;
    }

    private void smartChooseParkingLotToPark(Car car, Ticket ticket) {
        if (parkingLotFirst.isCarFull() || parkingLotFirst.getRestCapacity() < parkingLotSecond.getRestCapacity()) {
            parkingLotSecond.parkCar(car, ticket);
        } else {
            parkingLotFirst.parkCar(car, ticket);
        }
    }

    public String getErrorMessage() {
        return errorMessage;
    }

    public String checkTwoLotRestCapacity() {
        return "first: "+ parkingLotFirst.getRestCapacity() +", second: " + parkingLotSecond.getRestCapacity();
    }

}
