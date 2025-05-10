package com.example.carrentalmanagmentsystem;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import org.w3c.dom.events.MouseEvent;

import java.io.IOException;

public class CarCardController {

    @FXML
    private Label Dcarnamelabel;

    @FXML
    private Label Dcarpricelabel;


    @FXML
    private ImageView carimg;

    @FXML
    private VBox plainvbox;


    public void setData(CarCard carCard){
        try {
            Image image = new Image(getClass().getResourceAsStream(carCard.getCarCardImageSRC()));
            carimg.setImage(image);
        }
        catch(Exception e){
            Image image = new Image(getClass().getResourceAsStream("/Images/Aventador.png"));
            carimg.setImage(image);
        }

        Dcarnamelabel.setText(carCard.getCarCardName());
        Dcarpricelabel.setText(carCard.getCarCardPrice());


    }

    Stage stage1=new Stage();
    Scene scene;
    Parent root1,root2;

    @FXML
    void SelectedCardCar(ActionEvent event) throws IOException{
        FXMLLoader loader = new FXMLLoader(getClass().getResource("popup.fxml"));
        FXMLLoader loader1 = new FXMLLoader(getClass().getResource("UserDashboard.fxml"));
        root2 = loader1.load();

        // FXMLLoader loader1 = new FXMLLoader(getClass().getResource("UserDashboard.fxml"));
        root1 = loader.load();
        UserDashboardController udc = loader1.getController();
        popupController ppc = loader.getController();
        try {
            ppc.displaycarname(udc.givenCarModel);
            ppc.displaycarprice(udc.givenCarPrice);
            scene = new Scene(root1);
            stage1.setScene(scene);
            stage1.show();
        }
        catch(Exception e){
            e.printStackTrace();
        }
    }

    public void LoadCar(MouseEvent event) throws IOException {

//        FXMLLoader loader = new FXMLLoader(getClass().getResource("popup.fxml"));
//        root = loader.load();
//        popupController ppc = loader.getController();
//        try {
//          // stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
//            scene = new Scene(root);
//            stage.setScene(scene);
//            stage.show();
//        }
//        catch(Exception e){
//            e.printStackTrace();
//        }


    }

}