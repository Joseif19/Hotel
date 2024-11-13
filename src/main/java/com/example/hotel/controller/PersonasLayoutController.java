package com.example.hotel.controller;

import com.example.hotel.MainApp;
import model.Persona;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;


public class PersonasLayoutController {
    @FXML
    private TableView<Persona> personTable;
    @FXML
    private TableColumn<Persona, String> firstNameColumn;
    @FXML
    private TableColumn<Persona, String> lastNameColumn;

    @FXML
    private Label dniLabel;
    @FXML
    private Label nombreLabel;
    @FXML
    private Label apellidosLabel;
    @FXML
    private Label direccionLabel;
    @FXML
    private Label localidadLabel;
    @FXML
    private Label provinciaLabel;

    private MainApp mainApp;

    public PersonasLayoutController() {
    }
    @FXML
    private void initialize() {
        firstNameColumn.setCellValueFactory(cellData -> cellData.getValue().nombreProperty());
        lastNameColumn.setCellValueFactory(cellData -> cellData.getValue().apellidosProperty());

        showPersonDetails(null);

        personTable.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> showPersonDetails(newValue));
    }
//
//
// @FXML
//    private void handleNewPerson() {
//        Persona tempPerson = new Persona();
//        boolean okClicked = mainApp.showPersonEditDialog(tempPerson);
//        if (okClicked) {
//            mainApp.getPersonData().add(tempPerson);
//        }
//    }

//    @FXML
//    private void handleDeletePerson() {
//        int selectedIndex = personTable.getSelectionModel().getSelectedIndex();
//        if (selectedIndex >= 0) {
//            personTable.getItems().remove(selectedIndex);
//        } else {
//            // Nada seleccionado.
//            Alert alert = new Alert(Alert.AlertType.WARNING);
//            alert.setTitle("Seleciona algo");
//            alert.setHeaderText("Ninguna persona selecionada");
//            alert.setContentText("Elige una persona de la tabla.");
//
//            alert.showAndWait();
//        }
//    }
//
//
//    @FXML
//    private void handleEditPerson() {
//        Persona selectedPerson = personTable.getSelectionModel().getSelectedItem();
//        if (selectedPerson != null) {
//            boolean okClicked = mainApp.showPersonEditDialog(selectedPerson);
//            if (okClicked) {
//                showPersonDetails(selectedPerson);
//            }
//
//        } else {
//            // Nada seleccionado.
//            Alert alert = new Alert(Alert.AlertType.WARNING);
//            alert.setTitle("Seleciona algo");
//            alert.setHeaderText("Ninguna persona selecionada");
//            alert.setContentText("Elige una persona de la tabla.");
//
//            alert.showAndWait();
//        }
//
//    }

    public void setMainApp(MainApp mainApp) {
        this.mainApp = mainApp;

        personTable.setItems(mainApp.getPersonas());
    }

    private void showPersonDetails(Persona person) {
        if (person != null) {
            dniLabel.setText(person.getDni());
            nombreLabel.setText(person.getNombre());
            apellidosLabel.setText(person.getApellidos());
            direccionLabel.setText(person.getDireccion());
            localidadLabel.setText(person.getLocalidad());
            provinciaLabel.setText(person.getProvincia());

        } else {
            dniLabel.setText("");
            nombreLabel.setText("");
            apellidosLabel.setText("");
            direccionLabel.setText("");
            localidadLabel.setText("");
            provinciaLabel.setText("");
        }
    }
}
