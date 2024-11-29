package com.example.hotel.controller;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.collections.FXCollections;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.IOException;

public class ReservaDialogController {

    @FXML
    private DatePicker fechaLlegada;
    @FXML
    private DatePicker fechaSalida;
    @FXML
    private Spinner<Integer> numHabitaciones;
    @FXML
    private ComboBox<String> tipoHabitacion;
    @FXML
    private RadioButton fumador;
    @FXML
    private RadioButton noFumador;
    @FXML
    private ComboBox<String> regimen;
    @FXML
    private Button btnHacerReserva;

    @FXML
    private void initialize() {
        // Inicializar los ComboBox con los valores correspondientes
        tipoHabitacion.setItems(FXCollections.observableArrayList(
                "Doble de uso individual (20 Habitaciones)",
                "Doble (80 Habitaciones)",
                "Junior suite (15 Habitaciones)",
                "Suite (5 Habitaciones)"
        ));

        regimen.setItems(FXCollections.observableArrayList(
                "Alojamiento y desayuno",
                "Media pensión",
                "Pensión completa"
        ));
    }



    @FXML
    private void handleHacerReserva() {
        try {
            // Cargar el archivo FXML para la ventana de reserva
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/example/hotel/vista/ReservaDialogVista.fxml"));
            AnchorPane reservaLayout = loader.load();

            // Crear la escena para la nueva ventana
            Scene escenaReserva = new Scene(reservaLayout);

            // Crear la nueva ventana
            Stage ventanaReserva = new Stage();
            ventanaReserva.setTitle("Hacer Reserva");
            ventanaReserva.setScene(escenaReserva);

            // Mostrar la ventana
            ventanaReserva.show();
        } catch (IOException e) {
            e.printStackTrace();
            // Manejo de errores
        }
    }
}
