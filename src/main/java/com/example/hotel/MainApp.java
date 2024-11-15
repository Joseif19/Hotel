package com.example.hotel;

import com.example.hotel.controller.PersonEditDialogController;
import com.example.hotel.controller.PersonasLayoutController;
import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXMLLoader;
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
        personas.add(new Persona("20503981X", "José María", "Iglesias Fernández", "Calle Inventada", "Sevilla", "Sevilla"));
        personas.add(new Persona("12345678T", "Enzo", "Boyomo", "Flow Street", "El bronx", "Los Santos"));
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

    public void showPersonEditDialog(Persona tempPersona) throws IOException {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(MainApp.class.getResource("/com/example/hotel/vista/PersonEditDialogVista.fxml"));
        AnchorPane personEditDialog = (AnchorPane) loader.load();

        PersonEditDialogController controller = loader.getController();
        controller.setMainApp(this);

        Scene escena = new Scene(personEditDialog);
        primaryStage.setScene(escena);
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}