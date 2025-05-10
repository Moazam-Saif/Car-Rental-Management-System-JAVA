package com.example.carrentalmanagmentsystem;

import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.*;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class UserDashboardController implements Initializable {
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        myusernamelabel.setText(GetData.getUsername());
        // Database db = new Database();
        // listm = db.


        carCards= new ArrayList<>(carCards());

        int columns=0;
        int rows =1;


        try {
            for (int i = 0; i < carCards.size(); i++) {

                FXMLLoader fxmlLoader = new FXMLLoader();
//
                fxmlLoader.setLocation(getClass().getResource("CarCard.fxml"));

                VBox box = fxmlLoader.load();
                CarCardController carCardController= fxmlLoader.getController();
//                if(rows!=0)
                carCardController.setData(carCards.get(i));

                if(columns==3){
                    columns=0;
                    ++rows;
                }

                grid.add(box,columns++,rows);
                grid.setVgap(3);
                grid.setHgap(5.5);
                GridPane.setMargin(box,new Insets(10));

            }
        }
        catch(IOException e){
            e.printStackTrace();
        }

        CarIDColumn.setCellValueFactory(new PropertyValueFactory<CarData, String>("carId"));
        BrandColumn.setCellValueFactory(new PropertyValueFactory<CarData, String>("brand"));
        ModelColumn.setCellValueFactory(new PropertyValueFactory<CarData, String>("model"));
        PriceColumn.setCellValueFactory(new PropertyValueFactory<CarData, Double>("price"));
        StatusColumn.setCellValueFactory(new PropertyValueFactory<CarData, String>("status"));


        listm = Database.getCarDataFromDatabase();

        DeleteCarTable.setItems(listm);

        CarIDColumn1.setCellValueFactory(new PropertyValueFactory<CarData, String>("carId"));
        BrandColumn1.setCellValueFactory(new PropertyValueFactory<CarData, String>("brand"));
        ModelColumn1.setCellValueFactory(new PropertyValueFactory<CarData, String>("model"));
        PriceColumn1.setCellValueFactory(new PropertyValueFactory<CarData, Double>("price"));
        StatusColumn1.setCellValueFactory(new PropertyValueFactory<CarData, String>("status"));

        listm1 = Database.getCarDataFromDatabase();

        DeleteCarTable1.setItems(listm1);
    }



    Alert alert;
    Stage stage;
    Scene scene;
    Parent root;
    Stage popupstage;

    @FXML
    private Button SelectPreBookCarButton;

    @FXML
    private Button SelectRentACarbtn;

    @FXML
    private AnchorPane HomeContentPane;

    @FXML
    private Label myusernamelabel;

    @FXML
    private AnchorPane RentACarPane;

    @FXML
    private AnchorPane preBookACarPane;

    @FXML
    private Button mybtn;



    @FXML
    private HBox SceneChangeHBox;

    @FXML
    private AnchorPane UpperTitlePane;

    @FXML
    private GridPane grid;

    @FXML
    private BorderPane maindashboardPane;

    @FXML
    private AnchorPane maininfopane;



    @FXML
    private AnchorPane RequestACarPane;

    @FXML
    private Button Requestbtn;

    @FXML
    private TextField ModelField;

    @FXML
    private TextField BrandField;

    @FXML
    private TableView<CarData> DeleteCarTable;

    @FXML
    private TableColumn<CarData,String> CarIDColumn;

    @FXML
    private TableColumn<CarData,String> ModelColumn;

    @FXML
    private TableColumn<CarData,Double> PriceColumn;

    @FXML
    private TableColumn<CarData, String> StatusColumn;

    @FXML
    private TableColumn<CarData,String> BrandColumn;

    @FXML
    private TableView<CarData> DeleteCarTable1;
    @FXML
    private TableColumn<CarData,String> CarIDColumn1;

    @FXML
    private TableColumn<CarData,String> ModelColumn1;

    @FXML
    private TableColumn<CarData,Double> PriceColumn1;

    @FXML
    private TableColumn<CarData, String> StatusColumn1;

    @FXML
    private TableColumn<CarData,String> BrandColumn1;


    ObservableList<CarData> listm,listm1;
    Integer index;



    @FXML
    void close(ActionEvent event) {
        alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Rent-A-Car");
        alert.setHeaderText("Confirmation");
        alert.setContentText("Are you sure You want to exit?");

        if(alert.showAndWait().get()== ButtonType.OK)
            System.exit(0);
    }

    @FXML
    void logout(ActionEvent event) throws IOException {
        alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setHeaderText("Confirmation");
        alert.setContentText("Do you want to go to Login Screen?");
        alert.setTitle("Confirm");

        if(alert.showAndWait().get()==ButtonType.OK)
        {
            root = FXMLLoader.load(getClass().getResource("Login.fxml"));
            stage=(Stage)((Node)event.getSource()).getScene().getWindow();
            scene=new Scene(root);
            stage.setScene(scene);
            stage.centerOnScreen();
            stage.show();
        }
    }


    @FXML
    void Home(ActionEvent event) {
        preBookACarPane.setVisible(false);
        RentACarPane.setVisible(false);
        HomeContentPane.setVisible(true);
        RequestACarPane.setVisible(false);

    }



    @FXML
    void rentaCar(ActionEvent event) {

        preBookACarPane.setVisible(false);
        RentACarPane.setVisible(true);
        HomeContentPane.setVisible(false);
        RequestACarPane.setVisible(false);


    }

    @FXML
    void requestaCar(ActionEvent event) {

        preBookACarPane.setVisible(false);
        RentACarPane.setVisible(false);
        HomeContentPane.setVisible(false);
        RequestACarPane.setVisible(true);
    }

    @FXML
    void preBookaCar(ActionEvent event) {

        preBookACarPane.setVisible(true);
        RentACarPane.setVisible(false);
        HomeContentPane.setVisible(false);
        RequestACarPane.setVisible(false);

    }

    @FXML
    void SelectthePreBookCar(ActionEvent event) {

        if (DeleteCarTable1.getSelectionModel().getSelectedIndex()!=-1) {
            index = DeleteCarTable1.getSelectionModel().getSelectedIndex();

            if(StatusColumn1.getCellData(index).toString().equals("Unavailable")) {
                Alert alert = new Alert(Alert.AlertType.WARNING);
                alert.setTitle("Car Selection Error");
                alert.setHeaderText(null);
                alert.setContentText("You cannot rent an unavailable car.");

                alert.showAndWait();

                return;
            }

            // change
            try {
                popupstage = new Stage();

                FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("popup.fxml"));
                root = fxmlLoader.load();
                popupController ppcon=fxmlLoader.getController();
                ppcon.displaycarname( ModelColumn.getCellData(index).toString() + " - " + CarIDColumn.getCellData(index).toString());
                ppcon.displaycarprice(PriceColumn.getCellData(index).toString());
                ppcon.EnableRentingDate();

                Scene scene = new Scene(root);
                popupstage.setScene(scene);
                popupstage.setTitle("Pre-Book a Car");
                popupstage.setResizable(false);

                popupstage.show();

            }
            catch (Exception e){
                alert = new Alert(Alert.AlertType.ERROR);
                alert.setContentText("Invalid");
                alert.setHeaderText(null);
                alert.showAndWait();
                e.printStackTrace();
            }

        } else {
            alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("No Selection");
            alert.setHeaderText(null);
            alert.setContentText("Please Select a Car ID");
            alert.showAndWait();
        }

    }



    @FXML
    void SelecttheRentACar(ActionEvent event) throws IOException{



        if (DeleteCarTable.getSelectionModel().getSelectedIndex()!=-1) {
            index = DeleteCarTable.getSelectionModel().getSelectedIndex();


            if(StatusColumn.getCellData(index).toString().equals("Unavailable")) {
                Alert alert = new Alert(Alert.AlertType.WARNING);
                alert.setTitle("Car Selection Error");
                alert.setHeaderText(null);
                alert.setContentText("You cannot rent an unavailable car.");

                alert.showAndWait();

                return;
            }

            // change
            try {
                popupstage = new Stage();

                FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("popup.fxml"));
                root = fxmlLoader.load();
                popupController ppcon=fxmlLoader.getController();
                ppcon.displaycarname(CarIDColumn.getCellData(index).toString());
                ppcon.displaycarprice(PriceColumn.getCellData(index).toString());

                Scene scene = new Scene(root);
                popupstage.setScene(scene);
                popupstage.setTitle("Selected Car");
                popupstage.setResizable(false);

                popupstage.show();

            }
            catch (Exception e){
                alert = new Alert(Alert.AlertType.ERROR);
                alert.setContentText("Invalid");
                alert.showAndWait();
                e.printStackTrace();
            }

        } else {
            alert = new Alert(Alert.AlertType.ERROR);
            alert.setHeaderText("No Selection");
            alert.setContentText("Please Select a Car ID");
            alert.showAndWait();
        }

    }

    @FXML
    void Request(ActionEvent event) {
        Database db=new Database();
        db.request(BrandField.getText(),ModelField.getText());
        alert= new Alert(Alert.AlertType.INFORMATION);
        alert.setHeaderText("Car Requested Succesfully");
        alert.setContentText(null);
        alert.setTitle("Success");
        alert.showAndWait();
    }



    private List<CarCard> carCards;

    String givenCarModel;
    String givenCarPrice;

    public int findCorrecti(String model) {
        for (int i = 0; i < listm.size(); i++) {
            if (listm.get(i).getModel().equals(model)) {
                return i; // Return the index if model matches
            }
        }
        // Return -1 if model is not found
        return -1;
    }


    int myindex;
    private List<CarCard> carCards() {
        List<CarCard> ls = new ArrayList<>();




        CarCard carcard = new CarCard();
        listm = Database.getCarDataFromDatabase();

        CarData mycar;
        for(int i=0;i<listm.size();i++){

            carcard = new CarCard();
            mycar = listm.get(i);

            try {
                carcard.setCarCardImageSRC("/Images/" + mycar.getModel() + ".png");
            }
            catch (Exception e){
                carcard.setCarCardImageSRC("/Images/Aventador.png");
            }
            carcard.setCarCardName(mycar.getModel());


            // myindex=findCorrecti(grid.getColumnCount());
            this.givenCarModel= mycar.getModel();

            carcard.setCarCardPrice(mycar.getPrice().toString());
            this.givenCarPrice=mycar.getPrice().toString();
            ls.add(carcard);

        }
//
//        carcard = new CarCard();
//        carcard.setCarCardImageSRC("/Images/Aventador.png");
//        carcard.setCarCardName("Aventador");
//        carcard.setCarCardPrice("$200 Monthly");
//        ls.add(carcard);
//
//        carcard = new CarCard();
//        carcard.setCarCardImageSRC("/Images/Porsche-Taycan.png");
//        carcard.setCarCardName("Porsche-Taycan");
//        carcard.setCarCardPrice("$200 Monthly");
//        ls.add(carcard);
//
//        carcard = new CarCard();
//        carcard.setCarCardImageSRC("/Images/etron.png");
//        carcard.setCarCardName("Audi Etron");
//        carcard.setCarCardPrice("$600 Monthly");
//        ls.add(carcard);
//
//        carcard = new CarCard();
//        carcard.setCarCardImageSRC("/Images/Ferarri.png");
//        carcard.setCarCardName("Ferrari");
//        carcard.setCarCardPrice("$600 Monthly");
//        ls.add(carcard);
//
//        carcard = new CarCard();
//        carcard.setCarCardImageSRC("/Images/BMW.png");
//        carcard.setCarCardName("BMW");
//        carcard.setCarCardPrice("$600 Monthly");
//        ls.add(carcard);
//
//        carcard = new CarCard();
//        carcard.setCarCardImageSRC("/Images/mercedes.png");
//        carcard.setCarCardName("Mercedes");
//        carcard.setCarCardPrice("$600 Monthly");
//        ls.add(carcard);
//
//        carcard = new CarCard();
//        carcard.setCarCardImageSRC("/Images/Porsche-Taycan.png");
//        carcard.setCarCardName("Porsche-Taycan");
//        carcard.setCarCardPrice("$200 Monthly");
//        ls.add(carcard);
//
//
//        carcard = new CarCard();
//        carcard.setCarCardImageSRC("/Images/Aventador.png");
//        carcard.setCarCardName("Aventador");
//        carcard.setCarCardPrice("$200 Monthly");
//        ls.add(carcard);
//
//        carcard = new CarCard();
//        carcard.setCarCardImageSRC("/Images/etron.png");
//        carcard.setCarCardName("Audi Etron");
//        carcard.setCarCardPrice("$600 Monthly");
//        ls.add(carcard);
//
//        carcard = new CarCard();
//        carcard.setCarCardImageSRC("/Images/Ferarri.png");
//        carcard.setCarCardName("Ferrari");
//        carcard.setCarCardPrice("$600 Monthly");
//        ls.add(carcard);
//
//        carcard = new CarCard();
//        carcard.setCarCardImageSRC("/Images/BMW.png");
//        carcard.setCarCardName("BMW");
//        carcard.setCarCardPrice("$600 Monthly");
//        ls.add(carcard);
//
//        carcard = new CarCard();
//        carcard.setCarCardImageSRC("/Images/mercedes.png");
//        carcard.setCarCardName("Mercedes");
//        carcard.setCarCardPrice("$600 Monthly");
//        ls.add(carcard);
//
//








        return ls;
    }




}