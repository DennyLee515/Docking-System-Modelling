package Model;/*
 * Modelling Complex Software Systems_SWEN90004_2019
 * Assignment 1a
 * Student:Dongming Li
 * ID:1002971
 */

public class Berth {

    //the name of the berth
    private String berthName;

    //the number of ship in the berth
    private int numShip = 0;

    //the status of the shield
    private boolean shieldStatus;

    // create a new location with a given name
    public Berth(String name) {
        this.berthName = name;
        //initial shield status is deactivated
        shieldStatus = false;
    }

    //a synchronized methed for ships to dock into berth
    public synchronized void dock() throws InterruptedException {
        //if the berth is not available
        while (!isEmpty()) {
            //wait for other ships to leave
            wait();
        }
        //increase the counter
        numShip++;
    }

    //a synchronized methed for ships to undock from berth
    public synchronized void undock() throws InterruptedException {
        while(!isEmpty()){
            //decrease the counter
            numShip--;
            //notify threads which are waiting for docking
            notifyAll();
        }
    }

    //return the status of berth
    public boolean isEmpty() {
        if (numShip == 0)
            return true;
        else
            return false;
    }

    //when shield is activated, all following threads should wait
    public synchronized void checkShieldStatus() throws InterruptedException {
        if (shieldStatus)
            wait();
    }

    //when shield is deactivated, notify all waiting threads
    public synchronized void notifyMethod() {
        notifyAll();
    }

    //change the status of shield to true
    public void activateShield() {
        shieldStatus = true;
    }

    //change the status of shield to true
    public void deactivateShield() {
        shieldStatus = false;
    }
}
