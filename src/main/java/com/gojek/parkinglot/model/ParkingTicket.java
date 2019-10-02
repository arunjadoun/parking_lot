package com.gojek.parkinglot.model;

import com.gojek.parkinglot.enums.LeadStatus;
import com.gojek.parkinglot.request.Lead;

import java.util.Date;
import java.util.HashMap;
import java.util.UUID;

public class ParkingTicket {
    public static HashMap<String, ParkingTicket> ticketMap = new HashMap<String, ParkingTicket>();
    private Slot Slot;
    private String printedAt;
    private String parkedAt;
    private String leftAt;
    private String id;
    private STATUS status;

    private ParkingTicket(Lead Lead) {
        this.printedAt = new Date().toString();
        //Parking Time: Same as print time, Charging will consider this time only
        this.parkedAt = this.printedAt;
        this.id = UUID.randomUUID().toString();
        this.status = STATUS.ISSUED;
        this.Slot = Lead.getSlot();
    }

    /**
     * Issue ParkingTicket with all parking details post successful parking
     * @param Lead : Request object for parking
     * @return ParkingTicket: Ticket with all details: timing/spots/vehicle details
     */
    public static ParkingTicket issue(Lead Lead) {
        if (Lead.getStatus().equals(LeadStatus.PARKED)) {
            ParkingTicket parkingTicket = new ParkingTicket(Lead);
            ticketMap.put(Lead.getSlot().getParkable().getVehicle().getRegistrationNumber(), parkingTicket);
            return parkingTicket;
        }
        return null;
    }

    public static ParkingTicket getIssuedTicket(String registrationNo) {
        return ticketMap.get(registrationNo);
    }

    public String getLeftAt() {
        return leftAt;
    }

    public void setLeftAt(String leftAt) {
        this.leftAt = leftAt;
    }

    public String getPrintedAt() {
        return printedAt;
    }

    public void setPrintedAt(String printedAt) {
        this.printedAt = printedAt;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public STATUS getStatus() {
        return status;
    }

    public void setStatus(STATUS status) {
        this.status = status;
    }

    public Slot getSlot() {
        return Slot;
    }

    public void setSlot(Slot Slot) {
        this.Slot = Slot;
    }

    public void markReturned(String registrationNo) {
        // Set OUT time: Basis we can charge the customer
        this.setLeftAt(new Date().toString());
        this.setStatus(STATUS.RETURNED);
        //We can log and remove this object from ticketMap
        ticketMap.remove(registrationNo);
    }

    private static enum STATUS {ISSUED, RETURNED}


}