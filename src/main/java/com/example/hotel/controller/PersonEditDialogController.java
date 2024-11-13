package com.example.hotel.controller;

import com.example.hotel.MainApp;
import javafx.fxml.FXML;
import javafx.scene.control.TableView;
import model.Persona;

public class PersonEditDialogController {

    @FXML
    private TableView<Persona> tablaPersonas;
    private MainApp mainApp;

    public void setMainApp(MainApp mainApp) {
        this.mainApp = mainApp;

        tablaPersonas.setItems(mainApp.getPersonas());
    }

}
