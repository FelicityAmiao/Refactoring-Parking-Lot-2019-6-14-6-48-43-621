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
        if (parkingLotFirst.isCarFull() || parkingLotFirst.getRestCapacity() * 1.0 / parkingLotFirst.getTotalCapacity() < parkingLotSecond.getRestCapacity() * 1.0 / parkingLotSecond.getTotalCapacity()) {
            parkingLotSecond.parkCar(car, ticket);
        } else {
            parkingLotFirst.parkCar(car, ticket);
        }
    }

    public String getErrorMessage() {
        return errorMessage;
    }

    public String checkTwoLotPositionRate() {
        return "first: "+ parkingLotFirst.getRestCapacity() * 1.0 / parkingLotFirst.getTotalCapacity() +", second: " + parkingLotSecond.getRestCapacity() * 1.0 / parkingLotSecond.getTotalCapacity();
    }
}
