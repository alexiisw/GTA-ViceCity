package com.carsforsell.models;

public class LeaseContract extends Contract  {
    //The instructions say Create ExpectEndVaule & LeaseFee

    private double expectEndingVaule;
    private double leaseFee;

    //constructor

    public LeaseContract(String date, String customerName, String customerEmail, Vehicle vehicle, double expectEndVaule, double leaseFee) {
        super(date, customerName, customerEmail, vehicle);
        this.expectEndingVaule = expectEndVaule;
        this.leaseFee = leaseFee;
    }

    // Regular getters & Setters
    public double getExpectEndingVaule() {
        return expectEndingVaule;
    }

    public void setExpectEndVaule(double expectEndingVaule) {
        this.expectEndingVaule = expectEndingVaule;
    }

    public double getLeaseFee() {
        return leaseFee;
    }

    public void setLeaseFee(double leaseFee) {
        this.leaseFee = leaseFee;
    }

    @Override
    public double getTotalPrice() {
        return expectEndingVaule * 0.50;
    }
    @Override
    public double getMonthlyPayment() {
        return leaseFee * 0.07;
    }
}
