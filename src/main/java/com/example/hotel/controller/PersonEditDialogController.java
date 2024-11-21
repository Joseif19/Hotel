package com.example.hotel.controller;

import com.example.hotel.MainApp;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import model.Persona;

public class PersonEditDialogController {

    private Stage dialogStage;
    private Persona persona;
    private boolean okClicked = false;

    @FXML
    private TextField nombreField;
    @FXML
    private TextField apellidosField;
    @FXML
    private TextField dniField;
    @FXML
    private TextField direccionField;
    @FXML
    private TextField localidadField;
    @FXML
    private TextField provinciaField;

    public void setDialogStage(Stage dialogStage) {
        this.dialogStage = dialogStage;
    }

    public void setPerson(Persona persona) {
        this.persona = persona;

        // Rellenar los campos con los datos de la persona
        nombreField.setText(persona.getNombre());
        apellidosField.setText(persona.getApellidos());
        dniField.setText(persona.getDni());
        direccionField.setText(persona.getDireccion());
        localidadField.setText(persona.getLocalidad());
        provinciaField.setText(persona.getProvincia());
    }

    public boolean isOkClicked() {
        return okClicked;
    }

    @FXML
    private void handleOk() {
        // Actualizar los datos de la persona con los valores de los campos
        persona.setNombre(nombreField.getText());
        persona.setApellidos(apellidosField.getText());
        persona.setDni(dniField.getText());
        persona.setDireccion(direccionField.getText());
        persona.setLocalidad(localidadField.getText());
        persona.setProvincia(provinciaField.getText());

        okClicked = true;
        dialogStage.close(); // Cerrar el diálogo
    }

    @FXML
    private void handleCancel() {
        dialogStage.close(); // Cerrar el diálogo sin hacer cambios
    }
}

