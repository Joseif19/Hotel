package com.example.hotel.controller;

import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.collections.FXCollections;
import javafx.stage.Stage;
import model.Reserva;
import model.ReservaUtil;
import model.repository.impl.ReservaRepositoryImpl;
import model.ExcepcionReserva;

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

    private boolean okClicked = false;

    private ReservaRepositoryImpl reservaRepository = new ReservaRepositoryImpl();

    public void setDialogStage(Stage dialogStage) {
        this.dialogStage = dialogStage;
    }

    public boolean isOkClicked() {
        return okClicked;
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
        // Validate input
        if (isInputValid()) {
            // Capture data from form fields
            reserva.setFechaLlegada(fechaLlegada.getValue());
            reserva.setFechaSalida(fechaSalida.getValue());
            reserva.setNumHabitaciones(numHabitaciones.getValue());
            reserva.setTipoHabitacion(tipoHabitacion.getValue());
            reserva.setFumador(fumador.isSelected());
            reserva.setRegAlojamiento(regimen.getValue());

            try {
                // Convert to ReservaVO and save to database
                reservaRepository.addReserva(ReservaUtil.toReservaVO(reserva));
                dialogStage.close(); // Close the dialog if successful
            } catch (ExcepcionReserva e) {
                showErrorDialog("Error al guardar la reserva", "No se pudo guardar la reserva en la base de datos.", e);
            }
        }
    }

    private boolean isInputValid() {
        String errorMessage = "";

        if (fechaLlegada.getValue() == null) {
            errorMessage += "Fecha de llegada no válida!\n";
        }
        if (fechaSalida.getValue() == null) {
            errorMessage += "Fecha de salida no válida!\n";
        }
        if (numHabitaciones.getValue() == null || numHabitaciones.getValue() <= 0) {
            errorMessage += "Número de habitaciones no válido!\n";
        }
        if (tipoHabitacion.getValue() == null || tipoHabitacion.getValue().isEmpty()) {
            errorMessage += "Tipo de habitación no válido!\n";
        }
        if (regimen.getValue() == null || regimen.getValue().isEmpty()) {
            errorMessage += "Régimen de alojamiento no válido!\n";
        }

        if (errorMessage.isEmpty()) {
            return true;
        } else {
            showErrorDialog("Campos no válidos", "Por favor, corrige los campos no válidos", new Exception(errorMessage));
            return false;
        }
    }

    private void showErrorDialog(String title, String message, Exception e) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle(title);
        alert.setHeaderText(message);
        alert.setContentText(e.getMessage());
        alert.showAndWait();
    }
}