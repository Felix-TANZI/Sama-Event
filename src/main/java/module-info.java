module javafxtest.event {
    requires javafx.controls;
    requires javafx.fxml;


    opens javafxtest.event to javafx.fxml;
    exports javafxtest.event;
}