package com.example.hotel;

import com.example.hotel.controller.PersonasLayoutController;
import javafx.application.Application;
import javafx.beans.Observable;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXMLLoader;
import javafx.fxml.JavaFXBuilderFactory;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import model.Persona;

import java.io.IOException;

public class MainApp extends Application {

    private Stage primaryStage;
    private AnchorPane rootLayout;

    private ObservableList<Persona> personas = FXCollections.observableArrayList();

    public MainApp() {
        personas.add(new Persona("20503981X", "José María", "Iglesias Fernández"));
        personas.add(new Persona("12345678H", "Flavien Enzo", "Thiedort Boyomo"));
    }

    public ObservableList<Persona> getPersonas() {
        return personas;
    }

    @Override
    public void start(Stage primaryStage) {
        this.primaryStage = primaryStage;
        this.primaryStage.setTitle("Hotel");

        initRootLayout();

    }

    public void initRootLayout() {
        try{
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(MainApp.class.getResource("/com/example/hotel/vista/PersonasLayoutVista.fxml"));
            AnchorPane rootLayout = (AnchorPane) loader.load();

            PersonasLayoutController controller = loader.getController();
            controller.setMainApp(this);

            Scene escena = new Scene(rootLayout);
            primaryStage.setScene(escena);
            primaryStage.show();

        }catch (IOException e){
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        launch();
    }
}