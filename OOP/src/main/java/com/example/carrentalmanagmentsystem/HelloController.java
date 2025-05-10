package com.example.carrentalmanagmentsystem;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.io.IOException;

public class HelloController {

    Alert alert;
    Stage stage;
    Scene scene;
    Parent root;


    @FXML
    private Label Rent_a_car_label;

    @FXML
    private ImageView carimg;

    @FXML
    private Button closebtn;

    @FXML
    private AnchorPane leftpane;

    @FXML
    private Button loginbtn;

    @FXML
    private HBox mainpane;

    @FXML
    private PasswordField passfield;

    @FXML
    private AnchorPane rightpane;

    @FXML
    private Label signinlabel;

    @FXML
    private ImageView userimg;

    @FXML
    private TextField usernamefield;


    @FXML
    void closeapp(ActionEvent event) {

        alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Rent-A-Car");
        alert.setHeaderText("Confirmation");
        alert.setContentText("Are you sure You want to exit?");

        if(alert.showAndWait().get()==ButtonType.OK)
            System.exit(0);
    }

    @FXML
    void login(ActionEvent event) throws IOException{
        Database db=new Database();

        // Login Alerts
        if(usernamefield.getText().isEmpty() || passfield.getText().isEmpty())
        {
            alert = new Alert(Alert.AlertType.ERROR);
            alert.setHeaderText("Blank Fields");
            alert.setTitle("Error");
            alert.setContentText("Please Fill in the Blank fields");
            alert.showAndWait();
        }

        else if(db.checkPass(usernamefield.getText(),passfield.getText()))
        {
            GetData.setUsername(usernamefield.getText());


            alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Success");
            alert.setContentText("You have succesfully Logged on");
            alert.showAndWait();
            if(usernamefield.getText().equals("admin")){

                root = FXMLLoader.load(getClass().getResource("AdminDashboard.fxml"));
                stage=(Stage)((Node)event.getSource()).getScene().getWindow();
                scene=new Scene(root);
                stage.setScene(scene);
                stage.centerOnScreen();
                stage.show();
            }
            else{
                root = FXMLLoader.load(getClass().getResource("UserDashboard.fxml"));
                stage=(Stage)((Node)event.getSource()).getScene().getWindow();
                scene=new Scene(root);
                stage.setScene(scene);
                stage.centerOnScreen();
                stage.show();
            }

        }
        else
        {
            alert = new Alert(Alert.AlertType.ERROR);
            alert.setHeaderText("Invalid Input");
            alert.setTitle("Error");
            alert.setContentText("The username or the password is wrong");
            alert.showAndWait();
        }
        // Login Alert Ends
    }
}

