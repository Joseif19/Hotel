module com.example.hotel {
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.web;
    requires java.sql;

    requires org.controlsfx.controls;
    requires com.dlsc.formsfx;
    requires net.synedra.validatorfx;
    requires org.kordamp.ikonli.javafx;
    requires org.kordamp.bootstrapfx.core;
    requires eu.hansolo.tilesfx;
    requires com.almasb.fxgl.all;
    requires AccesoBBDDMoneda;
    requires mysql.connector.java;

    opens com.example.hotel to javafx.fxml;
    exports com.example.hotel.controller;
    opens com.example.hotel.controller to javafx.fxml;
    exports com.example.hotel;
}