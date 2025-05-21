module javafxtest.event {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;
    requires jdk.compiler;
    requires com.zaxxer.hikari;
    requires org.slf4j;
    requires org.slf4j.simple;


    opens javafxtest.event to javafx.fxml;
    exports javafxtest.event;
}