package com.gojek.parkinglot;

import com.gojek.parkinglot.model.Slot;

import java.util.ArrayList;
import java.util.List;

public class Parkings {

    public static void showParkedDetails() {
        Slot[] Slots = {};
        if (ParkingLot.getLot() != null)
            Slots = ParkingLot.getLot().getSpots();
        if (ParkingLot.getCurrentParked() > 0 && Slots.length > 0) {
            System.out.println("Slot No.    Registration No    Colour");
            for (int i = 0; i < ParkingLot.getSize(); i++) {
                if (Slots[i].getParkable() != null) {
                    System.out.println(i + 1 + "           " + Slots[i].getParkable().getVehicle().getRegistrationNumber() + "      " + Slots[i].getParkable().getVehicle().getColor() + "");
                }
            }
        } else {
            System.out.println("Not found");
        }

    }

    public static void showParkedVehiclesRegNosByColor(String color) {
        List<Slot> SlotList = getParkedvehicles();
        boolean resultFound = false;
        String registraionNumbers = "";
        if (ParkingLot.getCurrentParked() > 0 && SlotList.size() > 0) {
            for (Slot Slot : SlotList) {
                if (Slot.getParkable().getVehicle().getColor().equals(color)) {
                    resultFound = true;
                    registraionNumbers += Slot.getParkable().getVehicle().getRegistrationNumber() + ", ";
                }
            }

        }
        if(resultFound)
            System.out.println(registraionNumbers.trim().replaceAll(",$", ""));
        else
            System.out.println("Not found");

    }

    public static void showSlotNoForParkedVehicleWithRegNo(String regNo) {
        int slotNo = getSlotNoForParkedVehicle(regNo);
        if (slotNo < 0) {
            System.out.println("Not found");
        } else {
            System.out.println(slotNo);
        }
    }

    public static void showSlotNosForParkedVehicleWithColor(String color) {
        List<Integer> spotList = getParkableVehicleSlotsWithColor(color);
        if (spotList.size() > 0) {
            String spotListStr = "";
            for (int spot : spotList) {
                spotListStr += spot + ", ";
            }
            spotListStr = spotListStr.trim();
            if (spotListStr.endsWith(",")) {
                spotListStr = spotListStr.substring(0, spotListStr.length() - 1);
            }
            System.out.println(spotListStr);
        } else {
            System.out.println("Not found");
        }
    }


    private static List<Slot> getParkedvehicles() {
        Slot[] Slots = ParkingLot.getLot().getSpots();
        List<Slot> selectedSpots = new ArrayList<Slot>();
        if (ParkingLot.getLot() != null && Slots.length > 0) {
            for (int i = 0; i < ParkingLot.getSize(); i++) {
                if (Slots[i].getParkable() != null) {
                    selectedSpots.add(Slots[i]);
                }
            }

        }
        return selectedSpots;
    }

    private static int getSlotNoForParkedVehicle(String regNo) {
        List<Slot> getParkedvehicles = getParkedvehicles();
        for (Slot Slot : getParkedvehicles) {
            if (Slot.getParkable().getVehicle().getRegistrationNumber().equals(regNo))
                return Slot.getSpotId();
        }
        return -1;
    }

    private static List<Integer> getParkableVehicleSlotsWithColor(String color) {
        Slot[] Slots = ParkingLot.getLot().getSpots();
        List<Integer> selectedSpots = new ArrayList<Integer>();
        if (ParkingLot.getLot() != null && Slots.length > 0) {
            for (int i = 0; i < ParkingLot.getSize(); i++) {
                if (Slots[i].getParkable() != null && Slots[i].getParkable().getVehicle().getColor().equals(color)) {
                    selectedSpots.add(Slots[i].getSpotId());
                }
            }

        }
        return selectedSpots;
    }

}