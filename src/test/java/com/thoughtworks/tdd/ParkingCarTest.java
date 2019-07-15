package com.thoughtworks.tdd;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class ParkingCarTest {

    private static final int INIT_TOTAL_CAPACITY = 10;

    @Test
    public void should_return_car_when_call_fetch_given_ticket_by_parking_car() {
        Car car = new Car();
        ParkingBoy parkingBoy = new ParkingBoy();
        ParkingLot parkingLotFirst = new ParkingLot(INIT_TOTAL_CAPACITY);
        ParkingLot parkingLotSecond = new ParkingLot(INIT_TOTAL_CAPACITY);
        parkingBoy.setParkingLot(parkingLotFirst, parkingLotSecond);
        Ticket ticket = parkingBoy.park(car, new Ticket());
        Car car2 = parkingBoy.fetch(ticket);

        assertSame(car, car2);
    }

    @Test
    public void should_return_car_when_call_fetch_given_corresponding_ticket_by_parking_car() {
        Car car = new Car();
        Car car2 = new Car();
        ParkingBoy parkingBoy = new ParkingBoy();
        ParkingLot parkingLotFirst = new ParkingLot(INIT_TOTAL_CAPACITY);
        ParkingLot parkingLotSecond = new ParkingLot(INIT_TOTAL_CAPACITY);
        parkingBoy.setParkingLot(parkingLotFirst, parkingLotSecond);
        Ticket ticket = parkingBoy.park(car, new Ticket());
        Ticket ticket2 = parkingBoy.park(car2, new Ticket());
        Car fetchCar = parkingBoy.fetch(ticket);
        Car fetchCar2 = parkingBoy.fetch(ticket2);

        assertSame(car, fetchCar);
        assertSame(car2, fetchCar2);
    }

    @Test
    public void should_return_no_car_when_call_fetch_given_fake_ticket_by_parking_car() {
        ParkingBoy parkingBoy = new ParkingBoy();
        ParkingLot parkingLotFirst = new ParkingLot(INIT_TOTAL_CAPACITY);
        ParkingLot parkingLotSecond = new ParkingLot(INIT_TOTAL_CAPACITY);
        parkingBoy.setParkingLot(parkingLotFirst, parkingLotSecond);
        Ticket fakeTicket = new Ticket();
        Car fetchCar = parkingBoy.fetch(fakeTicket);

        assertNull(fetchCar);
    }

    @Test
    public void should_return_no_car_when_call_fetch_given_no_ticket() {
        ParkingBoy parkingBoy = new ParkingBoy();
        ParkingLot parkingLotFirst = new ParkingLot(INIT_TOTAL_CAPACITY);
        ParkingLot parkingLotSecond = new ParkingLot(INIT_TOTAL_CAPACITY);
        parkingBoy.setParkingLot(parkingLotFirst, parkingLotSecond);
        Car fetchCar = parkingBoy.fetch(null);

        assertNull(fetchCar);
    }

    @Test
    public void should_return_no_car_when_call_fetch_given_used_ticket_by_parking_car() {
        Car car = new Car();
        ParkingBoy parkingBoy = new ParkingBoy();
        ParkingLot parkingLotFirst = new ParkingLot(INIT_TOTAL_CAPACITY);
        ParkingLot parkingLotSecond = new ParkingLot(INIT_TOTAL_CAPACITY);
        parkingBoy.setParkingLot(parkingLotFirst, parkingLotSecond);
        Ticket ticket = parkingBoy.park(car, new Ticket());
        parkingBoy.fetch(ticket);
        Car fetchCar = parkingBoy.fetch(ticket);

        assertNull(fetchCar);
    }

    @Test
    public void should_return_no_ticket_when_call_park_given_parking_lot_is_full_of_20() {
        ParkingBoy parkingBoy = new ParkingBoy();
        ParkingLot parkingLotFirst = new ParkingLot(INIT_TOTAL_CAPACITY);
        ParkingLot parkingLotSecond = new ParkingLot(INIT_TOTAL_CAPACITY);
        parkingBoy.setParkingLot(parkingLotFirst, parkingLotSecond);
        for (int i = 0; i < 20; i++) {
            parkingBoy.park(new Car(), new Ticket());
        }
        Ticket ticket = parkingBoy.park(new Car(), new Ticket());

        assertNull(ticket);
    }

    @Test
    public void should_return_no_ticket_when_call_park_given_parked_car() {
        Car car = new Car();
        ParkingBoy parkingBoy = new ParkingBoy();
        ParkingLot parkingLotFirst = new ParkingLot(INIT_TOTAL_CAPACITY);
        ParkingLot parkingLotSecond = new ParkingLot(INIT_TOTAL_CAPACITY);
        parkingBoy.setParkingLot(parkingLotFirst, parkingLotSecond);
        parkingBoy.park(car, new Ticket());
        Ticket ticket = parkingBoy.park(car, new Ticket());

        assertNull(ticket);
    }

    @Test
    public void should_return_no_ticket_when_call_park_given_null_car() {
        Car car = new Car();
        ParkingBoy parkingBoy = new ParkingBoy();
        ParkingLot parkingLotFirst = new ParkingLot(INIT_TOTAL_CAPACITY);
        ParkingLot parkingLotSecond = new ParkingLot(INIT_TOTAL_CAPACITY);
        parkingBoy.setParkingLot(parkingLotFirst, parkingLotSecond);
        Ticket ticket = parkingBoy.park(null, new Ticket());

        assertNull(ticket);
    }

    @Test
    public void should_return_error_message_when_call_fetch_given_wrong_ticket() {
        Car car = new Car();
        ParkingBoy parkingBoy = new ParkingBoy();
        ParkingLot parkingLotFirst = new ParkingLot(INIT_TOTAL_CAPACITY);
        ParkingLot parkingLotSecond = new ParkingLot(INIT_TOTAL_CAPACITY);
        parkingBoy.setParkingLot(parkingLotFirst, parkingLotSecond);
        Ticket ticket = parkingBoy.park(car, new Ticket());
        Ticket fakeTicket = new Ticket();
        Car fetchCar = parkingBoy.fetch(fakeTicket);
        parkingBoy.fetch(ticket);
        Car fetchCar2 = parkingBoy.fetch(ticket);
        String errorMessage = parkingBoy.getErrorMessage();

        assertNull(fetchCar);
        assertNull(fetchCar2);
        assertEquals(ParkingBoy.UNRECOGNIEZD_PARKING_TICKET, errorMessage);
    }

    @Test
    public void should_return_error_message_when_call_fetch_given_no_ticket() {
        Car car = new Car();
        ParkingBoy parkingBoy = new ParkingBoy();
        ParkingLot parkingLotFirst = new ParkingLot(INIT_TOTAL_CAPACITY);
        ParkingLot parkingLotSecond = new ParkingLot(INIT_TOTAL_CAPACITY);
        parkingBoy.setParkingLot(parkingLotFirst, parkingLotSecond);
        parkingBoy.park(car, new Ticket());
        Car fetchCar1 = parkingBoy.fetch(null);
        String errorMessage = parkingBoy.getErrorMessage();

        assertNull(fetchCar1);
        assertEquals(ParkingBoy.NOT_PROVIDE_TICKET, errorMessage);
    }

    @Test
    public void should_return_error_message3_when_call_park_given_parking_lot_full_of_20() {
        ParkingBoy parkingBoy = new ParkingBoy();
        ParkingLot parkingLotFirst = new ParkingLot(INIT_TOTAL_CAPACITY);
        ParkingLot parkingLotSecond = new ParkingLot(INIT_TOTAL_CAPACITY);
        parkingBoy.setParkingLot(parkingLotFirst, parkingLotSecond);
        for (int i = 0; i < 20; i++) {
            parkingBoy.park(new Car(), new Ticket());
        }
        parkingBoy.park(new Car(), new Ticket());
        String errorMessage = parkingBoy.getErrorMessage();

        assertEquals(ParkingBoy.NOT_ENOUGH_POSITION, errorMessage);
    }

    @Test
    public void should_return_correct_car_when_call_fetch_given_parking_lot_first_full_of_10() {
        Car car = new Car();
        ParkingBoy parkingBoy = new ParkingBoy();
        ParkingLot parkingLotFirst = new ParkingLot(INIT_TOTAL_CAPACITY);
        ParkingLot parkingLotSecond = new ParkingLot(INIT_TOTAL_CAPACITY);
        parkingBoy.setParkingLot(parkingLotFirst, parkingLotSecond);
        for (int i = 0; i < 10; i++) {
            parkingBoy.park(new Car(), new Ticket());
        }
        Ticket ticket = parkingBoy.park(car, new Ticket());
        Car fetchCar = parkingBoy.fetch(ticket);

        assertSame(car, fetchCar);
    }

    @Test
    public void should_return_first_lot_rest_capacity_9_and_second_lot_capacity_9_when_call_park_given_2_car() {
        Car car = new Car();
        Car car2 = new Car();
        SmartParkingBoy parkingBoy = new SmartParkingBoy();
        ParkingLot parkingLotFirst = new ParkingLot(INIT_TOTAL_CAPACITY);
        ParkingLot parkingLotSecond = new ParkingLot(INIT_TOTAL_CAPACITY);
        parkingBoy.setParkingLot(parkingLotFirst, parkingLotSecond);
        parkingBoy.park(car, new Ticket());
        parkingBoy.park(car2, new Ticket());
        String twoLotRestCapacity = parkingBoy.checkTwoLotRestCapacity();

        assertEquals("first: 9, second: 9", twoLotRestCapacity);
    }

    @Test
    public void should_return_first_lot_rate_0p8_second_lot_rate_0p8_when_call_park_given_4_cars_with_first_lot_5_capacity_second_lot_10_capacity() {
        SuperSmartParkingBoy parkingBoy = new SuperSmartParkingBoy();
        ParkingLot parkingLotFirst = new ParkingLot(INIT_TOTAL_CAPACITY);
        ParkingLot parkingLotSecond = new ParkingLot(INIT_TOTAL_CAPACITY);
        parkingBoy.setParkingLot(parkingLotFirst, parkingLotSecond);
        for (int i = 0; i < 4; i++) {
            parkingBoy.park(new Car(), new Ticket());
        }
        String twoLotPositionRate = parkingBoy.checkTwoLotPositionRate();

        assertEquals("first: 0.8, second: 0.8", twoLotPositionRate);
    }

    @Test
    public void should_return_car_when_call_specifyParkingBoyPark_given_ticket_by_parking_before() {
        List<ParkingBoy> parkingBoys = new ArrayList<>();
        for (int i = 0; i < 5; i++) {
            SuperSmartParkingBoy parkingBoy = new SuperSmartParkingBoy();
            ParkingLot parkingLotFirst = new ParkingLot(INIT_TOTAL_CAPACITY);
            ParkingLot parkingLotSecond = new ParkingLot(INIT_TOTAL_CAPACITY);
            parkingBoy.setParkingLot(parkingLotFirst, parkingLotSecond);
            parkingBoys.add(parkingBoy);
        }
        ServiceManager serviceManager = new ServiceManager(parkingBoys);
        ParkingBoy parkingBoy = serviceManager.getManagerParkingBoys().get(0);

        Car car = new Car();
        Ticket ticket = serviceManager.specifyParkingBoyPark(parkingBoy, car);
        Car fetchCar = serviceManager.specifyParkingBoyFetch(parkingBoy, ticket);
        assertSame(car, fetchCar);
    }

    @Test
    public void should_return_car_when_call_fetch_given_ticket_by_parking_before() {
        List<ParkingBoy> parkingBoys = new ArrayList<>();
        ServiceManager serviceManager = new ServiceManager(parkingBoys);
        serviceManager.setParkingLot(new ParkingLot(INIT_TOTAL_CAPACITY), new ParkingLot(INIT_TOTAL_CAPACITY));

        Car car = new Car();
        Ticket ticket = serviceManager.park(car, new Ticket());
        Car fetchCar = serviceManager.fetch(ticket);
        assertSame(car, fetchCar);
    }

    @Test
    public void should_return_error_message_when_call_specifyParkingBoyFetch_given_no_ticket() {
        List<ParkingBoy> parkingBoys = new ArrayList<>();
        SuperSmartParkingBoy parkingBoy = new SuperSmartParkingBoy();
        ParkingLot parkingLotFirst = new ParkingLot(INIT_TOTAL_CAPACITY);
        ParkingLot parkingLotSecond = new ParkingLot(INIT_TOTAL_CAPACITY);
        parkingBoy.setParkingLot(parkingLotFirst, parkingLotSecond);
        parkingBoys.add(parkingBoy);
        ServiceManager serviceManager = new ServiceManager(parkingBoys);
        serviceManager.setParkingLot(new ParkingLot(INIT_TOTAL_CAPACITY), new ParkingLot(INIT_TOTAL_CAPACITY));

        serviceManager.specifyParkingBoyPark(parkingBoys.get(0), new Car());
        serviceManager.specifyParkingBoyFetch(parkingBoys.get(0), null);

        String errorMessage = serviceManager.getErrorMsgFromParkingBoy();

        assertEquals(ParkingBoy.NOT_PROVIDE_TICKET, errorMessage);
    }

}
