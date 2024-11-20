package com.example.hotel.controller;

import com.example.hotel.MainApp;
import javafx.scene.control.Alert;
import model.Persona;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;

import java.io.IOException;


public class PersonasLayoutController {
    @FXML
    private TableView<Persona> tablaPersonas;
    @FXML
    private TableColumn<Persona, String> firstNameColumn;
    @FXML
    private TableColumn<Persona, String> lastNameColumn;

    @FXML
    private Label dni;
    @FXML
    private Label nombre;
    @FXML
    private Label apellidos;
    @FXML
    private Label direccion;
    @FXML
    private Label localidad;
    @FXML
    private Label provincia;

    private MainApp mainApp;

    public PersonasLayoutController() {
    }
    @FXML
    private void initialize() {
        firstNameColumn.setCellValueFactory(cellData -> cellData.getValue().nombreProperty());
        lastNameColumn.setCellValueFactory(cellData -> cellData.getValue().apellidosProperty());

        showPersonDetails(null);

        tablaPersonas.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> showPersonDetails(newValue));
    }

    @FXML
    private void handleNewPerson() throws IOException {
        Persona tempPersona = new Persona();
        boolean okClicked = mainApp.showPersonEditDialog(tempPersona);
        if (okClicked) {
            mainApp.getPersonas().add(tempPersona);
        }
    }

    @FXML
    private void handleDeletePerson() {
        int selectedIndex = tablaPersonas.getSelectionModel().getSelectedIndex();
        if (selectedIndex >= 0) {
            tablaPersonas.getItems().remove(selectedIndex);
        } else {
            // Nada seleccionado
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Seleciona algo");
            alert.setHeaderText("Ninguna persona selecionada");
            alert.setContentText("Elige una persona de la tabla.");

            alert.showAndWait();
        }
    }

    @FXML
    private void handleEditPerson() throws IOException {
        Persona selectedPerson = tablaPersonas.getSelectionModel().getSelectedItem();
        if (selectedPerson != null) {
            boolean okClicked = mainApp.showPersonEditDialog(selectedPerson);
            if (okClicked) {
                showPersonDetails(selectedPerson);
            }

        } else {
            // Nada seleccionado.
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Seleciona algo");
            alert.setHeaderText("Ninguna persona selecionada");
            alert.setContentText("Elige una persona de la tabla.");

            alert.showAndWait();
        }

    }

    public void setMainApp(MainApp mainApp) {
        this.mainApp = mainApp;

        tablaPersonas.setItems(mainApp.getPersonas());
    }

    private void showPersonDetails(Persona person) {
        if (person != null) {
            dni.setText(person.getDni());
            nombre.setText(person.getNombre());
            apellidos.setText(person.getApellidos());
            direccion.setText(person.getDireccion());
            localidad.setText(person.getLocalidad());
            provincia.setText(person.getProvincia());

        } else {
            dni.setText("");
            nombre.setText("");
            apellidos.setText("");
            direccion.setText("");
            localidad.setText("");
            provincia.setText("");
        }
    }
}
