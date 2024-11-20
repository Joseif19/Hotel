package com.example.hotel.controller;

import com.example.hotel.MainApp;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import model.Persona;

public class PersonEditDialogController {

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

    private Stage dialogStage;
    private Persona persona;
    private boolean okClicked = false;

    // Establece el escenario para este diálogo
    public void setDialogStage(Stage dialogStage) {
        this.dialogStage = dialogStage;
    }

    // Establece la persona que se va a editar
    public void setPerson(Persona persona) {
        this.persona = persona;

        if (persona != null) {
            dniField.setText(persona.getDni());
            nombreField.setText(persona.getNombre());
            apellidosField.setText(persona.getApellidos());
            direccionField.setText(persona.getDireccion());
            localidadField.setText(persona.getLocalidad());
            provinciaField.setText(persona.getProvincia());
        }
    }

    // Verifica si el usuario hizo clic en OK
    public boolean isOkClicked() {
        return okClicked;
    }

    // Método que se llamará cuando el usuario haga clic en el botón OK
    @FXML
    private void handleOk() {
        String dni = dniField.getText();
        if (!esDniValido(dni)) {
            showError("DNI inválido", "El DNI introducido no es válido.");
            return;
        }

        if (isInputValid()) {
            persona.setDni(dni);
            persona.setNombre(nombreField.getText());
            persona.setApellidos(apellidosField.getText());
            persona.setDireccion(direccionField.getText());
            persona.setLocalidad(localidadField.getText());
            persona.setProvincia(provinciaField.getText());

            okClicked = true;
            dialogStage.close();
        }
    }

    private void showError(String header, String content) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Error");
        alert.setHeaderText(header);
        alert.setContentText(content);
        alert.showAndWait();
    }

    private static final char[] LETRAS = {
            'T', 'R', 'W', 'A', 'G', 'M', 'Y', 'F', 'P', 'D', 'X', 'B', 'N', 'J', 'Z', 'S', 'Q', 'V', 'H', 'L', 'C', 'K', 'E'
    };

    private boolean esDniValido(String dni) {

        if (dni == null || dni.length() != 9) {
            return false;
        }

        String numeroStr = dni.substring(0, 8); // Los primeros 8 caracteres deben ser números
        char letra = dni.charAt(8); // El último carácter debe ser la letra

        if (!numeroStr.matches("\\d{8}")) {
            return false;
        }

        int numero = Integer.parseInt(numeroStr);
        int indiceLetra = numero % 23;

        return letra == LETRAS[indiceLetra];
    }

    @FXML
    private void handleCancel() {
        dialogStage.close();
    }

    // Valida la entrada del usuario
    private boolean isInputValid() {
        String errorMessage = "";

        if (dniField.getText() == null || dniField.getText().isEmpty()) {
            errorMessage += "¡El DNI no puede estar vacío!\n";
        }
        if (nombreField.getText() == null || nombreField.getText().isEmpty()) {
            errorMessage += "¡El nombre no puede estar vacío!\n";
        }
        if (apellidosField.getText() == null || apellidosField.getText().isEmpty()) {
            errorMessage += "¡Los apellidos no pueden estar vacíos!\n";
        }
        if (direccionField.getText() == null || direccionField.getText().isEmpty()) {
            errorMessage += "¡La dirección no puede estar vacía!\n";
        }
        if (localidadField.getText() == null || localidadField.getText().isEmpty()) {
            errorMessage += "¡La localidad no puede estar vacía!\n";
        }
        if (provinciaField.getText() == null || provinciaField.getText().isEmpty()) {
            errorMessage += "¡La provincia no puede estar vacía!\n";
        }

        if (errorMessage.isEmpty()) {
            return true;
        } else {
            // Mostrar un mensaje de error al usuario
            showErrorAlert(errorMessage);
            return false;
        }
    }

    // Muestra una alerta con los mensajes de error
    private void showErrorAlert(String errorMessage) {
        javafx.scene.control.Alert alert = new javafx.scene.control.Alert(javafx.scene.control.Alert.AlertType.ERROR);
        alert.initOwner(dialogStage);
        alert.setTitle("Error en los datos");
        alert.setHeaderText("Por favor, corrige los campos inválidos");
        alert.setContentText(errorMessage);
        alert.showAndWait();
    }
}
