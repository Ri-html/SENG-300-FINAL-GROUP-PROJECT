module ca.ucalgary.cpsc.projectguiv1 {
    requires javafx.controls;
    requires javafx.fxml;


    opens ca.ucalgary.cpsc.projectguiv1 to javafx.fxml;
    exports ca.ucalgary.cpsc.projectguiv1;
}