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

    private MainApp mainApp;
    private PersonaRepository personaRepository = new PersonaRepositoryImpl(); // Acceso a datos

    public PersonasLayoutController() {
    }

    @FXML
    private void initialize() {
        // Asegúrate de que las columnas estén configuradas correctamente
        firstNameColumn.setCellValueFactory(cellData -> cellData.getValue().nombreProperty());
        lastNameColumn.setCellValueFactory(cellData -> cellData.getValue().apellidosProperty());

        showPersonDetails(null);

        // Configurar la selección de la tabla
        tablaPersonas.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> showPersonDetails(newValue));
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
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Selecciona algo");
            alert.setHeaderText("Ninguna persona seleccionada");
            alert.setContentText("Elige una persona de la tabla.");

            alert.showAndWait();
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
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Selecciona algo");
            alert.setHeaderText("Ninguna persona seleccionada");
            alert.setContentText("Elige una persona de la tabla.");

            alert.showAndWait();
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
            throw new RuntimeException(e);
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
