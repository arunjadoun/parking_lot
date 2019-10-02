package com.gojek.parkinglot;

import com.gojek.parkinglot.abstractions.Parkable;
import com.gojek.parkinglot.enums.LeadStatus;
import com.gojek.parkinglot.enums.LeadType;
import com.gojek.parkinglot.enums.SlotStatus;
import com.gojek.parkinglot.model.ParkingTicket;
import com.gojek.parkinglot.model.Slot;
import com.gojek.parkinglot.request.Lead;

public class ParkingLot {
    private static int size = 0;
    private static int currentParked = 0;
    private static ParkingLot singletonLot = null;
    private Slot[] spots;

    private ParkingLot(int num) {
        this.spots = new Slot[num];
        for (int i = 0; i < num; i++) {
            this.spots[i] = new Slot();
        }
    }

    /**
     *
     * @param num: Creating a lot with num slots
     * @return ParkingLot: Singleton ParkingLot Object
     */
    public static ParkingLot getLot(int num) {
        if (singletonLot == null) {
            synchronized (ParkingLot.class) {
                //Double check if any thread has passed above null check
                if(singletonLot==null) {
                    size = num;
                    singletonLot = new ParkingLot(num);
                }
            }
        }
        return singletonLot;
    }

    public static ParkingLot getLot() {
        return singletonLot;
    }

    public static int getCurrentParked(){
        return currentParked;
    }

    public static int getSize() {
        return size;
    }

    //Create a parking lot of "num" slots
    public static boolean create(int num) {
        try {
            getLot(num);
            System.out.println("Parking lot created with " + num + " slots");
        } catch (Exception e) {
            return false;
        }
        return true;

    }

    public static boolean isValidSlot(int slot){
        return slot >= 0 && slot <= ParkingLot.getSize() - 1 && ParkingLot.getLot() != null ;
    }

    public static int getNextSlot() {
        //Get next closer slot: scan from base index
        if (singletonLot != null && size > 0) {
            for (int i = 0; i < size; i++) {
                if (singletonLot.getSpots()[i].getParkable() == null)
                    return i;
            }
        }
        return -1;
    }

    /**
     * Do Parking related operatins
     * @param Lead : Request Object for Parking
     * @param nextSlot : NextSlot is an index for array of {@link Slot}
     */

    private static void doParkingOperations(Lead Lead, int nextSlot){
        singletonLot.getSpots()[nextSlot].setParkable(Lead.getParkableVehicle());
        singletonLot.getSpots()[nextSlot].setStatus(SlotStatus.OCCUPIED);
        singletonLot.getSpots()[nextSlot].setSpotId(nextSlot + 1);
        Lead.setSlot(singletonLot.getSpots()[nextSlot]);
        Lead.setStatus(LeadStatus.PARKED);
        //Request status changed to PARKED, parking Object lifecycle ends here
        currentParked++;
    }

    private static void doUnParkingOperations(Lead Lead, int parkedSpot){
        singletonLot.getSpots()[parkedSpot].setStatus(SlotStatus.EMPTY);
        //Remove parkable vehicle reference & mark the spot vacant
        singletonLot.getSpots()[parkedSpot].setParkable(null);
        currentParked--;
        //Mark request closed & return ticket, unpark request object lifecycle ends here
        Lead.setStatus(LeadStatus.CLOSED);
    }

    /**
     * Park with Lead, a composition with {@link Parkable} vehicle details
     * Making it synchronised as operating on singleton parkingLot object
     * @param Lead
     * @return
     */
    //Using singleton object of parkingLot: shared storage so synchronising thread access
    public synchronized static ParkingTicket park(Lead Lead) {
        if(Lead == null)
            return null;
        //Check if vehcile with this regNO already parked?
        if(Lead.getParkableVehicle() != null && Lead.getParkableVehicle().isParked()){
            System.out.println("Vehicle with this registration no is already parked");
            return null;
        }
        // Get next closer slot available
        int nextSlot = getNextSlot();
        if (singletonLot != null && currentParked < size && nextSlot != -1) {
            //Do parking relation operations: slot allocation/status update/count update etc
            doParkingOperations(Lead, nextSlot);
            System.out.println("Parked in slot number: " + (nextSlot + 1));
            //Issue new ticket
            return ParkingTicket.issue(Lead);
        } else if (singletonLot == null) {
            //Throw exception : Just printing here
            Lead.setStatus(LeadStatus.FAILED);
            System.out.println("Please create parking lot !!");
        } else if (size == currentParked) {
            Lead.setStatus(LeadStatus.CLOSED);
            System.out.println("parking lot is full");
        }
        return null;
    }

    public synchronized static void unPark(Lead Lead) {
        boolean isUnparkableRequest = Lead.getType().equals(LeadType.UNPARK);
        String unParkedCarRegistrationNo;
        if (singletonLot != null && currentParked > 0 && size != 0 && isUnparkableRequest) {
            ParkingTicket parkingTicket = ParkingTicket.getIssuedTicket(Lead.getParkableVehicle().getVehicle().getRegistrationNumber());
            if (parkingTicket == null){
                System.out.println("This vehicle is yet to be parked");
            }
            int parkedSpot = parkingTicket.getSlot().getSpotId() - 1;
            unParkedCarRegistrationNo = singletonLot.getSpots()[parkedSpot].getParkable().getVehicle().getRegistrationNumber();
            // Do UnParking re      lated operations
            doUnParkingOperations(Lead, parkedSpot);
            System.out.println("Slot number " + (parkedSpot + 1) + " is free");
            //Return ticket: mark returned
            parkingTicket.markReturned(unParkedCarRegistrationNo);
        }
        else{
            Lead.setStatus(LeadStatus.FAILED);
            return;
        }
    }

    public Slot[] getSpots() {
        return spots;
    }

}