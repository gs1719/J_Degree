module com.example.jdegree {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.example.jdegree to javafx.fxml;
    exports com.example.jdegree;
}