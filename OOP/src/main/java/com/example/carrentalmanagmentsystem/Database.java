package com.example.carrentalmanagmentsystem;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import javax.xml.transform.Result;
import java.sql.*;

public class Database {
    boolean checkPass(String username, String password) {
        try {
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/hadi", "root", "hadi123");

            if (con != null) {
                String query = "Select * from users";
                Statement stm = con.createStatement();
                ResultSet rs = stm.executeQuery(query);
                while (rs.next()) {
                    if ((username.equals(rs.getString(1))) && (password.equals(rs.getString(2)))) {
                        con.close();
                        return true;
                    }
                }
                con.close();
            }
        } catch (SQLException e) {
            System.err.println("Error Occured: " + e.getMessage());
        }
        return false;
    }

    void createData(String regNum, String brand, String model, double price, String status, String URL) {
        try {
            // Establishing connection to the MySQL database
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/hadi", "root", "hadi123");
            // If connection is successful, print a success message
            if (con != null) {
                String query = "INSERT INTO Car (RegistrationNumber, Brand, Model, Price, Status, Date, URL)" +
                        "VALUES (?,?,?,?,?,CURRENT_DATE(),?)";

                PreparedStatement pstm = con.prepareStatement(query);
                pstm.setString(1, regNum);
                pstm.setString(2, brand);
                pstm.setString(3, model);
                pstm.setDouble(4, price);
                pstm.setString(5, status);
                pstm.setString(6,URL);
                pstm.execute();
                con.close();
            }
        } catch (SQLException e) {
            // If connection fails, print the error message
            System.err.println("Error Occured: " + e.getMessage());
        }
    }

    void request(String brand, String model) {
        try {
            // Establishing connection to the MySQL database
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/hadi", "root", "hadi123");
            // If connection is successful, print a success message
            if (con != null) {
                String query = "INSERT INTO request (Brand, Model) VALUES (?,?)";

                PreparedStatement pstm = con.prepareStatement(query);
                pstm.setString(1, brand);
                pstm.setString(2, model);
                pstm.execute();
                con.close();
            }
        } catch (SQLException e) {
            // If connection fails, print the error message
            System.err.println("Error Occured: " + e.getMessage());
        }
    }

//    void deleteCar(String registration) {
//        try {
//            // Establishing connection to the MySQL database
//            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/hadi", "root", "hadi123");
//
//            // If connection is successful, print a success message
//            if (con != null) {
//                System.out.println("Connection successful!");
//
//                // Disable foreign key checks
//                Statement disableFK = con.createStatement();
//                disableFK.executeUpdate("SET foreign_key_checks = 0");
//
//                // Prepare and execute DELETE statement
//                String query = "DELETE FROM car WHERE RegistrationNumber=?";
//                PreparedStatement pstm = con.prepareStatement(query);
//                pstm.setString(1, registration);
//                int i = pstm.executeUpdate();
//
//                // Re-enable foreign key checks
//                Statement enableFK = con.createStatement();
//                enableFK.executeUpdate("SET foreign_key_checks = 1");
//
//
//                con.close();
//            }
//        } catch (SQLException e) {
//            System.out.println("Error Occurred: " + e);
//        }
//    }



    void deleteCar(String registration) {
        try {
            // Establishing connection to the MySQL database
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/hadi", "root", "hadi123");

            // If connection is successful, print a success message
            if (con != null) {
                String query = "DELETE FROM car WHERE RegistrationNumber=?";
                PreparedStatement pstm = con.prepareStatement(query);
                pstm.setString(1, registration);
                int i = pstm.executeUpdate();
                // Don't forget to close the connection when you're done using it
                con.close();
            }
        } catch (SQLException e) {
            // If connection fails, print the error message
            System.err.println("Error Occured: " + e.getMessage());
        }
    }

    void changeStatus(String Registration) {
        try {
            // Establishing connection to the MySQL database
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/hadi", "root", "hadi123");

            // If connection is successful, print a success message
            if (con != null) {
                String query = "UPDATE car SET Status=? where RegistrationNumber=?";
                PreparedStatement pstm = con.prepareStatement(query);
                pstm.setString(1, "Unavailable");
                pstm.setString(2, Registration);
                pstm.executeUpdate();
                // Don't forget to close the connection when you're done using it
                con.close();
            }
        } catch (SQLException e) {
            // If connection fails, print the error message
            System.err.println("Error Occured: " + e.getMessage());
        }
    }

    void enterRentedCars(String Name, String Registration,Double Rent,String RentingDate, String ReturningDate) {
        try {
            // Establishing connection to the MySQL database
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/hadi", "root", "hadi123");

            // If connection is successful, print a success message

            String query = "INSERT into RentedCars (Name,Registration,TotalRent,RentingDate,ReturningDate) VALUES (?,?,?,?,?)";
            PreparedStatement pstm = con.prepareStatement(query);
            pstm.setString(1, Name);
            pstm.setString(2, Registration);
            pstm.setDouble(3,Rent);
            pstm.setString(4, RentingDate);
            pstm.setString(5, ReturningDate);
            pstm.execute();
            con.close();
        } catch (SQLException e) {
            System.err.println("Error Occured: " + e);
            System.out.println("haa");
        }
    }


//    void enterRentedCars(String Name, String Registration, String RentingDate, String ReturningDate,Double Rent) {
//        try {
//            // Establishing connection to the MySQL database
//            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/hadi", "root", "hadi123");
//
//            // If connection is successful, print a success message
//            if (con != null) {
//                System.out.println("Connection successful!");
//                String query = "INSERT into RentedCars (Name,RegistrationNumber,RentingDate,ReturningDate,Rent) VALUES (?,?,?,?,?)";
//                PreparedStatement pstm = con.prepareStatement(query);
//                pstm.setString(1, Name);
//                pstm.setString(2, Registration);
//                pstm.setString(3, RentingDate);
//                pstm.setString(4, ReturningDate);
//                pstm.setDouble(5,Rent);
//                pstm.execute();
//                // Don't forget to close the connection when you're done using it
//                con.close();
//            }
//        } catch (SQLException e) {
//            // If connection fails, print the error message
//            System.err.println("Error Occured: " + e.getMessage());
//        }
//    }


    public static ObservableList<CarData> getCarDataFromDatabase(){
        try{
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/hadi", "root", "hadi123");
            ObservableList<CarData>list= FXCollections.observableArrayList();

            PreparedStatement ps=con.prepareStatement("SELECT * from car");
            ResultSet rs=ps.executeQuery();
            while(rs.next()){
                list.add(new CarData(rs.getString(1),rs.getString(2),rs.getString(3),rs.getDouble(4),rs.getString(5),rs.getString(6)));
            }
            return list;
        }
        catch(SQLException e){

        }
        return null;
    }

    public static ObservableList<CarData> getRequestCarDataFromDatabase(){
        try{
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/hadi", "root", "hadi123");
            ObservableList<CarData>list= FXCollections.observableArrayList();

            PreparedStatement ps=con.prepareStatement("SELECT * from request");
            ResultSet rs=ps.executeQuery();
            while(rs.next()){
                list.add(new CarData(rs.getString(1),rs.getString(2)));
            }
            return list;
        }
        catch(SQLException e){

        }
        return null;
    }

    public static ObservableList<Sales> getReportCarDataFromDatabase(){
        try{
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/hadi", "root", "hadi123");
            ObservableList<Sales>list= FXCollections.observableArrayList();

            PreparedStatement ps=con.prepareStatement("SELECT * from rentedcars");
            ResultSet rs=ps.executeQuery();
            while(rs.next()){
                list.add(new Sales(rs.getString(1),rs.getString(2),rs.getDouble(3),rs.getString(4),rs.getString(5)));
            }
            return list;
        }
        catch(SQLException e){

        }
        return null;
    }

//    public void DisplayReports(){
//
//        try{
//            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/hadi", "root", "hadi123");
//
//            PreparedStatement ps=con.prepareStatement("SELECT * from rentedcars");
//            ResultSet rs=ps.executeQuery();
//            while(rs.next()){
//                System.out.println(rs.getString(1)+ " | " + rs.getString(2) + " | " + rs.getDouble(3) + " | " + rs.getString(4) + " | " + rs.getString(5));
//                       }
//
//        }
//        catch(SQLException e){
//
//        }
//
//    }

}