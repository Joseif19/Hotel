package com.example.hotel.controller;

import com.example.hotel.MainApp;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.control.MenuItem;
import java.io.IOException;

public class MenuOpcionesController {

    @FXML
    private MenuItem hotelMenuItem;

    @FXML
    private MenuItem galeriaMenuItem;

    @FXML
    private MenuItem graficoMenuItem;

    private MainApp mainApp;

    public void setMainApp(MainApp mainApp) {
        this.mainApp = mainApp;
    }


    @FXML
    private void handleGaleria() {
        mainApp.mostrarGaleria();
    }

    @FXML
    private void handleHotel() {
        mainApp.mostrarHotel();
    }

    @FXML
    private void handleGrafico() {
        mainApp.mostrarGrafico();
    }

    @FXML
    public void initialize() {
        // Maneja eventos de los menús
        hotelMenuItem.setOnAction(e -> cargarVista("LayoutVista.fxml"));
        galeriaMenuItem.setOnAction(e -> cargarVista("GaleriaVista.fxml"));
        graficoMenuItem.setOnAction(e -> cargarVista("GraficoVista.fxml"));
    }

    private void cargarVista(String fxml) {
        try {
            // Carga el nuevo contenido
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/example/hotel/vista/"+fxml));
            Pane nuevaVista = loader.load();

        } catch (IOException e) {
            e.printStackTrace();
            // Manejo de errores al cargar el FXML
        }
    }
}
