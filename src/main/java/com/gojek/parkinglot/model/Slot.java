package com.gojek.parkinglot.model;

import com.gojek.parkinglot.abstractions.Parkable;
import com.gojek.parkinglot.enums.SlotStatus;

public class Slot {
    private int spotId = -1;
    private Parkable parkable;
    private SlotStatus status = SlotStatus.EMPTY;


    public int getSpotId() {
        return spotId;
    }

    public void setSpotId(int spotId) {
        this.spotId = spotId;
    }

    public Parkable getParkable() {
        return parkable;
    }

    public void setParkable(Parkable parkable) {
        this.parkable = parkable;
    }

    public SlotStatus getStatus() {
        return status;
    }

    public void setStatus(SlotStatus status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "Slot{" +
                "spotId=" + spotId +
                ", parkable=" + parkable +
                ", status=" + status +
                '}';
    }
}