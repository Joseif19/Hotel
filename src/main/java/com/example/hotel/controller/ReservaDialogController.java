package com.example.hotel.controller;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.collections.FXCollections;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import model.Persona;
import model.Reserva;

import java.io.IOException;

public class ReservaDialogController {

    private Stage dialogStage;
    private Reserva reserva;

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

    public void setDialogStage(Stage dialogStage) {
        this.dialogStage = dialogStage;
    }

    public void setReserva(Reserva reserva) {
        this.reserva = reserva;

        // Si estamos editando una reserva, configuramos los valores en los controles.
        // No es necesario configurar el idReserva porque es autoincremental.

        // Establecer las fechas de llegada y salida
        fechaLlegada.setValue(reserva.getFechaLlegada());  // setValue para DatePicker
        fechaSalida.setValue(reserva.getFechaSalida());    // setValue para DatePicker

        // Establecer el número de habitaciones en el Spinner
        numHabitaciones.getValueFactory().setValue(reserva.getNumHabitaciones());  // setValue para Spinner

        // Establecer el tipo de habitación en el ComboBox
        tipoHabitacion.setValue(reserva.getTipoHabitacion());  // setValue para ComboBox

        // Establecer el régimen de alojamiento en el ComboBox
        regimen.setValue(reserva.getRegAlojamiento());  // setValue para ComboBox

        // Establecer la preferencia de fumador en los RadioButtons
        if (reserva.isFumador()) {
            fumador.setSelected(true);  // Seleccionar el RadioButton correspondiente
        } else {
            noFumador.setSelected(true);  // Seleccionar el RadioButton correspondiente
        }
    }



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
