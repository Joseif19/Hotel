package com.example.hotel.controller;

import com.example.hotel.MainApp;
import model.ExcepcionPersona;
import model.Persona;
import model.PersonaUtil;
import model.repository.PersonaRepository;
import model.repository.impl.PersonaRepositoryImpl;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.Button;

import java.util.ArrayList;

public class PersonasLayoutController {

    @FXML
    private TableView<Persona> tablaPersonas;
    @FXML
    private TableColumn<Persona, String> firstNameColumn;
    @FXML
    private TableColumn<Persona, String> lastNameColumn;

    private ObservableList<Persona> personaList = FXCollections.observableArrayList();

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

    @FXML
    private Button botonHacerReserva;  // El botón que aparece al seleccionar una persona

    private MainApp mainApp;
    private PersonaRepository personaRepository = new PersonaRepositoryImpl(); // Acceso a datos

    public PersonasLayoutController() {
    }

    @FXML
    private void initialize() {
        // Asegúrate de que las columnas estén configuradas correctamente
        firstNameColumn.setCellValueFactory(cellData -> cellData.getValue().nombreProperty());
        lastNameColumn.setCellValueFactory(cellData -> cellData.getValue().apellidosProperty());

        // Cargar las personas y configurar la tabla
        descargarPersonas();

        showPersonDetails(null);
        tablaPersonas.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> showPersonDetails(newValue));

        // Inicializar el botón de hacer reserva como desactivado
        botonHacerReserva.setVisible(false);
    }

    public void setPersonaRepository(PersonaRepositoryImpl personaRepository) {
        this.personaRepository = personaRepository;
    }

    @FXML
    private void handleNewPerson() throws ExcepcionPersona {
        Persona tempPersona = new Persona();
        boolean okClicked = mainApp.showPersonEditDialog(tempPersona);
        if (okClicked) {
            personaRepository.addPersona(PersonaUtil.personaToPersonaVO(tempPersona));
            mainApp.getPersonData().add(tempPersona);
        }
    }

    @FXML
    private void handleDeletePerson() throws ExcepcionPersona {
        int selectedIndex = tablaPersonas.getSelectionModel().getSelectedIndex();
        if (selectedIndex >= 0) {
            Persona persona = tablaPersonas.getSelectionModel().getSelectedItem();
            tablaPersonas.getItems().remove(selectedIndex);
            personaRepository.deletePersona(persona.getDni());
        } else {
            showSelectionWarning();
        }
    }

    @FXML
    private void handleEditPerson() throws ExcepcionPersona {
        Persona selectedPerson = tablaPersonas.getSelectionModel().getSelectedItem();
        if (selectedPerson != null) {
            boolean okClicked = mainApp.showPersonEditDialog(selectedPerson);
            if (okClicked) {
                personaRepository.editPersona(PersonaUtil.personaToPersonaVO(selectedPerson));
                descargarPersonas();
            }
        } else {
            showSelectionWarning();
        }
    }

    // Método para cargar y actualizar la lista de personas desde la base de datos
    public ObservableList<Persona> descargarPersonas() {
        try {
            ArrayList<Persona> personas = PersonaUtil.personaVOListToPersonaList(personaRepository.ObtenerListaPersonas());
            personaList.setAll(personas);
            tablaPersonas.setItems(personaList);
            return personaList;
        } catch (ExcepcionPersona e) {
            showErrorDialog("Error al cargar personas", "No se pudieron cargar las personas desde la base de datos.", e);
            return FXCollections.emptyObservableList();
        }
    }

    // Establece el objeto mainApp correctamente
    public void setMainApp(MainApp mainApp) {
        this.mainApp = mainApp;
        tablaPersonas.setItems(mainApp.getPersonData());
    }

    private void showPersonDetails(Persona person) {
        if (person != null) {
            dni.setText(person.getDni());
            nombre.setText(person.getNombre());
            apellidos.setText(person.getApellidos());
            direccion.setText(person.getDireccion());
            localidad.setText(person.getLocalidad());
            provincia.setText(person.getProvincia());

            // Hacer visible el botón de reserva cuando se selecciona una persona
            botonHacerReserva.setVisible(true);
        } else {
            dni.setText("");
            nombre.setText("");
            apellidos.setText("");
            direccion.setText("");
            localidad.setText("");
            provincia.setText("");

            // Ocultar el botón de reserva si no hay persona seleccionada
            botonHacerReserva.setVisible(false);
        }
    }

    private void showSelectionWarning() {
        Alert alert = new Alert(Alert.AlertType.WARNING);
        alert.setTitle("Selecciona algo");
        alert.setHeaderText("Ninguna persona seleccionada");
        alert.setContentText("Elige una persona de la tabla.");
        alert.showAndWait();
    }

    private void showErrorDialog(String title, String message, Exception e) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle(title);
        alert.setHeaderText(message);
        alert.setContentText(e.getMessage());
        alert.showAndWait();
    }

    @FXML
    private void handleHacerReserva() {
        Persona selectedPersona = tablaPersonas.getSelectionModel().getSelectedItem();
        if (selectedPersona != null) {
            // Aquí puedes añadir la lógica para crear una reserva, por ejemplo:
            // mainApp.showReservaDialog(selectedPersona);
        } else {
            showSelectionWarning();
        }
    }
}
