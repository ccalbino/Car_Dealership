package com.pluralsight;


import java.text.NumberFormat;

public class SalesContract extends Contract {


    private double salesTax;
    private double recordingFee;
    private double processingFee;
    private boolean finance;

    public SalesContract(String date, String customerName, String customerEmail, Vehicle vehicle,boolean finance, double processingFee) {
        super("Sales", date, customerName, customerEmail, vehicle);
        this.salesTax = getVehicle().getPrice() * 0.05;
        this.recordingFee = 100;
        this.finance = finance;

        if (getTotalPrice() < 10000) {
            this.processingFee = 295;
        } else {
            this.processingFee = 495;
        }

    }


    public void setSalesTax(double salesTax) {
        this.salesTax = salesTax;
    }

    public void setRecordingFee(double recordingFee) {
        this.recordingFee = recordingFee;
    }

    public void setProcessingFee(double processingFee) {
        this.processingFee = processingFee;
    }

    public void setFinance(boolean finance) {
        this.finance = finance;
    }

    public double getSalesTax() {

        return salesTax;
    }

    public double getRecordingFee() {
        return recordingFee;
    }

    public double getProcessingFee() {
        return processingFee;
    }

    public boolean isFinance() {
        return finance;
    }


    @Override
    public double getTotalPrice() {
        double totalVehiclePrice = getVehicle().getPrice();



        if(totalVehiclePrice < 10000 ){
            this.processingFee = 295;

        } else {
            this.processingFee = 495;
        }


        return totalVehiclePrice + this.recordingFee + this.processingFee;
    }

    @Override
    public double getMonthlyPayment() {

        if(!isFinance()){
            return 0;
        }

        int loanLength;
        double loan = getTotalPrice();
        double loanInterest;

        if(getVehicle().getPrice() >= 10000){
            loanInterest = 0.0425;
            loanLength = 48; //48 months


        } else {
            loanInterest = 0.0525;
            loanLength = 24; // 24 months
        }

        double monthlyRate = loan * loanInterest / 12;
        double paymentDivision = loan / loanLength;

        return monthlyRate + paymentDivision; // MonthlyPayment

    }



    @Override
    public String toString() {
        NumberFormat currencyFormatter = NumberFormat.getCurrencyInstance();
        String dollarSign = currencyFormatter.format(getTotalPrice());


        return String.format("%-12s  %-20s  %-37s  %-80s  %-9s  %-14s  %-14s  %-14s  %-7s  %-16.2f",
                getDate(),
                getCustomerName(),
                getCustomerEmail(),
                getVehicle().toStringTwo(),
                salesTax,
                recordingFee,
                processingFee,
                dollarSign,
                finance,
                getMonthlyPayment());
    }

    public static String getFormattedHeader() {
        return """
    Date        | Name                 | Email                                 | Vehicle Information List ---------------------------------------------------------------------------->| Sales Tax | Recording Fee  | Processing Fee | Total Price  | Finance | Monthly Payment
    ----------- | -------------------- | ------------------------------------- | ----------------------------------------------------------------------------------------------------- | --------- | -------------- | -------------- | -------------| ------- | ----------------
    """;
    }




    public String toStringLog() {
        return String.format("SALE|%s|%s|%s|%s|%s|%s|%s|%.2f|%s|%.2f",
                getDate(), getCustomerName(), getCustomerEmail(),
                getVehicle().toStringLog(), salesTax, recordingFee, processingFee,
                getTotalPrice(), finance,  getMonthlyPayment());
    }




}
