package com.example.hotel.controller;

import com.example.hotel.MainApp;
import model.*;
import model.repository.PersonaRepository;
import model.repository.ReservaRepository;
import model.repository.impl.PersonaRepositoryImpl;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.layout.VBox;
import model.repository.impl.ReservaRepositoryImpl;

import java.util.ArrayList;

public class LayoutController {

    @FXML
    private TableView<Persona> tablaPersonas;
    @FXML
    private TableColumn<Persona, String> firstNameColumn;
    @FXML
    private TableColumn<Persona, String> lastNameColumn;

    @FXML
    private TableView<Reserva> tablaReservas;
    @FXML

    private ObservableList<Persona> personaList = FXCollections.observableArrayList();
    private ObservableList<Reserva> reservaList = FXCollections.observableArrayList();

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
    private VBox contenedorReservas;

    @FXML
    private Button botonAñadirReserva;

    private MainApp mainApp;
    private PersonaRepository personaRepository = new PersonaRepositoryImpl();
    private ReservaRepository reservaRepository = new ReservaRepositoryImpl();

    public LayoutController() {
    }

    @FXML
    private void initialize() {
        firstNameColumn.setCellValueFactory(cellData -> cellData.getValue().nombreProperty());
        lastNameColumn.setCellValueFactory(cellData -> cellData.getValue().apellidosProperty());

        descargarPersonas();

        // Ocultar la sección de reservas al inicio
        contenedorReservas.setVisible(false);

        showPersonDetails(null);
        tablaPersonas.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            showPersonDetails(newValue);
            toggleReservasSection(newValue != null);
        });

        botonAñadirReserva.setVisible(false);
    }

    public void setPersonaRepository(PersonaRepositoryImpl personaRepository) {
        this.personaRepository = personaRepository;
    }

    public void setReservaRepository(ReservaRepositoryImpl reservaRepository) {
        this.reservaRepository = reservaRepository;
    }

    //metodos de añadir, editar y eliminar persona

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

//    @FXML
//    private void handleNewReserva() throws ExcepcionReserva {
//        Reserva tempReserva = new Reserva();
//        boolean okClicked = mainApp.showReservaEditDialog(tempReserva);
//        if (okClicked) {
//            reservaRepository.addReserva(ReservaUtil.toReservaVO(tempReserva));
//            mainApp.getReservaData().add(tempReserva);
//        }
//    }
//
//    @FXML
//    private void handleEditReserva() throws ExcepcionReserva {
//        Reserva selectedReserva = tablaReservas.getSelectionModel().getSelectedItem();
//        if (selectedReserva != null) {
//            boolean okClicked = mainApp.showReservaEditDialog(selectedReserva);
//            if (okClicked) {
//                reservaRepository.editReserva(ReservaUtil.toReservaVO(selectedReserva));
//                descargarReservas();
//            }
//        } else {
//            showSelectionWarning();
//        }
//    }

    @FXML
    private void handleNuevoReserva() {
        // Crear un nuevo objeto de reserva vacío (para agregar una nueva reserva)
        Reserva nuevaReserva = new Reserva();

        // Llamar a la función para mostrar la ventana de edición de reserva
        mainApp.showReservaEditDialog(nuevaReserva);
    }

    @FXML
    private void handleEditarReserva() {
        // Obtener la reserva seleccionada de la lista de reservas
        Reserva reservaSeleccionada = tablaReservas.getSelectionModel().getSelectedItem();

        if (reservaSeleccionada != null) {
            // Si hay una reserva seleccionada, llamar a la función para editar esa reserva
            mainApp.showReservaEditDialog(reservaSeleccionada);
        } else {
            // Si no hay ninguna reserva seleccionada, mostrar un mensaje de error
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Advertencia");
            alert.setHeaderText("No se ha seleccionado ninguna reserva");
            alert.setContentText("Por favor, selecciona una reserva para editar.");
            alert.showAndWait();
        }
    }

    @FXML
    private void handleDeleteReserva() throws ExcepcionReserva {
        int selectedIndex = tablaReservas.getSelectionModel().getSelectedIndex();
        if (selectedIndex >= 0) {
            Reserva reserva = tablaReservas.getSelectionModel().getSelectedItem();
            tablaReservas.getItems().remove(selectedIndex);
            reservaRepository.deleteReserva(reserva.getIdReserva());
        } else {
            showSelectionWarning();
        }
    }



    public ObservableList<Persona> descargarPersonas() {
        try {
            ArrayList<Persona> personas = PersonaUtil.personaVOListToPersonaList(personaRepository.ObtenerListaPersonas());
            personaList.setAll(personas);
            tablaPersonas.setItems(personaList);
            return personaList;
        } catch (ExcepcionPersona e) {
            showErrorDialog("Error al cargar reservas", "No se pudieron cargar las reservas desde la base de datos.", e);
            return FXCollections.emptyObservableList();
        }
    }

    public ObservableList<Reserva> descargarReservas() {
        try {
            ArrayList<Reserva> reservas = ReservaUtil.toReservaList(reservaRepository.ObtenerListaReservas());
            reservaList.setAll(reservas);
            tablaReservas.setItems(reservaList);
            return reservaList;
        } catch (ExcepcionReserva e) {
            showErrorDialog("Error al cargar reservas", "No se pudieron cargar las personas desde la base de datos.", e);
            return FXCollections.emptyObservableList();
        }
    }

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

            botonAñadirReserva.setVisible(true);
        } else {
            dni.setText("");
            nombre.setText("");
            apellidos.setText("");
            direccion.setText("");
            localidad.setText("");
            provincia.setText("");

            botonAñadirReserva.setVisible(false);
        }
    }

    private void toggleReservasSection(boolean visible) {
        contenedorReservas.setVisible(visible);
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
            // Lógica para crear una reserva
        } else {
            showSelectionWarning();
        }
    }
}
