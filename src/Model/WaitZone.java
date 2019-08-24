package Model;/*
 * Modelling Complex Software Systems_SWEN90004_2019
 * Assignment 1a
 * Student:Dongming Li
 * ID:1002971
 */

import java.util.concurrent.Semaphore;

public class WaitZone {

    //the name of wait zone
    private String zoneName;
    //private ship instance
    volatile private Ship ship;
    //the maximum vacancy of the wait zone
    private final int waitZoneVacancy = 1;

    /*
    generate Semaphore notEmpty for removing ship, notFull for adding ship,
    isAccessed for accessing the zone
     */
    private Semaphore notEmpty = new Semaphore(0);
    private Semaphore notFull = new Semaphore(waitZoneVacancy);
    private Semaphore isAccessed = new Semaphore(1);

    // create a new zone with a given name
    public WaitZone(String name) {
        this.zoneName = name;
    }

    //arrive method for ship to entering wait zones
    public void arrive(Ship ship) throws InterruptedException {
        //acquire a vacancy in wait zone
        notFull.acquire();
        //acquire access to wait zone
        isAccessed.acquire();
        this.ship = ship;
        //release semaphore
        isAccessed.release();
        notEmpty.release();
    }


    /*
    arrive method for ship to leaving wait zones
    return ship instance
    */
    public Ship depart() throws InterruptedException {
        //ships can leave only when the wait zone is not empty
        notEmpty.acquire();
        ////acquire access to wait zone
        isAccessed.acquire();
        //release semaphore
        isAccessed.release();
        notFull.release();
        return ship;
    }

    //produce an identifying string for the zone name
    public String toString() {
        return zoneName + " zone";
    }
}
