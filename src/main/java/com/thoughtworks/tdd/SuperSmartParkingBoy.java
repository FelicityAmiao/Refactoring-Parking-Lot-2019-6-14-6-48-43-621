package com.thoughtworks.tdd;

public class SuperSmartParkingBoy extends ParkingBoy{
    public Ticket park(Car car, Ticket ticket) {
        if(isCarValid(car) || isParkingLotsFull()) {
            this.errorMessage = NOT_ENOUGH_POSITION;
            return null;
        }
        superSmartChooseParkingLotToPark(car, ticket);
        return ticket;
    }

    private void superSmartChooseParkingLotToPark(Car car, Ticket ticket) {
        if (parkingLotFirst.isCarFull() || isParkingLotFirstSmallerThanSecond()) {
            parkingLotSecond.parkCar(car, ticket);
        } else {
            parkingLotFirst.parkCar(car, ticket);
        }
    }

    private boolean isParkingLotFirstSmallerThanSecond() {
        return calculateParkingLotPositionRate(parkingLotFirst) < calculateParkingLotPositionRate(parkingLotSecond);
    }

    private double calculateParkingLotPositionRate(ParkingLot parkingLotFirst) {
        return parkingLotFirst.getRestCapacity() * 1.0 / parkingLotFirst.getTotalCapacity();
    }

    public String getErrorMessage() {
        return errorMessage;
    }

    public String checkTwoLotPositionRate() {
        return "first: "+ calculateParkingLotPositionRate(parkingLotFirst) +", second: " + calculateParkingLotPositionRate(parkingLotSecond);
    }
}
