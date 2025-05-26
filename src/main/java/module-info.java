module javafxtest.event {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;
    requires jdk.compiler;
    requires com.zaxxer.hikari;
    requires org.slf4j;
    requires org.slf4j.simple;

    requires com.fasterxml.jackson.databind;
    requires com.fasterxml.jackson.core;
    requires com.fasterxml.jackson.annotation;
    requires com.fasterxml.jackson.datatype.jsr310;

    opens javafxtest.event to javafx.fxml;
    exports javafxtest.event;
}