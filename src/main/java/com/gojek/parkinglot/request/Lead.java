package com.gojek.parkinglot.request;

import com.gojek.parkinglot.ParkingLot;
import com.gojek.parkinglot.abstractions.Parkable;
import com.gojek.parkinglot.enums.LeadStatus;
import com.gojek.parkinglot.enums.LeadType;
import com.gojek.parkinglot.model.Slot;

import java.util.Date;

public class Lead {

    private Parkable parkableVehicle;
    private long requestedAt;
    private LeadStatus status;
    private Slot Slot = null;
    /**
     * Default entry gate no: 1
     */
    private int entryGateNo = 1;
    private LeadType type;

    private Lead(Parkable parkableVehicle, LeadType LeadType) {
        this.parkableVehicle = parkableVehicle;
        this.setRequestedAt(new Date().getSeconds());
        this.status = LeadStatus.PENDING;
        this.setType(LeadType);
    }

    /**
     * Create Parking request for {@link Parkable} vehicle
     * @param parkable
     * @param LeadType
     * @return Lead
     */
    public static Lead createParkingLead(Parkable parkable, LeadType LeadType) {
        return new Lead(parkable, LeadType);
    }

    public static Lead createUnParkingLead(String slotStr) {
        int slot = Integer.parseInt(slotStr);
        slot = slot - 1;
        if (ParkingLot.isValidSlot(slot)) {
            Parkable vehicleToUnpark = ParkingLot.getLot().getSpots()[slot].getParkable();
            if(vehicleToUnpark == null){
                System.out.println("Slot is already free");
            }
            return new Lead(vehicleToUnpark, LeadType.UNPARK);
        }
        return null;
    }

    public LeadType getType() {
        return type;
    }

    public void setType(LeadType type) {
        this.type = type;
    }

    public Parkable getParkableVehicle() {
        return parkableVehicle;
    }

    public void setParkableVehicle(Parkable parkableVehicle) {
        this.parkableVehicle = parkableVehicle;
    }

    public long getRequestedAt() {
        return requestedAt;
    }

    public void setRequestedAt(long requestedAt) {
        this.requestedAt = requestedAt;
    }

    public LeadStatus getStatus() {
        return status;
    }

    public void setStatus(LeadStatus status) {
        this.status = status;
    }

    public int getEntryGateNo() {
        return entryGateNo;
    }

    public void setEntryGateNo(int entryGateNo) {
        this.entryGateNo = entryGateNo;
    }

    public Slot getSlot() {
        return Slot;
    }

    public void setSlot(Slot Slot) {
        this.Slot = Slot;
    }

}
