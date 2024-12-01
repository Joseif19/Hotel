package com.example.hotel.controller;

import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import model.Persona;
import java.util.List;
import java.util.ArrayList;

public class PersonEditDialogController {

    private Stage dialogStage;
    private Persona persona;
    private boolean okClicked = false;

    @FXML
    private TextField dniField;
    @FXML
    private TextField nombreField;
    @FXML
    private TextField apellidosField;
    @FXML
    private TextField direccionField;
    @FXML
    private TextField localidadField;
    @FXML
    private TextField provinciaField;

    private static final String LETRAS = "TRWAGMYFPDXBNJZSQVHLCKE";

    private static List<Persona> personasRegistradas = new ArrayList<>();

    public void setDialogStage(Stage dialogStage) {
        this.dialogStage = dialogStage;
    }

    public void setPerson(Persona persona) {
        this.persona = persona;

        if (persona.getDni() != null) {
            dniField.editableProperty().set(false);
        }
        dniField.setText(persona.getDni());
        nombreField.setText(persona.getNombre());
        apellidosField.setText(persona.getApellidos());
        direccionField.setText(persona.getDireccion());
        localidadField.setText(persona.getLocalidad());
        provinciaField.setText(persona.getProvincia());
    }

    public boolean isOkClicked() {
        return okClicked;
    }

    @FXML
    private void handleOk() {
        String dni = dniField.getText();

        if (!validarDNI(dni)) {
            Alert alert = new Alert(AlertType.ERROR);
            alert.setTitle("Error de DNI");
            alert.setHeaderText("DNI inválido");
            alert.setContentText("El DNI introducido no es válido. Por favor, verifica el formato.");
            alert.showAndWait();
            return;
        }

        if (existeDni(dni)) {
            Alert alert = new Alert(AlertType.ERROR);
            alert.setTitle("DNI duplicado");
            alert.setHeaderText("Este DNI ya está registrado");
            alert.setContentText("El DNI que intenta registrar ya está asociado a otra persona.");
            alert.showAndWait();
            return;
        }

        persona.setDni(dni);
        persona.setNombre(nombreField.getText());
        persona.setApellidos(apellidosField.getText());
        persona.setDireccion(direccionField.getText());
        persona.setLocalidad(localidadField.getText());
        persona.setProvincia(provinciaField.getText());

        okClicked = true;
        personasRegistradas.add(persona);
        dialogStage.close();
    }


    @FXML
    private void handleCancel() {
        dialogStage.close();
    }

    private boolean validarDNI(String dni) {
        if (dni == null || dni.length() != 9) {
            return false;
        }

        String numero = dni.substring(0, 8);
        char letra = dni.charAt(8);

        if (!numero.matches("[0-9]{8}")) {
            return false;
        }

        int numeroDNI = Integer.parseInt(numero);
        char letraCalculada = LETRAS.charAt(numeroDNI % 23);

        return letra == letraCalculada;
    }

    private boolean existeDni(String dni) {
        for (Persona p : personasRegistradas) {
            if (p.getDni().equals(dni) && !p.equals(persona)) {
                return true;
            }
        }
        return false;
    }


}
