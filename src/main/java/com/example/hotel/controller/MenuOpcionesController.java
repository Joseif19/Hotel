package com.example.hotel.controller;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.control.MenuItem;
import java.io.IOException;

public class MenuOpcionesController {

    @FXML
    private StackPane contentPane;

    @FXML
    private MenuItem closeMenuItem;

    @FXML
    private MenuItem deleteMenuItem;

    @FXML
    private MenuItem aboutMenuItem;

    @FXML
    public void initialize() {
        // Maneja eventos de los menús
        closeMenuItem.setOnAction(e -> cargarVista("CloseVista.fxml"));
        deleteMenuItem.setOnAction(e -> cargarVista("DeleteVista.fxml"));
        aboutMenuItem.setOnAction(e -> cargarVista("AboutVista.fxml"));
    }

    private void cargarVista(String fxml) {
        try {
            // Carga el nuevo contenido
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/example/hotel/view/" + fxml));
            Pane nuevaVista = loader.load();

            // Vacía el contenedor y añade el nuevo contenido
            contentPane.getChildren().clear();
            contentPane.getChildren().add(nuevaVista);

        } catch (IOException e) {
            e.printStackTrace();
            // Manejo de errores al cargar el FXML
        }
    }
}
