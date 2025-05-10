package com.example.carrentalmanagmentsystem;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.ResourceBundle;


public class popupController implements Initializable {

    public double getTotalrent() {
        return totalrent;
    }

    public void setTotalrent(double totalrent) {
        this.totalrent = totalrent;
    }

    LocalDate localDate= LocalDate.now();
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        RentingDatePicker.setValue(localDate);
    }

    Stage stage;
    Alert.AlertType alertType;
    int baseprice=0;
    static double totalrent;
    Alert alert;

    @FXML
    private Label CarNameppLabel;

    @FXML
    private Label CarPriceppLabel;

    @FXML
    private DatePicker RentingDatePicker;

    @FXML
    private DatePicker ReturningDatePicker;

    @FXML
    private TextField SelectedCarUsername;

    Parent root;


    public void displaycarname(String username){
        CarNameppLabel.setText(username);
    }

    public void displaycarprice(String price){
        CarPriceppLabel.setText(price + " - PKR per Day");
        baseprice=(int)(Double.parseDouble(price));
    }

    public void EnableRentingDate(){
        this.RentingDatePicker.setDisable(false);
        this.RentingDatePicker.setValue(null);
    }


    @FXML
    void popupClear(ActionEvent event) {
        stage=(Stage)((Node)event.getSource()).getScene().getWindow();
        stage.close();
    }

    @FXML
    void FinallyRent(ActionEvent event) throws IOException {

        if (SelectedCarUsername.getText().isEmpty() || RentingDatePicker.getValue() == null || ReturningDatePicker.getValue() == null) {
            showAlert(Alert.AlertType.ERROR, "Form Error!", "Please fill in all the fields.");
            return;
        }

        LocalDate returningDate = ReturningDatePicker.getValue();
        LocalDate rentingDate = RentingDatePicker.getValue();

        if (rentingDate.isBefore(localDate)) {
            showAlert(Alert.AlertType.ERROR, "Date Error!", "Select a valid Renting Date.");
            return;
        }

        if (rentingDate.isAfter(localDate.plusMonths(1))) {
            showAlert(Alert.AlertType.ERROR, "Date Error!", "Bookings can only be done 1 month prior.");
            return;
        }

        if (returningDate.isBefore(rentingDate)) {
            showAlert(Alert.AlertType.ERROR, "Date Error!", "The returning date cannot be before the renting date.");
            return;
        }

        if (returningDate.isBefore(rentingDate.plusDays(1))) {
            showAlert(Alert.AlertType.ERROR, "Date Error!", "The returning date must be at least 1 day after the renting date.");
            return;
        }

        if (returningDate.isAfter(rentingDate.plusMonths(1))) {
            showAlert(Alert.AlertType.ERROR, "Date Error!", "Cars can only be rented for a month. Select for a lesser period");
            return;
        }

        // Rest of the Code
        long daysBetween = ChronoUnit.DAYS.between(rentingDate, returningDate);


        alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Confirmation");
        alert.setHeaderText("Total Rent:" + (int)(daysBetween*baseprice) + " PKR.");
        alert.setContentText("You are renting " + CarNameppLabel.getText() + " for " + daysBetween + " days. Total Rent is " + (int)(daysBetween*baseprice) + " PKR. Do you want to confirm this deal?");

        if(alert.showAndWait().get()== ButtonType.OK){

            try {
                Database db = new Database();
                totalrent= daysBetween*baseprice;
                this.setTotalrent(totalrent);
                db.enterRentedCars(this.SelectedCarUsername.getText(),CarNameppLabel.getText(),totalrent ,rentingDate.toString(),returningDate.toString());


                db.changeStatus(CarNameppLabel.getText());

//
            }
            catch(Exception e){
                e.printStackTrace();
            }


            alert=new Alert(Alert.AlertType.INFORMATION);
            alert.setHeaderText("Succesfull Payment. Thanks for coming.");
            alert.setContentText(null);
            alert.showAndWait();
            stage=(Stage)((Node)event.getSource()).getScene().getWindow();
            stage.close();
        }
        else
            System.out.println("boohoo");



    }


    private void showAlert(Alert.AlertType alertType, String title, String message) {
        Alert alert = new Alert(alertType);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }

}