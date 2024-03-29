package Model;/*
 * Modelling Complex Software Systems_SWEN90004_2019
 * Assignment 1a
 * Student:Dongming Li
 * ID:1002971
 */

/**
 * Produces new cargo ships wanting to unload cargo at the space station.
 *
 * @author ngeard@unimelb.edu.au
 */

public class Producer extends Thread {

    // the wait zone at which ships will arrive
    private WaitZone arrivalZone;

    // create a new producer
    Producer(WaitZone newArrivalZone) {
        this.arrivalZone = newArrivalZone;
    }

    // cargo ships arrive at the arrival zone at random intervals.
    public void run() {
        while (!isInterrupted()) {
            try {
                // create a new cargo ship and send it to the arrvial zone.
                Ship ship = Ship.getNewShip();
                arrivalZone.arrive(ship);
                System.out.println(ship.toString() + " arrives at " +
                        arrivalZone.toString());
                // let some time pass before the next ship arrives
                sleep(Params.arrivalLapse());
            } catch (InterruptedException e) {
                this.interrupt();
            }
        }
    }

}
