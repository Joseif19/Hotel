package com.example.hotel.controller;

import com.example.hotel.MainApp;
import model.Persona;
import model.repository.impl.PersonaDAO;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
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
    private PersonaDAO personaDAO = new PersonaDAO(); // Acceso a datos

    // Constructor vacío
    public PersonasLayoutController() {
    }

    @FXML
    private void initialize() {
        // Asegúrate de que las columnas estén configuradas correctamente
        firstNameColumn.setCellValueFactory(cellData -> cellData.getValue().nombreProperty());
        lastNameColumn.setCellValueFactory(cellData -> cellData.getValue().apellidosProperty());

        // Cargar las personas desde la base de datos
        loadPersonasFromDatabase();

        // Configurar la selección de la tabla
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
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Selecciona algo");
            alert.setHeaderText("Ninguna persona seleccionada");
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
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Selecciona algo");
            alert.setHeaderText("Ninguna persona seleccionada");
            alert.setContentText("Elige una persona de la tabla.");

            alert.showAndWait();
        }
    }

    private void loadPersonasFromDatabase() {
        // Asegúrate de que mainApp no sea null antes de usarlo
        if (mainApp != null) {
            mainApp.getPersonas().clear();
            mainApp.getPersonas().addAll(personaDAO.getAllPersonas());
            System.out.println("Personas cargadas: " + mainApp.getPersonas().size());
        }
    }

    // Este método garantiza que el objeto mainApp se pase correctamente al controlador
    public void setMainApp(MainApp mainApp) {
        this.mainApp = mainApp;

        // Establece los elementos de la tabla
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
