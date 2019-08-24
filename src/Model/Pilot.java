package Model;/*
 * Modelling Complex Software Systems_SWEN90004_2019
 * Assignment 1a
 * Student:Dongming Li
 * ID:1002971
 */

public class Pilot extends Thread {

    //the unique id for pilot
    private int pilotId;

    //the wait zone at which ships will arrive
    private WaitZone arrivalZone;

    //the wait zone which ships will depart from
    private WaitZone departZone;

    //tugs used for docking and undocking
    private Tugs tugs;

    //the berth where ships will be unloaded
    private Berth berth;

    //ship instance
    private Ship ship;

    // create a new pilot with some existed instance
    public Pilot(int i, WaitZone arrivalZone,
                 WaitZone departureZone, Tugs tugs, Berth berth) {
        this.pilotId = i;
        this.arrivalZone = arrivalZone;
        this.departZone = departureZone;
        this.tugs = tugs;
        this.berth = berth;
    }

    @Override
    //repeatedly acquire ships from arrival zone and send them to departure zone
    public void run() {
        try {
            while (!interrupted()) {
                //ships depart from the arrival zone
                ship = arrivalZone.depart();
                System.out.println(this.toString() + " acquires " + ship + ".");
                /*let some time pass when the ship is traveling between the
                vicinity of the berth and arrival zone
                 */
                sleep(Params.TRAVEL_TIME);
                //dock process
                dock();
                //unload process
                unload();
                //undock process
                undock();
                // let some time pass when the ship is traveling between the
                // vicinity of the berth and departure zone
                sleep(Params.TRAVEL_TIME);
                //ships arrives at depart zone
                departZone.arrive(ship);
                System.out.println(ship + " departs departure zone.");
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
    //produce an identifying string for pilots
    public String toString() {
        return "pilot " + this.pilotId;
    }

    //the dock process from arrival zone to the vicinity of the berth
    public void dock() throws InterruptedException {
        //acquire needed tugs for docking process
        tugs.acquire(Params.DOCKING_TUGS);
        System.out.println(this.toString() + " acquires " + Params
                .DOCKING_TUGS + " tugs (" + tugs.getAvailTugs() + " " +
                "available).");
        //check if the shield is activated
        berth.checkShieldStatus();
        //ships dock to the berth
        berth.dock();
        System.out.println(ship + " docks at berth.");
        // let some time pass when the ship is docking
        sleep(Params.DOCKING_TIME);
    }

    public void unload() throws InterruptedException {
        ship.unload();
        //release tugs using in docking process
        tugs.release(Params.DOCKING_TUGS);
        // let some time pass when the ship is unloading
        sleep(Params.UNLOADING_TIME);
    }

    public void undock() throws InterruptedException {
        //acquire needed tugs for undocking process
        tugs.acquire(Params.UNDOCKING_TUGS);
        System.out.println(this.toString() + " acquires " + Params
                .UNDOCKING_TUGS + " tugs (" + tugs.getAvailTugs() + "" +
                " available)");
        //check the status of the shield
        berth.checkShieldStatus();
        //ships undock from the berth
        System.out.println(ship + " undocks from berth.");
        berth.undock();
        // let some time pass when the ship is undocking
        sleep(Params.UNDOCKING_TIME);
        //release tugs using in undocking process
        tugs.release(Params.UNDOCKING_TUGS);
    }

















}

