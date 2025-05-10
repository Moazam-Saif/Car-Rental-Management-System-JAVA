package com.example.carrentalmanagmentsystem;


import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.chart.*;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import org.w3c.dom.Document;


import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URL;
import java.sql.*;
import java.time.LocalDate;
import java.util.ResourceBundle;

public class AdminDashboardController implements Initializable {

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        CarIDColumn.setCellValueFactory(new PropertyValueFactory<CarData, String>("carId"));
        BrandColumn.setCellValueFactory(new PropertyValueFactory<CarData, String>("brand"));
        ModelColumn.setCellValueFactory(new PropertyValueFactory<CarData, String>("model"));
        PriceColumn.setCellValueFactory(new PropertyValueFactory<CarData, Double>("price"));
        StatusColumn.setCellValueFactory(new PropertyValueFactory<CarData, String>("status"));
        listm = Database.getCarDataFromDatabase();

        DeleteCarTable.setItems(listm);

        BrandColumnReq.setCellValueFactory(new PropertyValueFactory<CarData,String>("brand"));
        ModelColumnReq.setCellValueFactory(new PropertyValueFactory<CarData,String>("model"));
        ReqList =Database.getRequestCarDataFromDatabase();
        requestcarTable.setItems(ReqList);

        displaylinechart();
//        DisplayBarchart();


//
//        Customers_salesReport.setCellValueFactory(new PropertyValueFactory<Sales, String>("customername"));
//        car_Salesreport.setCellValueFactory(new PropertyValueFactory<Sales, String>("Car"));
//        Totalrent_salesreport.setCellValueFactory(new PropertyValueFactory<Sales, Double>("TotalRent"));
//        Rentingdate_salesreport.setCellValueFactory(new PropertyValueFactory<Sales, String>("RentingDate"));
//        returningdate_salesreport.setCellValueFactory(new PropertyValueFactory<Sales, String>("ReturningDate"));
//        ReportList = Database.getReportCarDataFromDatabase();
//
//        Salestable.setItems(ReportList);

//        Customers_salesReport.setCellValueFactory(new PropertyValueFactory<Sales, String>("customerName"));
//        car_Salesreport.setCellValueFactory(new PropertyValueFactory<Sales, String>("car"));
//        Totalrent_salesreport.setCellValueFactory(new PropertyValueFactory<Sales, Double>("totalRent"));
//        Rentingdate_salesreport.setCellValueFactory(new PropertyValueFactory<Sales, String>("rentingDate"));
//        returningdate_salesreport.setCellValueFactory(new PropertyValueFactory<Sales, String>("returningDate"));
//        ReportList = Database.getReportCarDataFromDatabase();
//
//        Salestable.setItems(ReportList);

        textare.setText("hello");
        String temp;
//        Database db = new Database();
//        temp = db.DisplayReports();




        MoneyLabel.setText(Double.toString(popupController.totalrent));

        DisplayReports();



//        XYChart.Series series = new XYChart.Series();
//        series.getData().add(new XYChart.Data("1","5"));
//        series.getData().add(new XYChart.Data("3","7"));
//        series.getData().add(new XYChart.Data("9","12"));






    }
    Alert alert;
    Parent root;
    Stage stage;
    Scene scene;
    Double Moneytracker;



    @FXML
    private Button AddaCarbtn;

    @FXML
    private Button DeleteACarbtn;

    @FXML
    private Button GenerateReportsbtn;

    @FXML
    private Button Homebtn;

    @FXML
    private HBox SceneChangeHBox;

    @FXML
    private Button UpdateDetailsbtn;

    @FXML
    private AnchorPane UpperTitlePane;

    @FXML
    private BorderPane maindashboardPane;

    @FXML
    private AnchorPane maininfopane;

    @FXML
    private Button mybtn;

    @FXML
    private AnchorPane AddaCarPane;

    @FXML
    private Button Addbth;

    @FXML
    private TextField BrandField;

    @FXML
    private TextField CarIDField;

    @FXML
    private AnchorPane DeleteACarPane;


    @FXML
    private AnchorPane HomePane;


    @FXML
    private TextField ModelField;

    @FXML
    private TextField PriceField;

    @FXML
    private AnchorPane ReportsPane;

    @FXML
    private TableView<CarData> DeleteCarTable;

    @FXML
    private Button DeleteBtn;

    @FXML
    private TableColumn<CarData,String> BrandColumn;

    @FXML
    private TableColumn<CarData,String> CarIDColumn;


    @FXML
    private TableColumn<CarData,String> ModelColumn;

    @FXML
    private TableColumn<CarData,Double> PriceColumn;

    @FXML
    private TableColumn<CarData, String> StatusColumn;

    @FXML
    private LineChart<?, ?> CustomersChart;

    @FXML
    private Label MoneyLabel;

    @FXML
    private HBox carrequestshbox;

    @FXML
    private HBox carrequeststablehbox;

    private BarChart<String, Number> IncomeChart;

    @FXML
    private TableColumn<CarData, String> BrandColumnReq;

    @FXML
    private TableColumn<CarData, String> ModelColumnReq;

    @FXML
    private TableView<CarData> requestcarTable;

    @FXML
    private TextArea textare;

    @FXML
    private CategoryAxis x;

    @FXML
    private NumberAxis y;

    @FXML
    private CategoryAxis a;

    @FXML
    private NumberAxis b;


    public void DisplayReports() {
        StringBuilder dataBuilder = new StringBuilder();

        try {
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/hadi", "root", "hadi123");
            PreparedStatement ps = con.prepareStatement("SELECT * FROM rentedcars");
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                dataBuilder.append(rs.getString(1)).append(" | ")
                        .append(rs.getString(2)).append(" | ")
                        .append(rs.getDouble(3)).append(" | ")
                        .append(rs.getString(4)).append(" | ")
                        .append(rs.getString(5)).append("\n");
            }

            // Set the text of the TextArea to the formatted string
            textare.setText(dataBuilder.toString());

        } catch (SQLException e) {
            e.printStackTrace();
            // Optionally, show an error message in the TextArea or a dialog
            textare.setText("Error fetching data from database.");
        }
    }

    void displaylinechart(){


        XYChart.Series series = new XYChart.Series();


        series.getData().add(new XYChart.Data("0",23));
        series.getData().add(new XYChart.Data("1",17));
        series.getData().add(new XYChart.Data("2",41));
        series.getData().add(new XYChart.Data("3",27));
        CustomersChart.getData().addAll(series);
    }




    popupController ppc = new popupController();


    @FXML
    private ImageView uploadimageview;
    ObservableList<CarData>listm,ReqList;
    ObservableList<Sales> ReportList;
    Integer index;

    public TableColumn<CarData, String> getCarIDColumn() {
        return CarIDColumn;
    }

    public TableView<CarData> getDeleteCarTable() {
        return DeleteCarTable;
    }

    @FXML
    void AddaCar(ActionEvent event) throws FileNotFoundException {

        AddaCarPane.setVisible(true);
        DeleteACarPane.setVisible(false);
        HomePane.setVisible(false);
        ReportsPane.setVisible(false);



    }


    @FXML
    void hideTable(ActionEvent event) {
        carrequeststablehbox.setVisible(false);
        carrequestshbox.setVisible(true);
    }

    @FXML
    void DeleteACar(ActionEvent event) throws SQLException {

        AddaCarPane.setVisible(false);
        DeleteACarPane.setVisible(true);
        HomePane.setVisible(false);
        ReportsPane.setVisible(false);

    }

    @FXML
    void GenerateReports(ActionEvent event) {

        AddaCarPane.setVisible(false);
        DeleteACarPane.setVisible(false);
        HomePane.setVisible(false);
        ReportsPane.setVisible(true);

    }

    @FXML
    void Home(ActionEvent event) {

        AddaCarPane.setVisible(false);
        DeleteACarPane.setVisible(false);
        HomePane.setVisible(true);
        ReportsPane.setVisible(false);

    }

    @FXML
    void loadRequests(ActionEvent event) {
        carrequestshbox.setVisible(false);
        carrequeststablehbox.setVisible(true);
    }


//

    @FXML
    void Add(ActionEvent event) {
        if (CarIDField.getText().isEmpty() || ModelField.getText().isEmpty() || BrandField.getText().isEmpty() || PriceField.getText().isEmpty() || uploadimageview.getImage() == null) {
            alert = new Alert(Alert.AlertType.ERROR);
            alert.setHeaderText("Blank Fields");
            alert.setTitle("Error");
            alert.setContentText("Please Fill in the Blank Fields");
            alert.showAndWait();
        } else {
            try {
                Database db = new Database();
                db.createData(CarIDField.getText(), BrandField.getText(), ModelField.getText(), Double.parseDouble(PriceField.getText()), "Available", imageUrl);
                listm = Database.getCarDataFromDatabase();
                DeleteCarTable.setItems(listm);

                alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Success");
                alert.setContentText(null);
                alert.setHeaderText("Car Added Successfully");
                alert.showAndWait();
            } catch (RuntimeException e) {
                alert = new Alert(Alert.AlertType.ERROR);
                alert.setHeaderText("Error");
                alert.setTitle("Invalid input");
                alert.setContentText("Please Fill in valid input");
                alert.showAndWait();
            }
        }
    }


    @FXML
    void Delete(ActionEvent event) {
        if (DeleteCarTable.getSelectionModel().getSelectedIndex()!=-1) {
            index = DeleteCarTable.getSelectionModel().getSelectedIndex();

            Database db=new Database();

            alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Confirmation");
            alert.setContentText(null);
            alert.setHeaderText("Are you sure you want to Delete it?" );

            if(alert.showAndWait().get()==ButtonType.OK) {


                db.deleteCar(CarIDColumn.getCellData(index).toString());
                listm = Database.getCarDataFromDatabase();
                DeleteCarTable.setItems(listm);
            }

        } else {
            alert = new Alert(Alert.AlertType.ERROR);
            alert.setHeaderText("No Selection");
            alert.setContentText("Please Select a Car ID");
            alert.showAndWait();
        }
    }

    File file;
    Image image;
    private String imageUrl;

    public String getImageUrl() {
        return imageUrl;
    }

    @FXML
    void UploadImage(ActionEvent event) {
        try {
            FileChooser fileChooser = new FileChooser();
            file = fileChooser.showOpenDialog(new Stage());
            if (file != null) {
                image = new Image(new FileInputStream(file));
                uploadimageview.setImage(image);
                imageUrl = file.toURI().toString();
            } else {
                alert = new Alert(Alert.AlertType.WARNING);
                alert.setContentText("No file selected");
                alert.show();
            }
        } catch (FileNotFoundException e) {
            alert = new Alert(Alert.AlertType.ERROR);
            alert.setContentText("File not found");
            alert.show();
        } catch (Exception e) {
            alert = new Alert(Alert.AlertType.ERROR);
            alert.setContentText("An unexpected error occurred: " + e.getMessage());
            alert.show();
        }
    }


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

}