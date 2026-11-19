package com.carsforsell.models;

public abstract class LeaseContract extends Contract  {
private double expectEndVaule;
private double leaseFee;

    public double getExpectEndVaule() {
        return expectEndVaule;
    }

    public void setExpectEndVaule(double expectEndVaule) {
        this.expectEndVaule = expectEndVaule;
    }

    public double getLeaseFee() {
        return leaseFee;
    }

    public void setLeaseFee(double leaseFee) {
        this.leaseFee = leaseFee;
    }
}
