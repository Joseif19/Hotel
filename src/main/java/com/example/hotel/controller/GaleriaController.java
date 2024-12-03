package com.example.hotel.controller;

import javafx.fxml.FXML;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import java.util.List;

public class GaleriaController {

        @FXML
        private ImageView imagen1;

        @FXML
        private ImageView imagen2;

        @FXML
        private ImageView imagen3;

        @FXML
        private ImageView imagen4;

        @FXML
        private void initialize() {
            imagen1.setImage(loadImage("/com/example/hotel/vista/imagenes/habitacion_doble_individual.jpg"));
            imagen2.setImage(loadImage("/com/example/hotel/vista/imagenes/habitacion_doble.jpg"));
            imagen3.setImage(loadImage("/com/example/hotel/vista/imagenes/junior_suite.jpg"));
            imagen4.setImage(loadImage("/com/example/hotel/vista/imagenes/suite.jpeg"));

        }

        private Image loadImage(String resourcePath) {
            try {
                return new Image(getClass().getResource(resourcePath).toExternalForm());
            } catch (NullPointerException e) {
                System.err.println("No se pudo cargar la imagen: " + resourcePath);
                return null;
            }
        }

}
