package com.example.carrentalmanagmentsystem;


import java.sql.*;

public class CarData {

    private String carId;
    private String brand;
    private String model;
    private Double price;
    private String status;
    private Date date;
    private String image;

    public CarData() {
    }

    public CarData(String brand, String model) {
        this.brand = brand;
        this.model = model;
    }

    public CarData(String carId, String brand, String model, Double price, String status) {
        this.carId = carId;
        this.brand = brand;
        this.model = model;
        this.price = price;
        this.status = status;
    }

    public CarData(String carId, String brand, String model, Double price, String status,String image) {
        this.carId = carId;
        this.brand = brand;
        this.model = model;
        this.price = price;
        this.status = status;
        this.image = image;
    }



    public String getCarId() {
        return carId;
    }

    public String getBrand() {
        return brand;
    }

    public String getModel() {
        return model;
    }

    public Double getPrice() {
        return price;
    }

    public String getStatus() {
        return status;
    }

    public Date getDate() {
        return date;
    }

    public String getImage() {
        return image;
    }
}