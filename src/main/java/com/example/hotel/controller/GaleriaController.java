package com.example.hotel.controller;

import javafx.fxml.FXML;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.TilePane;

import java.io.File;
import java.util.List;

public class GaleriaController {

    @FXML
    private TilePane tilePane;

    // Método para inicializar la galería.
    public void cargarGaleria(List<String> rutasImagenes) {
        tilePane.getChildren().clear(); // Limpia las imágenes previas.

        for (String ruta : rutasImagenes) {
            File archivoImagen = new File(ruta);
            if (archivoImagen.exists()) {
                Image imagen = new Image(archivoImagen.toURI().toString());
                ImageView imageView = new ImageView(imagen);
                imageView.setFitWidth(100); // Ajusta el tamaño de la miniatura.
                imageView.setFitHeight(100);
                imageView.setPreserveRatio(true);

                tilePane.getChildren().add(imageView);
            }
        }
    }
}
