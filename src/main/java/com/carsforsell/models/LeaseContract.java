package com.carsforsell.models;

public class LeaseContract extends Contract  {
    //The instructions say Create ExpectEndingVaule & LeaseFee

    private double expectEndingValue;
    private double leaseFee;

    //constructor

    public LeaseContract(String date, String customerName, String customerEmail, Vehicle vehicle) {
        super(date, customerName, customerEmail, vehicle);
        this.expectEndingValue = vehicle.getPrice() * 0.50;
        this.leaseFee = vehicle.getPrice() * 0.07;
    }

    // Regular getters & Setters
    public double getExpectEndingVaule() {
        return expectEndingValue;
    }

    public void setExpectEndVaule(double expectEndingVaule) {
        this.expectEndingValue = expectEndingVaule;
    }

    public double getLeaseFee() {
        return leaseFee;
    }

    public void setLeaseFee(double leaseFee) {
        this.leaseFee = leaseFee;
    }

    @Override
    public double getTotalPrice() {
        return getExpectEndingVaule() + leaseFee;
    }
    @Override
    public double getMonthlyPayment() {

        double monthlyInterestRate = 0.04 / 12;
        int months = 36;

        return getTotalPrice() *
                (monthlyInterestRate *
                        Math.pow(1 + monthlyInterestRate, months))
                /
                (Math.pow(1 + monthlyInterestRate, months) - 1);
    }
}
