package com.example.carrentalmanagmentsystem;

import java.time.LocalDate;

public class Sales {

    private String customername;
    private String Car;
    private Double TotalRent;
    private String RentingDate;
    private String ReturningDate;

    public Sales(String customername, String car, Double totalRent, String rentingDate, String returningDate) {
        this.customername = customername;
        Car = car;
        TotalRent = totalRent;
        RentingDate = rentingDate;
        ReturningDate = returningDate;
    }
}