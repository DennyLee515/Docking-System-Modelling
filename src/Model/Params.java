package Model;/*
 * Modelling Complex Software Systems_SWEN90004_2019
 * Assignment 1a
 * Student:Dongming Li
 * ID:1002971
 */

import java.util.Random;

class Params {
    //number of pilots
    static final int NUM_PILOTS = 2;
    //number of tugs
    static final int NUM_TUGS = 5;
    //number of tugs needed in the docking process
    static final int DOCKING_TUGS = 3;
    //number of tugs needed in the undocking process
    static final int UNDOCKING_TUGS = 2;
    //time needed in docking process
    static final int DOCKING_TIME = 800;
    //time needed in undocking process
    static final int UNDOCKING_TIME = 400;
    //time needed in unloading process
    static final int UNLOADING_TIME = 1200;
    //time needed for ship to travel between wait zone and the vicinity of
    // the berth
    static final int TRAVEL_TIME = 800;
    //time between two debris
    static final int DEBRIS_TIME = 1800;
    //the maximum arrival time interval
    private static final int MAX_ARRIVAL_INTERVAL = 400;
    //the maximum departure interval
    private static final int MAX_DEPARTURE_INTERVAL = 1000;
    //the maximum debris interval
    private static final int MAX_DEBRIS_INTERVAL = 2400;
    //generate a random debris interval
    static int debrisLapse() {
        Random rnd = new Random();
        return rnd.nextInt(MAX_DEBRIS_INTERVAL);
    }
    //generate a random arrival interval
    static int arrivalLapse() {
        Random rnd = new Random();
        return rnd.nextInt(MAX_ARRIVAL_INTERVAL);
    }
    //generate a random departure interval
    static int departureLapse() {
        Random rnd = new Random();
        return rnd.nextInt(MAX_DEPARTURE_INTERVAL);
    }
}
