module com.example.carrentalmanagmentsystem {
    requires javafx.controls;
    requires javafx.fxml;

    requires org.controlsfx.controls;
    requires java.sql;


    opens com.example.carrentalmanagmentsystem to javafx.fxml;
    exports com.example.carrentalmanagmentsystem;
}