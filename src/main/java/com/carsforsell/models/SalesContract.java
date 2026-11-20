package com.carsforsell.models;


//ALEXIS


/** MISSION 2: Calculate tax, fees, and optional financed monthly payments. */
public class SalesContract extends Contract {
    private boolean finance;

    public SalesContract(String date, String customerName, String customerEmail,
                         Vehicle vehicle, boolean finance) {
        super(date, customerName, customerEmail, vehicle);
        this.finance = finance;
    }

    public boolean isFinance() { return finance; }
    public void setFinance(boolean finance) { this.finance = finance; }

    @Override
    public double getTotalPrice() {
        double price = getVehicle().getPrice();
        double salesTax = price * 0.05;
        double recordingFee = 100;
        double processingFee;

        if (price < 10000) {  //price pf the vehicle is less than $10,000
            processingFee = 295;
        } else {
            processingFee = 495;
        }
        return price + salesTax + recordingFee + processingFee;
    }
    @Override
    public double getMonthlyPayment() {
        if(!isFinance()){ //if the customer pays cash, there is no monthly payment
            return(0);
        }
        double totalPrice = getTotalPrice(); //the final amount after taxes, fees, and down payment calculations
        double annualInterestRate;
        int loanLength;

        if (totalPrice >= 10000) { //for vehicles $10,000 or more
            annualInterestRate = 0.0425; // 4.25%
            loanLength = 48;
        } else { //for vehicles $10,000 or less
            annualInterestRate = 0.0525; // 5.25%
            loanLength = 24;
        }

        double monthlyInterestRate = annualInterestRate / 12; // coverts rate to monthly

        double monthlyPayment =
                totalPrice *
                        (monthlyInterestRate * Math.pow(1 + monthlyInterestRate, loanLength))
                        / (Math.pow(1 + monthlyInterestRate, loanLength) - 1); // // calculate financing payment; the standard loan amortization formula

        return monthlyPayment;
    }
}
