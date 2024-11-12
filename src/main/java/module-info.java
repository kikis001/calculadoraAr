module com.example.calculadoraarb {
    requires javafx.controls;
    requires javafx.fxml;
    requires jdk.compiler;


    opens com.example.calculadoraarb to javafx.fxml;
    exports com.example.calculadoraarb;
}