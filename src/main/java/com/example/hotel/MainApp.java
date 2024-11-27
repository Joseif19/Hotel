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
import javafx.scene.control.Alert;
import model.repository.PersonaRepository;
import model.repository.impl.PersonaRepositoryImpl;
import model.ExcepcionPersona;

import java.io.IOException;
import java.util.ArrayList;

public class MainApp extends Application {

    private Stage primaryStage;
    private ObservableList<Persona> personData = FXCollections.observableArrayList();
    private PersonaRepositoryImpl repository = new PersonaRepositoryImpl();

    public MainApp() {
    }

    public ObservableList<Persona> getPersonData() {
        return personData;
    }

    public void showPersonOverview() throws ExcepcionPersona {
        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(MainApp.class.getResource("/com/example/hotel/vista/PersonasLayoutVista.fxml"));
            AnchorPane personOverview = (AnchorPane) loader.load();

            PersonasLayoutController controller = loader.getController();
            controller.setMainApp(this);
            controller.setPersonaRepository(repository);
            personData=controller.descargarPersonas();


            Scene escena = new Scene(personOverview);
            primaryStage.setScene(escena);
            primaryStage.show();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void start(Stage primaryStage) throws ExcepcionPersona {
        this.primaryStage = primaryStage;
        this.primaryStage.setTitle("Agenda");

        showPersonOverview();
    }

    public boolean showPersonEditDialog(Persona persona) {
        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(MainApp.class.getResource("/com/example/hotel/vista/PersonEditDialogVista.fxml"));
            AnchorPane page = (AnchorPane) loader.load();

            Stage dialogStage = new Stage();
            if(persona.getDni() != null){
                dialogStage.setTitle("Editar Persona");
            }else{
                dialogStage.setTitle("Añadir Persona");
            }

            dialogStage.initOwner(primaryStage);
            Scene scene = new Scene(page);
            dialogStage.setScene(scene);

            PersonEditDialogController controller = loader.getController();
            controller.setDialogStage(dialogStage);
            controller.setPerson(persona);

            dialogStage.showAndWait();

            return controller.isOkClicked();
        } catch (IOException e) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText("No se pudo cargar el diálogo");
            alert.setContentText("Ha ocurrido un error al cargar el diálogo.");

            alert.showAndWait();

            return false;
        }
    }

    public Stage getPrimaryStage() {return primaryStage;}

    public static void main(String[] args) {launch(args);}
}
